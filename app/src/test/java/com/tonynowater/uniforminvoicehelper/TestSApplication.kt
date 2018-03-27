package com.tonynowater.uniforminvoicehelper

import org.robolectric.TestLifecycleApplication
import java.lang.reflect.Method

/**
 * Robolectric測試指定的Applicaiton
 *
 * Created by tonyliao on 2018/3/26.
 */
class TestSApplication : SApplication(), TestLifecycleApplication {

    override fun onCreate() {
        super.onCreate()
        println("onCreate")
    }

    override fun beforeTest(method: Method?) {
        println("beforeTest ${method?.toString()}")
    }

    override fun prepareTest(test: Any?) {
        println("prepareTest ${test?.toString()}")
    }

    override fun afterTest(method: Method?) {
        println("afterTest ${method?.toString()}")
    }
}