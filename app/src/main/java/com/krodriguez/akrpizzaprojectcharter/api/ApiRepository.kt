package com.krodriguez.akrpizzaprojectcharter.api

import com.krodriguez.akrpizzaprojectcharter.model.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


interface ApiRepository {
    suspend fun getOrders(): Flow<UIState>
}

class ApiRepositoryImpl(private val api: ApiService) : ApiRepository {
    override suspend fun getOrders(): Flow<UIState> = flow { emit(api.getOrders()) }
        .onStart { UIState.LOADING }
        .map { response ->
            response.body()?.let {
                println(it.order)
                UIState.SUCCESS(it)
            } ?: UIState.ERROR("Empty Response")
        }
        .flowOn(Dispatchers.IO)
        .conflate()
}