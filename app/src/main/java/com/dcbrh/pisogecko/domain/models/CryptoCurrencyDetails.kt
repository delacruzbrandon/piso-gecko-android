package com.dcbrh.pisogecko.domain.models

import com.google.gson.annotations.SerializedName

data class CryptoCurrencyDetails(
    val id: String,
    val symbol: String,
    val name: String,
    val description: DescriptionDto,
    val image: ImageDto,
    @SerializedName("market_cap_rank")
    val rank: Int,
    @SerializedName("market_data")
    val marketPrices: CurrentPriceDto,
    @SerializedName("genesis_date")
    val genesisDate: String
)

data class DescriptionDto(
    val en: String
)

data class ImageDto(
    val thumb: String,
    val small: String,
    val large: String
)

data class CurrentPriceDto(
    @SerializedName("current_price")
    val prices: Map<String, Double>
)