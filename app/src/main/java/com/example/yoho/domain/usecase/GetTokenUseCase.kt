package com.example.yoho.domain.usecase

import com.example.yoho.domain.model.local.Token
import com.example.yoho.domain.repository.TokenRepository

class GetTokenUseCase(
    private val repository: TokenRepository) {

    suspend operator fun invoke(): Token {
        return repository.getToken()
    }

}