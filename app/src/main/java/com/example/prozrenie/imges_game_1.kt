package com.example.prozrenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class imges_game_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imges)
        supportActionBar?.hide()

        val imgBtn1 = findViewById<ImageButton>(R.id.imageButton)
        val imgBtn2 = findViewById<ImageButton>(R.id.imageButton2)
        val imgBtn3= findViewById<ImageButton>(R.id.imageButton3)
        val intent = Intent(this, imges_game_2::class.java)

        imgBtn1.setOnClickListener() {
            intent.putExtra("IMG", "g1")
            startActivity(intent)
        }

        imgBtn2.setOnClickListener(){
            intent.putExtra("IMG", "g2")
            startActivity(intent)
        }

        imgBtn3.setOnClickListener(){
            intent.putExtra("IMG", "g3")
            startActivity(intent)
        }
    }
}