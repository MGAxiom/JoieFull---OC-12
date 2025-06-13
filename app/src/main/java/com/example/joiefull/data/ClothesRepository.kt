package com.example.joiefull.data

import com.example.joiefull.domain.ClothesApi
import com.example.joiefull.domain.toDomain
import com.example.joiefull.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ClothesRepository {
    suspend fun getAllClothes(): Result<List<Product>>
}

class ClothesRepositoryImpl(private val clothesApi: ClothesApi) : ClothesRepository {
    override suspend fun getAllClothes(): Result<List<Product>> {
        return try {
            val clothes =
                withContext(Dispatchers.IO) {
                    clothesApi.getClothes()
                }
            val mappedClothes = clothes.map { it.toDomain() }
            Result.success(mappedClothes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
