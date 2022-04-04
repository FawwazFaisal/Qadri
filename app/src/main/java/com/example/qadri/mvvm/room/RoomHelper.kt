package com.example.qadri.mvvm.room
import com.example.qadri.mvvm.model.addLead.DynamicLeadsItem
import com.example.qadri.mvvm.model.checkin.CheckinModel
import com.example.qadri.mvvm.model.location.UserLocation
import com.example.qadri.mvvm.model.lov.CompanyLeadStatu
import com.example.qadri.mvvm.model.portfolio.portfolioList
import com.example.qadri.mvvm.model.previousVisits.GetPreviousVisit
import com.example.qadri.mvvm.repository.BaseRepository
import com.example.qadri.security.EncryptionKeyStoreImpl
import javax.inject.Inject
import kotlin.collections.ArrayList


class RoomHelper @Inject constructor(private val daoAccess: DAOAccess, private val ablDatabase: ABLDatabase) : BaseRepository() {

    val encryptionKeyStore = EncryptionKeyStoreImpl.instance

    fun insertLeadData(dynamicLeadsItem: ArrayList<DynamicLeadsItem>) {
        daoAccess.insertLeadData(dynamicLeadsItem)
    }

    fun deleteLeadData() {
        daoAccess.deleteLeadData()
    }

    fun getLeadsData(source: String): List<DynamicLeadsItem> {
       // return daoAccess.getLeadData(source)
        return encryptionKeyStore.decryptList(daoAccess.getLeadData(source)) as List<DynamicLeadsItem>
    }

    fun getLeadsStatus(): List<CompanyLeadStatu> {
        return daoAccess.getLeadStatus() as List<CompanyLeadStatu>
    }

    fun getPreviousVisit(lead_id: String): List<GetPreviousVisit> {
        return daoAccess.getPreviousVisit(lead_id) as List<GetPreviousVisit>
    }

    fun insertPreviousVisit(previousVisit: GetPreviousVisit) {
         daoAccess.insertPreviousVisit(previousVisit)
    }

    fun insertAddLead(dynamicLeadsItem: DynamicLeadsItem) {
        daoAccess.insertAddLead(dynamicLeadsItem)
     }

    fun insertCheckIn(checkIn: CheckinModel) {
        daoAccess.insertCheckIn(checkIn)
    }

    fun insertVisitCallData(checkIn: List<CheckinModel>) {
        daoAccess.insertVisiCallData(checkIn)
    }

    fun getUserLocation(): List<UserLocation> {
        return daoAccess.getUserLocation()
    }

    fun deleteCheckInData(){
         daoAccess.deleteCheckInData()
    }

    fun checkUnSyncLeadData(): List<DynamicLeadsItem> {
        return daoAccess.checkUnSyncedLeadData()
    }

    fun checkUnSyncCheckInData(): List<CheckinModel> {
        return daoAccess.checkUnSyncedCheckInData()
    }

    fun getCheckInCallData(visitType: String): List<CheckinModel> {
        return daoAccess.getCheckInCall(visitType)
    }

    fun getCheckInVisitData(visitType: String): List<CheckinModel> {
        return daoAccess.getCheckInVisit(visitType)
    }

//    fun insertDashboardCount(dashboardResponse: DashboardResponse) {
//         daoAccess.insertDashBoardCount(dashboardResponse)
//    }
//
//    fun deleteDashboardCount(){
//        daoAccess.deleteDashBoardCount()
//    }

    fun getVisitLogsCount(): String {
        return daoAccess.getVisitLogsCount()
    }

    fun getCallLogsCount(): String {
        return daoAccess.getCallLogsCount()
    }

    fun getFollowupCount(): String {
        return daoAccess.getFollowupCount()
    }

    fun getFollowupData(): List<DynamicLeadsItem> {
        return daoAccess.getFollowupData()
    }

    fun clearDB() {
        ablDatabase.clearAllTables()
    }

    fun insertPortfolio(portfolioList: List<portfolioList>) {
        daoAccess.insertPortfolio(portfolioList)
    }

    fun getPortfolio(customerType: String): List<portfolioList> {
        return daoAccess.getPortfolio(customerType)
    }

    fun deletePortfolioData() {
         daoAccess.deletePortfolioData()
    }

    fun getAllPortfolio(): List<portfolioList> {
        return daoAccess.getAllPortfolio()
    }
}