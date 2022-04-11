package com.example.qadri.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import com.example.qadri.BuildConfig
import com.example.qadri.R
import com.example.qadri.constant.Constants
import com.example.qadri.databinding.ActivityMainBinding
import com.example.qadri.mvvm.model.addLead.DynamicLeadsItem
import com.example.qadri.mvvm.model.checkin.CheckinModel
import com.example.qadri.mvvm.model.lov.LovResponse
import com.example.qadri.mvvm.model.portfolio.PortfolioResponse
import com.example.qadri.mvvm.viewModel.coroutine.CoroutineViewModel
import com.example.qadri.security.EncryptionKeyStoreImpl
import com.example.qadri.ui.adapter.ExpandableListAdapter
import com.example.qadri.utils.Schedulers.LocationWorkManager.LocationWorker
import com.example.qadri.utils.Schedulers.UploadCheckInWorkManager.UploadCheckInWorker
import com.example.qadri.utils.Schedulers.UploadLeadWorkManager.UploadLeadWorker
import com.squareup.picasso.Picasso
import java.util.concurrent.TimeUnit
import kotlin.collections.set


class MainActivity : DockActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var viewModel: CoroutineViewModel
    lateinit var listDataChild: HashMap<String, List<String>>
    lateinit var listDataHeader: ArrayList<String>
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController

    private lateinit var switchAB: SwitchCompat
    val encryptionKeyStore = EncryptionKeyStoreImpl.instance

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this,R.color.black_light);

//      navController = findNavController(R.id.nav_host_main)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CoroutineViewModel::class.java)

        initView()
        setData()
        //   setGesture()
//        sendUserTracking()
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_host_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val item = menu.findItem(R.id.myswitch) as MenuItem
        menu.findItem(R.id.action_notification).setOnMenuItemClickListener {
            navigateToFragment(R.id.nav_home)
            true
        }
        menu.findItem(R.id.cart).setOnMenuItemClickListener {
            navigateToFragment(R.id.nav_home)
            true
        }

        item.setActionView(R.layout.switch_layout)
        switchAB = item.actionView.findViewById(R.id.switchAB)
        sharedPreferences = this.getSharedPreferences("SharedPrefs", MODE_PRIVATE)
        if (switchAB.isChecked) {
            Handler(Looper.getMainLooper()).postDelayed({
                foregroundOnlyLocationService?.subscribeToLocationUpdates()
            }, 120000)
        }
        switchAB.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
            } else {
                sharedPrefManager.setShiftStart(false)
                foregroundOnlyLocationService!!.unsubscribeToLocationUpdates()
                startActivity(Intent(this, WelcomeActivity::class.java))
//                   foregroundOnlyLocationService?.unsubscribeToLocationUpdates()
            }

        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun initView() {
        setSupportActionBar(findViewById(R.id.toolBar))
        navController = findNavController(R.id.nav_host_main)
        navController.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener{
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                if(this@MainActivity::switchAB.isInitialized){
                    if(destination.label!="Dashboard"){
                        switchAB.visibility = View.GONE
                    }else{
                        switchAB.visibility = View.VISIBLE
                    }
                }
            }

        })

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), binding.drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        if (roomHelper.checkUnSyncLeadData().isNotEmpty() || roomHelper.checkUnSyncCheckInData()
                .isNotEmpty() && internetHelper.isNetworkAvailable()
        ) {
//            sendLeadData()
        }

//        getSyncData(isShowLoading = false)
        prepareSideMenu()
    }

    private fun setData() {
//        binding.sideLayout.name.text = sharedPrefManager.getUserDetails()?.first_name + " " + sharedPrefManager.getUserDetails()?.last_name
        binding.sideLayout.version.text = "version: ${BuildConfig.VERSION_CODE}"
        Picasso.get().load("https://medias.spotern.com/spots/w640/229/229560-1567745387.jpg").error(R.drawable.ic_user).into(binding.sideLayout.userProfile)

    }

    private fun fragmentClickEvent(itemString: String) {
        when (itemString) {
            Constants.NODE_DASHBOARD -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.NODE_CREATE_ORDER -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.SUB_NODE_TODAY -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.SUB_NODE_PENDING -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.SUB_NODE_COMPLETED -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.SUB_NODE_OTHER_VISITS -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.NODE_CUSTOMERS -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.NODE_BANK_DEPOSIT -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.SUB_NODE_PENDING_ORDER -> {
                navigateToFragment(R.id.action_nav_home_to_pending_order)
            }
            Constants.SUB_NODE_IN_TRANSIT_ORDER -> {
                navigateToFragment(R.id.action_nav_home_to_transit_order)
            }
            Constants.SUB_NODE_COMPLETED_ORDER -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.SUB_NODE_REPORTS_VISIT -> {
                navigateToFragment(R.id.action_nav_home_to_reportVisit)
            }
            Constants.SUB_NODE_REPORTS_RECOVERY -> {
                navigateToFragment(R.id.action_nav_home_to_reportRecovery)

            }
            Constants.SUB_NODE_REPORTS_ORDER -> {
                navigateToFragment(R.id.action_nav_home_to_reportOrder)
            }
            Constants.SUB_NODE_REPORTS_AGING -> {
                navigateToFragment(R.id.action_nav_home_to_reportAging)
            }
            Constants.SUB_NODE_REPORTS_BANK_DEPOSIT -> {
                navigateToFragment(R.id.action_nav_home_to_reportBankDeposit)
            }
            Constants.SUB_NODE_REPORTS_COMPLAINTS -> {
                navigateToFragment(R.id.action_nav_home_to_reportComplaint)
            }
            Constants.SUB_NODE_REPORTS_SALES_PLAN -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.NODE_NOTIFICATIONS -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.SUB_NODE_CHANGE_PASSWORD -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.NODE_LOGOUT -> {
                showLogOutAlert()
            }
        }

    }

    private fun prepareSideMenu() {

        listDataHeader = ArrayList<String>()
        listDataChild = HashMap<String, List<String>>()
        val icons = ArrayList<Int>()

        icons.add(R.drawable.ic_dashboard) //0
        icons.add(R.drawable.ic_edit) //1
        icons.add(R.drawable.ic_sales) //2
        icons.add(R.drawable.ic_customers) // 3
        icons.add(R.drawable.ic_bank_deposit) //4
        icons.add(R.drawable.ic_orders) //5
        icons.add(R.drawable.ic_reports) //6
        icons.add(R.drawable.ic_notification) //7
        icons.add(R.drawable.ic_settings)//8
        icons.add(R.drawable.ic_logout)//9

        listDataHeader.add(Constants.NODE_DASHBOARD) //0
        listDataHeader.add(Constants.NODE_CREATE_ORDER) //1
        listDataHeader.add(Constants.NODE_SALES_PLAN) //2
        listDataHeader.add(Constants.NODE_CUSTOMERS) //3
        listDataHeader.add(Constants.NODE_BANK_DEPOSIT) //4
        listDataHeader.add(Constants.NODE_ORDERS) //5
        listDataHeader.add(Constants.NODE_REPORTS) //6
        listDataHeader.add(Constants.NODE_NOTIFICATIONS) //7
        listDataHeader.add(Constants.NODE_SETTINGS) //8
        listDataHeader.add(Constants.NODE_LOGOUT) //9

        val listAdapter = ExpandableListAdapter(
            this,
            listDataHeader,
            listDataChild,
            icons
        )

        // Sales management child nodes
        val salesPlan: MutableList<String> = ArrayList()
        salesPlan.add(Constants.SUB_NODE_TODAY)
        salesPlan.add(Constants.SUB_NODE_PENDING)
        salesPlan.add(Constants.SUB_NODE_COMPLETED)
        salesPlan.add(Constants.SUB_NODE_OTHER_VISITS)
        listDataChild[listDataHeader[2]] = salesPlan

        //order child nodes
        val orders: MutableList<String> = ArrayList()
        orders.add(Constants.SUB_NODE_PENDING_ORDER)
        orders.add(Constants.SUB_NODE_IN_TRANSIT_ORDER)
        orders.add(Constants.SUB_NODE_COMPLETED_ORDER)
        listDataChild[listDataHeader[5]] = orders

        //report child nodes
        val report: MutableList<String> = ArrayList()
        report.add(Constants.SUB_NODE_REPORTS_VISIT)
        report.add(Constants.SUB_NODE_REPORTS_RECOVERY)
        report.add(Constants.SUB_NODE_REPORTS_ORDER)
        report.add(Constants.SUB_NODE_REPORTS_AGING)
        report.add(Constants.SUB_NODE_REPORTS_BANK_DEPOSIT)
        report.add(Constants.SUB_NODE_REPORTS_COMPLAINTS)
        report.add(Constants.SUB_NODE_REPORTS_SALES_PLAN)
        listDataChild[listDataHeader[6]] = report

        val settings: MutableList<String> = ArrayList()
        settings.add(Constants.SUB_NODE_CHANGE_PASSWORD)
        listDataChild[listDataHeader[8]] = settings


        // setting list adapter
        binding.sideLayout.lvExp.setAdapter(listAdapter)

        // Listview on child click listener
        binding.sideLayout.lvExp.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val str = listDataChild[listDataHeader[groupPosition]]!![childPosition]
            fragmentClickEvent(str)
            false
        }

        // Listview parent node click listener
        binding.sideLayout.lvExp.setOnGroupExpandListener { groupPosition: Int ->
            fragmentClickEvent(listDataHeader[groupPosition])
        }
    }

    fun dropDownMenu(view: View) {
        showOrHide()
        binding.appBarMain.sideMenu.sync.setOnClickListener(::onCLickEvent)
        binding.appBarMain.sideMenu.upload.setOnClickListener(::onCLickEvent)
        binding.appBarMain.sideMenu.followup.setOnClickListener(::onCLickEvent)
        binding.appBarMain.sideMenu.close.setOnClickListener(::onCLickEvent)
    }

    private fun onCLickEvent(view: View) {
        showOrHide()
        when (view.id) {
            R.id.sync -> {
//                getSyncData()
            }
            R.id.upload -> {
               // sendLeadData()
//                sendUserTracking()
            }
            R.id.followup -> {
                navigateToFragment(R.id.nav_home)
            }
            R.id.close -> {
                goneWithAnimation(binding.appBarMain.sideMenu.root)
                visibleWithAnimation(binding.appBarMain.dropdownMenu)
            }
        }
    }

    private fun showOrHide() {
        if (!binding.appBarMain.sideMenu.root.isVisible) {
            goneWithAnimation(binding.appBarMain.dropdownMenu)
            visibleWithAnimation(binding.appBarMain.sideMenu.root)
        } else {
            visibleWithAnimation(binding.appBarMain.dropdownMenu)
            goneWithAnimation(binding.appBarMain.sideMenu.root)
        }
    }

    private fun navigateToFragment(@IdRes id: Int, args: Bundle? = null) {
        closeDrawer()
        if (args != null) {
            navController.navigate(id, args)
            return
        }
        navController.navigate(id)
    }

    private fun getSyncData(isShowLoading: Boolean? = true) {
        //  this.showProgressIndicator()
        if (!internetHelper.isNetworkAvailable()) {
            showToast("Internet is not available")
            return
        }

        if (isShowLoading == true) {
            this.showProgressIndicator()
        }

        viewModel.getLOV().observe(this) {
            this.hideProgressIndicator()
            if (it.lovResponse != null && it.lovResponse.company_lead_source.isNotEmpty()
                && it.lovResponse.company_lead_status.isNotEmpty()
            ) {
                processData(
                    it.lovResponse,
                    it.dynamicList,
                    it.visitCallResponse,
                    it.portfolioResponse
                )
                if (isShowLoading == true) {
                    this.showSuccessMessage("Data synced successfully")
                }
            } else {
                if (isShowLoading == true) {
                    this.showErrorMessage("Failed to sync data. Please try again")
                }
            }
        }
    }

    private fun processData(
        lovResponse: LovResponse,
        dynamicLeadsItem: ArrayList<DynamicLeadsItem>?,
        visitsCallResponseItem: ArrayList<CheckinModel>?,
        portfolioResponse: PortfolioResponse?
    ) {
        sharedPrefManager.setLeadStatus(lovResponse.company_lead_status)
        sharedPrefManager.setCompanyProducts(lovResponse.company_products)
        sharedPrefManager.setVisitStatus(lovResponse.company_visit_status)
        sharedPrefManager.setLeadSource(lovResponse.company_lead_source)

        // Set leads data in local DB
        if (dynamicLeadsItem != null) {
            roomHelper.deleteLeadData()
            roomHelper.insertLeadData(encryptionKeyStore.encryptList(dynamicLeadsItem) as ArrayList<DynamicLeadsItem>)
        }

        // Set checkIn data in local DB
        if (visitsCallResponseItem != null) {
            roomHelper.deleteCheckInData()
            roomHelper.insertVisitCallData(visitsCallResponseItem)
        }

        if (portfolioResponse != null) {
            roomHelper.deletePortfolioData()
            roomHelper.insertPortfolio(portfolioResponse.porfolio)
        }

        prepareSideMenu()
    }

    private fun sendUserTracking() {

        val workManager = WorkManager.getInstance(application)
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        try {

            // One time request
            val uploadLocationWorkRequest = OneTimeWorkRequestBuilder<LocationWorker>()
                .addTag(Constants.SYNC_LOCATION)
                .setConstraints(constraints)
                .build()


            // Periodic Request
            val periodicSyncDataWork = PeriodicWorkRequest.Builder(
                LocationWorker::class.java,
                15,//worker always fires AFTER this period
                TimeUnit.MINUTES,
                15,//worker runs during this time after repeat interval expires
                TimeUnit.MINUTES
            )
                .addTag(Constants.SYNC_LOCATION)
                .setConstraints(constraints)
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                ).build()

            workManager.enqueueUniquePeriodicWork(
                Constants.SYNC_UPLOADED,
                ExistingPeriodicWorkPolicy.KEEP,
                periodicSyncDataWork
            )

            workManager.beginWith(uploadLocationWorkRequest)
                .then(uploadLocationWorkRequest)
                .enqueue()


        } catch (e: Exception) {
            Log.i("LocationWorkerException", e.message.toString())
        }
    }

    fun sendLeadData() {
        try {
            val workManager = WorkManager.getInstance(applicationContext)
            val uploadDataConstraints =
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

            val uploadLeadWorkRequest = OneTimeWorkRequestBuilder<UploadLeadWorker>()
                .addTag("leadWorker")
                .setConstraints(uploadDataConstraints)
                .build()

            val uploadCheckInWorkRequest = OneTimeWorkRequestBuilder<UploadCheckInWorker>()
                .addTag("checkInWorker")
                .setConstraints(uploadDataConstraints)
                .build()

            this.showProgressIndicator()

            workManager.beginWith(uploadLeadWorkRequest)
                .then(uploadCheckInWorkRequest)
                .enqueue()


            workManager.getWorkInfoByIdLiveData(uploadCheckInWorkRequest.id)
                .observe(this) { workInfo ->
                    if (workInfo.state.isFinished) {
                        this.hideProgressIndicator()
                        showSuccessMessage("Data uploaded successfully")
                        Handler(Looper.getMainLooper()).postDelayed(Runnable {
                            getSyncData(isShowLoading = false)
                        }, 1000)
                    }
                }

        } catch (e: Exception) {
            Log.i("UploadWorkerLocation", e.message.toString())
        }
    }

    fun showLogOutAlert() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Logout")
        alertDialog.setMessage("Do you want to Logout?")
        alertDialog.setPositiveButton(
            "Yes"
        ) { dialog, which ->
            if (roomHelper.checkUnSyncLeadData().isNotEmpty() || roomHelper.checkUnSyncCheckInData()
                    .isNotEmpty()
            ) {
                showErrorMessage(getString(R.string.un_synced_msg))
            } else {
                sharedPrefManager.logout()
                roomHelper.clearDB()
                foregroundOnlyLocationService?.unsubscribeToLocationUpdates()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }

        }
        alertDialog.setNegativeButton(
            "No"
        ) { dialog: DialogInterface, which: Int -> dialog.cancel() }
        alertDialog.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}