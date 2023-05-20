package com.example.prozrenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class imges_game_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imges)
        supportActionBar?.hide()


    }
    fun Clicked(view: View) {
         var x = view.tag.toString().toInt()
            when (x) {
                1 -> intent.putExtra("IMG", "g1")
                2 -> intent.putExtra("IMG", "g2")
                3 -> intent.putExtra("IMG", "g3")
                4 -> intent.putExtra("IMG", "g4")
                5 -> intent.putExtra("IMG", "g5")
                6 -> intent.putExtra("IMG", "g6")
                7 -> intent.putExtra("IMG", "g7")
            }
            startActivity(intent)
        }
    }
