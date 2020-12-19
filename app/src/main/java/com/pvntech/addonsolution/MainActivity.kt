package com.pvntech.addonsolution

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.pvntech.addonsolution.Network.Api
import com.pvntech.addonsolution.Repository.UserRepository
import com.pvntech.addonsolution.ViewModel.UserViewModel
import com.pvntech.addonsolution.databinding.ActivityMainBinding
import com.pvntech.addonsolution.model.LoginResult
import com.pvntech.addonsolution.model.User
import com.pvntech.addonsolution.model.UserCredential
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class MainActivity : AppCompatActivity() {

    private var userViewModel: UserViewModel ? = null
    private var userRepository: UserRepository ?= null
    private var userlist: ArrayList<UserViewModel>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userCredential = UserCredential()
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.userCredential = userCredential
        userRepository = UserRepository(application)
        userlist = ArrayList()

        userViewModel!!.getUser().observe(
                this,
                object : Observer<User?> {
                    override fun onChanged(userlist: User?) {
                        if (userlist!=null){
                            Toast.makeText(this@MainActivity, "Already login " +userlist.userId, Toast.LENGTH_SHORT).show()
                        }
                    }
                })

        loginButton.setOnClickListener {
            val code = binding.userCredential!!.checkValidData()
            if (code == 4) {
                makeLoginRequest(binding.userCredential!!)
            }
        }
    }

    private fun makeLoginRequest(userCredential: UserCredential) {

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://private-222d3-homework5.apiary-mock.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        var jsonObject = JSONObject()
        jsonObject.put("username", userCredential.getName())
        jsonObject.put("password", userCredential.getPass())


        val api = retrofit.create(Api::class.java)
        val call = api.loginUser(jsonObject)
        call.enqueue(object : Callback<LoginResult> {
            override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                if (response.isSuccessful()) {
                    if (response.body()!!.errorCode.equals("00")) {
                        Toast.makeText(this@MainActivity, "Success " +response.body()!!.user!!.userId, Toast.LENGTH_SHORT).show()
                        userRepository?.insert(response.body()!!.user!!)
                    }
                }

            }

            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                Log.d("pvnmain", "onFailure: " + t.message)
            }
        })
    }
}