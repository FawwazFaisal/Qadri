package com.example.qadri.ui.fragment.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.qadri.R
import com.example.qadri.databinding.FragmentBankDepositGraphBinding
import com.example.qadri.ui.fragment.BaseDockFragment
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

class BankDepositGraphFragment : BaseDockFragment() {

    lateinit var bd: FragmentBankDepositGraphBinding
    var selectedEntry: Entry = Entry(25.0f, 0.0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = FragmentBankDepositGraphBinding.inflate(inflater)
        return bd.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initChart()
    }

    private fun initChart() {
        val months = monthList()
        bd.lineGraph.description.isEnabled = false
        bd.lineGraph.setPinchZoom(false)
        bd.lineGraph.setDrawGridBackground(false)
        bd.lineGraph.setScaleEnabled(false)
        bd.lineGraph.setDrawBorders(true)

        bd.lineGraph.xAxis.position = XAxis.XAxisPosition.BOTTOM
        bd.lineGraph.xAxis.setDrawLabels(true)
        bd.lineGraph.xAxis.labelCount = months.size
        bd.lineGraph.xAxis.isGranularityEnabled = true
        bd.lineGraph.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return months[value.toInt()]
            }
        }
        bd.lineGraph.axisLeft.axisMaximum = 150f
        bd.lineGraph.axisLeft.labelCount = 5
        setDatalineGraph(months)
        bd.lineGraph.animateY(1500)
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

    private fun setDatalineGraph(months: List<String>) {
        val achievementEntries = ArrayList<Entry>()
        val achievementColor = ArrayList<Int>()
        val achievementCircleColor = ArrayList<Int>()
        for (i in months.indices) {
            val entry = Entry((i).toFloat(), achievement()[i])
            achievementColor.add(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
            achievementCircleColor.add(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.yellow
                )
            )
            achievementEntries.add(entry)
        }
        val setAchievement: LineDataSet

        if (bd.lineGraph.data != null && bd.lineGraph.data.dataSetCount > 0) {
            setAchievement = bd.lineGraph.data.getDataSetByIndex(0) as LineDataSet
            setAchievement.values = achievementEntries
            setAchievement.setDrawValues(true)
            setAchievement.valueTextColor =
                ContextCompat.getColor(requireContext(), android.R.color.transparent)
            bd.lineGraph.data.notifyDataChanged()
        } else {
            bd.lineGraph.notifyDataSetChanged()
            setAchievement = LineDataSet(achievementEntries, "")
            setAchievement.colors = achievementColor
            setAchievement.setCircleColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.yellow
                )
            )
            fillLines(setAchievement)
            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(setAchievement)
            val barData = LineData(dataSets)
            bd.lineGraph.data = barData
        }
        removeGridLines()
//        setCustomLegend()
        bd.lineGraph.axisLeft.axisMinimum = 0f // remove space from month label
        bd.lineGraph.invalidate()

    }

    private fun fillLines(lineDataSet: LineDataSet) {
        lineDataSet.setDrawValues(true)
        lineDataSet.valueTextColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        lineDataSet.valueTextSize = 8f
        lineDataSet.setDrawCircles(true)
        lineDataSet.circleHoleRadius = 2f
        lineDataSet.circleHoleColor = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
        lineDataSet.lineWidth = 1.0f
        lineDataSet.circleRadius = 2f

        lineDataSet.setDrawFilled(true)
//        lineDataSet.fillColor = ContextCompat.getColor(requireContext(),R.color.colorAccent)
        lineDataSet.fillDrawable = ContextCompat.getDrawable(requireContext(),R.drawable.graph_gradient)
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        lineDataSet.fillAlpha = 80
    }

    private fun achievement(): ArrayList<Float> {
        val salesList = ArrayList<Float>()
        salesList.add(150f)
        salesList.add(120f)
        salesList.add(122f)
        salesList.add(135f)
        salesList.add(100f)
        salesList.add(80f)
        salesList.add(80f)
        salesList.add(50f)
        salesList.add(60f)
        salesList.add(70f)
        salesList.add(60f)
        salesList.add(150f)
        return salesList
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
        bd.lineGraph.setTouchEnabled(true)
        bd.lineGraph.isClickable = false
        bd.lineGraph.isDoubleTapToZoomEnabled = false
        bd.lineGraph.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                if (selectedEntry != e) {
                    bd.lineGraph.data.dataSets[0].setValueTextColors(getValColors(e!!.x))
                }
                selectedEntry = e
            }

            override fun onNothingSelected() {
                bd.lineGraph.data.dataSets[0].valueTextColor =
                    ContextCompat.getColor(requireContext(), android.R.color.transparent)
            }
        })

        bd.lineGraph.setDrawBorders(false)
        bd.lineGraph.setDrawGridBackground(false)

        bd.lineGraph.description.isEnabled = false
        bd.lineGraph.legend.isEnabled = false //bottom label

        bd.lineGraph.axisLeft.setDrawGridLines(false)
        bd.lineGraph.axisLeft.setDrawLabels(true)
        bd.lineGraph.axisLeft.setDrawAxisLine(false)

        bd.lineGraph.xAxis.setDrawGridLines(false)
        bd.lineGraph.xAxis.setDrawLabels(true)
        bd.lineGraph.xAxis.setDrawAxisLine(true)

        bd.lineGraph.axisRight.setDrawGridLines(false)
        bd.lineGraph.axisRight.setDrawLabels(false)
        bd.lineGraph.axisRight.setDrawAxisLine(false)
    }
}