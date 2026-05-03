package com.dcbrh.pisogecko.domain.repositories

import com.dcbrh.pisogecko.domain.models.CryptoCurrencyDetails

interface DetailsRepository {
    suspend fun getCurrency(id: String): Result<CryptoCurrencyDetails>
}
