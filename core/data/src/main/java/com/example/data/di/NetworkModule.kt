package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }


    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
            },
        ).addInterceptor(Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.addHeader("X-GitHub-Api-Version", "2022-11-28")
            builder.addHeader("Accept", "application/vnd.github+json")
            //TODO: Uncomment and replace token_value with your github token. Generate token with the below instruction
            //TODO: https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens#creating-a-fine-grained-personal-access-token
            //builder.addHeader("Authorization", "Bearer token_vale")
            chain.proceed(builder.build())
        }).build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        networkJson: Json,
        okhttpCallFactory: dagger.Lazy<Call.Factory>,
    ): ApiService {
        return Retrofit.Builder().baseUrl("https://api.github.com/")
            .callFactory { okhttpCallFactory.get().newCall(it) }.addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            ).build().create(ApiService::class.java)
    }
}
