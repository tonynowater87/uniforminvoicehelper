package com.tonynowater.uniforminvoicehelper.api

import com.tonynowater.uniforminvoicehelper.api.entity.SRiderInvoiceHeaderEntity
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by tonyliao on 2018/3/14.
 */
interface INetApi {

    /**
     * 載具發票表頭查詢
     */
    @POST("invServ/InvServ")
    fun getRiderInvoiceHeader(@Query("cardNo") cardNo: String,
                              @Query("startDate") startDate: String = "2018/02/1",
                              @Query("endDate") endDate: String = "2018/02/28",
                              @Query("onlyWinningInv") onlyWinningInv: String = "N",
                              @Query("cardEncrypt") cardEncrypt: String): Observable<SRiderInvoiceHeaderEntity>
}
