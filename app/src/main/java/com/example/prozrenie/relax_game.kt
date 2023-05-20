package com.example.prozrenie

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class relax_game : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relax_game)
        supportActionBar?.hide()

        var v = findViewById<VideoView>(R.id.videoView)

        val vsrc =
            "https://drive.google.com/file/d/1NiLtsdwlCdPMZg6B8sue7OCj8RcCDj0_/view?usp=sharing"

        v.setVideoPath(downloadUrl(vsrc));
        v.setMediaController(MediaController(this));
        v.requestFocus(0);
        v.start();
    }

    @Throws(IOException::class)
    fun downloadUrl(myurl: String?): String? {
        var `is`: InputStream? = null
        return try {
            val url = URL(myurl)
            val conn =
                url.openConnection() as HttpURLConnection
            conn.readTimeout = 10000
            conn.connectTimeout = 15000
            conn.requestMethod = "GET"
            conn.doInput = true
            conn.connect()
            `is` = conn.inputStream
            readIt(`is`)
        } finally {
            `is`?.close()
        }
    }

    @Throws(IOException::class)
    fun readIt(stream: InputStream?): String? {
        val reader = BufferedReader(InputStreamReader(stream, "UTF-8"))
        val sb = StringBuilder()
        var line: String
        while (reader.readLine().also { line = it } != null) {
            if (line.contains("fmt_stream_map")) {
                sb.append(
                    """
                    $line
                    
                    """.trimIndent()
                )
                break
            }
        }
        reader.close()
        val result: String = decode(sb.toString())!!
        val url = result.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        return url[1]
    }
    fun decode(`in`: String): String? {
        var working = `in`
        var index: Int
        index = working.indexOf("\\u")
        while (index > -1) {
            val length = working.length
            if (index > (length - 6)) break
            val numStart = index + 2
            val numFinish = numStart + 4
            val substring = working.substring(numStart, numFinish)
            val number = substring.toInt(16)
            val stringStart = working.substring(0, index)
            val stringEnd = working.substring(numFinish)
            working = stringStart + number.toChar() + stringEnd
            index = working.indexOf("\\u")
        }
        return working
    }
}