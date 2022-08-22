package com.example.yoho.presentation.screens.profile.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yoho.common.util.Resource
import com.example.yoho.domain.model.local.User
import com.example.yoho.domain.usecase.GetTokenUseCase
import com.example.yoho.domain.usecase.ProfileUseCase
import com.example.yoho.presentation.screens.authentication.util.AuthModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
  private val profileUseCase: ProfileUseCase,
  private val getTokenUseCase: GetTokenUseCase,
): ViewModel() {

    private val _profileUpdateState = mutableStateOf(AuthModel())
    val profileUpdateState: State<AuthModel> = _profileUpdateState

    private val _createPinState = mutableStateOf(AuthModel())
    val createPinState: State<AuthModel> = _createPinState

    private val _userState = mutableStateOf(AuthModel())
    val userState: State<AuthModel> = _userState

    private val _passwordState = mutableStateOf(AuthModel())
    val passwordState: State<AuthModel> = _passwordState

    private val _tokenState = mutableStateOf("")
    val tokenState: State<String> = _tokenState

    init {
        getToken()
    }

    private fun getToken() = viewModelScope.launch {
        _tokenState.value = getTokenUseCase().token
    }

    fun updateProfile(user: User) = viewModelScope.launch {
        profileUseCase.updateProfile(user = user).onEach {

            when(it) {

                is Resource.Loading -> {

                    _profileUpdateState.value = profileUpdateState.value.copy(
                        isLoading = true
                    )

                }
                is Resource.Success -> {

                    _profileUpdateState.value = profileUpdateState.value.copy(
                        isLoading = false,
                        data = it.data
                    )

                }
                is Resource.Error -> {

                    _profileUpdateState.value = profileUpdateState.value.copy(
                        isLoading = false,
                        message = it.message.toString()
                    )
                    
                }
            }
        }.launchIn(this)
    }
    
    fun createPin(userId: String, pin: String) = viewModelScope.launch { 
        
        profileUseCase.createPin(
            userId = userId,
            pin = pin
        ).onEach {

            when(it) {

                is Resource.Loading -> {

                    _createPinState.value = createPinState.value.copy(
                        isLoading = true
                    )

                }
                is Resource.Success -> {

                    _createPinState.value = createPinState.value.copy(
                        isLoading = false,
                        data = it.data
                    )

                }
                is Resource.Error -> {

                    _createPinState.value = createPinState.value.copy(
                        isLoading = false,
                        message = it.message.toString()
                    )

                }

            }
            
        }.launchIn(this)
        
    }


    fun getUser(email: String) = viewModelScope.launch {

        profileUseCase.getUser(
            email = email
        ).onEach {
            when(it) {

                is Resource.Loading -> {

                    _userState.value = userState.value.copy(
                        isLoading = true
                    )

                }
                is Resource.Success -> {

                    _userState.value = userState.value.copy(
                        isLoading = false,
                        data = it.data
                    )

                }
                is Resource.Error -> {

                    _userState.value = userState.value.copy(
                        isLoading = false,
                        message = it.message.toString()
                    )

                }
            }
        }.launchIn(this)

    }

    fun updatePassword(userId: String, password: String) = viewModelScope.launch {

        getToken()
        profileUseCase.updatePassword(
            token = tokenState.value,
            userId = userId,
            password = password
        ).onEach {

            when(it) {

                is Resource.Loading -> {

                    _passwordState.value = passwordState.value.copy(
                        isLoading = true
                    )

                }
                is Resource.Success -> {

                    _passwordState.value = passwordState.value.copy(
                        isLoading = false,
                        data = it.data
                    )

                }
                is Resource.Error -> {

                    _passwordState.value = passwordState.value.copy(
                        isLoading = false,
                        message = it.message.toString()
                    )

                }
            }
        }.launchIn(this)
    }

}