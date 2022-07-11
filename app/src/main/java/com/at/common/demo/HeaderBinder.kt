package com.at.common.demo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.BaseItemBinder
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @Create_time: 2022/5/26 09:37
 * @Description: item的头部
 */

class HeaderBinder: BaseItemBinder<HeaderBinder.Header, BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.home_header_binder_view, parent, false))
    }

    override fun convert(holder: BaseViewHolder, data: Header) {
        holder.setText(R.id.stv_title, data.name)
    }

    data class Header(val name: String)
}