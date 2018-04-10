package com.tonynowater.uniforminvoicehelper.dagger

import com.tonynowater.uniforminvoicehelper.view.prize.SPrizeNumberListFragment
import com.tonynowater.uniforminvoicehelper.view.query.SCarrierQueryFragment
import com.tonynowater.uniforminvoicehelper.view.query.SCarrierQueryListFragment
import com.tonynowater.uniforminvoicehelper.view.test.STestFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by tonyliao on 2018/3/13.
 */
@Module
abstract class SFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeTestFragment(): STestFragment

    @ContributesAndroidInjector
    abstract fun contributeSPrizeNumberListFragment(): SPrizeNumberListFragment

    @ContributesAndroidInjector
    abstract fun contributeSCarrierQueryFragment(): SCarrierQueryFragment

    @ContributesAndroidInjector
    abstract fun contributeSCarrierQueryListFragment(): SCarrierQueryListFragment
}