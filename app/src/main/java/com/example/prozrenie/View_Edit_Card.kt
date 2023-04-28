package com.example.prozrenie

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text
import java.util.*

class View_Edit_Card : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_edit_card)

        var actB: ActionBar = getSupportActionBar()!!
        actB.setCustomView(R.layout.actionbar_del_n_main)
        var del = actB.customView.findViewById<Button>(R.id.del_smth)
        var tohome = actB.customView.findViewById<Button>(R.id.tohome)
        actB.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        // --------------------------------------------------------- //

        val k = intent.getSerializableExtra("KEY")

        val scroll = findViewById<LinearLayout>(R.id.scroolForNotes)

        val n = findViewById<EditText>(R.id.Edit_Name)
        val s = findViewById<EditText>(R.id.Edit_Sur)
        val m = findViewById<EditText>(R.id.Edit_Mid)
        val d = findViewById<EditText>(R.id.Edit_Data)

        val save = findViewById<Button>(R.id.saveEdited)
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
                    val fbe = i.getValue(fb().javaClass)
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

        val notes = database.getReference("main/${upgradeID(k.toString())}/lessons")
        notes.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children)
                {
                    add(i.value.toString(), scroll, upgradeID(k.toString()), i.value.toString(), i.key.toString(), k.toString())
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        val plus = findViewById<Button>(R.id.adding)
        plus.setOnClickListener {
            val intent = Intent(this, create_note::class.java)
            intent.putExtra("KEY_id", upgradeID(k.toString()))
            startActivity(intent)
        }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val cr: Create_Card = Create_Card()
        d.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay->d.setText("${cr.updateDates(mDay, mMonth+1, mYear)}")}, year,month,day)
            dpd.show()
        }

        // --------------------------------------------------------- //

        del.setOnClickListener{
            val dell_dialog: Dialog =  Dialog(this);
            //var del = actB.customView.findViewById<Button>(R.id.del_smth)
            dell_dialog.setContentView(R.layout.del_dialog);
            val y = dell_dialog.findViewById<Button>(R.id.yes)
            val t = dell_dialog.findViewById<TextView>(R.id.txt_del)
            t.text = "Удалить карточку этого человека?"
            val n = dell_dialog.findViewById<Button>(R.id.no)
            y.setOnClickListener { addingRef.child(upgradeID(k.toString())).removeValue(); startActivity(Intent(this, MainActivity::class.java))}
            n.setOnClickListener { dell_dialog.dismiss() }
            dell_dialog.show()

        }
        tohome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    fun add(res: String, scrool: LinearLayout, ids: String, note: String, keyForNote: String, ID:String)
    {
        val noneContent: TextView = TextView(this)
        noneContent.text = "$res\n\n"
        scrool.addView(noneContent)

        noneContent.setOnClickListener{
            val intent = Intent(this, overlook_note::class.java)
            intent.putExtra("KEY_id", ids)
            intent.putExtra("KEY_note", note)
            intent.putExtra("KEY", keyForNote)
            intent.putExtra("ID", ID)
            startActivity(intent)
        }
    }
    fun upgradeID(s: String):String{
        return s.dropLast(2)}
}