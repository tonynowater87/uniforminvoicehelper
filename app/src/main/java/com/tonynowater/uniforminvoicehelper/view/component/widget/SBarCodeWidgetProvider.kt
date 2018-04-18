package com.tonynowater.uniforminvoicehelper.view.component.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.RemoteViews
import com.tonynowater.uniforminvoicehelper.R
import com.tonynowater.uniforminvoicehelper.util.SBarCodeImageGenerator
import com.tonynowater.uniforminvoicehelper.util.SLog
import com.tonynowater.uniforminvoicehelper.util.sp.SP_KEY_ACCOUNT
import com.tonynowater.uniforminvoicehelper.util.sp.SP_KEY_WIDGET_ID
import com.tonynowater.uniforminvoicehelper.util.sp.SSharePrefUtil
import com.tonynowater.uniforminvoicehelper.view.login.SLoginActivity

/**
 * Created by tonyliao on 2018/3/20.
 */
class SBarCodeWidgetProvider : AppWidgetProvider() {

    companion object {

        /** 更新桌面小工具的顯示狀態 */
        fun getUpdateRemoteView(context: Context): RemoteViews {
            val remoteViews = RemoteViews(context.packageName, R.layout.view_bar_code)

            val account = SSharePrefUtil.getString(SP_KEY_ACCOUNT)
            if (TextUtils.isEmpty(account)) {
                remoteViews.setImageViewResource(R.id.image_view_bar_code, R.drawable.invoice_carrier_no_login)
                remoteViews.setTextViewText(R.id.text_view_bar_code_hint, context.getString(R.string.barcode_hint_no_login))
            } else {
                val barCodeItem = SBarCodeImageGenerator.generateBarCodeImage(account)
                remoteViews.setImageViewBitmap(R.id.image_view_bar_code, barCodeItem.bitmap)
                remoteViews.setTextViewText(R.id.text_view_bar_code_content, barCodeItem.content)
                remoteViews.setTextViewText(R.id.text_view_bar_code_hint, context.getString(R.string.barcode_hint))
            }

            val intentClick = Intent(context, SLoginActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intentClick, PendingIntent.FLAG_UPDATE_CURRENT)
            remoteViews.setOnClickPendingIntent(R.id.image_view_bar_code, pendingIntent)

            return remoteViews
        }
    }

    /**
     * 更新
     */
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        for (id in appWidgetIds) {
            SLog.d("onUpdate id:$id", this@SBarCodeWidgetProvider.javaClass.simpleName)
            SSharePrefUtil.putInt(SP_KEY_WIDGET_ID, id)
            appWidgetManager.updateAppWidget(id, getUpdateRemoteView(context))
        }
    }

    /**
     * 首次新增至桌面
     */
    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        SLog.d("onEnabled", this@SBarCodeWidgetProvider.javaClass.simpleName)
    }

    /**
     * 全部刪除
     */
    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
        SLog.d("onDisabled", this@SBarCodeWidgetProvider.javaClass.simpleName)
    }

    /**
     * 刪除
     */
    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
        SLog.d("onDeleted", this@SBarCodeWidgetProvider.javaClass.simpleName)
    }

    /**
     * Receive Action
     */
    override fun onReceive(context: Context, intent: Intent?) {
        super.onReceive(context, intent)
        SLog.d("onReceive:${intent?.action}", this@SBarCodeWidgetProvider.javaClass.simpleName)
    }

    override fun onAppWidgetOptionsChanged(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetId: Int, newOptions: Bundle?) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
        SLog.d("onAppWidgetOptionsChanged", this@SBarCodeWidgetProvider.javaClass.simpleName)
    }

    override fun onRestored(context: Context?, oldWidgetIds: IntArray?, newWidgetIds: IntArray?) {
        super.onRestored(context, oldWidgetIds, newWidgetIds)
        SLog.d("onRestored", this@SBarCodeWidgetProvider.javaClass.simpleName)
    }
}