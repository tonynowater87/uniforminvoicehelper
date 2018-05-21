package com.tonynowater.uniforminvoicehelper.data.db

/**
 * Created by tonyliao on 2018/3/6.
 */
class SUserItemRepository(private var userItemDao: SUserItemDao):SUserItemDao {

    override fun queryAll(): List<SUserEntity> = userItemDao.queryAll()

    override fun queryUserById(id: Long): SUserEntity = userItemDao.queryUserById(id)

    override fun insertUser(entity: SUserEntity): Long = userItemDao.insertUser(entity)

    override fun deleteUser(userEntity: SUserEntity) {
        userItemDao.deleteUser(userEntity)
    }

    override fun deleteAll() {
        userItemDao.deleteAll()
    }

    override fun updateUser(id: Long, account: String) {
        userItemDao.updateUser(id, account)
    }
}