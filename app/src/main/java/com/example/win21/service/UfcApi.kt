package com.example.win21.service

import com.example.win21.model.UfcModel
import retrofit2.Call
import retrofit2.http.GET

interface UfcApi {
    @GET("ufsquestions.json")
    fun getQuestions(): Call<UfcModel>
}