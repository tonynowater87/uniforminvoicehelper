package com.tonynowater.uniforminvoicehelper.data.net.api

import com.tonynowater.uniforminvoicehelper.BuildConfig
import com.tonynowater.uniforminvoicehelper.util.sp.SP_KEY_UDID
import com.tonynowater.uniforminvoicehelper.util.sp.SSharePrefUtil
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
                .addQueryParameter("uuid", SSharePrefUtil.getString(SP_KEY_UDID))
                .addQueryParameter("appID", BuildConfig.APP_ID)
                .build()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
                .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}