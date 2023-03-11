package com.example.prozrenie

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

class Create_Card : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)
        getSupportActionBar()?.hide();

        var save = findViewById<Button>(R.id.save)
        var name = findViewById<EditText>(R.id.Create_name)
        var sur = findViewById<EditText>(R.id.Create_surname)
        var middle = findViewById<EditText>(R.id.Create_middlename)
        var date = findViewById<EditText>(R.id.Date)
        val database = Firebase.database
        val myRef = database.getReference("main")

        save.setOnClickListener {
            var x = "${name.text.hashCode()}${sur.text.hashCode()}${middle.text.hashCode()}${date.text.hashCode()}"
            val myRef = database.getReference(x)
            myRef.child("name").setValue("${name.text}")
            myRef.child("surname").setValue("${sur.text}")
            myRef.child("middlename").setValue("${middle.text}")
            myRef.child("date of birth").setValue(date.text)
            myRef.child("lessons").child("first").setValue("first lesson, jeez!")
        }
    }
}