package com.example.sample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample.models.ProductsData
import com.example.sample.network.APIClient
import com.example.sample.network.ApiResult
import com.example.sample.repositories.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModels @Inject constructor(private val repository: Repository) : ViewModel() {
    val liveData: LiveData<ApiResult<List<ProductsData>>>
        get() = repository.liveData

    fun fetchProductData() {
        viewModelScope.launch {
            repository.fetchData()
        }
    }
}