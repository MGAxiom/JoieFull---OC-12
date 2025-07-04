package com.example.joiefull.di

import com.example.joiefull.data.ClothesRepository
import com.example.joiefull.data.ClothesRepositoryImpl
import com.example.joiefull.ui.viewmodel.ClothesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule =
    module {
        viewModel { ClothesViewModel(get()) }
        factory<ClothesRepository> {
            ClothesRepositoryImpl(
                clothesApi = get(),
            )
        }
    }
