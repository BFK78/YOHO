package com.example.yoho.domain.repository

import com.example.yoho.domain.model.local.Token

interface TokenRepository {

    suspend fun getToken(): Token

}