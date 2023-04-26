package com.example.prozrenie

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
        var i = 0
        val database = Firebase.database
        database.getReference("main")

        save.setOnClickListener {
            i++
            when (i)
            {
                1-> {create.hint = "Имя"; name = create.text.toString();create.setText("")}
                2-> {create.hint = "Фамилия"; sur = create.text.toString();create.setText("")}
                3-> {create.hint = "Отчество"; middle = create.text.toString();create.setText("")}
                4-> {create.hint = "Дата Рождения"; date = showDataPic(create).toString();create.setText("")}
                5-> {save.setText("Сохранить?")}
//                6-> {i = 0
//                    var x = "${name.hashCode()}${sur.hashCode()}${middle.hashCode()}${date.hashCode()}"
//                    val myRef = database.getReference("main/$x")
//                    myRef.child("name").setValue(name)
//                    myRef.child("surname").setValue(sur)
//                    myRef.child("middlename").setValue(middle)
//                    myRef.child("date").setValue(date)
//                    myRef.child("id").setValue("${name.hashCode()}${sur.hashCode()}${middle.hashCode()}${date.hashCode()}${Random.nextInt(9)}${Random.nextInt(9)}") // in the and you must put in ${Random(1000).toString().hashCode()} for unique id, but it's for test
//                    myRef.child("lessons").child("0").setValue("Id: $x")
//                }
            }
        }
//        save.setOnClickListener {
//            if (date.toString().toCharArray().size != 10){
//                Toast.makeText(this, "Введите дату с 8 символами!", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener}
//            else
//                Toast.makeText(this, "Успешно!", Toast.LENGTH_SHORT).show()
//
//            var x = "${name.text.hashCode()}${sur.text.hashCode()}${middle.text.hashCode()}${date.text.hashCode()}"
//            val myRef = database.getReference("main/$x")
//            myRef.child("name").setValue("${name.text}")
//            myRef.child("surname").setValue("${sur.text}")
//            myRef.child("middlename").setValue("${middle.text}")
//            myRef.child("date").setValue("${date.text}")
//            myRef.child("id").setValue("${name.text.hashCode()}${sur.text.hashCode()}${middle.text.hashCode()}${date.text.hashCode()}${Random.nextInt(9)}${Random.nextInt(9)}") // in the and you must put in ${Random(1000).toString().hashCode()} for unique id, but it's for test
//            myRef.child("lessons").child("0").setValue("jeez! We are ready to go!")
//        }
//
//        var c = Calendar.getInstance()
//        var y = c.get(Calendar.YEAR)
//        var m = c.get(Calendar.MONTH)
//        var d = c.get(Calendar.DAY_OF_MONTH)
//
//        date.setOnClickListener {
//            var dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, mYear, mMonth, mDay->date.setText("${updateDates(mDay, mMonth+1, mYear)}")}, y,m,d)
//            dpd.show()
//        }
    }
    fun showDataPic(date: EditText)
    {
        val c = Calendar.getInstance()
        val y = c.get(Calendar.YEAR)
        val m = c.get(Calendar.MONTH)
        val d = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, mYear, mMonth, mDay->date.setText("${updateDates(mDay, mMonth+1, mYear)}")}, y,m,d)
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
}