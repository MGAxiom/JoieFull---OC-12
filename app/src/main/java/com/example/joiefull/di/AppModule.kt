package com.example.joiefull.di


import com.example.joiefull.data.ClothesRepositoryImpl
import com.example.joiefull.ui.viewmodel.ClothesListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { ClothesListViewModel(get()) }
    single { ClothesRepositoryImpl(get()) }
}