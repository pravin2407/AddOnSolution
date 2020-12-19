package com.pvntech.addonsolution.model

import com.google.gson.annotations.SerializedName

class LoginResult {

    @SerializedName("errorCode")
    var errorCode: String? = null

    @SerializedName("errorMessage")
    var errorMessage: String? = null

    @SerializedName("user")
    var user: User ?= null
}