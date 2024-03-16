package com.example.sample.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sample.models.ProductsData
import com.example.sample.network.APIClient
import com.example.sample.network.ApiResult
import javax.inject.Inject

class Repository @Inject constructor(){
    private val response: MutableLiveData<ApiResult<List<ProductsData>>> = MutableLiveData()
    val liveData: LiveData<ApiResult<List<ProductsData>>>
        get() = response


    suspend fun fetchData() {
        response.postValue(ApiResult.Loading())
        try {
            val productResponse = APIClient.apiService.getProductData()
            if (productResponse.isNullOrEmpty()) {
                response.postValue(ApiResult.Error("No data found"))
            } else {
                response.postValue(ApiResult.Success(productResponse))
            }
        } catch (e: Exception) {
            response.postValue(ApiResult.Error(e.message ?: ""))
        }
    }
}