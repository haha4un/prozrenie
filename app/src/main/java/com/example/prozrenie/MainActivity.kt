package com.example.prozrenie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide();


        var create_card = findViewById<Button>(R.id.create_card)
        create_card.setOnClickListener{
            val intent = Intent(this, Create_Card::class.java)
            startActivity(intent)
        }

        var view_card = findViewById<Button>(R.id.view_card)
        view_card.setOnClickListener{
            val intent = Intent(this, View_Card::class.java)
            startActivity(intent)
        }

        var info_btn = findViewById<ImageView>(R.id.inf_btn)
        info_btn.setOnClickListener {
            val intent = Intent(this, info::class.java)
            startActivity(intent)
        }

        var drawing_game = findViewById<LinearLayout>(R.id.drawing_game)
        drawing_game.setOnClickListener {
            val intent = Intent(this, paint_game::class.java)
            startActivity(intent)
        }

        var imgs_game = findViewById<LinearLayout>(R.id.imgs_game)
        imgs_game.setOnClickListener {
            val intent = Intent(this, imges_game_1::class.java)
            startActivity(intent)
        }

        var relax = findViewById<LinearLayout>(R.id.relax_game)
        relax.setOnClickListener {
            val intent = Intent(this, relax_game::class.java)
            startActivity(intent)
        }
    }
}