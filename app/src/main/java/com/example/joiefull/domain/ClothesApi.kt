package com.example.joiefull.domain

import com.example.joiefull.data.model.ProductResponse
import retrofit2.http.GET

interface ClothesApi {
    @GET("OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/api/clothes.json")
    suspend fun getClothes(): List<ProductResponse>
}
