package com.example.qadri.mvvm.model.branchDirectory


import com.google.gson.annotations.SerializedName

data class SubmitReport(
    @SerializedName("adc_performance_review")
    val adcPerformanceReview: String = "",
    @SerializedName("adc_performance_review_comment")
    val adcPerformanceReviewComment: String = "",
    @SerializedName("attendance_record")
    val attendanceRecord: String = "",
    @SerializedName("attendance_record_comment")
    val attendanceRecordComment: String = "",
    @SerializedName("bdo_tl_slots")
    val bdoTlSlots: String = "",
    @SerializedName("bdo_tl_slots_comment")
    val bdoTlSlotsComment: String = "",
    @SerializedName("branch_service")
    val branchService: String = "",
    @SerializedName("branch_service_comment")
    val branchServiceComment: String = "",
    @SerializedName("checking_smp_of_bdo")
    val checkingSmpOfBdo: String = "",
    @SerializedName("checking_smp_of_bdo_comment")
    val checkingSmpOfBdoComment: String = "",
    @SerializedName("cross_sell_product")
    val crossSellProduct: String = "",
    @SerializedName("cross_sell_product_comment")
    val crossSellProductComment: String = "",
    @SerializedName("cross_selling")
    val crossSelling: String = "",
    @SerializedName("cross_selling_comment")
    val crossSellingComment: String = "",
    @SerializedName("daily_output_report")
    val dailyOutputReport: String = "",
    @SerializedName("daily_output_report_comment")
    val dailyOutputReportComment: String = "",
    @SerializedName("dicipline_code_conduct")
    val diciplineCodeConduct: String = "",
    @SerializedName("dicipline_code_conduct_comment")
    val diciplineCodeConductComment: String = "",
    @SerializedName("dress_code")
    val dressCode: String = "",
    @SerializedName("dress_code_comment")
    val dressCodeComment: String = "",
    @SerializedName("employee_card")
    val employeeCard: String = "",
    @SerializedName("employee_card_comment")
    val employeeCardComment: String = "",
    @SerializedName("floor_management")
    val floorManagement: String = "",
    @SerializedName("floor_management_comment")
    val floorManagementComment: String = "",
    @SerializedName("inspection_call_report")
    val inspectionCallReport: String = "",
    @SerializedName("inspection_call_report_comment")
    val inspectionCallReportComment: String = "",
    @SerializedName("inspection_ctc_report")
    val inspectionCtcReport: String = "",
    @SerializedName("inspection_ctc_report_comment")
    val inspectionCtcReportComment: String = "",
    @SerializedName("market_visit_bm")
    val marketVisitBm: String = "",
    @SerializedName("market_visit_bm_comment")
    val marketVisitBmComment: String = "",
    @SerializedName("market_visit_tl")
    val marketVisitTl: String = "",
    @SerializedName("market_visit_tl_comment")
    val marketVisitTlComment: String = "",
    @SerializedName("morning_huddle")
    val morningHuddle: String = "",
    @SerializedName("morning_huddle_comment")
    val morningHuddleComment: String = "",
    @SerializedName("pam_score_card")
    val pamScoreCard: String = "",
    @SerializedName("pam_score_card_comment")
    val pamScoreCardComment: String = "",
    @SerializedName("product_knowledge")
    val productKnowledge: String = "",
    @SerializedName("product_knowledge_comment")
    val productKnowledgeComment: String = "",
    @SerializedName("review_of_bdo")
    val reviewOfBdo: String = "",
    @SerializedName("review_of_bdo_comment")
    val reviewOfBdoComment: String = "",
    @SerializedName("shariah_knowledge")
    val shariahKnowledge: String = "",
    @SerializedName("shariah_knowledge_comment")
    val shariahKnowledgeComment: String = "",
    @SerializedName("smp_of_bdos")
    val smpOfBdos: String = "",
    @SerializedName("smp_of_bdos_comment")
    val smpOfBdosComment: String = "",
    @SerializedName("tail_management")
    val tailManagement: String = "",
    @SerializedName("tail_management_comment")
    val tailManagementComment: String = ""
)