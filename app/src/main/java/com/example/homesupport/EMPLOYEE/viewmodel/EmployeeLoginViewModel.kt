package com.example.homesupport.EMPLOYEE.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homesupport.EMPLOYEE.dto.EmployeeLoginRequest
import com.example.homesupport.EMPLOYEE.repository.EmployeeAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class EmployeeLoginViewModel @Inject constructor(
    private val repository: EmployeeAuthRepository
) : ViewModel() {

    var loginSuccess by mutableStateOf(false)
        private set

    var error by mutableStateOf("")
        private set

    var loading by mutableStateOf(false)
        private set

    var role by mutableStateOf("")
        private set
    var name by mutableStateOf("")
        private set

    var employeeId by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun updateEmployeeId(value: String) {
        employeeId = value
    }

    fun updatePassword(value: String) {
        password = value
    }

    fun login() {
        if(loading) return
        if (employeeId.isBlank() || password.isBlank()) {
            error = "Please fill all fields"
            return
        }

        viewModelScope.launch {
            loading = true
            error = ""

            try {
                val request = EmployeeLoginRequest(
                    emp_id = employeeId.trim(),
                    password = password
                )

                val response = repository.login(request)

                if (response.isSuccessful) {
                    val body = response.body()

                    if (body != null && body.token.isNotBlank()) {
                        role = body.role
                        loginSuccess = true
                    } else {
                        error = "Invalid server response"
                    }
                } else {
                    error = when (response.code()) {
                        401 -> "Invalid employee ID or password"
                        403 -> "Access denied"
                        else -> "Login failed. Please try again."
                    }
                }
            } catch (e: java.io.IOException) {
                error = "Unable to connect to server"
            } catch (e: Exception) {
                error = "Something went wrong"
            } finally {
                loading = false
            }
        }
    }

    fun resetLoginState() {
        loginSuccess = false
        role = ""
    }
}
