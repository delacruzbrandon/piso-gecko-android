package com.dcbrh.pisogecko.presentation.home

import com.dcbrh.pisogecko.domain.models.CryptoCurrency

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(val currencies: List<CryptoCurrency>) : HomeUiState
    data class Error(val error: String) : HomeUiState
}