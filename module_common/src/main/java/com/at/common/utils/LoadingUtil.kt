package com.at.common.utils

import android.content.Context
import com.at.common.dialog.LoadingDialog

/**
 * @Create_time: 2022/5/19 15:28
 * @Description: loading加载框
 */

object LoadingUtil {
    @Volatile
    @JvmStatic
    private var sDialog: LoadingDialog? = null

    @JvmStatic
    @Synchronized
    fun showLoading(context: Context, hint: String = "") {
        if(sDialog != null) {
            dismiss()
        }
        sDialog = LoadingDialog(context, hint)
        sDialog!!.show()
    }

    @JvmStatic
    @Synchronized
    fun dismiss() {
        if(sDialog != null && sDialog!!.isShowing) {
            sDialog!!.dismiss()
        }
        sDialog = null
    }


}
