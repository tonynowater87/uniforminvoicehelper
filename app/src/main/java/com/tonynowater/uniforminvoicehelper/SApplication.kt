package com.tonynowater.uniforminvoicehelper

import com.tonynowater.uniforminvoicehelper.dagger.DaggerSAppComponent
import com.tonynowater.uniforminvoicehelper.dagger.SAppModule
import com.tonynowater.uniforminvoicehelper.dagger.SNetModule
import com.tonynowater.uniforminvoicehelper.dagger.SRoomModule
import com.tonynowater.uniforminvoicehelper.util.uuid.OpenUDID_manager
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kotlin.properties.Delegates

/**
 * Created by tonyliao on 2018/3/6.
 */
class SApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        OpenUDID_manager.sync(this)
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