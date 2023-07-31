package com.example.prozrenie

import android.app.Dialog
import android.graphics.drawable.GradientDrawable.Orientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.updateLayoutParams
import kotlin.random.Random

class cross_game : AppCompatActivity() {

    var cl: LinearLayout?= null
    var sc: TextView ?= null
    var sci: Int = 0
    var RAN_POS = 0

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
      if (cl?.childCount != 0) {
          cl?.removeAllViews()
      }

      for (i in 0..8) {
          var nl = LinearLayout(this)
          nl.setOrientation(LinearLayout.HORIZONTAL)
          cl?.addView(nl)
      }
        colorLines()
    }

    fun colorLines()
    {
        RAN_POS = Random.nextInt(2,48)
        var cont = 0
        for (i in 0 until cl!!.childCount)
        {
            val j = cl!!.getChildAt(i) as LinearLayout
            cont++
            for (ij in 0..8)
            {
                var ni = ImageView(this)
                ni.maxWidth = 25
                ni.maxHeight = 25
                if (cont % 2 == 0 && cont!= RAN_POS) {
                    ni.setImageResource(R.drawable.round_2)
                    ni.setOnClickListener {
                        sci--
                        sc?.text = sci.toString()
                        makeTheLines()
                    }
                }
                else if (cont == RAN_POS)
                {
                    ni.setImageResource(R.drawable.round_3)
                    ni.setOnClickListener {
                        sci++
                        Toast.makeText(this, "Congrats!", Toast.LENGTH_SHORT).show()
                        sc?.text = sci.toString()
                        cont = 0
                        makeTheLines()
                    }
                }
                else {
                    ni.setImageResource(R.drawable.round_1)
                    ni.setOnClickListener {
                        sci--
                        sc?.text = sci.toString()
                        makeTheLines()
                    }
                }
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