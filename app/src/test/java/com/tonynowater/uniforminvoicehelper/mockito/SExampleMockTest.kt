package com.tonynowater.uniforminvoicehelper.mockito

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

/**
 *
 * 使用Mockito驗證方法的調用 (純測java)
 *
 * Created by tonyliao on 2018/3/26.
 */
class SExampleMockTest {

    //Mock是空殼，不會執行Class的方法
    @Mock
    lateinit var mClosedClass: SClosedClass

    //Spy會執行Class的方法
    //@Spy
    //lateinit var mClosedClass: SClosedClass

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    //驗證是否有執行doSomething()
    @Test
    fun mockTest1() {
        mClosedClass.doSomething()
        verify(mClosedClass).doSomething()
    }

    //it would fail
    @Test
    fun mockTest2() {
        mClosedClass.getValue()
        verify(mClosedClass).doSomething()
    }

    //指定方法回傳值
    @Test
    fun mockTest3() {
        //mocking method return value
        Mockito.`when`(mClosedClass.getValue()).thenReturn(2)
        assertEquals(2, mClosedClass.getValue())
        verify(mClosedClass).getValue()
    }

    //指定field值
    @Test
    fun mockTest4() {
        //mocking field value
        Mockito.`when`(mClosedClass.mField).thenReturn(10)
        assertEquals(10, mClosedClass.mField)
    }
}