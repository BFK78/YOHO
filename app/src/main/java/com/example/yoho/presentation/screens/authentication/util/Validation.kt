package com.example.yoho.presentation.screens.authentication.util

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import java.util.*

fun String.checkEmail(): Boolean {
    return !TextUtils.isEmpty(this) &&
            Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.checkPassword(): Boolean {
    return !TextUtils.isEmpty(this) &&
            this.contains(regex = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"))
}

fun String.checkNumber(): Boolean {
    return !TextUtils.isEmpty(this) &&
            Patterns.PHONE.matcher(this).matches() &&
            this.length == 10
}

fun String.checkUsername(): Boolean {
    return !TextUtils.isEmpty(this) &&
            this.length > 5
}

fun String.checkDOB(): Boolean {
    val currentDate = Date().toString().split(" ")
    val currentYear = currentDate[currentDate.size - 1].toInt()
    val date = this.split("/")
    val year = date[date.size - 1].toInt()
    return year < currentYear - 10
}