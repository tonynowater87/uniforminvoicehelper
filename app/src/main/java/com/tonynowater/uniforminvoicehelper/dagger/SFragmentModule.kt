package com.tonynowater.uniforminvoicehelper.dagger

import android.app.Application
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.util.STimeUtil
import com.tonynowater.uniforminvoicehelper.view.login.SLoginFragment
import com.tonynowater.uniforminvoicehelper.view.prize.SPrizeNumberListFragment
import com.tonynowater.uniforminvoicehelper.view.query.*
import com.tonynowater.uniforminvoicehelper.view.test.STestFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Created by tonyliao on 2018/3/13.
 */
@Module
abstract class SFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeTestFragment(): STestFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): SLoginFragment

    @ContributesAndroidInjector
    abstract fun contributeSPrizeNumberListFragment(): SPrizeNumberListFragment

    @ContributesAndroidInjector(modules = [SCarrierQueryFragmentModule::class])
    abstract fun contributeSCarrierQueryFragment(): SCarrierQueryFragment

    @ContributesAndroidInjector
    abstract fun contributeSCarrierQueryListFragment(): SCarrierQueryListFragment
}

@Module
class SCarrierQueryFragmentModule {

    private fun provideThisMonthQueryData(): SCarrierQueryDateData {
        val type = ECarrierQueryType.THIS_MONTH
        return SCarrierQueryDateData(type, STimeUtil.getDateItemByType(type))
    }

    private fun provideLastMonthQueryData(): SCarrierQueryDateData {
        val type = ECarrierQueryType.LAST_MONTH
        return SCarrierQueryDateData(type, STimeUtil.getDateItemByType(type))
    }

    private fun providePrizeRecordQueryData(): SCarrierQueryDateData {
        val type = ECarrierQueryType.PRIZE_RECORD
        return SCarrierQueryDateData(type, STimeUtil.getDateItemByType(type))
    }

    private fun provideCustomMonthQueryData(): SCarrierQueryDateData {
        val type = ECarrierQueryType.CUSTOM_MONTH
        return SCarrierQueryDateData(type, STimeUtil.getDateItemByType(type))
    }

    @Provides
    fun provideSCarrierQueryDateItems(application: Application): List<SCarrierQueryAdapter.BaseViewPagerItem> {
        return listOf(SCarrierQueryAdapter.BaseViewPagerItem(SCarrierQueryListFragment.newInstance(provideThisMonthQueryData()), application.getString(R.string.this_month))
                , SCarrierQueryAdapter.BaseViewPagerItem(SCarrierQueryListFragment.newInstance(provideLastMonthQueryData()), application.getString(R.string.last_month))
                , SCarrierQueryAdapter.BaseViewPagerItem(SCarrierQueryListFragment.newInstance(providePrizeRecordQueryData()), application.getString(R.string.prize_record))
                , SCarrierQueryAdapter.BaseViewPagerItem(SCarrierQueryListFragment.newInstance(provideCustomMonthQueryData()), application.getString(R.string.custom_month)))
    }

    @Provides
    fun provideChildFragmentManager(fragment: SCarrierQueryFragment) = fragment.childFragmentManager
}