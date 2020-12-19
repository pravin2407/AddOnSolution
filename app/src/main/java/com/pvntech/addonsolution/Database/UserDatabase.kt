package com.pvntech.addonsolution.Database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pvntech.addonsolution.Dao.UserDao
import com.pvntech.addonsolution.model.User

@Database(entities = [User::class], version = 2)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao?

    companion object{

        @Volatile
        private var INSTANCE : UserDatabase ? = null

        fun getInstance(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    UserDatabase::class.java,
                    "userDatabase")
                    .addCallback(callback)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private var callback: Callback = object : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsyn(INSTANCE!!)
            }
        }

        class PopulateDbAsyn(userDatabase: UserDatabase) :
            AsyncTask<Void?, Void?, Void?>() {
            private val userDao: UserDao
            init {
                userDao = userDatabase.userDao()!!
            }

            override fun doInBackground(vararg p0: Void?): Void? {
                userDao.getDeleterUser()
                return null
            }
        }
    }
}