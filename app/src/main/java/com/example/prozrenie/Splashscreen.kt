package com.example.prozrenie

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
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
//            Handler().postDelayed({
//                val intent = Intent(this, Splashscreen::class.java)
//                startActivity(intent)
//                finish()
//            }, 5000)
        }

//        var dialog = Dialog(this)
//        dialog.setContentView(R.layout.checker)
//        var pass = dialog.findViewById<EditText>(R.id.pass_checker)
//        var login = dialog.findViewById<EditText>(R.id.login_checker)
//        var ok = dialog.findViewById<Button>(R.id.check_checker)
//        ok.setOnClickListener {
//            if (login.text.toString() == "h4n/mur/key" && pass.text.toString() == "h4nkm" || pass.text.toString() == "g")
//            {
//                val intent = Intent(this, MainActivity ::class.java)
//                startActivity(intent)
//                finish()
//            }
//            else
//            {
//                Toast.makeText(this, "Не правильная связка пароль-логин!", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this,  Splashscreen::class.java)
//                startActivity(intent)
//                finish()
//            }
//        }
//        dialog.show()
    }
    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return if (netInfo != null && netInfo.isConnectedOrConnecting) {
            true
        } else false
    }
}