package com.example.quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class Result : AppCompatActivity()
{

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var i = intent
        var score = i.getIntExtra("score", 0)

        findViewById<TextView>(R.id.score).text = "$score/10"
    }

    fun home(v: View?)
    {
        startActivity(Intent(this, MainActivity::class.java))
    }
}