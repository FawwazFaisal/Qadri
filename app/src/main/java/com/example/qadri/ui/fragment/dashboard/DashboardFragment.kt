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
import com.example.qadri.R
import com.example.qadri.ui.activity.MainActivity
import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.constant.Constants
import com.example.qadri.databinding.FragmentDashboardBinding
import com.example.qadri.mvvm.model.addLead.DynamicLeadsItem
import com.example.qadri.mvvm.model.dashboard.DashboardResponse
import com.example.qadri.mvvm.model.generic.GenericMsgResponse
import com.example.qadri.security.EncryptionKeyStoreImpl
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
    private lateinit var todayCall: String
    private lateinit var todayVisit: String
    private lateinit var todayFollowup: String
    var selectedEntry: Entry = Entry(25.0f, 0.0f)
    val encryptionKeyStore = EncryptionKeyStoreImpl.instance
    var followupList: List<DynamicLeadsItem> = listOf()
    var followUpCount = ArrayList<DynamicLeadsItem>()

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

        setAnim()
        val labels = monthList()
        initChart(binding, labels)
    }


    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    private fun init() {
        binding.barchart.description.isEnabled = false
        binding.barchart.setMaxVisibleValueCount(60)
        binding.name.text =
            sharedPrefManager.getUserDetails()?.first_name + " " + sharedPrefManager.getUserDetails()?.last_name
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            (requireActivity() as MainActivity).findViewById<TextView>(R.id.JD).text =
                sharedPrefManager.getUserDetails()?.des_name
        }, 1000)


        followupList = encryptionKeyStore.decryptList(roomHelper.getFollowupData()) as List<DynamicLeadsItem>

        if (followupList.isNotEmpty()) {
            for (item in followupList) {
                if (item.lead_status.equals("17")) {
                    followUpCount.add(item)
                }
            }
        }

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val image =
                (requireActivity() as MainActivity).findViewById<ImageView>(R.id.user_profile)

                if (sharedPrefManager.getUserDetails()?.profile_img?.isNotEmpty() == true)
                {
                    Picasso.get()
                        .load(sharedPrefManager.getUserDetails()?.profile_img)
                        .placeholder(R.drawable.ic_user_add)
                        .into(image)
                }

            image.setOnClickListener {
                showAttachmentDialog()
            }
        }, 1000)

        binding.bdb.text = sharedPrefManager.getUserDetails()?.des_name
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

    private fun initChart(binding: FragmentDashboardBinding, months: List<String>?) {
//      binding.barchart.description.isEnabled = false
        val monthNames = ArrayList<String>()
        for (i in months!!.indices) {
            monthNames.add(months[i])
        }
        // scaling can now only be done on x- and y-axis separately
        binding.barchart.setPinchZoom(false)
        binding.barchart.setDrawGridBackground(false)
        binding.barchart.setScaleEnabled(false)
        binding.barchart.setDrawBorders(true)
        val xAxis: XAxis = binding.barchart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawLabels(true)
        binding.barchart.xAxis.labelCount = monthNames.size
        binding.barchart.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return monthNames[value.toInt()]
            }
        }
        binding.barchart.axisLeft.axisMaximum = 10f
        binding.barchart.axisLeft.labelCount = 5
        months.let { setDataBarChart(it) }
        // add a nice and smooth animation
        binding.barchart.animateY(1500)
    }

    private fun setDataBarChart(months: List<String>) {
        val valuesAchievement = ArrayList<Entry>()
        val valuesTarget = ArrayList<Entry>()
        var salesList: ArrayList<Float>
        var achievementList: ArrayList<Float>
        val targetColor = ArrayList<Int>()
        val achievementColor = ArrayList<Int>()
        val targetCircleColor = ArrayList<Int>()
        val achievementCircleColor = ArrayList<Int>()
        val xAxisLabel = ArrayList<String>()
        for (i in months.indices) {
            xAxisLabel.add(months[i])
            salesList = salesTarget()
            val barEntry = Entry((i).toFloat(), salesList[i])
            targetColor.add(ContextCompat.getColor(requireContext(), R.color.yellow))
            targetCircleColor.add(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorPrimary
                )
            )
            valuesTarget.add(barEntry)
        }
        for (i in months.indices) {
            achievementList = achievement()
            val entry = Entry((i).toFloat(), achievementList[i])
            achievementColor.add(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
            achievementCircleColor.add(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.yellow
                )
            )
            valuesAchievement.add(entry)
        }
        val setTarget: LineDataSet
        val setAchievement: LineDataSet

        if (binding.barchart.data != null && binding.barchart.data.dataSetCount > 0) {
            setTarget = binding.barchart.data.getDataSetByIndex(0) as LineDataSet
            setTarget.values = valuesTarget
            setTarget.setDrawValues(true)
            setTarget.valueTextColor =
                ContextCompat.getColor(requireContext(), android.R.color.transparent)
            setAchievement = binding.barchart.data.getDataSetByIndex(1) as LineDataSet
            setAchievement.values = valuesAchievement
            setAchievement.setDrawValues(true)
            setAchievement.valueTextColor =
                ContextCompat.getColor(requireContext(), android.R.color.transparent)
            binding.barchart.xAxis.labelCount = xAxisLabel.size
            binding.barchart.data.notifyDataChanged()
        } else {
            binding.barchart.notifyDataSetChanged()
            binding.barchart.xAxis.valueFormatter = object :
                ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return xAxisLabel[value.toInt()]
                }
            }
            val xl: XAxis = binding.barchart.xAxis
            xl.isGranularityEnabled = true
            xl.position = XAxis.XAxisPosition.BOTTOM
            setTarget = LineDataSet(valuesTarget, "")
            setAchievement = LineDataSet(valuesAchievement, "")
            setTarget.colors = targetColor
            setAchievement.colors = achievementColor
            setTarget.fillColor = ContextCompat.getColor(requireContext(), R.color.yellow)
            setAchievement.fillColor =
                ContextCompat.getColor(requireContext(), R.color.colorPrimary)
            setTarget.fillAlpha = 150
            setAchievement.fillAlpha = 200
            setTarget.setCircleColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorPrimary
                )
            )
            setAchievement.setCircleColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.yellow
                )
            )
            fillLines(setAchievement)
            fillLines(setTarget)
            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(setTarget)
            dataSets.add(setAchievement)
            val barData = LineData(dataSets)
            binding.barchart.data = barData
        }
        removeGridLines()
        setCustomLegend()
        binding.barchart.axisLeft.axisMinimum = 0f // remove space from month label
        binding.barchart.invalidate()

    }

    private fun fillLines(lineDataSet: LineDataSet) {
        lineDataSet.setDrawValues(true)
        lineDataSet.valueTextColor =
            ContextCompat.getColor(requireContext(), android.R.color.transparent)
        lineDataSet.valueTextSize = 8f
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        lineDataSet.setDrawCircles(true)
        lineDataSet.circleHoleRadius = 2f
        lineDataSet.circleHoleColor =
            ContextCompat.getColor(requireContext(), R.color.colorPrimary)
        lineDataSet.lineWidth = 1.0f
        lineDataSet.circleRadius = 2f
        lineDataSet.setDrawFilled(true)
    }

    private fun achievement(): ArrayList<Float> {
        val salesList = ArrayList<Float>()
        salesList.add(7f)
        salesList.add(2f)
        salesList.add(2f)
        salesList.add(3f)
        salesList.add(3f)
        salesList.add(2f)
        salesList.add(5f)
        salesList.add(6f)
        salesList.add(1f)
        salesList.add(1f)
        salesList.add(2f)
        salesList.add(3f)
        return salesList
    }

    private fun salesTarget(): ArrayList<Float> {
        val achievementList = ArrayList<Float>()
        achievementList.add(5f)
        achievementList.add(3f)
        achievementList.add(3f)
        achievementList.add(4f)
        achievementList.add(3.6f)
        achievementList.add(3.4f)
        achievementList.add(5.5f)
        achievementList.add(6.5f)
        achievementList.add(2.5f)
        achievementList.add(3.5f)
        achievementList.add(5f)
        achievementList.add(3f)
        return achievementList
    }

    private fun getValColors(xAxis: Float): ArrayList<Int> {
        return arrayListOf<Int>().apply {
            for (i in 0 until (23)) {
                if (xAxis.toInt() == i) {
                    add(ContextCompat.getColor(requireContext(), android.R.color.black))
                } else {
                    add(ContextCompat.getColor(requireContext(), android.R.color.transparent))
                }
            }
        }
    }

    private fun removeGridLines() {
        binding.barchart.setTouchEnabled(true)
        binding.barchart.isClickable = false
        binding.barchart.isDoubleTapToZoomEnabled = false
        binding.barchart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                if (selectedEntry != e) {
                    binding.barchart.data.dataSets[0].setValueTextColors(getValColors(e!!.x))
                    binding.barchart.data.dataSets[1].setValueTextColors(getValColors(e.x))
                }
                selectedEntry = e
            }

            override fun onNothingSelected() {
                binding.barchart.data.dataSets[0].valueTextColor =
                    ContextCompat.getColor(requireContext(), android.R.color.transparent)
                binding.barchart.data.dataSets[1].valueTextColor =
                    ContextCompat.getColor(requireContext(), android.R.color.transparent)
            }
        })

        binding.barchart.setDrawBorders(false)
        binding.barchart.setDrawGridBackground(false)

        binding.barchart.description.isEnabled = false
        binding.barchart.legend.isEnabled = true //bottom label

        binding.barchart.axisLeft.setDrawGridLines(true)
        binding.barchart.axisLeft.setDrawLabels(true)
        binding.barchart.axisLeft.setDrawAxisLine(true)

        binding.barchart.xAxis.setDrawGridLines(false)
        binding.barchart.xAxis.setDrawLabels(true)
        binding.barchart.xAxis.setDrawAxisLine(true)

        binding.barchart.axisRight.setDrawGridLines(false)
        binding.barchart.axisRight.setDrawLabels(false)
        binding.barchart.axisRight.setDrawAxisLine(false)
    }

    private fun setCustomLegend() {
        val l: Legend = binding.barchart.legend
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        binding.barchart.extraBottomOffset = 9f  // set margin from top
        l.isWordWrapEnabled = true
        l.textSize = 10f
        val l1 = LegendEntry(
            resources.getString(R.string.sales_target), Legend.LegendForm.LINE, 10f,
            2f, null, resources.getColor(R.color.yellow)
        )
        val l2 = LegendEntry(
            resources.getString(R.string.achievement), Legend.LegendForm.LINE, 10f,
            2f, null, resources.getColor(
                R.color.colorPrimary
            )
        )
        l.setCustom(arrayOf(l1, l2))
        l.isEnabled = true
    }

    private fun monthList(): ArrayList<String> {
        val months = ArrayList<String>()
        months.add("Jan")
        months.add("Feb")
        months.add("Mar")
        months.add("Apr")
        months.add("May")
        months.add("June")
        months.add("July")
        months.add("Aug")
        months.add("Sep")
        months.add("Oct")
        months.add("Nov")
        months.add("Dec")
        return months
    }

    private fun setAnim() {

        binding.llTodaysCounter.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(),
                R.anim.righttoleft
            )
        )
    }

    override fun onResume() {
        super.onResume()
        try {
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                binding.todayVisit.text = roomHelper.getVisitLogsCount()

                binding.todaysFollowUp.text = followUpCount.size.toString()
            }, 2000)
        } catch (e: Exception) {
            Log.i("DashboardCount", e.message.toString())
        }

        if (roomHelper.checkUnSyncLeadData().isNotEmpty() || roomHelper.checkUnSyncCheckInData()
                .isNotEmpty() && myDockActivity?.internetHelper?.isNetworkAvailable() == true
        ) {
            Log.i("xxUpload", "upload")
            (requireActivity() as MainActivity).sendLeadData()
        }
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

    override fun onDestroy() {
        super.onDestroy()
        followUpCount.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        followUpCount.clear()
    }
}