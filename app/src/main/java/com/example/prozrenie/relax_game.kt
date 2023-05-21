package com.example.prozrenie

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class relax_game : AppCompatActivity() {

    private var x1 = 0f
    private var x2 = 0f

    var limitOfVideos = 3
    var i = 0
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relax_game)
        supportActionBar?.hide()

        var x = "android.resource://$packageName/"

        var ch = findViewById<Button>(R.id.choose_vid)
        var v = findViewById<VideoView>(R.id.videoView)

        var temp_for_name = ""
        var stopper = false
        ch.setOnTouchListener(
            object : View.OnTouchListener {
                override fun onTouch(view: View?, event: MotionEvent): Boolean {
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> x1 = event.x
                        MotionEvent.ACTION_UP -> {
                            x2 = event.x
                            val deltaX = x2 - x1
                            if (deltaX < 0) {
                                i++
                                stopper = false
                            } else if (deltaX > 0) {
                                i--
                                stopper = false
                            }
                            else if (deltaX == 0f)
                            {
                                if (!stopper){
                                    v.pause()
                                    stopper = true
                                }
                                else if (stopper)
                                    stopper = false
                            }
                        }
                    }
                    if (!stopper){
                       toNextVideo(v, x)
                    }
                    return false
                }
            })
    }
    fun toNextVideo(view: VideoView, txt: String)
    {
        var path = txt
        path += com.example.prozrenie.R.raw.v1
        var uri: Uri = Uri.parse(path)
        view.setVideoURI(uri)
        val m = MediaController(this)
        view.setMediaController(m)
        m.setAnchorView(view)
        view.start()
    }
}