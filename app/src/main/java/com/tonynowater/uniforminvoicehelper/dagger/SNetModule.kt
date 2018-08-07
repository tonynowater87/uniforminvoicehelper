package com.tonynowater.uniforminvoicehelper.dagger

import com.tonynowater.uniforminvoicehelper.data.net.api.*
import com.tonynowater.uniforminvoicehelper.data.net.api.util.STransferEntityToDtoUtil
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by tonyliao on 2018/3/14.
 */
@Module
class SNetModule {

    companion object {
        private const val TIMEOUT = 5L
    }

    @Singleton
    @Provides
    fun provideTestApi(okHttpClientBuilder: OkHttpClient.Builder, okHttpLogger: HttpLoggingInterceptor): ITestApi {
        val okHttpClient = okHttpClientBuilder
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
    fun provideCarrierApi(okHttpClientBuilder: OkHttpClient.Builder, carrierInterceptor: SCarrierQueryMapInterceptor, baseInterceptor: SBaseQueryMapInterceptor, okHttpLogger: HttpLoggingInterceptor): ICarrierApi {
        val okHttpClient = okHttpClientBuilder
                .addInterceptor(baseInterceptor)
                .addInterceptor(carrierInterceptor)
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
    fun provideInvAppApi(okHttpClientBuilder: OkHttpClient.Builder, baseInterceptor: SBaseQueryMapInterceptor, okHttpLogger: HttpLoggingInterceptor): IInvAppApi {
        val okHttpClient = okHttpClientBuilder
                .addInterceptor(baseInterceptor)
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

    @Singleton
    @Provides
    fun provideHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)

    @Singleton
    @Provides
    fun provideCarrierInterceptor(): SCarrierQueryMapInterceptor = SCarrierQueryMapInterceptor()

    @Singleton
    @Provides
    fun provideBaseInterceptor(): SBaseQueryMapInterceptor = SBaseQueryMapInterceptor()

    @Singleton
    @Provides
    fun provideLoggerInterceptor(): HttpLoggingInterceptor {
        val okHttpLogger = HttpLoggingInterceptor()
        okHttpLogger.level = HttpLoggingInterceptor.Level.BODY
        return okHttpLogger
    }

    @Singleton
    @Provides
    fun provideTransferEntityToDtoUtil() = STransferEntityToDtoUtil()
}