package com.example.yoho.presentation.screens.authentication.util

import com.example.yoho.data.remote.model.AuthResponse

data class AuthModel(
    val isLoading: Boolean = false,
    val data: AuthResponse? = null,
    val message: String = ""
)
