package com.example.qadri.ui.activity

import android.annotation.SuppressLint
import android.content.*
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.qadri.R
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.location.ForegroundOnlyLocationService
import com.example.qadri.location.toText
import com.example.qadri.progress.ProgressDialog
import com.example.qadri.progress.ProgressIndicator
import com.example.qadri.mvvm.room.RoomHelper
import com.example.qadri.utils.InternetHelper
import com.example.qadri.utils.SharedPrefManager
import com.example.qadri.utils.UtilHelper
import com.example.qadri.mvvm.viewModel.UserViewModel
import com.google.android.gms.location.LocationServices
import com.tapadoo.alerter.Alerter
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.util.*
import javax.inject.Inject


/**
 * @author Abdullah Nagori
 */

abstract class DockActivity : DaggerAppCompatActivity(), ProgressIndicator {

    @Inject
    lateinit var sharedPrefManager: SharedPrefManager

    @Inject
    lateinit var roomHelper: RoomHelper

    @Inject
    lateinit var internetHelper: InternetHelper

    @Inject
    lateinit var utilHelper: UtilHelper

    var location: Location = Location(LocationManager.GPS_PROVIDER)
    var latitude: String? = ""
    var longitude: String? = ""
    var locationManager: LocationManager? = null
    private lateinit var progressBarDialog: ProgressDialog
    val viewModel: UserViewModel by viewModels()
    private lateinit var foregroundOnlyBroadcastReceiver: ForegroundOnlyBroadcastReceiver
    var foregroundOnlyLocationService: ForegroundOnlyLocationService? = null
    private var mBound = false

    val foregroundOnlyServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as ForegroundOnlyLocationService.LocalBinder
            foregroundOnlyLocationService = binder.service
            mBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            foregroundOnlyLocationService = null
            mBound = false
        }
    }

    override fun onStart() {
        super.onStart()

        val serviceIntent = Intent(this, ForegroundOnlyLocationService::class.java)
        bindService(serviceIntent, foregroundOnlyServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onResume() {
        super.onResume()

        LocalBroadcastManager.getInstance(this).registerReceiver(
            foregroundOnlyBroadcastReceiver!!,
            IntentFilter(
                ForegroundOnlyLocationService.ACTION_FOREGROUND_ONLY_LOCATION_BROADCAST
            )
        )
    }

    override fun onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
            foregroundOnlyBroadcastReceiver!!
        )
        super.onPause()
    }

    override fun onStop() {
        if (mBound) {
            unbindService(foregroundOnlyServiceConnection)
            mBound = false
        }
        super.onStop()
    }

    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foregroundOnlyBroadcastReceiver = ForegroundOnlyBroadcastReceiver()
        getLocation()

    }

    fun hideKeyboard(view: View) {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            view.applicationWindowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    fun replaceDockableFragmentWithoutBackStack(frag: BaseDockFragment?) {
        val transaction = supportFragmentManager
            .beginTransaction()
        transaction.replace(R.id.frame_holder, frag!!)
        transaction.commit()
    }

    fun getFileFromUri(list: Uri): File {
            val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(list))
            val scaledBitmap = Bitmap.createScaledBitmap(bitmap,bitmap.width/2,bitmap.height/2,false)
            val file = File(externalCacheDir,"qadri.PNG${Random().nextInt()}")
            file.createNewFile()
            FileOutputStream(file).use { outputStream ->
                scaledBitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream)
            }
        return file
    }

    fun getUserViewModel(): UserViewModel {
        return viewModel
    }

    open fun showErrorMessage(message: String) {
        Alerter.create(this)
            .setTitle(getString(R.string.error))
            .setText(message)
            .setDuration(5000)
            .setIcon(R.drawable.ic_close)
            .setBackgroundColorRes(R.color.red_error)
            .enableSwipeToDismiss()
            .show()
    }

    open fun showSuccessMessage(message: String) {
        Alerter.create(this)
            .setTitle(getString(R.string.success))
            .setText(message)
            .setDuration(5000)
            .setIcon(R.drawable.ic_close)
            .setBackgroundColorRes(R.color.darkGreen)
            .enableSwipeToDismiss()
            .show()
    }

    open fun onSuccessResponse(liveData: LiveData<String>, tag: String) {
        this.hideProgressIndicator()
    }

    open fun onFailureResponse(message: String, tag: String) {
        this.hideProgressIndicator()
        this.showErrorMessage(message)
    }

    fun <T : Iterable<*>?> nullGuard(item: T?): T {
        return (item ?: Collections.EMPTY_LIST) as T
    }

    override fun showProgressIndicator() {
        progressBarDialog = ProgressDialog()
        progressBarDialog.showDialog(
            supportFragmentManager,
            DockActivity::class.java.simpleName
        )
    }

    override fun hideProgressIndicator() {
        if (this::progressBarDialog.isInitialized && progressBarDialog.isAdded) {
            progressBarDialog.dismiss()
        }
    }

    private fun logResultsToScreen(output: String) {
        Log.i("Foreground", output)
    }

    private inner class ForegroundOnlyBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val location = intent.getParcelableExtra<Location>(
                ForegroundOnlyLocationService.EXTRA_LOCATION
            )

            if (location != null) {
                logResultsToScreen("Foreground location: ${location.toText()}")
            }
        }
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        LocationServices.getFusedLocationProviderClient(this).lastLocation.addOnSuccessListener {
            try {
                if (it == null) {
                    getLocation()
                }
                latitude = it.latitude.toString()
                longitude = it.longitude.toString()
                Log.i(
                    "CurrentLocation",
                    "Your Location: \nLatitude: $latitude\nLongitude: $longitude"
                )
            } catch (e: java.lang.Exception) {
            }
        }
    }

    fun visibleWithAnimation(view: View) {
        view.visibility = View.VISIBLE
        view.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.slide_in_right
            )
        )
    }

    fun goneWithAnimation(view: View) {
        view.visibility = View.GONE
        view.startAnimation(
            AnimationUtils.loadAnimation(
                this,
                R.anim.slide_out_right
            )
        )
    }

    fun closeDrawer() {
        drawer_layout.closeDrawer(GravityCompat.START)
    }

}