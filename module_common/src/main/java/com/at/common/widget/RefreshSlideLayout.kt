package com.at.common.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

/**
 *
 * @Description: java类作用描述
 * @CreateDate: 2022/7/6 下午1:50
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/6 下午1:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class RefreshSlideLayout  @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var downX: Float = 0f
    private var downY: Float = 0f
    private var isDragged = false
    private val touchSlop = ViewConfiguration.get(context).scaledTouchSlop

    private var viewPager2:ViewPager2? = null

    fun setViewPager(viewPager: ViewPager2)
     {
        viewPager2 = viewPager
     }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.x
                downY = ev.y
                isDragged = false
            }
            MotionEvent.ACTION_MOVE -> {
                if (!isDragged) {
                    val dx = abs(ev.x - downX)
                    val dy = abs(ev.y - downY)
                        isDragged = dy > touchSlop && dy > dx
                    viewPager2?.let {
                        it.isUserInputEnabled = !isDragged
                    }
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                isDragged = false
               viewPager2?.let {
                   it.isUserInputEnabled = !isDragged
               }
            }
        }
        return super.onInterceptTouchEvent(ev)
    }
}
