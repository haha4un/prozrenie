package com.example.prozrenie

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class overlook_note : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_overlook_note)

        var actB: ActionBar = getSupportActionBar()!!
        actB.setCustomView(R.layout.actionbar_del_n_main)
        var del = actB.customView.findViewById<Button>(R.id.del_smth)
        var tohome = actB.customView.findViewById<Button>(R.id.tohome)
        actB.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        // --------------------------------------------------------- //

        var note = intent.getSerializableExtra("KEY_note")
        var id = intent.getSerializableExtra("KEY_id")
        var key = intent.getSerializableExtra("KEY")
        var ID = intent.getSerializableExtra("ID")

        var n = findViewById<EditText>(R.id.note)
        var s = findViewById<Button>(R.id.save_editedNote)

        n.setText(note.toString())

        val database = Firebase.database
        s.setOnClickListener {
            val db = database.getReference("main/$id/lessons/$key")
            db.setValue(n.text.toString())
            var intent = Intent(this, View_Edit_Card::class.java)
            intent.putExtra("KEY", ID)
            startActivity(intent)
        }

        // --------------------------------------------------------- //
        del.setOnClickListener{
            var dellRef = database.getReference("main/$id/lessons/$key")
            var dell_dialog: Dialog =  Dialog(this);
            dell_dialog.setTitle("Удалить?:");
            dell_dialog.setContentView(R.layout.del_dialog);
            var y = dell_dialog.findViewById<Button>(R.id.yes)
            var n = dell_dialog.findViewById<Button>(R.id.no)
            y.setOnClickListener {
                if (check(key.toString()) == "denied")
                    dell_dialog.dismiss()
                else
                {
                    dellRef.child(check(key.toString())).removeValue()
                    //startActivity(Intent(this, MainActivity::class.java))
                }
            }
            n.setOnClickListener { dell_dialog.dismiss() }
            dell_dialog.show()
        }
        tohome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    fun check(x: String) : String
    {
        if (x == "0") {
            Toast.makeText(this, "Ты не можешь удалить id-заметку!", Toast.LENGTH_SHORT).show()
            return "denied"
        }
        else
            return x
    }
}