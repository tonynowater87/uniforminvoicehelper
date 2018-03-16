package com.tonynowater.uniforminvoicehelper.api

import com.tonynowater.uniforminvoicehelper.BuildConfig
import com.tonynowater.uniforminvoicehelper.util.uuid.OpenUDID_manager
import okhttp3.Interceptor
import okhttp3.Response

/**
 *
 * 全部查詢共用的QueryParams
 *
 * Created by tonyliao on 2018/3/16.
 */
class SBaseQueryMapInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("version", SURLDefinition.VERSION.toString())
                .addQueryParameter("uuid", OpenUDID_manager.getOpenUDID())
                .addQueryParameter("appID", BuildConfig.APP_ID)
                .build()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
                .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}