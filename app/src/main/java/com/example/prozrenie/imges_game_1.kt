package com.example.prozrenie

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class imges_game_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imges)
        supportActionBar?.hide()

        var sc = findViewById<LinearLayout>(R.id.linearLayout)
        for (i in 1..7){
            var iv = ImageView(this)
            iv.adjustViewBounds = true

            iv.tag = i
            iv.setImageResource(resources.getIdentifier("g$i", "drawable", packageName))

            iv.maxHeight = 300
            iv.maxWidth = 300

            sc.addView(iv)

            iv.setOnClickListener {
                var x = iv.tag.toString()
                var i: Intent = Intent(this, imges_game_2::class.java)
                i.putExtra("IMG", "g$x");
                startActivity(i)
            }
        }
    }
    fun setMargins (v: View, l: Int, t: Int, r: Int, b: Int) {
            if (v.getLayoutParams() is ViewGroup.MarginLayoutParams) {
                val p: ViewGroup.MarginLayoutParams = v.getLayoutParams() as ViewGroup.MarginLayoutParams;
                p.setMargins(l, t, r, b);
                v.requestLayout();
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
