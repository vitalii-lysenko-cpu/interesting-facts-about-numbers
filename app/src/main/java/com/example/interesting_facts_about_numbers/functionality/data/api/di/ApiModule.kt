package com.example.interesting_facts_about_numbers.functionality.data.api.di

import com.example.interesting_facts_about_numbers.functionality.data.api.ApiDataSource
import com.example.interesting_facts_about_numbers.functionality.data.api.ApiDataSourceImpl
import com.example.interesting_facts_about_numbers.functionality.data.api.NumberApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "http://numbersapi.com/"

@Module
@InstallIn(SingletonComponent::class)
internal interface ApiModule {

    @Binds
    fun bindApiDataSource(impl: ApiDataSourceImpl): ApiDataSource

    companion object {
        private lateinit var numberApi: NumberApi

        @Reusable
        @Provides
        fun provideNumberApi(): NumberApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient(httpLoggingInterceptor()))
                .addConverterFactory(
                    MoshiConverterFactory
                        .create()
                        .asLenient()
                )
                .build()
            numberApi = retrofit.create(NumberApi::class.java)
            return numberApi
        }

        @Provides
        fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }

        @Provides
        fun httpLoggingInterceptor(): HttpLoggingInterceptor {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }
    }
}