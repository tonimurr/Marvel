package com.tonimurr.marvel.di

import com.tonimurr.marvel.BuildConfig
import com.tonimurr.marvel.common.toMd5
import com.tonimurr.marvel.data.remote.AppApi
import com.tonimurr.marvel.data.repositories.MarvelRepositoryImplementation
import com.tonimurr.marvel.domain.repositories.MarvelRepository
import com.tonimurr.marvel.domain.usecases.GetMarvelCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesRetrofit(): AppApi {

        //Add default query parameters, headers, etc...
        val genericInterceptor = Interceptor {
            val requestBuilder = it.request().newBuilder()
            val originalUrl = it.request().url
            val ts = System.currentTimeMillis()
            val strToHash = "$ts${BuildConfig.PRIVATE_KEY}${BuildConfig.API_KEY}"
            val newUrl = originalUrl.newBuilder()
                .addQueryParameter("apikey", BuildConfig.API_KEY)
                .addQueryParameter("hash", strToHash.toMd5())
                .addQueryParameter("ts", ts.toString())
                .build()
            requestBuilder.url(newUrl)
            it.proceed(requestBuilder.build())
        }

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .connectTimeout(BuildConfig.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(BuildConfig.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(genericInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppApi::class.java)

    }

    @Singleton
    @Provides
    fun providesMarvelRepository(api: AppApi): MarvelRepository {
        return MarvelRepositoryImplementation(api)
    }


    @Provides
    fun providesGetMarvelCharactersUseCase(marvelRepository: MarvelRepository): GetMarvelCharactersUseCase {
        return GetMarvelCharactersUseCase(marvelRepository)
    }

}