package com.pvntech.addonsolution.Network

import com.pvntech.addonsolution.model.LoginResult
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {

    @Headers("Content-Type: application/json")
    @POST("api/login")
    fun loginUser(@Body body: JSONObject): Call<LoginResult>
}