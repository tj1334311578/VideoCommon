package com.at.common.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.melancholy.router.api.launcher.Router

/**
 * @Create_time: 2022/4/28 10:00
 * @Description: ViewPager2适配器
 */

class FragmentAdapter: FragmentStateAdapter {
    private val mData: MutableList<PagerData>

    @JvmOverloads
    constructor(activity: FragmentActivity, data: MutableList<PagerData> = mutableListOf()):
            super(activity) {
        mData = data
    }

    @JvmOverloads
    constructor(fragment: Fragment, data: MutableList<PagerData> = mutableListOf()):
            super(fragment) {
        mData = data
    }

    @JvmOverloads
    constructor(manager: FragmentManager, lifecycle: Lifecycle,
                data: MutableList<PagerData> = mutableListOf()): super(manager, lifecycle) {
        mData = data
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun createFragment(position: Int): Fragment {
        val bean: PagerData = mData[position]
        val fragment: Fragment = Router.getInstance().build(bean.path).navigation() as Fragment
        bean.args?.let {
            fragment.arguments = it
        }
        return fragment
    }

    override fun getItemId(position: Int): Long {
        return mData[position].itemId
    }

    override fun containsItem(itemId: Long): Boolean {
        mData.forEach {
            if(it.itemId == itemId) {
                return true
            }
        }
        return false
    }

    fun add(bean: PagerData) {
        add(mData.size, bean)
    }

    fun add(index: Int, bean: PagerData) {
        val indexOf: Int = when {
            index < 0 -> 0
            index > mData.size -> mData.size
            else -> index
        }
        mData.add(indexOf, bean)
        notifyItemInserted(indexOf)
    }

    fun remove(bean: PagerData) {
        removeAt(mData.indexOf(bean))
    }

    fun removeAt(index: Int) {
        if(index >= 0 && index < mData.size) {
            mData.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun addAll(data: List<PagerData>) {
        val startIndex: Int = mData.size
        mData.addAll(data)
        val endIndex: Int = mData.size
        notifyItemRangeInserted(startIndex, endIndex)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        mData.clear()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(data: PagerData) {
        mData.clear()
        mData.add(data)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(data: List<PagerData>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }
}