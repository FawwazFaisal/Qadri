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
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
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
import com.example.qadri.mvvm.model.customer.CustomerResponse
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
    val coroutineViewModel: CoroutineViewModel by viewModels()
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
        val notification = menu.findItem(R.id.action_notification)
        val cart = menu.findItem(R.id.cart)
        notification.setOnMenuItemClickListener {
            navigateToFragment(R.id.notification)
            true
        }
        cart.setOnMenuItemClickListener {
            navigateToFragment(R.id.view_cart_fragment)
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
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (this@MainActivity::switchAB.isInitialized) {
                cart.isVisible = destination.label != "Notifications" && destination.label != "Cart"
                notification.isVisible = destination.label != "Notifications" && destination.label != "Cart"
                if (destination.label != "Dashboard") {
                    switchAB.visibility = View.GONE
                } else {
                    switchAB.visibility = View.VISIBLE
                }
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun initView() {
        setSupportActionBar(findViewById(R.id.toolBar))
        navController = findNavController(R.id.nav_host_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), binding.drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

//        if (roomHelper.checkUnSyncLeadData().isNotEmpty() || roomHelper.checkUnSyncCheckInData()
//                .isNotEmpty() && internetHelper.isNetworkAvailable()
//        ) {
//            sendLeadData()
//        }

        getSyncData(isShowLoading = false)
        prepareSideMenu()
    }

    private fun setData() {
        binding.sideLayout.version.text = "version: ${BuildConfig.VERSION_CODE}"
        Picasso.get().load("https://medias.spotern.com/spots/w640/229/229560-1567745387.jpg").error(R.drawable.ic_user).into(binding.sideLayout.userProfile)
    }

    private fun fragmentClickEvent(itemString: String) {
        when (itemString) {
            Constants.NODE_CREATE_ORDER -> {
                navigateToFragment(R.id.action_nav_home_to_create_order)
            }
            Constants.NODE_SALES_PLAN -> {
                navigateToFragment(R.id.action_nav_home_to_hostSalesPlan)
            }
            Constants.NODE_CUSTOMERS -> {
                navigateToFragment(R.id.action_nav_home_to_customers)
            }
            Constants.NODE_BANK_DEPOSIT -> {
                navigateToFragment(R.id.action_nav_home_to_bankDeposit)
            }
            Constants.SUB_NODE_PENDING_ORDER -> {
                navigateToFragment(R.id.action_nav_home_to_pending_order)
            }
            Constants.SUB_NODE_IN_TRANSIT_ORDER -> {
                navigateToFragment(R.id.action_nav_home_to_transit_order)
            }
            Constants.SUB_NODE_COMPLETED_ORDER -> {
                navigateToFragment(R.id.action_nav_home_to_completedOrderFragment)
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
                navigateToFragment(R.id.action_nav_home_to_reportSalesPlan)
            }
            Constants.NODE_NOTIFICATIONS -> {
                navigateToFragment(R.id.action_nav_home_to_notification)
            }
            Constants.SUB_NODE_CHANGE_PASSWORD -> {
                navigateToFragment(R.id.action_nav_home_to_changePassword)
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

        icons.add(R.drawable.ic_edit) //0
        icons.add(R.drawable.ic_sales) //1
        icons.add(R.drawable.ic_customers) // 2
        icons.add(R.drawable.ic_bank_deposit) //3
        icons.add(R.drawable.ic_orders) //4
        icons.add(R.drawable.ic_reports) //5
        icons.add(R.drawable.ic_notification) //6
        icons.add(R.drawable.ic_settings)//7
        icons.add(R.drawable.ic_logout)//8

        listDataHeader.add(Constants.NODE_CREATE_ORDER) //0
        listDataHeader.add(Constants.NODE_SALES_PLAN) //1
        listDataHeader.add(Constants.NODE_CUSTOMERS) //2
        listDataHeader.add(Constants.NODE_BANK_DEPOSIT) //3
        listDataHeader.add(Constants.NODE_ORDERS) //4
        listDataHeader.add(Constants.NODE_REPORTS) //5
        listDataHeader.add(Constants.NODE_NOTIFICATIONS) //6
        listDataHeader.add(Constants.NODE_SETTINGS) //7
        listDataHeader.add(Constants.NODE_LOGOUT) //8

        val listAdapter = ExpandableListAdapter(
            this,
            listDataHeader,
            listDataChild,
            icons
        )

        // Sales management child nodes
//        val salesPlan: MutableList<String> = ArrayList()
//        salesPlan.add(Constants.SUB_NODE_TODAY)
//        salesPlan.add(Constants.SUB_NODE_PENDING)
//        salesPlan.add(Constants.SUB_NODE_COMPLETED)
//        salesPlan.add(Constants.SUB_NODE_OTHER_VISITS)
//        listDataChild[listDataHeader[2]] = salesPlan

        //order child nodes
        val orders: MutableList<String> = ArrayList()
        orders.add(Constants.SUB_NODE_PENDING_ORDER)
        orders.add(Constants.SUB_NODE_IN_TRANSIT_ORDER)
        orders.add(Constants.SUB_NODE_COMPLETED_ORDER)
        listDataChild[listDataHeader[4]] = orders

        //report child nodes
        val report: MutableList<String> = ArrayList()
        report.add(Constants.SUB_NODE_REPORTS_VISIT)
        report.add(Constants.SUB_NODE_REPORTS_RECOVERY)
        report.add(Constants.SUB_NODE_REPORTS_ORDER)
        report.add(Constants.SUB_NODE_REPORTS_AGING)
        report.add(Constants.SUB_NODE_REPORTS_BANK_DEPOSIT)
        report.add(Constants.SUB_NODE_REPORTS_COMPLAINTS)
        report.add(Constants.SUB_NODE_REPORTS_SALES_PLAN)
        listDataChild[listDataHeader[5]] = report

        val settings: MutableList<String> = ArrayList()
        settings.add(Constants.SUB_NODE_CHANGE_PASSWORD)
        listDataChild[listDataHeader[7]] = settings


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
                getSyncData()
            }
            R.id.upload -> {
               // sendLeadData()
//                sendUserTracking()
            }
            R.id.followup -> {
                navigateToFragment(R.id.action_nav_home_to_followUp)
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

        coroutineViewModel.getLOV().observe(this) {
            this.hideProgressIndicator()
            if (it.lovResponse != null && it.lovResponse.categories.isNotEmpty()
                && it.lovResponse.department.isNotEmpty()
            ) {
                processData(
                    it.lovResponse,
                    it.customersResponse
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
        customerResponse: CustomerResponse
    ) {
        sharedPrefManager.setDepartment(lovResponse.department)
        sharedPrefManager.setCategories(lovResponse.categories)

        // Set leads data in local DB
        roomHelper.deleteCustomersData()
        roomHelper.insertCustomerData(customerResponse)

//        // Set checkIn data in local DB
//        if (visitsCallResponseItem != null) {
//            roomHelper.deleteCheckInData()
//            roomHelper.insertVisitCallData(visitsCallResponseItem)
//        }
//
//        if (portfolioResponse != null) {
//            roomHelper.deletePortfolioData()
//            roomHelper.insertPortfolio(portfolioResponse.porfolio)
//        }

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
//            if (roomHelper.checkUnSyncLeadData().isNotEmpty() || roomHelper.checkUnSyncCheckInData()
//                    .isNotEmpty()
//            ) {
//                showErrorMessage(getString(R.string.un_synced_msg))
//            } else {
//                sharedPrefManager.logout()
//                roomHelper.clearDB()
//                foregroundOnlyLocationService?.unsubscribeToLocationUpdates()
//                startActivity(Intent(this, LoginActivity::class.java))
//                finish()
//            }

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