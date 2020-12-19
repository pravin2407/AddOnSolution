package com.pvntech.addonsolution.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pvntech.addonsolution.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user")
    fun getUser() : LiveData<User>

    @Query("DELETE FROM user")
    fun getDeleterUser()
}