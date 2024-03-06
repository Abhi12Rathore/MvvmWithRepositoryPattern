package com.example.sample.network

import com.example.sample.models.ProductsData
import com.example.sample.network.ApiConstants.PRODUCTS
import retrofit2.http.GET

interface ApiInterface {

    @GET(PRODUCTS)
    suspend fun getProductData():List<ProductsData>
}