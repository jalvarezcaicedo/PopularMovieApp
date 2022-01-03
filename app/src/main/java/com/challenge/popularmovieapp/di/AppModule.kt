package com.challenge.popularmovieapp.di

import androidx.room.Room
import androidx.viewbinding.BuildConfig
import com.challenge.popularmovieapp.data.local.MovieDatabase
import com.challenge.popularmovieapp.data.remote.MovieService
import com.challenge.popularmovieapp.data.repository.MovieRepository
import com.challenge.popularmovieapp.util.ApiKeyInterceptor
import com.challenge.popularmovieapp.util.Injection
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.MainScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {
    single(named(Injection.NonCancellableScope)) { MainScope() }
    single<Moshi> { Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build() }
    single<Converter.Factory> { MoshiConverterFactory.create(get()) }
    single<Retrofit.Builder> {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(get())
    }
    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }
    single { ApiKeyInterceptor() }
    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(get<ApiKeyInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single<Retrofit> {
        get<Retrofit.Builder>()
            .client(get())
            .build()
    }
    single<MovieService> { get<Retrofit>().create(MovieService::class.java) }
    single {
        Room.databaseBuilder(androidContext(), MovieDatabase::class.java, "movie-database")
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        MovieRepository(
            movieService = get(),
            moviesDatabase = get()
        )
    }

}