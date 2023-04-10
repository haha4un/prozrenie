package com.example.prozrenie

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class View_Edit_Card : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_edit_card)
        var k = intent.getSerializableExtra("KEY")

        var scroll = findViewById<LinearLayout>(R.id.scroolForNotes)

        var n = findViewById<EditText>(R.id.Edit_Name)
        var s = findViewById<EditText>(R.id.Edit_Sur)
        var m = findViewById<EditText>(R.id.Edit_Mid)
        var d = findViewById<EditText>(R.id.Edit_Data)

        var save = findViewById<Button>(R.id.saveEdited)
        save.setOnClickListener {
            val database = Firebase.database
            val myRef = database.getReference("main/${upgradeID(k.toString())}")

            myRef.child("name").setValue("${n.text}")
            myRef.child("surname").setValue("${s.text}")
            myRef.child("middlename").setValue("${m.text}")
            myRef.child("date").setValue("${d.text}")
        }

        var fb: fb = fb()
        val database = Firebase.database
        val addingRef = database.getReference("main")
        addingRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children)
                {
                    var fbe = i.getValue(fb().javaClass)
                    if(fbe?.getIds().toString() == k.toString())
                    {
                        n.setText(fbe?.getNames())
                        s.setText(fbe?.getSurnames())
                        m.setText(fbe?.getMiddlenames())
                        d.setText(fbe?.getData())
                        return
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        var notes = database.getReference("main/${upgradeID(k.toString())}/lessons")
        notes.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children)
                {
                    add(i.value.toString(), scroll, upgradeID(k.toString()), i.value.toString(), i.key.toString())
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        var plus = findViewById<Button>(R.id.adding)
        plus.setOnClickListener {
            var intent = Intent(this, create_note::class.java)
            intent.putExtra("KEY_id", upgradeID(k.toString()))
            startActivity(intent)
        }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        var day = c.get(Calendar.DAY_OF_MONTH)
        val cr: Create_Card = Create_Card()
        d.setOnClickListener {
            var dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay->d.setText("${cr.updateDates(mDay, mMonth+1, mYear)}")}, year,month,day)
            dpd.show()
        }
    }
    fun add(res: String, scrool: LinearLayout, ids: String, note: String, keyForNote: String)
    {
        var noneContent: TextView = TextView(this)
        noneContent.text = "$res\n\n"
        scrool.addView(noneContent)

        noneContent.setOnClickListener{
            var intent = Intent(this, overlook_note::class.java)
            intent.putExtra("KEY_id", ids)
            intent.putExtra("KEY_note", note)
            intent.putExtra("KEY", keyForNote)
            startActivity(intent)
        }
    }
    fun upgradeID(s: String):String{
        return s.dropLast(2)}
}