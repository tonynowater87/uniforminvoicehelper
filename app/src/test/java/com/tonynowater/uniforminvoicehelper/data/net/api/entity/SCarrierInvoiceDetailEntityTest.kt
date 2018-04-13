package com.tonynowater.uniforminvoicehelper.data.net.api.entity

import com.tonynowater.uniforminvoicehelper.data.net.api.SBaseMockJsonData
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by tonyliao on 2018/3/29.
 */
class SCarrierInvoiceDetailEntityTest:SBaseMockJsonData() {

    lateinit var entity: SCarrierInvoiceDetailEntity

    @Test
    fun getV() {
        entity = gson.fromJson(json_carrier_invoice_detail_200, SCarrierInvoiceDetailEntity::class.java)
        assertEquals("0.3", entity.v)
    }

    @Test
    fun getCode() {
        entity = gson.fromJson(json_carrier_invoice_detail_200, SCarrierInvoiceDetailEntity::class.java)
        assertEquals(200, entity.code)
    }

    @Test
    fun getMsg() {
        entity = gson.fromJson(json_carrier_invoice_detail_200, SCarrierInvoiceDetailEntity::class.java)
        assertEquals("執行成功", entity.msg)
    }

    @Test
    fun is200() {
        entity = gson.fromJson(json_carrier_invoice_detail_200, SCarrierInvoiceDetailEntity::class.java)
        assertTrue(entity.is200())
    }

    @Test
    fun isNot200() {
        entity = gson.fromJson(json_carrier_invoice_detail_400, SCarrierInvoiceDetailEntity::class.java)
        assertFalse(entity.is200())
    }

    @Test
    fun msgCode() {
        entity = gson.fromJson(json_carrier_invoice_detail_400, SCarrierInvoiceDetailEntity::class.java)
        assertEquals("執行失敗(400)", entity.msgCode())
    }
}