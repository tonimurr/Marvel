package com.tonimurr.marvel.di

import android.content.Context
import com.tonimurr.marvel.BuildConfig
import com.tonimurr.marvel.data.remote.AppApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesRetrofit(@ApplicationContext context: Context): AppApi {

        val headerInterceptor = Interceptor {
            val requestBuilder = it.request().newBuilder()
            //Add custom headers
            it.proceed(requestBuilder.build())
        }

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        val client = OkHttpClient.Builder()
            .connectTimeout(BuildConfig.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(BuildConfig.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AppApi::class.java)


    }

}