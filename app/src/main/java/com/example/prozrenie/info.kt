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

        val s = findViewById<TextView>(R.id.s_inf)
        s.movementMethod = LinkMovementMethod.getInstance()

        val m = findViewById<TextView>(R.id.m_inf)
        m.movementMethod = LinkMovementMethod.getInstance()

        val d = findViewById<TextView>(R.id.d_inf)
        d.movementMethod = LinkMovementMethod.getInstance()
    }
}