package com.example.prozrenie

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
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

        var game_pict = findViewById<Button>(R.id.game_pict)
        game_pict.setOnClickListener {
            var intent = Intent(this, imges::class.java)
            startActivity(intent)
        }
        var game_paint = findViewById<Button>(R.id.game_paint)
        game_paint.setOnClickListener {
            var intent = Intent(this, paint::class.java)
            startActivity(intent)
        }
        var game_relax = findViewById<Button>(R.id.game_relax)
        game_relax.setOnClickListener {
            var intent = Intent(this, relax_game::class.java)
            startActivity(intent)
        }
    }
}