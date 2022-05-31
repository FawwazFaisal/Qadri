package com.example.qadri.ui.fragment.dashboard

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.viewpager.widget.ViewPager
import com.example.qadri.R
import com.example.qadri.ui.activity.MainActivity
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.constant.Constants
import com.example.qadri.databinding.FragmentDashboardBinding
import com.example.qadri.mvvm.model.dashboard.DashboardResponse
import com.example.qadri.mvvm.model.generic.GenericMsgResponse
import com.example.qadri.security.EncryptionKeyStoreImpl
import com.example.qadri.ui.activity.DockActivity
import com.example.qadri.utils.GsonFactory
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.squareup.picasso.Picasso
import java.io.File
import java.io.OutputStream
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : BaseDockFragment() {


    private lateinit var binding: FragmentDashboardBinding
    val encryptionKeyStore = EncryptionKeyStoreImpl.instance
//    var followupList: List<DynamicLeadsItem> = listOf()
//    var followUpCount = ArrayList<DynamicLeadsItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        myDockActivity?.getUserViewModel()?.apiListener = this
//        getDashBoardCount()
        initGraphViewPager()

    }

    private fun initGraphViewPager() {
        binding.graphViewPager.offscreenPageLimit = 1
        binding.graphViewPager.adapter = GraphFragmentAdapter(childFragmentManager).apply {
            addFragment(VisitsGraphFragment())
            addFragment(BankDepositGraphFragment())
        }
        binding.graphViewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if(position==0){
                    binding.pendingRecoveryCard.visibility = View.VISIBLE
                    binding.pendingBankDepositCard.visibility = View.GONE
                }else{
                    binding.pendingRecoveryCard.visibility = View.GONE
                    binding.pendingBankDepositCard.visibility = View.VISIBLE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }


    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    private fun init() {
//        binding.name.text =
//            sharedPrefManager.getUserDetails()?.first_name + " " + sharedPrefManager.getUserDetails()?.last_name
//        Handler(Looper.getMainLooper()).postDelayed(Runnable {
//            (requireActivity() as MainActivity).findViewById<TextView>(R.id.JD).text =
//                sharedPrefManager.getUserDetails()?.des_name
//        }, 1000)

//        followupList = encryptionKeyStore.decryptList(roomHelper.getFollowupData()) as List<DynamicLeadsItem>
//
//        if (followupList.isNotEmpty()) {
//            for (item in followupList) {
//                if (item.lead_status.equals("17")) {
//                    followUpCount.add(item)
//                }
//            }
//        }

//        Handler(Looper.getMainLooper()).postDelayed(Runnable {
//            val image =
//                (requireActivity() as MainActivity).findViewById<ImageView>(R.id.user_profile)
//
//                if (sharedPrefManager.getUserDetails()?.profile_img?.isNotEmpty() == true)
//                {
//                    Picasso.get()
//                        .load(sharedPrefManager.getUserDetails()?.profile_img)
//                        .placeholder(R.drawable.ic_user_add)
//                        .into(image)
//                }
//
//            image.setOnClickListener {
//                showAttachmentDialog()
//            }
//        }, 1000)

//        binding.bdb.text = sharedPrefManager.getUserDetails()?.des_name
        binding.shiftStart.text = "Shift Started At ${
            sharedPrefManager.sharedPreferences.getString(
                Constants.SHIFT_TIME,
                "N/A"
            )
        }"
        binding.llTodaysVisit.setOnClickListener {
            navigateToFragment(R.id.nav_home)
        }
        binding.llFollowup.setOnClickListener {
            navigateToFragment(R.id.nav_home)
        }
        binding.pendingBankDepositCard.setOnClickListener {
            navigateToFragment(R.id.action_nav_home_to_bankDeposit)
        }
        binding.pendingRecoveryCard.setOnClickListener {
            navigateToFragment(R.id.action_nav_home_to_reportRecovery)
        }
    }


    private fun uploadProfileImage(image: File) {
        myDockActivity?.getUserViewModel()?.uploadUserProfileImage(image)
    }

    fun showAttachmentDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.upload_image_dialog)
        val cameraBtn = dialog.findViewById(R.id.camera) as ImageView
        val galleryBtn = dialog.findViewById(R.id.gallery) as ImageView
        val cancelBtn = dialog.findViewById(R.id.cancel_btn) as Button
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        cameraBtn.setOnClickListener {
            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(callCameraIntent, Constants.CAMERA_RESULT_CODE)
            dialog.dismiss()
        }
        galleryBtn.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.ACTION_PICK, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                Constants.GALLERY_RESULT_CODE
            )
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val imageView =
            (requireActivity() as MainActivity).findViewById<ImageView>(R.id.user_profile)
        if (requestCode == Constants.CAMERA_RESULT_CODE && resultCode == Activity.RESULT_OK) {
            val photo = data?.extras?.get("data") as? Bitmap
            val imageUri = saveImage(photo!!)
            imageView.setImageBitmap(photo)
            uploadProfileImage(myDockActivity?.getFileFromUri(imageUri!!)!!)

        } else if (requestCode == Constants.GALLERY_RESULT_CODE && resultCode == Activity.RESULT_OK) {
            val fileUri = data?.data
            imageView.setImageURI(fileUri)
            uploadProfileImage(myDockActivity?.getFileFromUri(fileUri!!)!!)
        }

    }

    private fun saveImage(bitmap: Bitmap): Uri? {
        val contentValues = ContentValues().apply {
            this.put(MediaStore.Images.Media.MIME_TYPE, "image/PNG")
            this.put(MediaStore.Images.Media.DISPLAY_NAME, "image${Random().nextInt()}")
        }
        val path = context?.contentResolver?.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )
        return saveImageToStream(bitmap, context?.contentResolver?.openOutputStream(path!!), path!!)
    }

    private fun saveImageToStream(bitmap: Bitmap, outputStream: OutputStream?, uri: Uri): Uri {
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        outputStream!!.close()
        return uri
    }

    override fun onResume() {
        super.onResume()
//        try {
//            Handler(Looper.getMainLooper()).postDelayed(Runnable {
//                binding.todayVisit.text = roomHelper.getVisitLogsCount()
//
//                binding.todaysFollowUp.text = followUpCount.size.toString()
//            }, 2000)
//        } catch (e: Exception) {
//            Log.i("DashboardCount", e.message.toString())
//        }
//
//        if (roomHelper.checkUnSyncLeadData().isNotEmpty() || roomHelper.checkUnSyncCheckInData()
//                .isNotEmpty() && myDockActivity?.internetHelper?.isNetworkAvailable() == true
//        ) {
//            Log.i("xxUpload", "upload")
//            (requireActivity() as MainActivity).sendLeadData()
//        }
    }

    private fun getDashBoardCount() {
        myDockActivity?.getUserViewModel()?.getDashBoard()
    }

    override fun onSuccess(liveData: LiveData<String>, tag: String) {
        super.onSuccess(liveData, tag)
        when (tag) {
            Constants.DASHBOARD_COUNT -> {
                Log.i("DashboardCount", liveData.value.toString())
                val dashboardResponseEnt = GsonFactory.getConfiguredGson()
                    ?.fromJson(liveData.value, DashboardResponse::class.java)


            }
            Constants.UPLOAD_PHOTO -> {
                val dashboardResponseEnt = GsonFactory.getConfiguredGson()
                    ?.fromJson(liveData.value, GenericMsgResponse::class.java)
                myDockActivity?.showSuccessMessage(dashboardResponseEnt?.message!!)
            }
        }
    }

    override fun onFailure(message: String, tag: String) {
        super.onFailure(message, tag)

    }

    override fun onDestroy() {
        super.onDestroy()
     //   followUpCount.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
       // followUpCount.clear()
    }
}