package com.tonynowater.uniforminvoicehelper

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Created by tonyliao on 2018/3/26.
 */
class CustomRule:TestRule {
    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {

            fun before() {
                println("before custom rule")
            }

            override fun evaluate() {
                before()
                base?.evaluate()//執行測試方法
                after()
            }

            fun after() {
                println("after custom rule")
            }
        }
    }
}