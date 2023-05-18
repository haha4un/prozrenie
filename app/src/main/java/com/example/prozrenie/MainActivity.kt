package com.example.prozrenie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var x1 = 0f
    private var x2 = 0f
    val MIN_DISTANCE = 150

    var limitOfGames = 3
    var i = 0
    @SuppressLint("MissingInflatedId", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide();


        var create_card = findViewById<ImageView>(R.id.create_card)
        create_card.setOnClickListener{
            val intent = Intent(this, Create_Card::class.java)
            startActivity(intent)
        }

        var view_card = findViewById<ImageView>(R.id.view_card)
        view_card.setOnClickListener{
            val intent = Intent(this, View_Card::class.java)
            startActivity(intent)
        }

        var info_btn = findViewById<ImageView>(R.id.inf_btn)
        info_btn.setOnClickListener {
            val intent = Intent(this, info::class.java)
            startActivity(intent)
        }

        var drawing_game = findViewById<ImageView>(R.id.drawing_game)
        drawing_game.setOnClickListener {
            val intent = Intent(this, paint_game::class.java)
            startActivity(intent)
        }

        var imgs_game = findViewById<ImageView>(R.id.imgs_game)
        imgs_game.setOnClickListener {
            val intent = Intent(this, imges_game_1::class.java)
            startActivity(intent)
        }

        var relax_game = findViewById<ImageView>(R.id.relax_game)
        relax_game.setOnClickListener {
            val intent = Intent(this, relax_game::class.java)
            startActivity(intent)
        }
    }
}