package com.example.prozrenie

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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
    var HEIGHT = 8
    var LENGHT = 8
    var SIZES = 200
    var SPEED = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_cross_game)

        cl = findViewById(R.id.common_lay)
        sc = findViewById(R.id.scores_cross)

        val w = findViewById<EditText>(R.id.height_cross)
        w.setText(LENGHT.toString())
        val h = findViewById<EditText>(R.id.lenght_cross)
        h.setText(HEIGHT.toString())
        val s = findViewById<EditText>(R.id.sizes_cross)
        s.setText(SIZES.toString())
        val sp = findViewById<EditText>(R.id.speed_cross)
        sp.setText(SPEED.toString())
        val sv = findViewById<Button>(R.id.submit_data)
        sv.setOnClickListener {
            HEIGHT = h.text.toString().toInt()
            LENGHT = w.text.toString().toInt()
            SIZES = s.text.toString().toInt()
            SPEED = sp.text.toString().toInt()
        }
        RAN_POS = Random.nextInt(1, 20)
        starter()
    }

    fun starter() = GlobalScope.launch(Dispatchers.Main) {
        repeat(99999999){
            delay(SPEED.toLong())
            main(1,2)
            delay(SPEED.toLong())
            main(2,1)
            delay(SPEED.toLong())
            main(1,2)
            delay(SPEED.toLong())
            main(2,1)
        }
    }

    fun main(f: Byte, s: Byte) = runBlocking {
            makeTheLines()
            colorLines(f,s)
    }

    suspend fun makeTheLines()
    {
      if (cl?.childCount != 0) {
          cl?.removeAllViews()
      }

      for (i in 0..HEIGHT) {
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
            for (ij in 0..LENGHT-1)
            {
                count_el++
                var ni = ImageView(this)
                ni.setLayoutParams(ViewGroup.LayoutParams(SIZES, SIZES))

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
                        RAN_POS = Random.nextInt(1, 20)
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