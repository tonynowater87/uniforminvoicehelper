package com.tonynowater.uniforminvoicehelper.junit

import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import kotlin.test.expect

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * 基本的UnitTest (純測kotlin/java)
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class SExampleUnitTest {

    // =+ @Before & @After
    //@Rule
    //@JvmField
    //val customRule = CustomRule()

    companion object {

        // =+ @BeforeClass & @AfterClass
        @ClassRule
        @JvmField
        val customRule = SCustomRule()

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

    @Test
    fun kotlinASCIITest() {
        val a = 'a'
        assertEquals(97, a.toInt())
    }

    /**
     *  用委託 by XXX的方式可指定覆寫的方法
     */
    @Test
    fun kotlinDelegateTest() {
        val delegate = DelegationTest("origin")
        assertEquals("override toString", delegate.toString())
    }

    /**
     * 用with簡化一連串對某一類的操作
     *
     * with語句會返回最後一行
     */
    @Test
    fun kotlinWithTest() {
        val withCandy = with(StringBuilder()) {
            append("W")
            append("I")
            append("T")
            append("H")
            for (number in 0..5) {
                append(number)
            }
            toString()
        }

        assertEquals("WITH012345", withCandy)
    }

    /**
     * 可用apply語句簡化類的初始化
     */
    @Test
    fun kotlinApplyTest() {
        val testItem = TestItem().apply {
            v1 = "v1"
            v2 = "v2"
        }

        assertEquals("v1", testItem.v1)
        assertEquals("v2", testItem.v2)
    }

    @Test
    fun kotlinLetTest() {
        var letTest: TestItem? = null

        //通過let語句，在?.let之後，如果為空不會有任何操作，只有在非空的時候才會執行let之後的操作
        letTest?.let {

        }
        assertNull(letTest)

        letTest = TestItem()
        letTest.let {
            it.v1 = "let v1"
        }

        assertEquals("let v1", letTest.v1)
    }

    /**
     * 使用?:簡化為null的判斷處理
     */
    @Test
    fun kotlinElvisTest() {
        var s1: String? = "s1"
        //if s1 != null, r = 2
        var r = s1?.length ?: -1
        assertEquals(2, r)

        s1 = null
        //if s1 == null, r = -1
        r = s1?.length ?: -1
        assertEquals(-1, r)
    }

    /**
     * 重載二元運算符
     * A * B times
     * A / B div
     * A % B mod
     * A + B plus
     * A - B minus
     */
    @Test
    fun kotlinOverloadMethod() {
        val item1 = OperatorTestItem(10)
        val item2 = OperatorTestItem(5)
        assertEquals(15, (item1 + item2).v1)
        assertEquals(5, (item1 - item2).v1)
        assertEquals(50, (item1 * item2).v1)
        assertEquals(2, (item1 / item2).v1)
        assertEquals(0, (item1 % item2).v1)
    }

    /**
     * 函式變數測試
     */
    @Test
    fun kotlinFunctionVariesTest() {

        val sum = { x: Int, y: Int -> x + y }
        val action = { "action" }

        val sum2: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
        val action2: () -> Int = { 42 }

        assertEquals(3, sum(1, 2))
        assertEquals(3, sum2(1, 2))
        assertEquals("action", action())
        assertEquals(42, action2())
    }

    /**
     * Function return Function 測試
     */
    @Test
    fun kotlinFunctionReturnTypeTest() {
        val v1 = getShippingCostCalculator(Delivery.STANDARD)
        assertEquals(1.3, v1(1), 0.1)

        val v2 = getShippingCostCalculator(Delivery.EXPEDITED)
        assertEquals(8.1, v2(1), 0.1)
    }

    /**
     * 擴充已有類別Function測試
     */
    @Test
    fun kotlinExtendFunction() {
        val s = "kotlin6666666666"
        assertEquals("kotlin", s.filter { it in 'a'..'z' })
    }

    /**
     * 使用 lambda 取代 interface
     */
    @Test
    fun kotlinStrategicPatternTest() {
        val worker1 = Worker({ true })
        assertEquals(true, worker1.work())

        val worker2 = Worker({ false })
        assertEquals(false, worker2.work())
    }
}