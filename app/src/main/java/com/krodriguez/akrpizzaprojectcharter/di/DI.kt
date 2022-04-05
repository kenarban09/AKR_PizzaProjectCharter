package com.krodriguez.akrpizzaprojectcharter.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.krodriguez.akrpizzaprojectcharter.api.ApiRepositoryImpl
import com.krodriguez.akrpizzaprojectcharter.api.ApiService
import com.krodriguez.akrpizzaprojectcharter.util.BASE_URL
import com.krodriguez.akrpizzaprojectcharter.viewModel.OrderViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DI {
    private val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private fun provideRepositoryImpl() = ApiRepositoryImpl(api)

    fun provideViewModel(storeOwner: ViewModelStoreOwner) =
        ViewModelProvider(storeOwner,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return OrderViewModel(provideRepositoryImpl()) as T
                }
            })[OrderViewModel::class.java]
}