package com.example.prozrenie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class create_note : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        var info = intent.getSerializableExtra("KEY_id")

        var s = findViewById<Button>(R.id.save_newNote)
        var n = findViewById<EditText>(R.id.Newnote)
        var i = 0

        val database = Firebase.database
        var ref = database.getReference("main/${info.toString()}/lessons")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                i = dataSnapshot.childrenCount.toInt()
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        s.setOnClickListener {
            if (n.text.isBlank()||n.text.isEmpty()) {
                Toast.makeText(this, "Заметка должна содержать больше символов!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                ref = database.getReference("main/${info.toString()}/lessons")
                ref.child("/$i").setValue(n.text.toString())
                var intent = Intent(this, View_Card::class.java)
                startActivity(intent)
                return@setOnClickListener
            }
        }
    }
}