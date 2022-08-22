package com.example.yoho.domain.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val _id: String,
    val email: String,
    val password: String,
    val fullName: String,
    val nickName: String,
    val dob: String,
    val number: String,
    val image: String?,
    val pinCode: String?,
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1
)
