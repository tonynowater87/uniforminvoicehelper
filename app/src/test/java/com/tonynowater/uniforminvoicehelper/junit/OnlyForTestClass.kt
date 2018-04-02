package com.tonynowater.uniforminvoicehelper.junit

/**
 * Created by tonyliao on 2018/4/2.
 */
class DelegationTest(private val originString: String) : CharSequence by originString {
    override fun toString(): String {
        return "override toString"
    }
}

class TestItem {
    lateinit var v1: String
    lateinit var v2: String
}

/**
 * 重載二元運算符
 * A * B times
 * A / B div
 * A % B mod
 * A + B plus
 * A - B minus
 */
class OperatorTestItem(var v1: Int) {
    operator fun plus(other: OperatorTestItem): OperatorTestItem {
        return OperatorTestItem(v1 + other.v1)
    }

    operator fun minus(other: OperatorTestItem): OperatorTestItem {
        return OperatorTestItem(v1 - other.v1)
    }

    operator fun times(other: OperatorTestItem): OperatorTestItem {
        return OperatorTestItem(v1 * other.v1)
    }

    operator fun div(other: OperatorTestItem): OperatorTestItem {
        return OperatorTestItem(v1 / other.v1)
    }

    operator fun mod(other: OperatorTestItem): OperatorTestItem {
        return OperatorTestItem(v1 % other.v1)
    }
}

enum class Delivery {
    STANDARD, EXPEDITED
}

/**
 * 依據enum return 不同的 function
 */
fun getShippingCostCalculator(delivery: Delivery): (Int) -> Double {
    if (delivery == Delivery.EXPEDITED) {
        return { 6 + 2.1 * it }
    }
    return { 1.3 * it }
}

/**
 * 擴展String類Function，實現字串過濾
 */
fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (index in 0 until length) {
        val element = get(index)
        if (predicate(element)) sb.append(element)
    }
    return sb.toString()
}

/**
 * 策略模式
 */
class Worker(private val strategy: () -> Boolean) {
    fun work():Boolean {
        return strategy()
    }
}