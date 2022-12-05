package com.example.win21.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.win21.model.UfcModel
import com.example.win21.service.UfcApi
import retrofit2.awaitResponse

class UfsRepository(val ufcApi: UfcApi){
    private val quizLiveData = MutableLiveData<UfcModel>()
    val questions: LiveData<UfcModel>
    get() = quizLiveData

    suspend fun getQuestions(){
        val result = ufcApi.getQuestions().awaitResponse()
        if (result.isSuccessful){
            quizLiveData.postValue(result.body())
        }
    }
}