package com.example.yoho.common.network

object EndPoints {

    const val BASE_URL = "http://10.0.2.2:5000/api/"
    const val LOGIN_URL = "users/login"
    const val REGISTRATION_URL ="users/register"
    const val UPDATE_PROFILE = "profile/profile-update"
    const val CREATE_PIN = "profile/create-pin"
    const val GET_OTP = "users/sent-otp"
    const val VERIFY_OTP ="users/verify-otp"
    const val GET_USER = "profile/get-user"
    const val GET_EMAIL = "users/email-verification"
    const val VERIFY_EMAIL = "users/verify-email"
    const val UPDATE_PASSWORD = "profile/update-password"
    const val SCHEDULE_MEETING = "meeting/schedule-meeting"
    const val SCHEDULED_MEETING = "meeting/scheduled-meeting"
    const val COMPLETED_MEETING = "meeting/completed-meeting"

    const val test = "test"

}