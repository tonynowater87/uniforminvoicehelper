package com.tonynowater.uniforminvoicehelper.data.db

/**
 * Created by tonyliao on 2018/3/6.
 */
class SUserItemRepository(var userItemDao: SUserItemDao):SUserItemDao {

    override fun queryAllItems(): List<SUserEntity> = userItemDao.queryAllItems()

    override fun getUserItemById(id: Long): SUserEntity = userItemDao.getUserItemById(id)

    override fun insertUserItem(entity: SUserEntity): Long = userItemDao.insertUserItem(entity)

    override fun deleteUserItem(userEntity: SUserEntity) {
        userItemDao.deleteUserItem(userEntity)
    }
}