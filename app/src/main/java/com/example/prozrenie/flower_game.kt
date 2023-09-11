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
import com.example.prozrenie.R
import kotlin.random.Random

class flower_game : AppCompatActivity() {

    var RAN = 0
    var RAN_POS = 0
    var RAN_HEX_C = 0
    var RAN_HEX = 1

    var RAN_HEX_C_img = 0
    var RAN_HEX_img = 1

    var cl: LinearLayout ?= null
    var sc: TextView ?= null
    var sci: Int = 0
    var PREFIX = "animal_"
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
            RAN_HEX_C_img = Random.nextInt(0,46)

            RAN_HEX = Random.nextInt(1,4)
            RAN_HEX_img = Random.nextInt(1,46)
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
        list += RAN_HEX_C
        list += RAN_HEX_C_img
        list += RAN_HEX_img
        RAN_POS = Random.nextInt(1,7)
        for (i in 0 until cl!!.childCount)
        {
            val j = cl!!.getChildAt(i) as LinearLayout
            for (ij in 0 until j.childCount) {
                val fr = j.getChildAt(ij) as FrameLayout

                if (fr.tag.toString() == "0") {
                    val t = fr.get(1) as TextView
                    t.text = RAN.toString()

                    val im = fr.get(2) as ImageView


                    change_hexes(fr.get(0) as ImageView, RAN_HEX_C)
                    set_img(im, RAN_HEX_C_img)
                    continue
                } else if (fr.tag.toString() == "$RAN_POS") {
                    val t = fr.get(1) as TextView
                    t.text = RAN.toString()

                    val im = fr.get(2) as ImageView

                    set_img(im, RAN_HEX_C_img)
                    change_hexes(fr.get(0) as ImageView, RAN_HEX)

                    continue
                }

                val t = fr.get(1) as TextView
                val im = fr.get(2) as ImageView

                val x = checktocol(list, Random.nextInt(1, 10))
                val y = checktocol(list, Random.nextInt(1, 46))
                change_hexes(fr.get(0) as ImageView, RAN_HEX)

                set_img(im, y)
                t.text = x.toString()
            }
        }
    }
    fun change_hexes(v: ImageView, c: Int) {
        if (c > 4)
            v.setImageResource(resources.getIdentifier("hexagon_${Random.nextInt(4)}", "drawable", packageName))
        else
            v.setImageResource(resources.getIdentifier("hexagon_$c", "drawable", packageName))
    }
    fun set_img(v: ImageView, c: Int)
    { v.setImageResource(resources.getIdentifier("$PREFIX$c", "drawable", packageName)) }

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