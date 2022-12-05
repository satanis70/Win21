package com.example.win21.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.win21.model.UfcModel
import com.example.win21.repository.UfsRepository
import kotlinx.coroutines.launch

class UfcViewModel(
    private val ufcRepository: UfsRepository
) : ViewModel() {
    init {
        viewModelScope.launch {
            ufcRepository.getQuestions()
        }
    }
    val questions: LiveData<UfcModel>
    get() = ufcRepository.questions
}