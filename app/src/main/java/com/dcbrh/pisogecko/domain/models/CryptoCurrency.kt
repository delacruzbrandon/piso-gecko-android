package com.dcbrh.pisogecko.domain.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CryptoCurrency(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    @SerializedName("current_price")
    val currentPrice: Double,
    @SerializedName("market_cap_rank")
    val rank: Int
)