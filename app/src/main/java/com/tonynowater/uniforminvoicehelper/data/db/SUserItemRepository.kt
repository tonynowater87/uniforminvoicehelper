package com.tonynowater.uniforminvoicehelper.data.db

/**
 * Created by tonyliao on 2018/3/6.
 */
class SUserItemRepository(var userItemDao: SUserItemDao):SUserItemDao {

    override fun queryAllItems(): List<SUserItem> = userItemDao.queryAllItems()

    override fun getUserItemById(id: Long): SUserItem = userItemDao.getUserItemById(id)

    override fun insertUserItem(item: SUserItem): Long = userItemDao.insertUserItem(item)

    override fun deleteAll(userItem: SUserItem) {
        userItemDao.deleteAll(userItem)
    }
}