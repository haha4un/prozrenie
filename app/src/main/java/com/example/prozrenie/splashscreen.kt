package com.example.prozrenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
//        Handler().postDelayed({
//            val intent = Intent(this, splashscreen::class.java)
//            startActivity(intent)
//            finish()
//        }, 1500)
    }
}