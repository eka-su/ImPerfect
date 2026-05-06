package com.example.imperfect.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.imperfect.core.database.entity.AppUserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: AppUserEntity): Long

    @Query("SELECT * FROM app_user WHERE email = :email LIMIT 1")
    suspend fun getByEmail(email: String): AppUserEntity?
}