package com.tonynowater.uniforminvoicehelper.data.db

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by tonyliao on 2018/3/19.
 */
@Singleton
class SCustomViewModuleFactory @Inject constructor(var repository: SUserItemRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SUserItemViewModel::class.java)) {
            return SUserItemViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}