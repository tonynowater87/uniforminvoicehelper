package com.tonynowater.uniforminvoicehelper.view.test

import android.util.Log
import com.tonynowater.uniforminvoicehelper.base.IBaseView
import com.tonynowater.uniforminvoicehelper.base.SBasePresenter
import com.tonynowater.uniforminvoicehelper.base.SBaseRecyclerViewAdapter
import com.tonynowater.uniforminvoicehelper.data.db.SUserEntity
import com.tonynowater.uniforminvoicehelper.data.db.SUserItemRepository
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by tonyliao on 2018/3/15.
 */
class STestPresenter @Inject constructor(mModule: SUserItemRepository) : SBasePresenter<STestPresenter.ITestView, SUserItemRepository>(mModule), SBaseRecyclerViewAdapter.OnClickItemListener<SUserEntity> {

    private var mInsertId: Long? = null

    private val TAG = "TEST"

    fun clickTestButton(account: String, password: String) {
        Observable.create(ObservableOnSubscribe<Long> {
            val id = mModule.insertUser(SUserEntity(account = account, password = password))
            println("insert id:$id")
            it.onNext(id)
            it.onComplete()
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    mInsertId = it
                    queryAllListItem()
                    mView?.showToast("$it 加入成功!")
                }, {
                    mView?.showToast(it.message!!)
                    Log.d(TAG, it.message!!)
                })
    }

    fun getItem() {
        Observable.create(ObservableOnSubscribe<SUserEntity> {
            if (mInsertId == null) {
                it.onError(Throwable("mInsertId is null"))
            } else {
                val userItem = mModule.queryUserById(mInsertId!!)
                it.onNext(userItem)
                it.onComplete()
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    mView?.showToast("$it 取得成功!")
                }, {
                    mView?.showToast(it.message!!)
                    Log.d(TAG, it.message!!)
                })
    }

    fun queryAllListItem() {
        Observable.create(ObservableOnSubscribe<List<SUserEntity>> {
            it.onNext(mModule.queryAll())
            it.onComplete()
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    mView?.showData(it)
                }, {
                    mView?.showToast(it.message!!)
                    Log.d(TAG, it.message!!)
                })
    }

    override fun onClickItem(position: Int, entity: SUserEntity) {
        Observable.create(ObservableOnSubscribe<List<SUserEntity>> {
            mModule.deleteUser(entity)
            it.onNext(mModule.queryAll())
            it.onComplete()
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    mView?.showData(it)
                }, {
                    mView?.showToast(it.message!!)
                    Log.d(TAG, it.message!!)
                })
    }

    override fun onLongClickItem(position: Int, t: SUserEntity) {
        Observable.create(ObservableOnSubscribe<List<SUserEntity>> {
            mModule.updateUser(t.id, "update_${t.account}")
            it.onNext(mModule.queryAll())
            it.onComplete()
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    mView?.showData(it)
                }, {
                    mView?.showToast(it.message!!)
                    Log.d(TAG, it.message!!)
                })
    }

    fun deleteAll() {
        Observable.create(ObservableOnSubscribe<List<SUserEntity>> {
            mModule.deleteAll()
            it.onNext(mModule.queryAll())
            it.onComplete()
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    mView?.showData(it)
                }, {
                    mView?.showToast(it.message!!)
                    Log.d(TAG, it.message!!)
                })
    }

    interface ITestView : IBaseView {
        fun showData(listEntities: List<SUserEntity>)
    }
}