package com.krodriguez.akrpizzaprojectcharter.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.krodriguez.akrpizzaprojectcharter.R
import com.krodriguez.akrpizzaprojectcharter.di.DI
import com.krodriguez.akrpizzaprojectcharter.model.OrderItem
import com.krodriguez.akrpizzaprojectcharter.model.UIState
import com.krodriguez.akrpizzaprojectcharter.util.LARGE
import com.krodriguez.akrpizzaprojectcharter.util.MEDIUM
import com.krodriguez.akrpizzaprojectcharter.util.SMALL


class MainActivity : AppCompatActivity() {

    private val TAG = "--AKR--"
    private val viewModel by lazy { DI.provideViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureObservers()
    }

    private fun configureObservers()  {
        viewModel.uiState.observe(this) {
            when(it) {
                is UIState.LOADING -> {
                    // show loading spinner
                    Log.d(TAG, "Loading...")
                }
                is UIState.ERROR -> {
                    // show error text
                    Log.d(TAG, it.msg)
                }
                is UIState.SUCCESS -> {
                    calculateTotal(it.order.order)
                }
            }
        }
    }

    private fun calculateTotal(list: List<OrderItem>) {
        var total = 0

        for(i in list) {
            when(i.size) {
                SMALL -> {
                    total += 4
                }
                MEDIUM -> {
                    total += 8
                }
                LARGE -> {
                    total += 15
                }
            }
        }
        Log.d(TAG, "Total is ${total}€")
    }
}