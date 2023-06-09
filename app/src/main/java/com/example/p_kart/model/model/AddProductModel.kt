package com.example.p_kart.model

data class AddProductModel(
    val productName:String?="",
    val productDescription:String?="",
    val productCoverImg:String?="",
    val productCategory:String?="",
    val productID:String?="",
    val productMRP:String?="",
    val productSP:String?="",
    val productImages:ArrayList<String>
)
