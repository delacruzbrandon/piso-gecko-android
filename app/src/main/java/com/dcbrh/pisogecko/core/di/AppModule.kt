package com.dcbrh.pisogecko.core.di

import com.dcbrh.pisogecko.core.RetrofitClient
import com.dcbrh.pisogecko.data.CoinGeckoService
import com.dcbrh.pisogecko.data.HomeRepositoryImpl
import com.dcbrh.pisogecko.domain.repositories.HomeRepository
import com.dcbrh.pisogecko.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    /** Services **/
    single<CoinGeckoService> { RetrofitClient().coinGeckoService }

    /** Repositories **/
    single<HomeRepository> { HomeRepositoryImpl(get()) }

    /** ViewModels **/
    viewModel<HomeViewModel> { HomeViewModel(get()) }
}