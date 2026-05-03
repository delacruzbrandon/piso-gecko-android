package com.dcbrh.pisogecko.core.di

import com.dcbrh.pisogecko.data.remote.CoinGeckoApi
import com.dcbrh.pisogecko.data.repositories.DetailsRepositoryImpl
import com.dcbrh.pisogecko.data.services.CoinGeckoService
import com.dcbrh.pisogecko.data.repositories.HomeRepositoryImpl
import com.dcbrh.pisogecko.domain.repositories.DetailsRepository
import com.dcbrh.pisogecko.domain.repositories.HomeRepository
import com.dcbrh.pisogecko.presentation.details.DetailsViewModel
import com.dcbrh.pisogecko.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    /** Services **/
    single<CoinGeckoService> { CoinGeckoApi().coinGeckoService }

    /** Repositories **/
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single<DetailsRepository> { DetailsRepositoryImpl(get()) }

    /** ViewModels **/
    viewModel<HomeViewModel> { HomeViewModel(get()) }
    viewModel<DetailsViewModel> { DetailsViewModel(get()) }
}