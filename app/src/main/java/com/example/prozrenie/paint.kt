package com.example.prozrenie

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Environment
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.drawingapp.DrawingView
import com.example.drawingapp.DrawingView.Companion.colorList
import com.example.drawingapp.DrawingView.Companion.currentBrush
import com.example.drawingapp.DrawingView.Companion.pathList
import com.example.drawingapp.DrawingView.Companion.sizeList
import java.io.File
import java.io.FileOutputStream
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
            sizeList.clear()
            path.reset()
            s= 10f
            x= 10f
            size.progress = 10
            path = Path()
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
//    fun saved(bitmap: Bitmap)
//    {
//        var root: String = Environment.getExternalStorageDirectory().absolutePath
//        var file: File = File(root+"/Download")
//        var filename = "h4n-mur-key.jpg"
//        var myfile = File(file,filename)
//        if (myfile.exists())
//        {
//            //than do nothing
//        }
//        try {
//            var fileOutputStream: FileOutputStream = FileOutputStream(myfile)
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,fileOutputStream)
//            fileOutputStream.flush()
//            fileOutputStream.close()
//        }
//        catch (e: Exception)
//        {
//            Toast.makeText(this, "$e", Toast.LENGTH_LONG).show()
//        }
//
//    }
}