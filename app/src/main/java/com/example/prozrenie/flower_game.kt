package com.example.prozrenie

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.get
import kotlin.random.Random

class flower_game : AppCompatActivity() {

    var RAN = 0
    var RAN_POS = 0
    var RAN_HEX_C = 0
    var RAN_HEX = 1
    var cl: LinearLayout ?= null
    var sc: TextView ?= null
    var sci: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_flower_game)

        cl = findViewById(R.id.common_lay)
        sc = findViewById(R.id.scores_flower)

        RAN_HEX_C = Random.nextInt(0,4)
        RAN_HEX = Random.nextInt(1,4)

        newgame()
    }
    fun game(v: View)
    {
        val t = v as FrameLayout
        val txt = t.getChildAt(1) as TextView
       if (txt.text == "$RAN")
       {
           ++sci
           sc?.text = sci.toString()
           RAN_HEX_C = Random.nextInt(0,4)
           RAN_HEX = Random.nextInt(1,4)
           list = emptyList()
           newgame()
       }
        else
           Toast.makeText(this, "Неправильно", Toast.LENGTH_SHORT).show()
    }

    var list: List<Int> = emptyList()
    fun newgame()
    {
        RAN = Random.nextInt(0,10)
        list += RAN
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

                    change_hexes(fr.get(0) as ImageView, RAN_HEX_C)
                    continue
                }
                else if (fr.tag.toString() == "$RAN_POS")
                {
                    val t = fr.get(1) as TextView
                    t.text = RAN.toString()

                    change_hexes(fr.get(0) as ImageView, RAN_HEX)

                    continue
                }

                val t = fr.get(1) as TextView

                val x = checktocol(list, Random.nextInt(1, 10))
                change_hexes(fr.get(0) as ImageView, RAN_HEX)

                t.text = x.toString()
            }
        }
    }
    fun change_hexes(v: ImageView, c: Int)
    { v.setImageResource(resources.getIdentifier("hexagon_$c", "drawable", packageName)) }

    fun checktocol(l: List<Int>, n: Int) : Int
    {
        var x = n
        if (l.contains(n))
        {
            for (i in 0..10)
            {
                if (l.contains(i))
                    continue
                else
                {
                    list += i
                    return i
                }
            }
        }
        else
        {
            list = list + x
            return x
        }
        return x
    }
    fun question_btn_flower(view: View) {
        var dialog = Dialog(this)
        dialog.setContentView(R.layout.questtext)
        var txt = dialog.findViewById<TextView>(R.id.questtxt)
        txt.setText(R.string.howto_flower)
        var ok = dialog.findViewById<Button>(R.id.offthedial)
        ok.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
}