package com.example.win21

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.win21.model.Ufsquestion
import com.example.win21.repository.UfsRepository
import com.example.win21.service.UfcApi
import com.example.win21.viewmodel.UfcViewModel
import com.example.win21.viewmodel.UfcViewModelFactory
import com.onesignal.OneSignal
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuizActivity : AppCompatActivity() {
    private lateinit var ufcViewModel: UfcViewModel
    val APP_ID = "714b9f14-381d-4fc4-a93c-28d480557381"
    var questionsList = ArrayList<Ufsquestion>()
    var questionPosition = 0
    var correctAnswer = 0
    private lateinit var nextButton: AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(APP_ID)
        nextButton = findViewById(R.id.next_button)
        val constraint = findViewById<ConstraintLayout>(R.id.constraint_layout_quiz)
        LoadImage.upload(constraint, this)
        getQuestions()
        nextButton.setOnClickListener {
            getQuestions()
            if (questionPosition == questionsList.size) {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", correctAnswer)
                intent.putExtra("total", questionsList.size)
                startActivity(intent)
            }
        }
    }

    private fun getQuestions() {
        questionsList.clear()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://49.12.202.175/win21/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UfcApi::class.java)
        val ufcRepository = UfsRepository(retrofit)
        ufcViewModel =
            ViewModelProvider(this, UfcViewModelFactory(ufcRepository))[UfcViewModel::class.java]
        ufcViewModel.questions.observe(this) {
            questionsList.addAll(it.ufsquestions)
            Log.i("LIST", questionsList.size.toString())
            setData()
            questionPosition += 1
        }
    }

    private fun setData() {
        nextButton.isEnabled = false
        val title = findViewById<TextView>(R.id.textView_title)
        val button1 = findViewById<AppCompatButton>(R.id.button_answer1)
        val button2 = findViewById<AppCompatButton>(R.id.button_answer2)
        val button3 = findViewById<AppCompatButton>(R.id.button_answer3)
        val button4 = findViewById<AppCompatButton>(R.id.button_answer4)
        button1.isEnabled = true
        button2.isEnabled = true
        button3.isEnabled = true
        button4.isEnabled = true
        button1.setBackgroundResource(R.drawable.round_button)
        button2.setBackgroundResource(R.drawable.round_button)
        button3.setBackgroundResource(R.drawable.round_button)
        button4.setBackgroundResource(R.drawable.round_button)
        title.text = questionsList[questionPosition].question
        button1.text = questionsList[questionPosition].answer1.name
        button2.text = questionsList[questionPosition].answer2.name
        button3.text = questionsList[questionPosition].answer3.name
        button4.text = questionsList[questionPosition].answer4.name
        button1.setOnClickListener {
            if (questionsList[questionPosition].answer1.trueorfalse == "true") {
                button1.setBackgroundResource(R.drawable.round_button_green)
                button1.isEnabled = false
                button2.isEnabled = false
                button3.isEnabled = false
                button4.isEnabled = false
                nextButton.isEnabled = true
                correctAnswer += 1

            } else {
                button1.setBackgroundResource(R.drawable.round_button_red)
                button1.isEnabled = false
                button2.isEnabled = false
                button3.isEnabled = false
                button4.isEnabled = false
                nextButton.isEnabled = true
            }
        }
        button2.setOnClickListener {
            if (questionsList[questionPosition].answer2.trueorfalse == "true") {
                button2.setBackgroundResource(R.drawable.round_button_green)
                button1.isEnabled = false
                button2.isEnabled = false
                button3.isEnabled = false
                button4.isEnabled = false
                nextButton.isEnabled = true
                correctAnswer += 1

            } else {
                button2.setBackgroundResource(R.drawable.round_button_red)
                button1.isEnabled = false
                button2.isEnabled = false
                button3.isEnabled = false
                button4.isEnabled = false
                nextButton.isEnabled = true
            }
        }
        button3.setOnClickListener {
            if (questionsList[questionPosition].answer3.trueorfalse == "true") {
                button3.setBackgroundResource(R.drawable.round_button_green)
                button1.isEnabled = false
                button2.isEnabled = false
                button3.isEnabled = false
                button4.isEnabled = false
                nextButton.isEnabled = true
                correctAnswer += 1
            } else {
                button3.setBackgroundResource(R.drawable.round_button_red)
                button1.isEnabled = false
                button2.isEnabled = false
                button3.isEnabled = false
                button4.isEnabled = false
                nextButton.isEnabled = true
            }
        }
        button4.setOnClickListener {
            if (questionsList[questionPosition].answer4.trueorfalse == "true") {
                button4.setBackgroundResource(R.drawable.round_button_green)
                button1.isEnabled = false
                button2.isEnabled = false
                button3.isEnabled = false
                button4.isEnabled = false
                nextButton.isEnabled = true
                correctAnswer += 1
            } else {
                button4.setBackgroundResource(R.drawable.round_button_red)
                button1.isEnabled = false
                button2.isEnabled = false
                button3.isEnabled = false
                button4.isEnabled = false
                nextButton.isEnabled = true
            }
        }
    }
}