package com.example.prozrenie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class View_Card : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_card)

        var scroll = findViewById<LinearLayout>(R.id.scroll)

        var Fullname = ""
        var data = ""
        var ids = ""

        var txt: TextView? = null

        var fb: fb = fb()
        val database = Firebase.database
        val myRef = database.getReference("")
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children)
                {
                    var fbe = i.getValue(fb().javaClass)
                    Fullname = fbe?.getFullName().toString()
                    data = fbe?.getData().toString()
                    ids = fbe?.getIds().toString()
                    txt?.text = "$Fullname \n$data \n$ids"
                    scroll.addView(txt)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}