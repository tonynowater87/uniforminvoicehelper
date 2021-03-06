package com.tonynowater.uniforminvoicehelper.data.net.api

import com.tonynowater.uniforminvoicehelper.data.net.api.entity.SInvAppPrizeNumListEntity
import com.tonynowater.uniforminvoicehelper.util.STimeUtil
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by tonyliao on 2018/3/16.
 */
interface IInvAppApi {

    /**
     * 查詢中獎發票號碼清單
     * @param invTerm 日期(必須為民國年雙數月)
     */
    @POST("invapp/InvApp")
    fun getPrizeNumberList(@Query("version") version: String = SURLDefinition.INVAPP_VERSION,
                           @Query("action") action: String = SURLDefinition.ACTION_QRY_WINNING_LIST,
                           @Query("invTerm") invTerm: String): Observable<SInvAppPrizeNumListEntity>
}