package com.tonynowater.uniforminvoicehelper.dagger

import com.tonynowater.uniforminvoicehelper.view.SLoginActivity
import com.tonynowater.uniforminvoicehelper.view.SMainActivity
import com.tonynowater.uniforminvoicehelper.view.test.STestActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by tonyliao on 2018/3/12.
 */
@Module
abstract class SActivityModule {
    @ContributesAndroidInjector()
    abstract fun contributeTestActivity(): STestActivity

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): SLoginActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): SMainActivity
}