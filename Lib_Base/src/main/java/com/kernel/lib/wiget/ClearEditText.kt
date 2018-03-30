package com.kernel.lib.wiget

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import com.kernel.lib.R
import com.kernel.lib.helper.Helper


/**
 *
 * function: 清空的输入框(用于单行)
 * 说明：修改删除图片 android:drawableRight
 *
 * 注意：
 * setOnFocusChangeListener换成setOnFocusChangeImpl
 * addTextChangedListener换成addTextWatcherImpl
 *
 * @ author:linjunying
 */
class ClearEditText @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = android.R.attr.editTextStyle) : EditText(context, attrs, defStyle), OnFocusChangeListener, TextWatcher {

    // 删除按钮的引用
    private var mClearDrawable: Drawable? = null

    // 控件是否有焦点
    private var hasFoucs: Boolean = false

    private var onFocusChangeImpl: OnFocusChangeImpl? = null

    private var textWatcherImpl: TextWatcherImpl? = null

    init {
        init()
    }

    fun setOnFocusChangeImpl(onFocusChangeImpl: OnFocusChangeImpl) {
        this.onFocusChangeImpl = onFocusChangeImpl
    }

    fun addTextWatcherImpl(textWatcherImpl: TextWatcherImpl) {
        this.textWatcherImpl = textWatcherImpl
    }

    private fun init() {
        //获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        mClearDrawable = compoundDrawables[2]
        if (Helper.isNull(mClearDrawable)) {
            mClearDrawable = resources.getDrawable(R.drawable.base_btn_editext_clear_fw)
        }
        mClearDrawable!!.setBounds(0, 0, mClearDrawable!!.intrinsicWidth, mClearDrawable!!.intrinsicHeight)

        //默认设置隐藏图标
        setClearIconVisible(false)
        //设置焦点改变的监听
        onFocusChangeListener = this
        //设置输入框里面内容发生改变的监听
        addTextChangedListener(this)
    }


    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            if (compoundDrawables[2] != null) {

                val touchable = event.x > width - totalPaddingRight && event.x < width - paddingRight

                if (touchable) {
                    this.setText("")
                }
            }
        }

        return super.onTouchEvent(event)
    }

    /**
     * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
     */
    override fun onFocusChange(v: View, hasFocus: Boolean) {
        this.hasFoucs = hasFocus
        if (hasFocus) {
            setClearIconVisible(text.length > 0)
        } else {
            setClearIconVisible(false)
        }

        onFocusChangeImpl!!.onFocusChange(v, hasFocus)
    }


    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     * @param visible
     */
    protected fun setClearIconVisible(visible: Boolean) {
        val right = if (visible) mClearDrawable else null
        setCompoundDrawables(compoundDrawables[0],
                compoundDrawables[1], right, compoundDrawables[3])
    }


    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    override fun onTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        if (hasFoucs) {
            setClearIconVisible(s.length > 0)
        }

        textWatcherImpl!!.onTextChanged(s, start, count, after)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        textWatcherImpl!!.beforeTextChanged(s, start, count, after)
    }

    override fun afterTextChanged(s: Editable) {
        textWatcherImpl!!.afterTextChanged(s)
    }

    /**
     *
     * function: OnFocusChange实现
     *
     * @ author:linjunying
     */
    interface OnFocusChangeImpl {
        fun onFocusChange(v: View, focus: Boolean)
    }

    /**
     *
     * function: TextWatcher实现
     *
     * @ author:linjunying
     */
    interface TextWatcherImpl {

        fun afterTextChanged(s: Editable)

        fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int)

        fun onTextChanged(s: CharSequence, start: Int, count: Int, after: Int)
    }

}
