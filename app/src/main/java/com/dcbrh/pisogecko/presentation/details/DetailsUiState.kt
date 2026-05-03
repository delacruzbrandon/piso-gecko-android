package com.dcbrh.pisogecko.presentation.details

import com.dcbrh.pisogecko.domain.models.CryptoCurrencyDetails

sealed interface DetailsUiState {
    object Loading: DetailsUiState
    data class Success(val currency: CryptoCurrencyDetails): DetailsUiState
    data class Error(val error: String): DetailsUiState
}