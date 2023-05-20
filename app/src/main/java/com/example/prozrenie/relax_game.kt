package com.example.prozrenie

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class relax_game : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relax_game)
        supportActionBar?.hide()

        var x = "android.resource://$packageName/"

        var ch = findViewById<Button>(R.id.choose_vid)
        var v = findViewById<VideoView>(R.id.videoView)

        val vsrc =
            "https://drive.google.com/drive/folders/1D2E7KRjoFqBYVtt7XZ5JmnK_qbv7E0NU"
        v.setVideoURI(Uri.parse(vsrc));
        v.setVideoPath(vsrc);
        v.setMediaController(MediaController(this));
        v.requestFocus(0);
        v.start();
        ch.visibility = View.INVISIBLE
    }
}