package com.miladheydari.interview.s780androidtest.di.module

import android.os.Environment
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.miladheydari.interview.s780androidtest.BuildConfig
import com.miladheydari.interview.s780androidtest.core.Constants
import com.miladheydari.interview.s780androidtest.domain.Api
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {
    @Singleton
    @Provides
    @Named("cached")
    fun provideOkHttpClient(): OkHttpClient {
        val cache = Cache(Environment.getDownloadCacheDirectory(), 10 * 1024 * 1024)
        val client = OkHttpClient.Builder()
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptorBody = HttpLoggingInterceptor()
            httpLoggingInterceptorBody.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(
                httpLoggingInterceptorBody
            )

            val httpLoggingInterceptorHeader = HttpLoggingInterceptor()
            httpLoggingInterceptorHeader.level = HttpLoggingInterceptor.Level.HEADERS
            client.addNetworkInterceptor(
                httpLoggingInterceptorHeader
            )
        }

        return client.cache(cache).build()
    }

    @Singleton
    @Provides
    @Named("non_cached")
    fun provideNonCachedOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptorBody = HttpLoggingInterceptor()
            httpLoggingInterceptorBody.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(httpLoggingInterceptorBody)

            val httpLoggingInterceptorHeader = HttpLoggingInterceptor()
            httpLoggingInterceptorHeader.level = HttpLoggingInterceptor.Level.HEADERS
            client.addNetworkInterceptor(httpLoggingInterceptorHeader)
        }

        client.addInterceptor {
            val request = it.request()
            val response = it.proceed(request)

            when (response.code) {
                400 -> {
                    Timber.d("400 - Bad Request")
                }
                401 -> {
                    Timber.d("401 - Unauthorized - refresh token")
                }
                403 -> {
                    Timber.d("403 - Forbidden")
                }
                404 -> {
                    Timber.d("404 - Not Found - URL")
                }
                405 -> {
                    Timber.d("405 - Method Not Allowed - DELETE - GET")
                }
                500 -> {
                    Timber.d("500 - Internal Server ErrorT")
                }
                else -> {
                    Timber.d("Server error - ${response.message}")
                }
            }

            response
        }

        return client.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, @Named("non_cached") client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit.Builder): Api {
        return retrofit.baseUrl(Constants.NetworkService.BASE_URL)
            .build()
            .create(Api::class.java)
    }
}