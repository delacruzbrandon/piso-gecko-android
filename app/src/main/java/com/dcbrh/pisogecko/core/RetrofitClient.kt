package com.dcbrh.pisogecko.core

import com.dcbrh.pisogecko.BuildConfig
import com.dcbrh.pisogecko.core.contstants.Constants
import com.dcbrh.pisogecko.data.CoinGeckoService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    val baseUrl = Constants.BASE_URL
    val apiKey = BuildConfig.API_KEY

    private val headerInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()

        val requestWithHeaders = originalRequest.newBuilder()
            .header("x-cg-api-key", apiKey)
            .build()

        chain.proceed(requestWithHeaders)
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val coinGeckoService: CoinGeckoService = retrofit.create(CoinGeckoService::class.java)
}