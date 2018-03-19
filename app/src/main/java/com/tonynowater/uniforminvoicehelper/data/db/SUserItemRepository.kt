package com.tonynowater.uniforminvoicehelper.data.db

import android.arch.lifecycle.LiveData

/**
 * Created by tonyliao on 2018/3/6.
 */
class SUserItemRepository(var userItemDao: SUserItemDao) {
    fun getUserItem(id: String): LiveData<SUserItem> = userItemDao.getUserItemById(id)
    fun deleteUserItem(userItem: SUserItem) = userItemDao.deleteAll(userItem)
}