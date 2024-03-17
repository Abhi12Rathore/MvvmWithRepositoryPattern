package com.example.sample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample.models.ProductsData
import com.example.sample.network.APIClient
import com.example.sample.network.ApiResult
import com.example.sample.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModels @Inject constructor(private val repo:Repository) : ViewModel() {
    val liveData: LiveData<ApiResult<List<ProductsData>>>
        get() = repo.liveData

    fun fetchProductData() {
        viewModelScope.launch {
            repo.fetchData()
        }
    }
}