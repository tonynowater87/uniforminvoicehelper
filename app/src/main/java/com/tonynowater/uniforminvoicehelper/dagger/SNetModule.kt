package com.tonynowater.uniforminvoicehelper.dagger

import com.tonynowater.uniforminvoicehelper.api.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by tonyliao on 2018/3/14.
 */
@Module
class SNetModule {

    @Singleton
    @Provides
    fun testClient(): ITestApi {
        val okHttpLogger = HttpLoggingInterceptor()
        okHttpLogger.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(okHttpLogger)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(SURLDefinition.BaseTestURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        return retrofit.create(ITestApi::class.java)
    }

    @Singleton
    @Provides
    fun carrierClient(): ICarrierApi {
        val okHttpLogger = HttpLoggingInterceptor()
        okHttpLogger.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(SBaseQueryMapInterceptor())
                .addInterceptor(SCarrierQueryMapInterceptor())
                .addInterceptor(okHttpLogger)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(SURLDefinition.BaseNetURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        return retrofit.create(ICarrierApi::class.java)
    }

    @Singleton
    @Provides
    fun invAppClient(): IInvAppApi {
        val okHttpLogger = HttpLoggingInterceptor()
        okHttpLogger.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(SBaseQueryMapInterceptor())
                .addInterceptor(okHttpLogger)
                .build()
        val retrofit = Retrofit.Builder()
                .baseUrl(SURLDefinition.BaseNetURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        return retrofit.create(IInvAppApi::class.java)
    }
}