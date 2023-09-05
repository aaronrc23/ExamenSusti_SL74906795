package com.example.consumoapirest

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sharedPreferences= getSharedPreferences(LoginActivity.SESSION_PREFERENCE, MODE_PRIVATE)

        Handler().postDelayed({
            val email: String=sharedPreferences.getString(LoginActivity.EMAIL,"")?:""

            val intent = if (email.isNotEmpty()){
                Intent(this, MainActivity::class.java)
            }else{
                Intent(this, LoginActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 5000)
    }
}