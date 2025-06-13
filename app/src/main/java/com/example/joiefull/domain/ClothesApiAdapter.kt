package com.example.joiefull.data.model // Or your preferred package

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "picture") val picture: PictureInfo,
    @Json(name = "name") val name: String,
    @Json(name = "category") val category: String,
    @Json(name = "likes") val likes: Int,
    @Json(name = "price") val price: Double,
    @Json(name = "original_price") val originalPrice: Double,
)

@JsonClass(generateAdapter = true)
data class PictureInfo(
    @Json(name = "url") val url: String,
    @Json(name = "description") val description: String,
)
