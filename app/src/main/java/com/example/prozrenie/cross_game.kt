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
import com.example.prozrenie.R
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
    var MULHL = HEIGHT * LENGHT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_cross_game)

        cl = findViewById(R.id.common_lay)
        sc = findViewById(R.id.scores_cross)

        RAN_POS = randPos()
        starter()
    }

    fun starter() = GlobalScope.launch(Dispatchers.Main) {
        repeat(99999999) {
            delay(SPEED.toLong())
            main(1, 2)
            delay(SPEED.toLong())
            main(2, 1)
            delay(SPEED.toLong())
            main(1, 2)
            delay(SPEED.toLong())
            main(2, 1)
        }
    }

    fun main(f: Byte, s: Byte) = runBlocking {
        makeTheLines()
        if ((f == 1.toString().toByte()) and (s == 2.toString().toByte()))
            colorLines(f, s, 1)
        else if ((f == 2.toString().toByte()) and (s == 1.toString().toByte()))
            colorLines(f, s, 2)
    }

    suspend fun makeTheLines() {
        if (cl?.childCount != 0) {
            cl?.removeAllViews()
        }

        for (i in 0..HEIGHT) {
            var nl = LinearLayout(this)
            nl.setOrientation(LinearLayout.HORIZONTAL)
            cl?.addView(nl)
        }
    }

    fun randPos(): Int {
        while (true) {
            var t = Random.nextInt(2, MULHL - 2)
            if ((t % 2 != 0)) {
                return t
            }
        }
        return 0
    }

    suspend fun colorLines(f: Byte, s: Byte, turng: Byte) {
        var fs = "$PRE_FIX$f"
        var ss = "$PRE_FIX$s"
        var fd = "$PRE_FIX$3"
        var cont = 0
        var count_el = 0
        for (i in 0 until cl!!.childCount - 1) {
            val j = cl!!.getChildAt(i) as LinearLayout
            cont++
            for (ij in 0 until LENGHT) {
                var ni = ImageView(this)
                ni.setLayoutParams(ViewGroup.LayoutParams(SIZES, SIZES))

                // The ides is good, isnt it?)
                if ((count_el == RAN_POS) or (count_el == RAN_POS - LENGHT) or (count_el == RAN_POS + LENGHT) or (count_el == RAN_POS + 1) or (count_el == RAN_POS - 1)) {
                    when (turng) {
                        1.toString().toByte() -> ni.setImageResource(R.drawable.round_1);
                        2.toString().toByte() -> ni.setImageResource(R.drawable.round_2);
                    }
                    ni.setOnClickListener {
                        sci++
                        Toast.makeText(this, "Congrats!", Toast.LENGTH_SHORT).show()
                        sc?.text = sci.toString()
                        cont = 0
                        RAN_POS = randPos()
                        main(f, s)
                        return@setOnClickListener
                    }
                } else if (cont % 2 == 0 && count_el != RAN_POS) {
                    ni.setImageResource(resources.getIdentifier(fs, "drawable", packageName))
                    ni.setOnClickListener {
                        sci--
                        sc?.text = sci.toString()
                        main(s, f)
                    }
                } else if (cont % 2 != 0 && count_el != RAN_POS) {
                    ni.setImageResource(resources.getIdentifier(ss, "drawable", packageName))
                    ni.setOnClickListener {
                        sci--
                        sc?.text = sci.toString()
                        main(s, f)
                    }
                }
                count_el++
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

    fun resize_btn_click(view: View){
        var dialog = Dialog(this)
        dialog.setContentView(R.layout.resizement)
        var height = dialog.findViewById<TextView>(R.id.height_cross)
        height.setText(HEIGHT.toString())
        var length = dialog.findViewById<TextView>(R.id.lenght_cross)
        length.setText(LENGHT.toString())
        var speed = dialog.findViewById<TextView>(R.id.speed_cross)
        speed.setText(SPEED.toString())
        var size = dialog.findViewById<TextView>(R.id.sizes_cross)
        size.setText(SIZES.toString())
        var ok = dialog.findViewById<Button>(R.id.submit_data)
        ok.setOnClickListener {
            if (HEIGHT <= 0 || LENGHT<=0||SIZES<=0||SPEED<=0){
                Toast.makeText(this, "Данные должны быть не нулевыми!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            HEIGHT = height.text.toString().toInt()
            LENGHT = length.text.toString().toInt()
            SIZES = size.text.toString().toInt()
            SPEED = speed.text.toString().toInt()
            MULHL =HEIGHT* LENGHT
            RAN_POS = randPos()
            dialog.dismiss()
        }
        dialog.show()
    }
}