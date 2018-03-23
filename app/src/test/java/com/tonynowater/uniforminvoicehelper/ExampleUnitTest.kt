package com.tonynowater.uniforminvoicehelper

import org.junit.*

import org.junit.Assert.*
import kotlin.test.expect

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * gradlew test Debug?UnitTest // debug版本
 * gradlew test Release?UnitTest // release版本
 *
 * 測試報告路徑
 * project_root/app/build/reports/tests/testDebug?UnitTest/index.html
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

    // =+ @Before & @After
    //@Rule
    //@JvmField
    //val customRule = CustomRule()

    companion object {

        // =+ @BeforeClass & @AfterClass
        @ClassRule
        @JvmField
        val customRule = CustomRule()

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            println("beforeClass")
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            println("afterClass")
        }
    }

    @Before
    fun before() {
        println("before")
    }

    @After
    fun after() {
        println("after")
    }

    @Test()
    @Throws(Exception::class)
    fun addition_isCorrect() {
        println("addition_isCorrect")
        assertEquals(4, (2 + 2).toLong())
    }

    //測試預期發生的Exception
    @Test(expected = ArithmeticException::class)
    fun divideZero() {
        println("divideZero")
        2 / 0
    }

    @Test
    @Ignore("not implement yet")
    fun ignoreTest() {

    }

    @Test
    fun kotlinJunitTest() {
        expect(1, { 1 })
    }
}