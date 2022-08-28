package com.example.yoho.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yoho.data.local.dao.MeetingDao
import com.example.yoho.data.local.dao.TokenDao
import com.example.yoho.data.local.dao.UserDao
import com.example.yoho.domain.model.local.Meeting
import com.example.yoho.domain.model.local.Token
import com.example.yoho.domain.model.local.User

@Database(
    entities = [Token::class, User::class, Meeting::class],
    version = 1
)
abstract class YoHoDatabase: RoomDatabase() {

    abstract val tokenDao: TokenDao

    abstract val userDao: UserDao

    abstract val meetingDao: MeetingDao

}