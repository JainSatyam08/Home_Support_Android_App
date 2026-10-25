package com.example.homesupport.EMPLOYEE.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homesupport.EMPLOYEE.dto.DashBoardDTOs.DashBoardCountStatsResponse
import com.example.homesupport.EMPLOYEE.dto.DashBoardDTOs.DashBoardRecentServiceResponse
import com.example.homesupport.EMPLOYEE.dto.DashBoardDTOs.DashBoardServiceStatsResponse
import com.example.homesupport.EMPLOYEE.repository.DashBoardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch



@HiltViewModel
class DashBoardViewModel @Inject constructor(
    val dashBoardRepository: DashBoardRepository
): ViewModel() {
    var  serviceType by mutableStateOf<DashBoardServiceStatsResponse?>(null)
       private set

    var recentService by mutableStateOf<List<DashBoardRecentServiceResponse>>(emptyList())
        private set;

    var serviceStats by mutableStateOf<DashBoardCountStatsResponse?>(null)
        private set

    var errorMessageStats by mutableStateOf<String?>(null)
        private set

    var errorMessageServiceType by mutableStateOf<String?>(null)
        private set

    var errorMessageRecentService by mutableStateOf<String?>(null)
        private set

    var isLoadingStats by mutableStateOf(false)
        private set

    var isLoadingServiceType by mutableStateOf(false)
        private set
    var isLoadingRecentService by mutableStateOf(false)
        private set

    fun loadCountStats(days: Int=7){
        viewModelScope.launch {
            isLoadingStats=true;
            errorMessageStats=null;
            try{
                val response=dashBoardRepository.fetchDashboardStats(days)
                serviceStats = response

            }
            catch (e: Exception){
                errorMessageStats = e.message;
            }
            finally {
                isLoadingStats=false
            }
        }
    }
    fun loadServiceType(){
        viewModelScope.launch {
            isLoadingServiceType=true;
            errorMessageServiceType=null;
            try{
                val response=dashBoardRepository.fetchCategoryStats();
                serviceType = response
            }
            catch (e: Exception){
                errorMessageServiceType = e.message;
            }
            finally {
                isLoadingServiceType=false
            }
        }
    }
    fun loadRecentService(){
        viewModelScope.launch {
            isLoadingRecentService=true;
            errorMessageRecentService=null;
            try{
                val response= dashBoardRepository.fetchRecentComplaints();
                recentService = response
            }
            catch (e: Exception){
                errorMessageRecentService = e.message;

            }
            finally {
                isLoadingRecentService=false
            }
        }
    }

}