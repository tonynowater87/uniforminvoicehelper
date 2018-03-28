package com.tonynowater.uniforminvoicehelper.data.net.api.entity

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Created by tonyliao on 2018/3/28.
 */
class BaseEntityTest {

    lateinit var baseEntity: BaseEntity

    @Before
    fun setUp() {
        baseEntity = BaseEntity()
    }

    @Test
    fun init() {
        assertNull(baseEntity.v)
        assertNull(baseEntity.msg)
        assertEquals(0, baseEntity.code)
    }

    @Test
    fun is200() {
        baseEntity.code = 200
        assertTrue(baseEntity.is200())
    }

    @Test
    fun isNot200() {
        baseEntity.code = 400
        assertFalse(baseEntity.is200())
    }

    @Test
    fun msgCode() {
        val msg = "錯誤訊息"
        val code = 104
        val expectedMsgCode = "$msg($code)"
        baseEntity.msg = "錯誤訊息"
        baseEntity.code = 104
        assertEquals(expectedMsgCode, baseEntity.msgCode())
    }
}