package com.example.yoho.presentation.screens.authentication.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoho.common.util.Resource
import com.example.yoho.data.remote.model.AuthUser
import com.example.yoho.domain.usecase.AuthenticationUseCase
import com.example.yoho.presentation.screens.authentication.util.AuthModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase
): ViewModel() {

    private val _loginResponse = mutableStateOf(AuthModel())
    val loginResponse: State<AuthModel> = _loginResponse

    private val _registerResponse = mutableStateOf(AuthModel())
    val registerResponse: State<AuthModel> = _registerResponse
    
    private val _verifyOtpResponse = mutableStateOf(AuthModel())
    val verifyOtpResponse: State<AuthModel> = _verifyOtpResponse
    
    private val _otpResponse = mutableStateOf(AuthModel())
    val otpResponse: State<AuthModel> = _otpResponse

    private val _emailOtpResponse = mutableStateOf(AuthModel())
    val emailOtpResponse: State<AuthModel> = _emailOtpResponse

    private val _emailResponse = mutableStateOf(AuthModel())
    val emailResponse: State<AuthModel> = _emailResponse


    fun loginUser(user: AuthUser) = viewModelScope.launch {
        authenticationUseCase.login(user = user).onEach {
            when(it) {

                is Resource.Loading -> {

                    _loginResponse.value = loginResponse.value.copy(
                        isLoading = true
                    )

                }
                is Resource.Success -> {

                    _loginResponse.value = loginResponse.value.copy(
                        isLoading = false,
                        data = it.data
                    )

                }
                is Resource.Error -> {

                    _loginResponse.value = loginResponse.value.copy(
                        isLoading = false
                    )

                }

            }
        }.launchIn(this)
    }

    fun registerUser(user: AuthUser) = viewModelScope.launch {
        authenticationUseCase.register(user = user).onEach {
            when(it) {

                is Resource.Loading -> {

                    _registerResponse.value = registerResponse.value.copy(
                        isLoading = true
                    )

                }
                is Resource.Success -> {

                    _registerResponse.value = registerResponse.value.copy(
                        isLoading = false,
                        data = it.data
                    )

                }
                is Resource.Error -> {

                    _registerResponse.value = registerResponse.value.copy(
                        isLoading = false,
                        message = it.message.toString()
                    )

                }

            }
        }.launchIn(this)
    }
    
    fun sendOtp(userId: String) = viewModelScope.launch {
        authenticationUseCase.sendOtp(userId = userId).onEach {
            when (it) {

                is Resource.Loading -> {

                    _otpResponse.value = otpResponse.value.copy(
                        isLoading = true
                    )

                }
                is Resource.Success -> {

                    _otpResponse.value = otpResponse.value.copy(
                        isLoading = false,
                        data = it.data
                    )

                }
                is Resource.Error -> {

                    _otpResponse.value = otpResponse.value.copy(
                        isLoading = false,
                        message = it.message.toString()
                    )

                }

            }
        }.launchIn(this)
    }
    
    fun verifyEmail(userId: String, otp: String) = viewModelScope.launch {
        authenticationUseCase.verifyEmail(userId = userId, otp = otp).onEach {
            when (it) {

                is Resource.Loading -> {

                    _emailOtpResponse.value = emailOtpResponse.value.copy(
                        isLoading = true
                    )

                }
                is Resource.Success -> {

                    _emailOtpResponse.value = emailOtpResponse.value.copy(
                        isLoading = false,
                        data = it.data
                    )

                }
                is Resource.Error -> {

                    _emailOtpResponse.value = emailOtpResponse.value.copy(
                        isLoading = false,
                        message = it.message.toString()
                    )

                }

            }
        }.launchIn(this)
    }

    fun sendEmail(userId: String) = viewModelScope.launch {
        authenticationUseCase.sendEmail(userId = userId).onEach {
            when (it) {

                is Resource.Loading -> {

                    _emailResponse.value = emailResponse.value.copy(
                        isLoading = true
                    )

                }
                is Resource.Success -> {

                    _emailResponse.value = emailResponse.value.copy(
                        isLoading = false,
                        data = it.data
                    )

                }
                is Resource.Error -> {

                    _emailResponse.value = emailResponse.value.copy(
                        isLoading = false,
                        message = it.message.toString()
                    )

                }

            }
        }.launchIn(this)
    }

    fun verifyOtp(userId: String, otp: String) = viewModelScope.launch {
        authenticationUseCase.verifyOtp(userId = userId, otp = otp).onEach {
            when (it) {

                is Resource.Loading -> {

                    _verifyOtpResponse.value = verifyOtpResponse.value.copy(
                        isLoading = true
                    )

                }
                is Resource.Success -> {

                    _verifyOtpResponse.value = verifyOtpResponse.value.copy(
                        isLoading = false,
                        data = it.data
                    )

                }
                is Resource.Error -> {

                    _verifyOtpResponse.value = verifyOtpResponse.value.copy(
                        isLoading = false,
                        message = it.message.toString()
                    )

                }

            }
        }.launchIn(this)
    }


}