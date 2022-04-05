package com.krodriguez.akrpizzaprojectcharter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.krodriguez.akrpizzaprojectcharter.api.ApiRepository
import com.krodriguez.akrpizzaprojectcharter.model.UIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class OrderViewModel(private val repo: ApiRepository) : ViewModel() {

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState>
        get() = _uiState

    init {
        getOrders()
    }

    private fun getOrders() {
        CoroutineScope(Dispatchers.IO).launch {
            _uiState.postValue(UIState.LOADING)
            repo.getOrders()
                .catch {
                    _uiState.postValue(UIState.ERROR("Failed network call"))
                }
                .collect {
                    _uiState.postValue(it)
                }
        }
    }
}