package com.at.common.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import com.at.common.R

/**
 * @Create_time: 2022/5/19 15:33
 * @Description:
 */

class LoadingDialog(context: Context, private val hint: String): Dialog(context,
    R.style.LoadingDialogStyle) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.common_dialog_loading)
        findViewById<TextView>(R.id.tv_hint).text = hint
        setCancelable(false)
    }

}
