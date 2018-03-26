package com.tonynowater.uniforminvoicehelper.mockito

/**
 * Created by tonyliao on 2018/3/26.
 */
class SClosedClass(val mField: Int) {
    fun doSomething() {
        println("doSomething")
    }

    fun getValue(): Int {
        val value = 3
        println("getValue:$value")
        return value
    }
}