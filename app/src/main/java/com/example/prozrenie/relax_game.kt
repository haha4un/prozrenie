package com.example.prozrenie

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class relax_game : AppCompatActivity() {

    private var x1 = 0f
    private var x2 = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relax_game)
        supportActionBar?.hide()

        var i = 0
        var x = "android.resource://$packageName/"

//        var ch = findViewById<Button>(R.id.choose_vid)
        var v = findViewById<VideoView>(R.id.videoView)

//        ch.setOnClickListener {
//            i++
//            v.setVisibility(VideoView.VISIBLE)
//            var path = x
//            when (i) {
//                 1 -> path += R.raw.v1
//                 2-> path += R.raw.v2
//            }
//            var uri: Uri = Uri.parse(path)
//            v.setVideoURI(uri)
//            val m = MediaController(this)
//            v.setMediaController(m)
//            m.setAnchorView(v)
//            v.start()
//        }
        v.setOnTouchListener(
            object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent): Boolean {
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> x1 = event.x
                        MotionEvent.ACTION_UP -> {
                            x2 = event.x
                            val deltaX = x2 - x1
                            if (deltaX < 0) {
                                Toast.makeText(
                                    this@relax_game,
                                    "Right to Left swipe",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (deltaX > 0) {
                                Toast.makeText(
                                    this@relax_game,
                                    "Left to Right swipe",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    return false
                }
            })
//        videoView.setOnCompletionListener(OnCompletionListener {
//            // Video Playing is completed
//        })
    }
    fun create ():String
    {
        return ""
    }
}