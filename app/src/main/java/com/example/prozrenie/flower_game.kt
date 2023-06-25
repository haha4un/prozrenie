package com.example.prozrenie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.core.view.get
import kotlin.random.Random

class flower_game : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_flower_game)

        var cl = findViewById<LinearLayout>(R.id.common_lay)
        var fl = findViewById<LinearLayout>(R.id.first_lay)
        var sl = findViewById<LinearLayout>(R.id.second_lay)
        var tl = findViewById<LinearLayout>(R.id.third_lay)

    }
    var isst = false
    fun game(v: View)
    {
        if (v.tag == 0 && isst == false)
        {
            isst = true
            var ran = Random(10).toString().toInt()
        }
    }
}