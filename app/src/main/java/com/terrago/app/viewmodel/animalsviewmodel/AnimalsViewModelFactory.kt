package com.terrago.app.viewmodel.animalsviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.terrago.app.database.repositories.AnimalsRepository

class AnimalsViewModelFactory(
    private val repo: AnimalsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimalsViewModel(repo) as T
    }
}