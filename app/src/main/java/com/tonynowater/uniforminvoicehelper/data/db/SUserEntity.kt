package com.tonynowater.uniforminvoicehelper.data.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

/**
 * Created by tonyliao on 2018/3/6.
 */
@Entity(tableName = "User")//指定自訂的TableName，預設為ClassName
data class SUserEntity(@PrimaryKey(autoGenerate = true)//一定要有個PrimaryKey
                       @NonNull
                       var id: Long = 0,
                       @ColumnInfo(name = "account") var account: String,
                       @ColumnInfo(name = "password")var password: String) {
}