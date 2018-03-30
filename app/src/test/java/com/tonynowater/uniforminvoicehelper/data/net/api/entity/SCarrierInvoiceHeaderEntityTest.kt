package com.tonynowater.uniforminvoicehelper.data.net.api.entity

import com.tonynowater.uniforminvoicehelper.data.net.api.SBaseTestJsonData
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by tonyliao on 2018/3/29.
 */
class SCarrierInvoiceHeaderEntityTest : SBaseTestJsonData() {

    lateinit var entity: SCarrierInvoiceHeaderEntity

    @Test
    fun getV() {
        entity = gson.fromJson(json_carrier_invoice_header_200, SCarrierInvoiceHeaderEntity::class.java)
        assertEquals("0.3", entity.v)
    }

    @Test
    fun getCode() {
        entity = gson.fromJson(json_carrier_invoice_header_200, SCarrierInvoiceHeaderEntity::class.java)
        assertEquals(200, entity.code)
    }

    @Test
    fun getMsg() {
        entity = gson.fromJson(json_carrier_invoice_header_200, SCarrierInvoiceHeaderEntity::class.java)
        assertEquals("執行成功", entity.msg)
    }

    @Test
    fun is200() {
        entity = gson.fromJson(json_carrier_invoice_header_200, SCarrierInvoiceHeaderEntity::class.java)
        assertTrue(entity.is200())
    }

    @Test
    fun isNot200() {
        entity = gson.fromJson(json_carrier_invoice_header_400, SCarrierInvoiceHeaderEntity::class.java)
        assertFalse(entity.is200())
    }

    @Test
    fun msgCode() {
        entity = gson.fromJson(json_carrier_invoice_header_400, SCarrierInvoiceHeaderEntity::class.java)
        assertEquals("執行失敗(400)", entity.msgCode())
    }
}