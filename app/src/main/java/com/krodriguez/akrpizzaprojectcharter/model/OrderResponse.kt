package com.krodriguez.akrpizzaprojectcharter.model

data class OrderResponse(
    val order: List<OrderItem>
)

data class OrderItem(
    val type: String,
    val size: String,
    val toppings: List<String>?,
    val sauce: List<String>?
)
