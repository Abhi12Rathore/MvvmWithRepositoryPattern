package com.example.sample.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sample.repositories.Repository
import com.example.sample.viewmodels.MainActivityViewModels
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    private val map: Map<Class<*>,@JvmSuppressWildcards ViewModel>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return map[modelClass] as T
    }
}