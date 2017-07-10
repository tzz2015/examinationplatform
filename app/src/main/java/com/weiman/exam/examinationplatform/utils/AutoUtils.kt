package com.weiman.exam.examinationplatform.utils

import android.app.Activity
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.weiman.exam.examinationplatform.utils.StatusBarUtil.getStatusBarHeight

/**

 * @author ZhengJingle
 */

object AutoUtils {

    private var displayWidth: Int = 0
    private var displayHeight: Int = 0

    private var designWidth: Int = 0
    private var designHeight: Int = 0

    private var textPixelsRate: Double = 0.toDouble()

    fun setSize(act: Activity, hasStatusBar: Boolean, designWidth: Int, designHeight: Int) {
        if (designWidth < 1 || designHeight < 1) return

        val display = act.windowManager.defaultDisplay
        val width = display.width
        var height = display.height

        if (hasStatusBar) {
            height -= getStatusBarHeight(act)
        }

        AutoUtils.displayWidth = width
        AutoUtils.displayHeight = height

        AutoUtils.designWidth = designWidth
        AutoUtils.designHeight = designHeight

        val displayDiagonal = Math.sqrt(Math.pow(AutoUtils.displayWidth.toDouble(), 2.0) + Math.pow(AutoUtils.displayHeight.toDouble(), 2.0))
        val designDiagonal = Math.sqrt(Math.pow(AutoUtils.designWidth.toDouble(), 2.0) + Math.pow(AutoUtils.designHeight.toDouble(), 2.0))
        AutoUtils.textPixelsRate = displayDiagonal / designDiagonal
    }

    /**
     * 自适应activity
     */
    fun autoView(activity: Activity) {
        if (displayHeight < 1 || displayWidth < 1) return
        var view = activity.window.decorView
        autoView(view)
    }

    /**
     * 自适应view
     */
    private fun autoView(view: View?) {
        if (view == null || displayWidth < 1 || displayHeight < 1) return
        autoTextSize(view)
        autoSize(view)
        autoPadding(view)
        autoMargin(view)
        if (view is ViewGroup) {
            auto(view)
        }
    }

    private fun auto(viewGroup: ViewGroup) {
        val count = viewGroup.childCount

        for (i in 0..count - 1) {

            val child = viewGroup.getChildAt(i)

            if (child != null) {
                autoView(child)
            }
        }
    }

    /**
     * 自适应margin
     */
    private fun autoMargin(view: View) {
        if (view.layoutParams !is ViewGroup.MarginLayoutParams)
            return

        val lp = view.layoutParams as ViewGroup.MarginLayoutParams ?: return

        lp.leftMargin = getDisplayWidthValue(lp.leftMargin)
        lp.topMargin = getDisplayHeightValue(lp.topMargin)
        lp.rightMargin = getDisplayWidthValue(lp.rightMargin)
        lp.bottomMargin = getDisplayHeightValue(lp.bottomMargin)
    }

    /**
     * 自适应padding
     */
    private fun autoPadding(view: View) {
        var l = view.paddingLeft
        var t = view.paddingTop
        var r = view.paddingRight
        var b = view.paddingBottom

        l = getDisplayWidthValue(l)
        t = getDisplayHeightValue(t)
        r = getDisplayWidthValue(r)
        b = getDisplayHeightValue(b)

        view.setPadding(l, t, r, b)
    }

    /**
     * 自适应控件大小
     */
    private fun autoSize(view: View) {
        val lp = view.layoutParams ?: return

        if (lp.width > 0) {
            lp.width = getDisplayWidthValue(lp.width)
        }

        if (lp.height > 0) {
            lp.height = getDisplayHeightValue(lp.height)
        }

    }

    /**
     * 自适应文本大小
     */
    private fun autoTextSize(view: View) {
        if (view is TextView) {
            val designPixels = view.textSize.toDouble()
            val displayPixels = textPixelsRate * designPixels
            view.includeFontPadding = false
            view.setTextSize(TypedValue.COMPLEX_UNIT_PX, displayPixels.toFloat())
        }
    }

    /**
     * 屏幕显示高
     */
    private fun getDisplayHeightValue(height: Int): Int {
        if (height < 2) {
            return height
        }
        return height * displayHeight / designHeight
    }

    /**
     * 屏幕显示宽
     */
    private fun getDisplayWidthValue(width: Int): Int {
        if (width < 2) {
            return width
        }
        return width * displayWidth / designWidth
    }


}
