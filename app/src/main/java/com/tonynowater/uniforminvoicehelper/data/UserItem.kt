package com.tonynowater.uniforminvoicehelper.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

/**
 * Created by tonyliao on 2018/3/6.
 */
@Entity
data class UserItem(@PrimaryKey
                    @NonNull
                    var id:String,
                    var account:String,
                    var password:String) {

}