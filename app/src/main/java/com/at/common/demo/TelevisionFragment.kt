package com.at.common.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.at.common.databinding.CommonStatusRefreshListBinding
import com.at.common.global.Constants
import com.at.common.mvi.BaseMviFragment
import com.at.common.mvi.collectState
import com.at.common.status.PageStatus
import com.at.common.status.RefreshStatus
import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.kennyc.view.MultiStateView
import com.melancholy.router.annotation.annotation.Route
import com.melancholy.router.api.launcher.Router
import com.melancholy.widget.decoration.DefaultDecoration
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Create_time: 2022/5/18 15:10
 * @Description:
 */

@AndroidEntryPoint
@Route(path = "/test/TelevisionFragment")
class TelevisionFragment: BaseMviFragment<CommonStatusRefreshListBinding, TelevisionViewModel>(),
    OnRefreshListener, OnItemClickListener {

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?,
                                   savedInstanceState: Bundle?): CommonStatusRefreshListBinding {
        return CommonStatusRefreshListBinding.inflate(inflater, container, false)
    }

    override fun getViewModelClass(): Class<TelevisionViewModel> {
        return TelevisionViewModel::class.java
    }

    override fun getViewLifecycleOwner(): LifecycleOwner {
        return requireActivity()
    }

    override fun getViewModelKey(): String? {
        return requireArguments().getString(Constants.KEY_DATA_1)
    }
    private lateinit var adapter: BaseBinderAdapter
    override fun initialize(view: View, savedInstanceState: Bundle?) {
//        getViewModel().sendEvent(TelevisionUiEvent.HeaderData(requireArguments().getInt(Constants.KEY_DATA),
//            requireArguments().getInt(Constants.KEY_DATA_1)))
        requireBinding().includedRefreshList.apply {
            srlRefresh.setOnRefreshListener(this@TelevisionFragment)
            srlRefresh.setEnableRefresh(true)
            srlRefresh.setEnableLoadMore(true)
            rvList.itemAnimator = null
            rvList.setHasFixedSize(true)
            rvList.layoutManager =LinearLayoutManager(requireContext())
//            val padding: Int = resources.getDimensionPixelSize(com.at.common.R.dimen.dp_16)
//            val cPadding: Int = resources.getDimensionPixelSize(com.at.common.R.dimen.dp_13)
//            rvList.setPadding(padding, 0, padding, 0)
//            rvList.addItemDecoration(DefaultDecoration.create(0, padding,
//                0, padding, cPadding, padding))
             adapter = BaseBinderAdapter()
            rvList.adapter = adapter

        }
    }

    override fun observer() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            getViewModel().state.collectState(TelevisionUiState::refreshStatus){
                when(it){
                    is RefreshStatus.RefreshSuccess -> requireBinding().includedRefreshList.apply { srlRefresh.finishRefresh() }
                    is RefreshStatus.RefreshLoading ->requireBinding().includedRefreshList.apply  {
                        if (!srlRefresh.isRefreshing) {
                            srlRefresh.autoRefreshAnimationOnly()
                        }
                    }
                }
            }
        }
    }

    override fun loadData() {
        val data = mutableListOf<Any>()
        for (i in 0.. 100){
            data.add(HeaderBinder.Header(i.toString()))
        }
        adapter.addItemBinder(HeaderBinder())
        adapter.setNewInstance(data)
        requireBinding().apply {
            msvView.viewState = MultiStateView.ViewState.CONTENT
        }
//        getViewModel().sendEvent(TelevisionUiEvent.InitLoadData)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        getViewModel().sendEvent(TelevisionUiEvent.RefreshData)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

    }
}