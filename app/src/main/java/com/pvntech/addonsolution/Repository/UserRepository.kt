package com.pvntech.addonsolution.Repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import com.pvntech.addonsolution.Dao.UserDao
import com.pvntech.addonsolution.Database.UserDatabase
import com.pvntech.addonsolution.model.User

class UserRepository(application: Application) {
    private var database: UserDatabase? = null
    private var getUser : LiveData<User>? = null

    init {
        database = UserDatabase.getInstance(application)
        getUser = database!!.userDao()!!.getUser()
    }

    fun insert(user: User) {
        Log.d("pvnmain", "addding")
        InsertAsynTask(database!!).execute(user)
    }

    fun getUser() : LiveData<User>? {
        return getUser
    }

    internal class InsertAsynTask(countryDatabase: UserDatabase) :
        AsyncTask<User?, Void?, Void?>() {
        private val userDao: UserDao
        init {
            userDao = countryDatabase.userDao()!!
        }

        override fun doInBackground(vararg user: User?): Void? {
            user.get(0)?.let { userDao.insert(it) }
            return null
        }
    }
}