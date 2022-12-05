package com.example.win21

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val constraint = findViewById<ConstraintLayout>(R.id.constraint_layout_main)
        LoadImage.upload(constraint, this)
        val startButton = findViewById<AppCompatButton>(R.id.start_quiz_button)
        startButton.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}