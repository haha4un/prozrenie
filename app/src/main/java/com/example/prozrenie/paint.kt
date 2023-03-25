package com.example.prozrenie

import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.drawingapp.DrawingView
import java.util.*


class paint : AppCompatActivity() {

    private var dv: DrawingView? = null
    companion object
    {
        var path = Path()
        var brushs = Paint()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)


        var size = findViewById<SeekBar>(R.id.brushsize)
        var x = 0f
        size.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                x = progress.toFloat()
//                dv?.edit("#0062ff", x )
            }
        })
        var reset = findViewById<ImageButton>(R.id.new_btn)
        reset.setOnClickListener{
//            dv?.reset()
        }
        var save = findViewById<ImageButton>(R.id.save_btn)
        save.setOnClickListener {
        }

        dv = findViewById<DrawingView>(R.id.view);
//        dv?.edit("#0062ff", x )
    }
}