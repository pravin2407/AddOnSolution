package com.pvntech.addonsolution.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.pvntech.addonsolution.Repository.UserRepository
import com.pvntech.addonsolution.model.LoginResult
import com.pvntech.addonsolution.model.User
import com.pvntech.addonsolution.model.UserCredential

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository: UserRepository

    private val getUser: LiveData<User>

    init {
        userRepository = UserRepository(application)
        getUser = userRepository.getUser()!!
    }

    fun insert(list: User) {
        userRepository.insert(list)
    }

    fun getUser(): LiveData<User> {
        return getUser
    }

}