package com.example.prozrenie

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.Calendar
import kotlin.random.Random

class Create_Card : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)
        getSupportActionBar()?.hide();

        var save = findViewById<Button>(R.id.save)
        var create = findViewById<EditText>(R.id.create)
        var name = ""
        var sur = ""
        var middle = ""
        var date = ""
        var i = 1
        val database = Firebase.database
        database.getReference("main")

        save.setOnClickListener {
            if (create.text.isNotBlank()) {
                when (i) {
                    1 -> {
                        name = create.text.toString(); Toast.makeText(
                            this,
                            "name: $name",
                            Toast.LENGTH_SHORT
                        ).show();create.setText("");create.hint = "Фамилия";
                    }

                    2 -> {
                        sur = create.text.toString(); Toast.makeText(
                            this,
                            "sur: $sur",
                            Toast.LENGTH_SHORT
                        ).show();create.setText("");create.hint = "Отчество";
                    }

                    3 -> {
                        middle = create.text.toString(); Toast.makeText(
                            this,
                            "mid: $middle",
                            Toast.LENGTH_SHORT
                        ).show();create.setText("");create.hint = "Дата рождения"; openDataPic(create);
                    }
                    4 -> {
                        date = create.text.toString();
                        Toast.makeText(
                            this,
                            "date: $date",
                            Toast.LENGTH_SHORT
                        ).show(); create.setVisibility(EditText.INVISIBLE);create.hint = "";save.text = "Сохранить?";
                    }
                    5 -> {
                        i = 0
                        var id = "${name.hashCode()}${sur.hashCode()}${middle.hashCode()}${date.hashCode()}${Random.nextInt(9)}${Random.nextInt(9)}"
                        var x = "${name.hashCode()}${sur.hashCode()}${middle.hashCode()}${date.hashCode()}"
                        val myRef = database.getReference("main/$x")
                        myRef.child("name").setValue(name)
                        myRef.child("surname").setValue(sur)
                        myRef.child("middlename").setValue(middle)
                        myRef.child("date").setValue(date)
                        myRef.child("id").setValue(id) // in the and you must put in ${Random(1000).toString().hashCode()} for unique id, but it's for test
                        myRef.child("lessons").child("0").setValue("Id: $id")
                        Toast.makeText(this, "Успешно!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                }
                i++
            }
            else
            {
                Toast.makeText(
                    this,
                    "Поле не должно быть пустым!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
        }
    }
    fun openDataPic(view_text: EditText)
    {
        val c = Calendar.getInstance()
        val y = c.get(Calendar.YEAR)
        val m = c.get(Calendar.MONTH)
        val d = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, mYear, mMonth, mDay->
            view_text.setText(updateDates(mDay, mMonth+1, mYear));}, y,m,d)
        dpd.show()
    }
    fun updateDates(d: Int, m: Int, y: Int):String
    {
        var str =""
        if (d<10)
            str += "0$d."
        else
            str+="$d."
        if(m<10)
            str+= "0$m.$y"
        else
            str+= "$m.$y"
        return str
    }
    /*
    when (i)
            {
                1 -> {
                    create.hint = "Имя"; name = create.text.toString();
                    if (name == "")
                    {  Toast.makeText(this, "Поле не должно быть пустым!", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener}
                    else{
                    Toast.makeText(this, "name: $name", Toast.LENGTH_SHORT).show(); create.setText("");create.hint = "Фамилия";i++;
                    }
                }
                2-> {sur = create.text.toString();
                    if (sur == "")
                    {  Toast.makeText(this, "Поле не должно быть пустым!", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener}
                    else{
                    Toast.makeText(this, "sur: $sur", Toast.LENGTH_SHORT).show(); create.setText("");create.hint = "Отчество";  i++}}
                3-> {middle = create.text.toString();
                    if (middle == "")
                    {  Toast.makeText(this, "Поле не должно быть пустым!", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener}
                    else{
                    Toast.makeText(this, "middle: $middle", Toast.LENGTH_SHORT).show();i++;create.setText("");create.hint = "Нажмите на кнопку еще раз" }}
                4-> {
                    create.setText("");create.hint = "Дата Рождения";
                    val c = Calendar.getInstance()
                    val y = c.get(Calendar.YEAR)
                    val m = c.get(Calendar.MONTH)
                    val d = c.get(Calendar.DAY_OF_MONTH)
                    val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, mYear, mMonth, mDay->date=
                        updateDates(mDay, mMonth+1, mYear);create.setText(date);}, y,m,d)
                    dpd.show()
                    i++
                }
                5-> {create.hint = "";save.text = "Сохранить?"; create.setText(""); i++}
                6-> {i = 0
                    var x = "${name.hashCode()}${sur.hashCode()}${middle.hashCode()}${date.hashCode()}"
                    val myRef = database.getReference("main/$x")
                    myRef.child("name").setValue(name)
                    myRef.child("surname").setValue(sur)
                    myRef.child("middlename").setValue(middle)
                    myRef.child("date").setValue(date)
                    myRef.child("id").setValue("${name.hashCode()}${sur.hashCode()}${middle.hashCode()}${date.hashCode()}${Random.nextInt(9)}${Random.nextInt(9)}") // in the and you must put in ${Random(1000).toString().hashCode()} for unique id, but it's for test
                    myRef.child("lessons").child("0").setValue("Id: $x")
                    Toast.makeText(this, "Успешно!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
    */
}