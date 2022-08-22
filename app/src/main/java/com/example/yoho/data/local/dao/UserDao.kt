package com.example.yoho.data.local.dao

import androidx.room.*
import com.example.yoho.domain.model.local.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("DELETE FROM USER WHERE id=1")
    suspend fun deleteUser()

    @Query("SELECT * FROM USER WHERE id=1")
    suspend fun getUser(): User

    @Update
    suspend fun updateUser(user: User)

}