package com.dcbrh.pisogecko.data.services

import com.dcbrh.pisogecko.domain.models.CryptoCurrencyDetails
import com.dcbrh.pisogecko.domain.models.CryptoCurrency
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoService {
    @GET("coins/markets")
    suspend fun getCurrencies(
        @Query("vs_currency") vsCurrency: String,
        @Query("per_page") perPage: String,
        @Query("page") page: String
    ): List<CryptoCurrency>

    @GET("coins/{id}")
    suspend fun getCurrency(
        @Path("id") id: String
    ): CryptoCurrencyDetails
}