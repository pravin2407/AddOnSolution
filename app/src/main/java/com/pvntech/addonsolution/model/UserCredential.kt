package com.pvntech.addonsolution.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.pvntech.addonsolution.BR


class UserCredential : BaseObservable() {
    private var name: String? = null
    private var pass: String? = null

    @Bindable
    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
        notifyPropertyChanged(BR.name)
        notifyPropertyChanged(BR.error)
    }

    @Bindable
    fun getPass(): String? {
        return pass
    }

    fun setPass(pass: String?) {
        this.pass = pass
        notifyPropertyChanged(BR.pass)
        notifyPropertyChanged(BR.passError)
    }

    @get:Bindable
    val error: String?
        get() = if (name == null || name!!.length < 3) {
            "Too short!"
        } else {
            null
        }

    @get:Bindable
    val passError: String?
        get() = if (pass == null || pass!!.length < 6) {
            "Enter valid password!"
        } else {
            null
        }

    fun checkValidData(): Int?
    {
        if (name ==  null || pass == null){
            return 1
        }
        else if(name == null || name.toString().trim().length < 3 ){
            return 2
        }
        else if(pass == null || pass.toString().trim().length < 6 ){
            return 3
        }
        else{
            return 4
        }
        return 0
    }
}