package com.dcbrh.pisogecko.data

import android.util.Log
import com.dcbrh.pisogecko.domain.models.CryptoCurrency
import com.dcbrh.pisogecko.domain.repositories.HomeRepository

class HomeRepositoryImpl(
    private val _service: CoinGeckoService
): HomeRepository {
    val TAG = "HomeRepositoryImpl"
    override suspend fun getCurrencies(page: Int): Result<List<CryptoCurrency>> {
        try {
            val response: List<CryptoCurrency> = _service.getCurrencies(
                vsCurrency = "usd",
                perPage = "20",
                page = page.toString()
            )
            Log.d(TAG, "getCurrencies: $response")
            return Result.success(response)
        } catch (e: Exception) {
            Log.d(TAG, "getCurrencies: $e")
            return Result.failure(e)
        }
    }
}