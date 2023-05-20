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
    fun paintClicked(view: View) {
         var x = view.tag.toString().toInt()
            when (x) {
                1 ->
            }

        }
    }
