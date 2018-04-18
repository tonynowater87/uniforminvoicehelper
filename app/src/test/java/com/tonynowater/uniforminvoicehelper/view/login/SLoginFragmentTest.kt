package com.tonynowater.uniforminvoicehelper.view.login

import android.widget.Button
import android.widget.EditText
import com.tonynowater.uniforminvoicehelper.BuildConfig
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.SBaseRobolectricTestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowProgressDialog
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil
import kotlin.test.assertNotNull

/**
 * Created by tonyliao on 2018/4/18.
 */
@RunWith(RobolectricTestRunner::class)
class SLoginFragmentTest : SBaseRobolectricTestCase() {

    private lateinit var loginFragment: SLoginFragment

    @Before
    override fun setUp() {
        super.setUp()
        loginFragment = SLoginFragment.newInstance()
        SupportFragmentTestUtil.startVisibleFragment(loginFragment, SLoginActivity::class.java, R.id.fragment_container)
    }

    @Test
    fun clickLoginButton() {
        val etAccount = loginFragment.view!!.findViewById<EditText>(R.id.et_user_account)
        etAccount.setText(BuildConfig.TestAccount)
        val etPassword = loginFragment.view!!.findViewById<EditText>(R.id.et_password_account)
        etPassword.setText(BuildConfig.TestPassword)
        loginFragment.view!!.findViewById<Button>(R.id.button_login).callOnClick()
        val progressDialog = ShadowProgressDialog.getLatestAlertDialog()
        assertNotNull(progressDialog)

        //val expectedIntent = Intent(loginFragment.context, SMainActivity::class.java)
        //val actualIntent = ShadowApplication.getInstance().nextStartedActivity
        //assertEquals(expectedIntent.component, actualIntent.component)
    }
}