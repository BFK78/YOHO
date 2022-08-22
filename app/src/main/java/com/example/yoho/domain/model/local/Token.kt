package com.example.yoho.domain.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Token(
    val token: String,
    val userId: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1
)
