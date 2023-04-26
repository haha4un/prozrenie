package com.example.prozrenie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class imges_game_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lookatimg)
        supportActionBar?.hide()

        var img: ImageView = findViewById(R.id.g)
        val mDrawableName = intent.getStringExtra("IMG")
        img.setBackgroundResource(resources.getIdentifier(mDrawableName, "drawable", packageName))
    }
}