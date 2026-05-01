package com.dcbrh.pisogecko.domain.repositories

import com.dcbrh.pisogecko.domain.models.CryptoCurrency

interface HomeRepository {
    suspend fun getCurrencies(): Result<List<CryptoCurrency>>
}