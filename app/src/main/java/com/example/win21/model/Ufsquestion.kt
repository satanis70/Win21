package com.example.win21.model

import androidx.annotation.Keep

@Keep
data class Ufsquestion(
    val answer1: Answer1,
    val answer2: Answer1,
    val answer3: Answer1,
    val answer4: Answer1,
    val question: String
)