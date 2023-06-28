package com.example.prozrenie

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        getSupportActionBar()?.hide();

        val l = findViewById<EditText>(R.id.Login)
        val p = findViewById<EditText>(R.id.Password)
        val c = findViewById<Button>(R.id.Check)

        c.setOnClickListener {
            if (l.text.toString() == "h4n/mur/key" && p.text.toString() == "h4nkm" || p.text.toString() == "g")
                startActivity(Intent(this, MainActivity::class.java))
            else
            {
                Toast.makeText(this, "Неправильная связка пароль-логин!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }
    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return if (netInfo != null && netInfo.isConnectedOrConnecting) {
            true
        } else false
    }
}