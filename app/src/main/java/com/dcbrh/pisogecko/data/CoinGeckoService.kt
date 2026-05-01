package com.dcbrh.pisogecko.data

import com.dcbrh.pisogecko.core.contstants.Constants
import com.dcbrh.pisogecko.domain.models.CryptoCurrency
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoService {
    @GET("coins/markets")
    suspend fun getCurrencies(
        @Query("vs_currency") vsCurrency: String,
        @Query("per_page") perPage: String,
        @Query("page") page: String
    ): List<CryptoCurrency>
}