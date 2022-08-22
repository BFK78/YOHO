package com.example.yoho.data.local.repository

import com.example.yoho.data.local.dao.TokenDao
import com.example.yoho.domain.model.local.Token
import com.example.yoho.domain.repository.TokenRepository

class TokenRepositoryImplementation(
    private val tokenDao: TokenDao
): TokenRepository {

    override suspend fun getToken(): Token {
        return tokenDao.getToken()
    }
}