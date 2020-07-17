package com.example.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        var splashThread=object :Thread(){
            override fun run() {
             sleep(2000)
                ContextCompat.startActivity(
                    this@SplashActivity,
                    Intent(this@SplashActivity, MainActivity::class.java), null
                )
                finish()

            }

        }
        splashThread.start()
    }

}