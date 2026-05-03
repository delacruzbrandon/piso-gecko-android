package com.dcbrh.pisogecko.presentation.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dcbrh.pisogecko.domain.repositories.DetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val _repository: DetailsRepository

): ViewModel() {
    val TAG = "DetailsViewModel"

    val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getCurrency(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = DetailsUiState.Loading

            _repository.getCurrency(id)
                .onSuccess { currency ->
                    Log.d(TAG, "getCurrency: ${currency.id}")
                    _uiState.value = DetailsUiState.Success(currency)
                }
                .onFailure {
                    _uiState.value = DetailsUiState.Error(it.message ?: "Unknown Error")
                }
        }
    }
}


