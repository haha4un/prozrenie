package com.example.prozrenie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.get
import kotlin.random.Random

class flower_game : AppCompatActivity() {

    var RAN = 0
    var RAN_POS = 0
    var cl: LinearLayout ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_flower_game)

        cl = findViewById(R.id.common_lay)

        RAN = Random.nextInt(0,10)
        RAN_POS = Random.nextInt(1,7)

//        val t: LinearLayout = cl.getChildAt(0) as LinearLayout
//        val g = t.getChildAt(0) as FrameLayout
//        val fr = g.get(1) as TextView
//        fr.text = "Success!"

        for (i in 0 until cl!!.childCount)
        {
            val j = cl!!.getChildAt(i) as LinearLayout
            for (ij in 0 until j.childCount)
            {
                val fr = j.getChildAt(ij) as FrameLayout

                if (fr.tag.toString() == "0")
                {
                    val t = fr.get(1) as TextView
                    t.text = RAN.toString()
                    continue
                }
                else if (fr.tag.toString() == "$RAN_POS")
                {
                    val t = fr.get(1) as TextView
                    t.text = RAN.toString()
                    continue
                }
                val t = fr.get(1) as TextView
                var x = Random.nextInt(0,10)
//                if (x == RAN)
//                    x =  Random.nextInt(0,10)
                t.text = x.toString()
            }
        }

    }
    fun game(v: View)
    {
        val t = v as FrameLayout
        val txt = t.getChildAt(1) as TextView
       if (txt.text == "$RAN")
       {
           Toast.makeText(this , "YOu wOn!", Toast.LENGTH_SHORT).show(); newgame()
       }
        else
           Toast.makeText(this, "YYY, Y're Lost!", Toast.LENGTH_SHORT).show()
    }

    fun newgame()
    {
        RAN = Random.nextInt(0,10)
        RAN_POS = Random.nextInt(1,7)
        for (i in 0 until cl!!.childCount)
        {
            val j = cl!!.getChildAt(i) as LinearLayout
            for (ij in 0 until j.childCount)
            {
                val fr = j.getChildAt(ij) as FrameLayout

                if (fr.tag.toString() == "0")
                {
                    val t = fr.get(1) as TextView
                    t.text = RAN.toString()
                    continue
                }
                else if (fr.tag.toString() == "$RAN_POS")
                {
                    val t = fr.get(1) as TextView
                    t.text = RAN.toString()
                    continue
                }
                val t = fr.get(1) as TextView
                var x = Random.nextInt(0,10)
//                if (x == RAN)
//                    x =  Random.nextInt(0,10)
                t.text = x.toString()
            }
        }

    }
}