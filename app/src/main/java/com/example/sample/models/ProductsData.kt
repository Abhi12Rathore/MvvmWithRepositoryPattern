package com.example.sample.models

import com.google.gson.annotations.SerializedName

data class ProductsData(
    @SerializedName("title")
    var title:String?=null,

    @SerializedName("price")
    var price:String?=null,

    @SerializedName("description")
    var description:String?=null,

    @SerializedName("category")
    var category:String?=null,

    @SerializedName("image")
    var image:String?=null,

)
