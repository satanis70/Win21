package com.example.win21

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        val score = intent.getIntExtra("score", 0)
        val all = intent.getIntExtra("total", 0)
        val constraint = findViewById<ConstraintLayout>(R.id.constraint_layout_score)
        LoadImage.upload(constraint, this)
        val tvScore = findViewById<TextView>(R.id.textView_score)
        tvScore.text = "$score/$all"
        val againButton = findViewById<AppCompatButton>(R.id.button_again)
        againButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}