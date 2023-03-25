package com.example.prozrenie

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.drawingapp.DrawingView
import com.example.drawingapp.DrawingView.Companion.colorList
import com.example.drawingapp.DrawingView.Companion.currentBrush
import com.example.drawingapp.DrawingView.Companion.pathList
import java.util.*


class paint : AppCompatActivity() {

    private var dv: DrawingView? = null
    companion object
    {
        var path = Path()
        var brushs = Paint()
        var s = 10f
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)
        dv = findViewById<DrawingView>(R.id.view);

        var size = findViewById<SeekBar>(R.id.brushsize)
        var x = 0f
        size.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                x = progress.toFloat()
                s = x
                dv?.setStrokeWidth(x)
                path = Path()
            }
        })
        var reset = findViewById<ImageButton>(R.id.new_btn)
        reset.setOnClickListener{
            pathList.clear()
            colorList.clear()
            path.reset()
        }
        var save = findViewById<ImageButton>(R.id.save_btn)
        save.setOnClickListener {
        }
        var bl = findViewById<ImageButton>(R.id.bl)
        var gr = findViewById<ImageButton>(R.id.gr)
        bl.setOnClickListener{currentColor(Color.BLACK)}
        gr.setOnClickListener{currentColor(Color.GREEN)}
    }
    private fun currentColor(color: Int)
    {
        currentBrush = color
        path = Path()
    }


}