package com.example.prozrenie

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
            if (date.text.toString().toCharArray().size != 8){
                Toast.makeText(this, "Введите дату с 8 символами!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener}
            else
                Toast.makeText(this, "Успешно!", Toast.LENGTH_SHORT).show()

            var x = "${name.text.hashCode()}${sur.text.hashCode()}${middle.text.hashCode()}${date.text.hashCode()}"
            val myRef = database.getReference("main/$x")
            myRef.child("name").setValue("${name.text}")
            myRef.child("surname").setValue("${sur.text}")
            myRef.child("middlename").setValue("${middle.text}")
            myRef.child("date").setValue("${date.text}")
            myRef.child("id").setValue("${name.text.hashCode()}${sur.text.hashCode()}${middle.text.hashCode()}${date.text.hashCode()}${Random(1000).toString().hashCode()}")
            myRef.child("lessons").child("first").setValue("first lesson, jeez!")
        }
    }
}