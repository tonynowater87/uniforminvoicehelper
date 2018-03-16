package com.tonynowater.uniforminvoicehelper.api

import com.tonynowater.uniforminvoicehelper.api.entity.SCarrierInvoiceDetailEntity
import com.tonynowater.uniforminvoicehelper.api.entity.SCarrierInvoiceHeaderEntity
import com.tonynowater.uniforminvoicehelper.util.sp.SP_KEY_ACCOUNT
import com.tonynowater.uniforminvoicehelper.util.sp.SP_KEY_PASSWORD
import com.tonynowater.uniforminvoicehelper.util.sp.SSharePrefUtil
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
    fun getCarrierInvoiceHeader(@Query("cardNo") cardNo: String,
                                @Query("startDate") startDate: String = "2018/02/1",
                                @Query("endDate") endDate: String = "2018/02/28",
                                @Query("onlyWinningInv") onlyWinningInv: String = "N",
                                @Query("action") action: String = SURLDefinition.ACTION_HEADER,
                                @Query("cardEncrypt") cardEncrypt: String): Observable<SCarrierInvoiceHeaderEntity>

    /**
     * 載具發票明細查詢
     */
    @POST("invServ/InvServ")
    fun getCarrierInvoiceDetail(@Query("cardNo") cardNo: String = SSharePrefUtil.getString(SP_KEY_ACCOUNT),
                                @Query("action") action: String = SURLDefinition.ACTION_DETAIL,
                                @Query("cardEncrypt") cardEncrypt: String = SSharePrefUtil.getString(SP_KEY_PASSWORD),
                                @Query("invNum") invNum: String,
                                @Query("invDate") invDate: String,
                                @Query("sellerName") sellerName: String = "",
                                @Query("amount") amount: String = ""): Observable<SCarrierInvoiceDetailEntity>
}
