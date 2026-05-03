package com.dcbrh.pisogecko.data.repositories

import com.dcbrh.pisogecko.data.services.CoinGeckoService
import com.dcbrh.pisogecko.domain.models.CryptoCurrency
import com.dcbrh.pisogecko.domain.repositories.HomeRepository

class HomeRepositoryImpl(
    private val _service: CoinGeckoService
): HomeRepository {
    override suspend fun getCurrencies(page: Int): Result<List<CryptoCurrency>> {
        try {
            val response: List<CryptoCurrency> = _service.getCurrencies(
                vsCurrency = "usd",
                perPage = "20",
                page = page.toString()
            )
            return Result.success(response)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}