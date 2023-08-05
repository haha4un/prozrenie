package com.example.prozrenie

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class cross_game : AppCompatActivity() {

    var cl: LinearLayout? = null
    var sc: TextView? = null
    var sci: Int = 0
    var RAN_POS = 0
    val PRE_FIX = "round_"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_cross_game)

        cl = findViewById(R.id.common_lay)
        sc = findViewById(R.id.scores_cross)

        RAN_POS = Random.nextInt(10, 48)
        GlobalScope.launch (Dispatchers.Main) {
            repeat(100){
                delay(1000)
                main(1,2)
                delay(1000)
                main(2,1)
                delay(1000)
                main(1,2)
                delay(1000)
                main(2,1)
            }
        }
    }


    fun main(f: Byte, s: Byte) = runBlocking {
//        RAN_POS = Random.nextInt(10, 48)
            makeTheLines()
            colorLines(f,s)
    }

    suspend fun makeTheLines()
    {
      if (cl?.childCount != 0) {
          cl?.removeAllViews()
      }

      for (i in 0..8) {
          var nl = LinearLayout(this)
          nl.setOrientation(LinearLayout.HORIZONTAL)
          cl?.addView(nl)
      }
    }

    suspend fun colorLines(f: Byte, s: Byte)
    {
        var fs = "$PRE_FIX$f"
        var ss = "$PRE_FIX$s"
        var cont = 0
        var count_el = 0
        for (i in 0 until cl!!.childCount-1)
        {
            val j = cl!!.getChildAt(i) as LinearLayout
            cont++
            for (ij in 0..7)
            {
                count_el++
                var ni = ImageView(this)
                ni.setLayoutParams(ViewGroup.LayoutParams(200, 200))

                if (cont % 2 == 0 && cont!= RAN_POS) {
                    ni.setImageResource(resources.getIdentifier(fs, "drawable", packageName))
                    ni.setOnClickListener {
                        sci--
                        sc?.text = sci.toString()
                        main(s,f)
                    }
                }
                else if (cont-3 == RAN_POS-3)
                {
                    ni.setImageResource(R.drawable.round_3)
                    ni.setOnClickListener {
                        sci++
                        Toast.makeText(this, "Congrats!", Toast.LENGTH_SHORT).show()
                        sc?.text = sci.toString()
                        cont = 0
                        RAN_POS = Random.nextInt(10, 48)
                        main(s,f)
                        return@setOnClickListener
                    }
                }
                else if (cont % 2 != 0 && cont!= RAN_POS) {
                    ni.setImageResource(resources.getIdentifier(ss, "drawable", packageName))
                    ni.setOnClickListener {
                        sci--
                        sc?.text = sci.toString()
                        main(s,f)
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