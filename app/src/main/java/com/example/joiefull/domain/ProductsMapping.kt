package com.example.joiefull.domain

import com.example.joiefull.data.model.ProductResponse
import com.example.joiefull.model.Product

fun ProductResponse.toDomain(): Product =
    Product(
        id = id.toString(),
        name = name,
        price = price,
        strikedPrice = originalPrice,
        rate = likes.toDouble(),
        imageUrl = picture.url,
        imageDescription = picture.description,
        category = category.lowercase(),
    )

fun List<ProductResponse>.toDomain(): List<Product> = map { it.toDomain() }
