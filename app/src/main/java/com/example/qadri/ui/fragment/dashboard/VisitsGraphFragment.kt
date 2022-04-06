package com.example.qadri.ui.fragment.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.qadri.R
import com.example.qadri.databinding.FragmentVisitsGraphBinding
import com.example.qadri.ui.fragment.BaseDockFragment
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import java.util.ArrayList


class VisitsGraphFragment : BaseDockFragment() {

    lateinit var bd: FragmentVisitsGraphBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentVisitsGraphBinding.inflate(inflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initChart()
    }
    private fun initChart() {
        bd.barchart.description.isEnabled = false
        val dayLabels = ArrayList<String>()
        for (i in dayLabels().indices) {
            dayLabels.add(dayLabels()[i])
        }
        // scaling can now only be done on x- and y-axis separately
        bd.barchart.setPinchZoom(false)
        bd.barchart.setDrawBarShadow(false)
        bd.barchart.setDrawGridBackground(false)
        val xAxis: XAxis = bd.barchart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        xAxis.setDrawGridLines(false)
        bd.barchart.xAxis.labelCount = dayLabels.size
        bd.barchart.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return dayLabels[value.toInt()]
            }
        }
        bd.barchart.axisLeft.setDrawGridLines(false)
        setDataBarChart(dayLabels())
        // add a nice and smooth animation
        bd.barchart.animateY(1500)
    }


    private fun setDataBarChart(dayLabels: List<String>) {
        val values = ArrayList<BarEntry>()
        var salesList = ArrayList<Float>()
        var achievementList = ArrayList<Float>()
        val colors = ArrayList<Int>()
        val xAxisLabel = ArrayList<String>()
        for (i in dayLabels.indices) {
            xAxisLabel.add(dayLabels[i])
            colors.add(resources.getColor(R.color.colorPrimary))
            colors.add(resources.getColor(R.color.lightGray))
            salesList = salesTarget()
            achievementList = achievement()
            values.add(BarEntry((i).toFloat(), floatArrayOf(achievementList[i], salesList[i])))
        }
        val set1: BarDataSet
        if (bd.barchart.data != null &&
            bd.barchart.data.dataSetCount > 0
        ) {
            set1 = bd.barchart.data.getDataSetByIndex(0) as BarDataSet
            set1.setDrawIcons(false)
            set1.values = values
            bd.barchart.xAxis.labelCount = xAxisLabel.size
            bd.barchart.data.notifyDataChanged()
        } else {
            bd.barchart.notifyDataSetChanged()
            bd.barchart.xAxis.valueFormatter = object :
                ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return xAxisLabel[value.toInt()]
                }
            }
            val xl: XAxis = bd.barchart.xAxis
            // xl.setAvoidFirstLastClipping(true)
            xl.isGranularityEnabled = true
            xl.position = XAxis.XAxisPosition.BOTTOM
            xl.textSize = 9f

            set1 = BarDataSet(values, "")
            set1.setDrawIcons(false)
            set1.setDrawValues(false) //hide value from bar entry
            set1.colors = colors
            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)

            val yl: YAxis = bd.barchart.axisLeft
            yl.textSize = 8f

            val data = BarData(dataSets)
            bd.barchart.data = data
            bd.barchart.setScaleEnabled(false)

        }
        removeGridLines()
        setCustomLegend()
        bd.barchart.setFitBars(true)
        bd.barchart.invalidate()
    }

    private fun salesTarget(): ArrayList<Float> {
        val salesList = ArrayList<Float>()
        salesList.add(6f)
        salesList.add(13f)
        salesList.add(13f)
        salesList.add(15f)
        salesList.add(7f)
        salesList.add(4f)
        return salesList
    }

    private fun achievement(): ArrayList<Float> {
        val achievementList = ArrayList<Float>()
        achievementList.add(5f)
        achievementList.add(1f)
        achievementList.add(10f)
        achievementList.add(13f)
        achievementList.add(7f)
        achievementList.add(3f)
        return achievementList
    }

    private fun removeGridLines() {
        bd.barchart.setTouchEnabled(true)
        bd.barchart.isClickable = false
        bd.barchart.isDoubleTapToZoomEnabled = false

        bd.barchart.setDrawBorders(false)
        bd.barchart.setDrawGridBackground(false)

        bd.barchart.description.isEnabled = false
        bd.barchart.legend.isEnabled = true //bottom label

        bd.barchart.axisLeft.setDrawGridLines(false)
        bd.barchart.axisLeft.setDrawLabels(true)
        bd.barchart.axisLeft.setDrawAxisLine(false)

        bd.barchart.xAxis.setDrawGridLines(false)
        bd.barchart.xAxis.setDrawLabels(true)
        bd.barchart.xAxis.setDrawAxisLine(false)

        bd.barchart.axisRight.setDrawGridLines(false)
        bd.barchart.axisRight.setDrawLabels(false)
        bd.barchart.axisRight.setDrawAxisLine(false)

//        bd.barchart.axisRight.axisMinimum = -100F
//        bd.barchart.axisRight.axisMaximum = 100F
    }

    private fun setCustomLegend() {
        val l: Legend = bd.barchart.legend
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        bd.barchart.extraBottomOffset = 9f  // set margin from top
        l.isWordWrapEnabled = true
        l.textSize = 10f
        val l2 = LegendEntry(
            resources.getString(R.string.achievement), Legend.LegendForm.CIRCLE, 10f,
            2f, null, resources.getColor(R.color.colorAccent)
        )
        val l1 = LegendEntry(
            resources.getString(R.string.sales_target), Legend.LegendForm.CIRCLE, 10f,
            2f, null, resources.getColor(R.color.lightGray)
        )
        l.setCustom(arrayOf(l1, l2))
        l.isEnabled = true
    }

    private fun dayLabels(): ArrayList<String> {
        val months = ArrayList<String>()
        months.add("Mon")
        months.add("Tue")
        months.add("Wed")
        months.add("Thurs")
        months.add("Fri")
        months.add("Sat")
        return months
    }
}