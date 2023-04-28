package com.example.prozrenie


import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        supportActionBar?.hide()

        val t = findViewById<TextView>(R.id.inf)
        t.movementMethod = LinkMovementMethod.getInstance()
    }
}