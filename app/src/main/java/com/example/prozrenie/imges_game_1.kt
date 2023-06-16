package com.example.prozrenie

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class imges_game_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imges)
        supportActionBar?.hide()


    }
    fun Clicked(view: View) {
             var x = view.tag.toString()
             var i: Intent = Intent(this, imges_game_2::class.java)
             i.putExtra("IMG", "g$x");
             startActivity(i)
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
