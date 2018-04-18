package com.tonynowater.uniforminvoicehelper

import org.junit.Before
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

/**
 * Created by tonyliao on 2018/4/18.
 */
@Config(application = TestSApplication::class)
open class SBaseRobolectricTestCase {
    @Before
    open fun setUp() {
        ShadowLog.stream = System.out
    }
}