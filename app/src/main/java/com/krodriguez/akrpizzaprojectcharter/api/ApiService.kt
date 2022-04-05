package com.krodriguez.akrpizzaprojectcharter.api

import com.krodriguez.akrpizzaprojectcharter.model.OrderResponse
import com.krodriguez.akrpizzaprojectcharter.util.ORDER
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(ORDER)
    suspend fun getOrders(): Response<OrderResponse>
}