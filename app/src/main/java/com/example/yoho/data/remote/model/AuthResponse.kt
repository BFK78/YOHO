package com.example.yoho.data.remote.model

import com.example.yoho.domain.model.network.TokenResponse

data class AuthResponse(
    val status: Boolean,
    val message: String,
    val data: TokenResponse?,
)
