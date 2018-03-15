package com.tonynowater.uniforminvoicehelper.api

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by tonyliao on 2018/3/14.
 */
interface ITestApi {
    @GET(".")
    fun hello(): Observable<Void>
}