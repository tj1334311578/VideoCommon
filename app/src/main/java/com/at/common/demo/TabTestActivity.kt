package com.at.common.demo

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.at.common.demo.databinding.TabLayoutBinding
import com.at.common.mvi.BaseMviActivity
import com.google.android.material.tabs.TabLayout
import com.loper7.tab_expand.ext.buildIndicator
import com.loper7.tab_expand.ext.buildText
import com.loper7.tab_expand.indicator.LinearIndicator
import com.loper7.tab_expand.text.BaseText
import com.melancholy.utils.DensityUtils

/**
 *
 * @Description: java类作用描述
 * @CreateDate: 2022/6/30 下午3:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/6/30 下午3:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class TabTestActivity: BaseMviActivity<TabLayoutBinding, MainViewModel>() {
    override fun initialize(savedInstanceState: Bundle?) {
        initTabLayout()
    }
    private val titles = arrayListOf("影视", "小说", "图集")

    override fun loadData() {

    }
    private fun initTabLayout() {
       requireBinding().apply {
//           titles.forEachIndexed { index, s ->
//               tabLayout.addTab(tabLayout.newTab());
//               tabLayout.getTabAt(index)?.apply {
//                   val tabView = TextView(this@TabTestActivity)
//                   tabView.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//                   tabView.text = titles[index]
//                   tabView.textSize = 14F
//                   tabView.setTextColor(Color.parseColor("#666666"))
//                   tabView.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
//                   customView = tabView
//                   tag = index
//               }
//           }
//           tabLayout.getTabAt(0)?.select()
           val params: ViewGroup.LayoutParams = tabLayout.layoutParams
           params.height = resources.getDimensionPixelOffset(com.at.common.R.dimen.dp_40)
           tabLayout.layoutParams = params
           tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
           tabLayout.buildIndicator<LinearIndicator>()
               .setHeight(resources.getDimensionPixelSize(com.at.common.R.dimen.dp_2))
               .setWidth(resources.getDimensionPixelSize(com.at.common.R.dimen.dp_20))
               .bind()
           tabLayout.buildText<BaseText>()
               .setNormalTextBold(false)
               .setNormalTextSize(
                   DensityUtils.px2sp(
                       this@TabTestActivity,
                       resources.getDimensionPixelSize(com.at.common.R.dimen.sp_14)
                   )
               )
               .setSelectTextBold(true)
               .setSelectTextSize(
                   DensityUtils.px2sp(
                       this@TabTestActivity,
                       resources.getDimensionPixelSize(com.at.common.R.dimen.sp_20)
                   )
               )
               .bind()
       }
    }
    override fun createViewBinding(): TabLayoutBinding =TabLayoutBinding.inflate(layoutInflater)

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java
}