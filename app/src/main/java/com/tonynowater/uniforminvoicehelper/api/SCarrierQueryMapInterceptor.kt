package com.tonynowater.uniforminvoicehelper.api

import com.tonynowater.uniforminvoicehelper.util.STimeUtil
import okhttp3.Interceptor
import okhttp3.Response


/**
 *
 * 載具查詢的共用QueryParams
 *
 * Created by tonyliao on 2018/3/15.
 */
class SCarrierQueryMapInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("version", SURLDefinition.CARRIER_VERSION)
                .addQueryParameter("cardType", SURLDefinition.CARDTYPE)
                .addQueryParameter("expTimeStamp", STimeUtil.expTimeStamp().toString())
                .addQueryParameter("timeStamp", STimeUtil.timeStamp().toString())
                .build()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
                .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}