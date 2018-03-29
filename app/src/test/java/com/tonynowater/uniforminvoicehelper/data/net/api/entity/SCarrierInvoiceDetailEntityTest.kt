package com.tonynowater.uniforminvoicehelper.data.net.api.entity

import com.google.gson.Gson
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by tonyliao on 2018/3/29.
 */
class SCarrierInvoiceDetailEntityTest {

    lateinit var entity: SCarrierInvoiceDetailEntity
    var gson = Gson()
    val json_200 = "{\"v\":\"0.3\",\"code\":200,\"msg\":\"執行成功\",\"onlyWinningInv\":\"N\",\"details\":[{\"rowNum\":1,\"invNum\":\"BD42245191\",\"cardType\":\"3J0002\",\"cardNo\":\"/W6MU8BQ\",\"sellerName\":\"統一超商股份有限公司台中縣第二０一分公司\",\"invStatus\":\"2\",\"invDonatable\":true,\"amount\":16,\"invPeriod\":\"10704\",\"donateMark\":0,\"invDate\":{\"year\":107,\"month\":3,\"date\":10,\"day\":6,\"hours\":0,\"minutes\":0,\"seconds\":0,\"time\":1520611200000,\"timezoneOffset\":-480},\"sellerBan\":\"27233523\",\"sellerAddress\":\"台中市大里區西榮里益民路二段137號\",\"invoiceTime\":\"19:11:22\"},{\"rowNum\":2,\"invNum\":\"BE00044328\",\"cardType\":\"3J0002\",\"cardNo\":\"/W6MU8BQ\",\"sellerName\":\"統一超商股份有限公司台中市第三三三分公司\",\"invStatus\":\"2\",\"invDonatable\":true,\"amount\":69,\"invPeriod\":\"10704\",\"donateMark\":0,\"invDate\":{\"year\":107,\"month\":3,\"date\":17,\"day\":6,\"hours\":0,\"minutes\":0,\"seconds\":0,\"time\":1521216000000,\"timezoneOffset\":-480},\"sellerBan\":\"53672174\",\"sellerAddress\":\"台中市大里區國光路二段257號\",\"invoiceTime\":\"10:40:07\"},{\"rowNum\":3,\"invNum\":\"BF86939590\",\"cardType\":\"3J0002\",\"cardNo\":\"/W6MU8BQ\",\"sellerName\":\"統一超商股份有限公司台中市第三二八分公司\",\"invStatus\":\"2\",\"invDonatable\":true,\"amount\":68,\"invPeriod\":\"10704\",\"donateMark\":0,\"invDate\":{\"year\":107,\"month\":3,\"date\":19,\"day\":1,\"hours\":0,\"minutes\":0,\"seconds\":0,\"time\":1521388800000,\"timezoneOffset\":-480},\"sellerBan\":\"53666383\",\"sellerAddress\":\"台中市西屯區台灣大道三段688號690號\",\"invoiceTime\":\"18:25:24\"}]}"
    val json_400 = "{\"v\":\"0.3\",\"code\":400,\"msg\":\"執行失敗\",\"onlyWinningInv\":\"N\",\"details\":[{\"rowNum\":1,\"invNum\":\"BD42245191\",\"cardType\":\"3J0002\",\"cardNo\":\"/W6MU8BQ\",\"sellerName\":\"統一超商股份有限公司台中縣第二０一分公司\",\"invStatus\":\"2\",\"invDonatable\":true,\"amount\":16,\"invPeriod\":\"10704\",\"donateMark\":0,\"invDate\":{\"year\":107,\"month\":3,\"date\":10,\"day\":6,\"hours\":0,\"minutes\":0,\"seconds\":0,\"time\":1520611200000,\"timezoneOffset\":-480},\"sellerBan\":\"27233523\",\"sellerAddress\":\"台中市大里區西榮里益民路二段137號\",\"invoiceTime\":\"19:11:22\"},{\"rowNum\":2,\"invNum\":\"BE00044328\",\"cardType\":\"3J0002\",\"cardNo\":\"/W6MU8BQ\",\"sellerName\":\"統一超商股份有限公司台中市第三三三分公司\",\"invStatus\":\"2\",\"invDonatable\":true,\"amount\":69,\"invPeriod\":\"10704\",\"donateMark\":0,\"invDate\":{\"year\":107,\"month\":3,\"date\":17,\"day\":6,\"hours\":0,\"minutes\":0,\"seconds\":0,\"time\":1521216000000,\"timezoneOffset\":-480},\"sellerBan\":\"53672174\",\"sellerAddress\":\"台中市大里區國光路二段257號\",\"invoiceTime\":\"10:40:07\"},{\"rowNum\":3,\"invNum\":\"BF86939590\",\"cardType\":\"3J0002\",\"cardNo\":\"/W6MU8BQ\",\"sellerName\":\"統一超商股份有限公司台中市第三二八分公司\",\"invStatus\":\"2\",\"invDonatable\":true,\"amount\":68,\"invPeriod\":\"10704\",\"donateMark\":0,\"invDate\":{\"year\":107,\"month\":3,\"date\":19,\"day\":1,\"hours\":0,\"minutes\":0,\"seconds\":0,\"time\":1521388800000,\"timezoneOffset\":-480},\"sellerBan\":\"53666383\",\"sellerAddress\":\"台中市西屯區台灣大道三段688號690號\",\"invoiceTime\":\"18:25:24\"}]}"

    @Before
    fun setUp() {}

    @After
    fun tearDown() {}

    @Test
    fun getV() {
        entity = gson.fromJson(json_200, SCarrierInvoiceDetailEntity::class.java)
        assertEquals("0.3", entity.v)
    }

    @Test
    fun getCode() {
        entity = gson.fromJson(json_200, SCarrierInvoiceDetailEntity::class.java)
        assertEquals(200, entity.code)
    }

    @Test
    fun getMsg() {
        entity = gson.fromJson(json_200, SCarrierInvoiceDetailEntity::class.java)
        assertEquals("執行成功", entity.msg)
    }

    @Test
    fun is200() {
        entity = gson.fromJson(json_200, SCarrierInvoiceDetailEntity::class.java)
        assertTrue(entity.is200())
    }

    @Test
    fun isNot200() {
        entity = gson.fromJson(json_400, SCarrierInvoiceDetailEntity::class.java)
        assertFalse(entity.is200())
    }

    @Test
    fun msgCode() {
        entity = gson.fromJson(json_400, SCarrierInvoiceDetailEntity::class.java)
        assertEquals("執行失敗(400)", entity.msgCode())
    }
}