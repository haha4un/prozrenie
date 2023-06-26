package com.example.prozrenie

import android.R.attr.bottom
import android.R.attr.left
import android.R.attr.right
import android.R.attr.top
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams


class imges_game_1 : AppCompatActivity() {

    val MAX_COUNT_IMGS = 13
    val PRE_FIX = "g"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imges)
        supportActionBar?.hide()

        var sc = findViewById<LinearLayout>(R.id.linearLayout)
        for (i in 1..MAX_COUNT_IMGS){
            var iv = ImageView(this)
            iv.adjustViewBounds = true

            iv.tag = i
            iv.setImageResource(resources.getIdentifier("$PRE_FIX$i", "drawable", packageName))


            iv.maxHeight = 300
            iv.maxWidth = 300

            sc.addView(iv)

            iv.setOnClickListener {
                val x = iv.tag.toString()
                val i: Intent = Intent(this, imges_game_2::class.java)
                i.putExtra("IMG", "$PRE_FIX$x")
                startActivity(i)
            }
            iv.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                setMargins(0,20,0,0)
            }
        }
    }
    fun question_btn_imgs(view: View) {
        var dialog = Dialog(this)
        dialog.setContentView(R.layout.questtext)
        var txt = dialog.findViewById<TextView>(R.id.questtxt)
        txt.setText(R.string.howto_img)
        var ok = dialog.findViewById<Button>(R.id.offthedial)
        ok.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
}
