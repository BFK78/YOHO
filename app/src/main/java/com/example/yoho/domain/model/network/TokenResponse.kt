package com.example.yoho.domain.model.network

import com.example.yoho.domain.model.local.User

data class TokenResponse(
    val userId: String?,
    val token: String?,
    val user: User?
)