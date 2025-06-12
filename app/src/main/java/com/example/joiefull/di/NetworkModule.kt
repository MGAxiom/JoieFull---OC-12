package com.example.joiefull.di


import com.example.joiefull.domain.ClothesApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import org.koin.dsl.module

val networkModule = module {
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }
    single<ClothesApi> { get<Retrofit>().create(ClothesApi::class.java) }
}
