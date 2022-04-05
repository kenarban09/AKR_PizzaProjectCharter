package com.krodriguez.akrpizzaprojectcharter.model

sealed class UIState {
    object LOADING : UIState()
    data class ERROR(val msg: String) : UIState()
    data class SUCCESS(val order: OrderResponse) : UIState()
}