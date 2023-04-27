package com.example.prozrenie

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        getSupportActionBar()?.hide();

        if (!isOnline(this))
        {
            Toast.makeText(this, "Нет соеденения с сетью!", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({
                val intent = Intent(this, Splashscreen::class.java)
                startActivity(intent)
                finish()
            }, 2000)
        }

        Handler().postDelayed({
            val intent = Intent(this, MainActivity ::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }
    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return if (netInfo != null && netInfo.isConnectedOrConnecting) {
            true
        } else false
    }
}