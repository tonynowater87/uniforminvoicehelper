package com.tonynowater.uniforminvoicehelper.robolectric

/**
 * 使用Robolectric框架，可以在JVM中測試Android的Code
 *
 * Created by tonyliao on 2018/3/26.
 */
//@RunWith(RobolectricTestRunner::class)
//@Config(application = TestSApplication::class)
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