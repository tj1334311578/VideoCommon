package com.at.common.demo

import android.os.Bundle
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.at.common.adapter.FragmentAdapter
import com.at.common.adapter.PagerData
import com.at.common.binding.BaseBindingActivity
import com.at.common.databinding.CommonTitleBarTabViewPagerBinding
import com.at.common.global.Constants
import com.at.common.setLightStatusBar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.loper7.tab_expand.ext.buildIndicator
import com.loper7.tab_expand.ext.buildText
import com.loper7.tab_expand.indicator.LinearIndicator
import com.loper7.tab_expand.text.BaseText
import com.melancholy.utils.DensityUtils
import com.melancholy.widget.tabs.TabLayoutMediators
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @Description: java类作用描述
 * @CreateDate: 2022/6/30 下午3:21
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/30 下午3:21
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@AndroidEntryPoint
class TabActivity:BaseBindingActivity<CommonTitleBarTabViewPagerBinding>(), TabLayoutMediators.TabConfigurationStrategy  {

    override fun createViewBinding(): CommonTitleBarTabViewPagerBinding {
        return CommonTitleBarTabViewPagerBinding.inflate(layoutInflater)
    }

    override fun immersiveBar() {
        super.immersiveBar()
        setLightStatusBar(true)
    }
    val mTabs = arrayListOf<String>("测试1","测试2","测试3","测试4","测试5","测试6")
    private var mMediator: TabLayoutMediators? = null
    private lateinit var adapter:FragmentAdapter
    override fun initialize(savedInstanceState: Bundle?) {
        requireTitleBar().apply {
            addBackButton()
            setTitle("资料")
        }
        requireBinding().apply {
            includedTabLayout.apply {
                val params: ViewGroup.LayoutParams = tbTab.layoutParams
                params.height = resources.getDimensionPixelOffset(com.at.common.R.dimen.dp_48)
                tbTab.layoutParams = params
                tbTab.tabMode = TabLayout.MODE_SCROLLABLE
                tbTab.buildIndicator<LinearIndicator>()
                    .setWidth(resources.getDimensionPixelOffset(com.at.common.R.dimen.dp_20))
                    .setHeight(resources.getDimensionPixelOffset(com.at.common.R.dimen.dp_2))
                    .setColor(ContextCompat.getColor(this@TabActivity, R.color.color_FF2167FF))
                    .bind()
                val size: Float = DensityUtils.px2sp(this@TabActivity,
                    resources.getDimensionPixelOffset(com.at.common.R.dimen.sp_16))
                tbTab.buildText<BaseText>()
                    .setNormalTextBold(false)
                    .setNormalTextSize(size)
                    .setSelectTextBold(true)
                    .setSelectTextSize(size)
                    .bind()
            }
            adapter = FragmentAdapter(supportFragmentManager, lifecycle)
            vpViewPager.adapter = adapter
            var itemId  = 0
            mTabs.forEach {
                val bundle = Bundle()
                itemId +=1
                bundle.putInt(Constants.KEY_DATA, (itemId +1).toInt())
                adapter.add(PagerData("/test/TelevisionFragment", itemId.toLong(), bundle))
            }
            vpViewPager.offscreenPageLimit = mTabs.size
            refreshLayout.setViewPager(vpViewPager)
            mMediator = TabLayoutMediators(includedTabLayout.tbTab, vpViewPager,
                this@TabActivity, true)
            mMediator!!.attach()
        }
    }
    override fun loadData() {
    }


    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
        tab.text = mTabs[position]
    }
}