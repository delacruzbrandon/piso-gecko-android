package com.dcbrh.pisogecko.data.repositories

import com.dcbrh.pisogecko.domain.models.CryptoCurrencyDetails
import com.dcbrh.pisogecko.data.services.CoinGeckoService
import com.dcbrh.pisogecko.domain.repositories.DetailsRepository

class DetailsRepositoryImpl(
    private val _service: CoinGeckoService
): DetailsRepository {
    override suspend fun getCurrency(id: String): Result<CryptoCurrencyDetails> {
        try {
            val response: CryptoCurrencyDetails = _service.getCurrency(id)
            return Result.success(response)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}