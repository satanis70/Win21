package com.example.win21.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.win21.repository.UfsRepository

class UfcViewModelFactory(
    private val ufcRepository: UfsRepository
    ):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UfcViewModel(ufcRepository) as T
    }
}