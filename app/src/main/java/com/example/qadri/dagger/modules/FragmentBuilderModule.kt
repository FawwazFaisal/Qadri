package com.example.qadri.dagger.modules

import com.example.qadri.ui.fragment.BaseDockFragment
import com.example.qadri.ui.fragment.dashboard.BankDepositGraphFragment
import com.example.qadri.ui.fragment.dashboard.DashboardFragment
import com.example.qadri.ui.fragment.dashboard.VisitsGraphFragment
import com.example.qadri.ui.fragment.login.*
import com.example.qadri.ui.fragment.reports.aging.ReportAging
import com.example.qadri.ui.fragment.reports.bankDeposit.ReportBankDeposit
import com.example.qadri.ui.fragment.reports.complaint.ReportComplaint
import com.example.qadri.ui.fragment.reports.order.ReportOrder
import com.example.qadri.ui.fragment.reports.order.ReportOrderDetail
import com.example.qadri.ui.fragment.reports.recovery.ReportRecovery
import com.example.qadri.ui.fragment.reports.salesPlanReport.ReportSalesPlan
import com.example.qadri.ui.fragment.reports.visit.ReportVisit
import com.example.qadri.ui.fragment.reports.visit.VisitLogDetail
import com.example.qadri.ui.fragment.salesPlan.HostSalesPlan
import com.example.qadri.ui.fragment.salesPlan.SalesPlanTab
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 *  @author Abdullah Nagori
 */

@Module
interface
FragmentBuilderModule {

    @ContributesAndroidInjector
    fun contributeBaseFragment(): BaseDockFragment

    @ContributesAndroidInjector
    fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    fun contributeVerifyPwdReqFragment(): NewPasswordFragment

    @ContributesAndroidInjector
    fun contributeForgotPasswordFragment(): ForgotPasswordFragment

    @ContributesAndroidInjector
    fun contributeWelcomeFragment(): WelcomeFragment

    @ContributesAndroidInjector
    fun contributeDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector
    fun contributeChangePasswordFragment(): ChangePasswordFragment

    @ContributesAndroidInjector
    fun contributeVisitsGraphFragment(): VisitsGraphFragment

    @ContributesAndroidInjector
    fun contributeReportAging(): ReportAging

    @ContributesAndroidInjector
    fun contributeReportBankDeposit(): ReportBankDeposit

    @ContributesAndroidInjector
    fun contributeReportComplaint(): ReportComplaint

    @ContributesAndroidInjector
    fun contributeReportOrder(): ReportOrder

    @ContributesAndroidInjector
    fun contributeReportRecovery(): ReportRecovery

    @ContributesAndroidInjector
    fun contributeReportSalesPlan(): ReportSalesPlan

    @ContributesAndroidInjector
    fun contributeReportVisit(): ReportVisit

    @ContributesAndroidInjector
    fun contributeBankDepositGraphFragment(): BankDepositGraphFragment

    @ContributesAndroidInjector
    fun contributeVisitLogDetail(): VisitLogDetail

    @ContributesAndroidInjector
    fun contributeReportOrderDetail(): ReportOrderDetail

    @ContributesAndroidInjector
    fun contributeHostSalesPlan(): HostSalesPlan

    @ContributesAndroidInjector
    fun contributeSalesPlanTab(): SalesPlanTab
}