package com.example.prozrenie


import android.app.Dialog
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Button
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
    fun License_btn(view: View)
    {
        var dialog = Dialog(this)
        dialog.setContentView(R.layout.questtext)
        var txt = dialog.findViewById<TextView>(R.id.questtxt)
        txt.setText(R.string.LICENSE)
        var ok = dialog.findViewById<Button>(R.id.offthedial)
        ok.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
}