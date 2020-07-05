package com.example.kotlin.training

import android.app.Application
import com.example.kotlin.training.data.MediaProvider
import com.example.kotlin.training.data.MediaProviderImpl
import com.example.kotlin.training.ui.detail.DetailActivity
import com.example.kotlin.training.ui.detail.DetailViewModel
import com.example.kotlin.training.ui.home.MainActivity
import com.example.kotlin.training.ui.home.MainViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class PlayerApp: Application() {

    private val appModule = module {
        single<MediaProvider> { MediaProviderImpl }
        single<CoroutineDispatcher>(named("ioDispatcher")) { Dispatchers.IO }
    }

    private val scopesModule = module {
        scope <MainActivity> {
            viewModel { MainViewModel(get(), get(named("ioDispatcher"))) }
        }
        scope<DetailActivity> {
            viewModel { DetailViewModel(get(), get(named("ioDispatcher"))) }
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PlayerApp)
            modules(appModule, scopesModule)
        }
    }
}