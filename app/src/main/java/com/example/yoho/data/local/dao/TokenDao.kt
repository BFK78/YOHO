package com.example.yoho.data.local.dao

import androidx.room.*
import com.example.yoho.domain.model.local.Token

@Dao
interface TokenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inertToken(token: Token)

    @Query("SELECT * FROM Token WHERE id=1")
    suspend fun getToken(): Token

    @Query("DELETE FROM TOKEN WHERE id=1")
    suspend fun deleteToken()

    @Update
    suspend fun updateToken(token: Token)


}