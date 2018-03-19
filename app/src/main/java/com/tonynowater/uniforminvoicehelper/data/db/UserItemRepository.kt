package com.tonynowater.uniforminvoicehelper.data.db

import android.arch.lifecycle.LiveData

/**
 * Created by tonyliao on 2018/3/6.
 */
class UserItemRepository(var userItemDao: UserItemDao) {
    fun getUserItem(id: String): LiveData<UserItem> = userItemDao.getUserItemById(id)
    fun deleteUserItem(userItem: UserItem) = userItemDao.deleteAll(userItem)
}