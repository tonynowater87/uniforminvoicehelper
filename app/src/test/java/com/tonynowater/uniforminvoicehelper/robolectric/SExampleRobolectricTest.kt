package com.tonynowater.uniforminvoicehelper.robolectric

import android.content.Intent
import android.widget.Button
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.TestSApplication
import com.tonynowater.uniforminvoicehelper.view.SLoginActivity
import com.tonynowater.uniforminvoicehelper.view.SMainActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowApplication
import kotlin.test.assertEquals

/**
 * 使用Robolectric框架，可以在JVM中測試Android的Code
 *
 * Created by tonyliao on 2018/3/26.
 */
@RunWith(RobolectricTestRunner::class)
@Config(application = TestSApplication::class)
class SExampleRobolectricTest {

    /**
     * 測試點擊按鈕後，是否有啟動正確的Intent
     */
//    @Test
//    fun testLogin() {
//        val loginActivity = Robolectric.setupActivity(SLoginActivity::class.java)
//        loginActivity.findViewById<Button>(R.id.button).performClick()
//
//        val expectedIntent = Intent(loginActivity, SMainActivity::class.java)
//        val actualIntent = ShadowApplication.getInstance().nextStartedActivity //取最近一個啟動的Activity 的Intent
//        assertEquals(expectedIntent.component, actualIntent.component)
//    }
}