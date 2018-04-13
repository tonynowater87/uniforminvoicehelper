package com.tonynowater.uniforminvoicehelper.data.net.api.entity

import com.tonynowater.uniforminvoicehelper.data.net.api.SBaseMockJsonData
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by tonyliao on 2018/3/29.
 */
class SInvAppPrizeNumListEntityTest : SBaseMockJsonData() {

    lateinit var entity: SInvAppPrizeNumListEntity

    @Test
    fun getV() {
        entity = gson.fromJson(json_prize_number_list_200, SInvAppPrizeNumListEntity::class.java)
        assertEquals("0.2", entity.v)
    }

    @Test
    fun getCode() {
        entity = gson.fromJson(json_prize_number_list_200, SInvAppPrizeNumListEntity::class.java)
        assertEquals(200, entity.code)
    }

    @Test
    fun getMsg() {
        entity = gson.fromJson(json_prize_number_list_200, SInvAppPrizeNumListEntity::class.java)
        assertEquals("查詢成功", entity.msg)
    }

    @Test
    fun is200() {
        entity = gson.fromJson(json_prize_number_list_200, SInvAppPrizeNumListEntity::class.java)
        assertTrue(entity.is200())
    }

    @Test
    fun isNot200() {
        entity = gson.fromJson(json_prize_number_list_400, SInvAppPrizeNumListEntity::class.java)
        assertFalse(entity.is200())
    }

    @Test
    fun msgCode() {
        entity = gson.fromJson(json_prize_number_list_400, SInvAppPrizeNumListEntity::class.java)
        assertEquals("查詢失敗(400)", entity.msgCode())
    }
}