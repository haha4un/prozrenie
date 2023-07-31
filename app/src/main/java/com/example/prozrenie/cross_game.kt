package com.example.prozrenie

import android.app.Dialog
import android.graphics.drawable.GradientDrawable.Orientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.get
import kotlin.random.Random

class cross_game : AppCompatActivity() {

    var cl: LinearLayout?= null
    var sc: TextView ?= null
    var sci: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_cross_game)

        cl = findViewById(R.id.common_lay)
        sc = findViewById(R.id.scores_cross)


        makeTheLines()
    }

    fun makeTheLines()
    {
      for (i in 1..8) {
          var nl = LinearLayout(this)
          nl.setOrientation(LinearLayout.HORIZONTAL)
          cl?.addView(nl)
      }
        colorLines()
    }

    fun colorLines()
    {
        var cont = 0
        for (i in 0 until cl!!.childCount)
        {
            val j = cl!!.getChildAt(i) as LinearLayout
            cont++
            for (ij in 1..8)
            {
                var ni = ImageView(this)
                if (cont % 2 == 0)
                    ni.setImageResource(R.drawable.hexagon_4)
                else
                    ni.setImageResource(R.drawable.hexagon_3)
                ni.maxWidth = 100
                ni.maxHeight = 100
                j.addView(ni)
                cont++
            }
        }
    }


    fun question_btn_cross(view: View) {
        var dialog = Dialog(this)
        dialog.setContentView(R.layout.questtext)
        var txt = dialog.findViewById<TextView>(R.id.questtxt)
        txt.setText(R.string.howto_cross)
        var ok = dialog.findViewById<Button>(R.id.offthedial)
        ok.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
}