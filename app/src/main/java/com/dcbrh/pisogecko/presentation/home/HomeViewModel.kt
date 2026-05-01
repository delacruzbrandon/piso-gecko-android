package com.dcbrh.pisogecko.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dcbrh.pisogecko.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val _repository: HomeRepository
): ViewModel() {
    val TAG = "HomeViewModel"
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
    }

    fun getCurrencies(page: Int) {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading

            _repository.getCurrencies(page = page)
                .onSuccess { currencies ->
                    Log.d(TAG, "getCurrencies size: ${currencies.size}")
                    _uiState.value = HomeUiState.Success(currencies)
                }
                .onFailure {
                    Log.d(TAG, "getCurrencies error viewModel: ${it}")
                    _uiState.value = HomeUiState.Error(it.message ?: "Unknown Error")
                }
        }
    }
}