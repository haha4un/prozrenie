package com.example.prozrenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        getSupportActionBar()?.hide();
        Handler().postDelayed({
            val intent = Intent(this, MainActivity ::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }
}