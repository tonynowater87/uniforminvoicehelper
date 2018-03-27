package com.tonynowater.uniforminvoicehelper

import android.provider.Settings
import com.tonynowater.uniforminvoicehelper.dagger.DaggerSAppComponent
import com.tonynowater.uniforminvoicehelper.dagger.SAppModule
import com.tonynowater.uniforminvoicehelper.dagger.SNetModule
import com.tonynowater.uniforminvoicehelper.dagger.SRoomModule
import com.tonynowater.uniforminvoicehelper.util.sp.SP_KEY_UDID
import com.tonynowater.uniforminvoicehelper.util.sp.SSharePrefUtil
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kotlin.properties.Delegates

/**
 * Created by tonyliao on 2018/3/6.
 */
open class SApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        initAndroidId()
    }

    private fun initAndroidId() {
        val androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        if (androidId != null) {
            SSharePrefUtil.putString(SP_KEY_UDID, androidId)
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerSAppComponent.builder()
                .sAppModule(SAppModule(this))
                .sNetModule(SNetModule())
                .sRoomModule(SRoomModule(this))
                .build()
    }

    companion object {
        var mInstance : SApplication by Delegates.notNull()
    }
}