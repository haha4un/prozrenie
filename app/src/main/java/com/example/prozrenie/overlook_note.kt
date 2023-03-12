package com.example.prozrenie

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class overlook_note : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overlook_note)

        var note = intent.getSerializableExtra("KEY_note")
        var id = intent.getSerializableExtra("KEY_id")
        var key = intent.getSerializableExtra("KEY")

        var n = findViewById<EditText>(R.id.note)
        var s = findViewById<Button>(R.id.save_editedNote)

        n.setText(note.toString())

        s.setOnClickListener {
            val database = Firebase.database
            val db = database.getReference("main/$id/lessons/$key")
            db.setValue(n.text.toString())

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}