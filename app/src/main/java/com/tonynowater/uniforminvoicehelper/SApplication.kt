package com.tonynowater.uniforminvoicehelper

import android.app.Activity
import android.app.Application
import com.tonynowater.uniforminvoicehelper.dagger.DaggerSAppComponent
import com.tonynowater.uniforminvoicehelper.dagger.SAppComponent
import com.tonynowater.uniforminvoicehelper.dagger.SAppModule
import com.tonynowater.uniforminvoicehelper.dagger.SNetModule
import com.tonynowater.uniforminvoicehelper.util.uuid.OpenUDID_manager
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by tonyliao on 2018/3/6.
 */
class SApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    private lateinit var mAppComponent: SAppComponent

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        OpenUDID_manager.sync(this)
        mAppComponent = DaggerSAppComponent.builder()
                .sAppModule(SAppModule(this))
                .sNetModule(SNetModule())
                .build()
        mAppComponent.inject(this)
    }

    companion object {
        var mInstance : SApplication by Delegates.notNull()
    }
}