package com.example.prozrenie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var x1 = 0f
    private var x2 = 0f
    val MIN_DISTANCE = 150

    var limitOfGames = 3
    var i = 0
    @SuppressLint("MissingInflatedId", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide();


        var create_card = findViewById<Button>(R.id.create_card)
        create_card.setOnClickListener{
            val intent = Intent(this, Create_Card::class.java)
            startActivity(intent)
        }

        var view_card = findViewById<Button>(R.id.view_card)
        view_card.setOnClickListener{
            val intent = Intent(this, View_Card::class.java)
            startActivity(intent)
        }

        var info_btn = findViewById<Button>(R.id.inf_btn)
        info_btn.setOnClickListener {
            val intent = Intent(this, info::class.java)
            startActivity(intent)
        }

        var gamesPic = findViewById<ImageView>(R.id.games_big_pic)

        var temp = 0
        var games = findViewById<ImageButton>(R.id.games)
        games.setOnTouchListener(
            object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent): Boolean {
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> x1 = event.x
                        MotionEvent.ACTION_UP -> {
                            x2 = event.x
                            val deltaX = x2 - x1
                            if (deltaX < 0) {
                                i++
                            } else if (deltaX > 0) {
                                i--
                            }
                            else if (deltaX == 0f)
                            {
                                when(temp)
                                {
                                    1 -> { var intent = Intent(this@MainActivity, imges_game_1::class.java ); startActivity(intent) }
                                    2 -> { var intent = Intent(this@MainActivity, paint_game::class.java ); startActivity(intent)}
                                    3 ->  { var intent = Intent(this@MainActivity, relax_game::class.java); startActivity(intent) }
                                }
                            }
                        }
                    }
                    temp = toNextActivity(i, gamesPic)
                    return false
                }
            })

    }
    fun toNextActivity(i:Int, view: ImageView) : Int
    {
        var j = i
        if (j > limitOfGames){
            j = 1
            this.i = 1
        }
        if (j <= 0) {
            j = limitOfGames
            this.i = limitOfGames
        }
        when(j)
        {
            1 -> {
                view.setImageResource(R.drawable.gi1)
                return 1
            }
            2 -> {
                view.setImageResource(R.drawable.gi2)
                return 2
            }
            3 -> {
                view.setImageResource(R.drawable.gi3)
                return 3
            }
        }
        return 0
    }
}