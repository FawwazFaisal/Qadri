package com.example.qadri.ui.fragment.login

import android.annotation.SuppressLint
import android.content.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.qadri.R
import com.example.qadri.ui.activity.MainActivity
import com.example.qadri.ui.activity.WelcomeActivity
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.constant.Constants
import com.example.qadri.databinding.FragmentWelcomeBinding
import com.example.qadri.mvvm.model.markAttendance.MarkAttendanceModel
import com.example.qadri.mvvm.model.userDetail.UserDetailsResponse
import com.example.qadri.utils.GsonFactory
import com.example.qadri.utils.SharedPrefKeyManager
import com.example.qadri.mvvm.viewModel.coroutine.CoroutineViewModel
import java.text.SimpleDateFormat
import java.util.*

class WelcomeFragment : BaseDockFragment() {
    lateinit var binding: FragmentWelcomeBinding
    var latitude = ""
    var longitude = ""
    private lateinit var mCalender: Calendar
    companion object {
        fun newInstance(): WelcomeFragment {
            val args = Bundle()
            val fragment = WelcomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var viewModel: CoroutineViewModel


    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this, viewModelFactory).get(CoroutineViewModel::class.java)
        mCalender = Calendar.getInstance()
        SharedPrefKeyManager.with(requireContext())
        initView()
        myDockActivity?.getUserViewModel()?.apiListener = this

        binding.fab.setOnClickListener {

            if (myDockActivity?.latitude == "" && myDockActivity?.longitude == "") {
                myDockActivity?.latitude = "0.0"
                myDockActivity?.longitude = "0.0"
            }

            markAttendance("checkin", myDockActivity?.latitude.toString(), myDockActivity?.longitude.toString())
//            (requireActivity() as WelcomeActivity).foregroundOnlyLocationService?.subscribeToLocationUpdates()
        }

//        getUserData()

        return binding.root
    }

    private fun initView() {
        binding = FragmentWelcomeBinding.inflate(layoutInflater)
    }

    fun getUserData() {
        myDockActivity?.getUserViewModel()?.uerDetails("Bearer " + sharedPrefManager.getToken())
    }


    fun markAttendance(type: String, lat: String, lng: String) {
        myDockActivity?.showProgressIndicator()

        enterDashboard()

//        myDockActivity?.getUserViewModel()?.markAttendance(
//            MarkAttendanceModel(type,lat,lng))

    }

    private fun enterDashboard() {
        sharedPrefManager.setShiftStart(true)
        val sdf = SimpleDateFormat("hh:mm:ss dd/M/yyyy")
        val currentDate = sdf.format(Date())
        sharedPrefManager.setTime(currentDate)
        val welcomeIntent = Intent(context, MainActivity::class.java)
        welcomeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(welcomeIntent)
        activity?.finish()
        activity?.overridePendingTransition(R.anim.bottomtotop, R.anim.toptobottom)
    }

    override fun onSuccess(liveData: LiveData<String>, tag: String) {
        super.onSuccess(liveData, tag)
        myDockActivity?.hideProgressIndicator()
        when (tag) {
            Constants.USER_DETAIL -> {
                try {
                    val user = GsonFactory.getConfiguredGson()?.fromJson(liveData.value, UserDetailsResponse::class.java)
                    if (user != null) {
                        binding.name.text = user.first_name + " " + user.last_name
                        sharedPrefManager.setUserDetails(user)
                    }
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            Constants.MARK_ATTENDANCE -> {
                try {
                    sharedPrefManager.setShiftStart(true)
                    val sdf = SimpleDateFormat("hh:mm:ss dd/M/yyyy")
                    val currentDate = sdf.format(Date())
                    sharedPrefManager.setTime(currentDate)
                    val welcomeIntent = Intent(context, MainActivity::class.java)
                    welcomeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(welcomeIntent)
                    activity?.finish()
                    activity?.overridePendingTransition(R.anim.bottomtotop, R.anim.toptobottom)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }
        }
    }

    override fun onFailure(message: String, tag: String) {
        super.onFailure(message, tag)
        myDockActivity?.hideProgressIndicator()
    }
}