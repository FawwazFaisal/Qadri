package com.example.qadri.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.*
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import android.widget.ImageButton
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import butterknife.ButterKnife
import butterknife.Unbinder
import com.deepakkumardk.kontactpickerlib.KontactPicker
import com.deepakkumardk.kontactpickerlib.model.KontactPickerItem
import com.deepakkumardk.kontactpickerlib.model.SelectionMode
import com.example.qadri.R
import com.example.qadri.ui.adapter.ExpandableListAdapter
import com.example.qadri.constant.Constants
import com.example.qadri.databinding.ActivityMainBinding
import com.example.qadri.mvvm.model.addLead.DynamicLeadsItem
import com.example.qadri.mvvm.model.checkin.CheckinModel
import com.example.qadri.mvvm.model.lov.CompanyLeadSource
import com.example.qadri.mvvm.model.lov.LovResponse
import com.example.qadri.mvvm.model.portfolio.PortfolioResponse
import com.example.qadri.security.EncryptionKeyStoreImpl
import com.example.qadri.utils.*
import com.example.qadri.utils.Schedulers.LocationWorkManager.LocationWorker
import com.example.qadri.utils.Schedulers.UploadCheckInWorkManager.UploadCheckInWorker
import com.example.qadri.utils.Schedulers.UploadLeadWorkManager.UploadLeadWorker
import com.example.qadri.mvvm.viewModel.coroutine.CoroutineViewModel
import com.squareup.picasso.Picasso
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.set


class MainActivity : DockActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var number: CustomEditText
    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var contentView: ConstraintLayout
    val END_SCALE = 0.7f
    private lateinit var actionBarMenu: Menu
    private lateinit var switchAB: SwitchCompat
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var viewModel: CoroutineViewModel
    lateinit var listDataChild: HashMap<String, List<String>>
    lateinit var listDataHeader: ArrayList<String>
    private var x1 = 0f
    private var x2 = 0f
    val MIN_DISTANCE = 60
    val encryptionKeyStore = EncryptionKeyStoreImpl.instance

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      navController = findNavController(R.id.nav_host_main)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CoroutineViewModel::class.java)

        initView()
        setData()
        //   setGesture()
        sendUserTracking()
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_host_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        actionBarMenu = menu
        val item = menu.findItem(R.id.myswitch) as MenuItem
        actionBarMenu.findItem(R.id.action_notification).setOnMenuItemClickListener {
            navigateToFragment(R.id.nav_home)
            true
        }

        item.setActionView(R.layout.switch_layout)
        switchAB = item.actionView.findViewById(R.id.switchAB)
        sharedPreferences = this.getSharedPreferences("SharedPrefs", MODE_PRIVATE)
        if (switchAB.isChecked) {
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
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
        contentView = binding.appBarMain.content
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
        animateNavigationDrawer(binding.drawerLayout)

        if (roomHelper.checkUnSyncLeadData().isNotEmpty() || roomHelper.checkUnSyncCheckInData()
                .isNotEmpty() && internetHelper.isNetworkAvailable()
        ) {
            sendLeadData()
        }

        getSyncData(isShowLoading = false)
        prepareSideMenu()
    }

    private fun setData() {
        binding.sideLayout.name.text = sharedPrefManager.getUserDetails()?.first_name + " " + sharedPrefManager.getUserDetails()?.last_name
        Picasso.get().load("https://medias.spotern.com/spots/w640/229/229560-1567745387.jpg").error(R.drawable.ic_user).into(binding.sideLayout.profile)

    }

    private fun animateNavigationDrawer(drawerLayout: DrawerLayout) {
        drawerLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                // Scale the View based on current slide offset
                val diffScaledOffset: Float = slideOffset * (1 - END_SCALE)
                val offsetScale = 1 - diffScaledOffset
                contentView.scaleX = offsetScale
                contentView.scaleY = offsetScale
                // Translate the View, accounting for the scaled width
                val xOffset: Float = drawerView.width * slideOffset
                val xOffsetDiff: Float = contentView.width * diffScaledOffset / 2
                val xTranslation = xOffset - xOffsetDiff
                contentView.translationX = xTranslation
            }
        })
    }

    private fun fragmentClickEvent(itemString: String) {
        closeDrawer()
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
                navigateToFragment(R.id.nav_home)
            }
            Constants.SUB_NODE_IN_TRANSIT_ORDER -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.SUB_NODE_COMPLETED_ORDER -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.SUB_NODE_REPORTS_VISIT -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.SUB_NODE_REPORTS_RECOVERY -> {
                navigateToFragment(R.id.nav_home)

            }
            Constants.SUB_NODE_REPORTS_ORDER -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.SUB_NODE_REPORTS_AGING -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.SUB_NODE_REPORTS_BANK_DEPOSIT -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.SUB_NODE_REPORTS_COMPLAINTS -> {
                navigateToFragment(R.id.nav_home)
            }
            Constants.SUB_NODE_REPORTS_SALES_PLAN -> {
                showLogOutAlert()
            }
            Constants.NODE_NOTIFICATIONS -> {
                showLogOutAlert()
            }
            Constants.SUB_NODE_CHANGE_PASSWORD -> {
                showLogOutAlert()
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
        listDataChild[listDataHeader[1]] = salesPlan

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

    @SuppressLint("ClickableViewAccessibility")
    private fun setGesture() {
        binding.appBarMain.sideMenu.root.setOnTouchListener { p0, p1 ->
            if (p1 != null) {
                when (p1.action) {
                    MotionEvent.ACTION_DOWN -> x1 = p1.x
                    MotionEvent.ACTION_UP -> {
                        x2 = p1.x
                        val deltaX: Float = x2 - x1
                        if (Math.abs(deltaX) > MIN_DISTANCE) {
                            showOrHide()
                        }
                    }
                }
            }
            true
        }
    }

    private fun navigateToFragment(@IdRes id: Int, args: Bundle? = null) {
        if (args != null) {
            navController.navigate(id, args)
            return
        }
        navController.navigate(id)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 3000) {
            val list = KontactPicker.getSelectedKontacts(data) //ArrayList<MyContacts>
            if (list!!.isNotEmpty()) {
                list[0].contactNumber?.let {
                    number.setText(it)
                }
            }
        }
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