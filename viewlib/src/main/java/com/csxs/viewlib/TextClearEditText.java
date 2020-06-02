package com.csxs.viewlib;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import androidx.appcompat.widget.AppCompatEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 带清空功能的EditText
 */
public class TextClearEditText extends AppCompatEditText implements View.OnFocusChangeListener,TextWatcher {

    // 特殊下标位置
    private static final int PHONE_INDEX_3 = 3;
    private static final int PHONE_INDEX_4 = 4;
    private static final int PHONE_INDEX_8 = 8;
    private static final int PHONE_INDEX_9 = 9;



    /**
     * 删除Drawable引用
     */
    private Drawable mClearDrawable;
    /**
     * 控件是否有焦点
     */
    private boolean hasFocus;

    // 构造方法 1->2->3
    public TextClearEditText(Context context) {
        this(context, null);
    }

    public TextClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public TextClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // 初始化方法
    private void init() {
        // Drawable顺序左上右下，0123
        // 获取DrawRight内容
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            // 未设置默认DrawableRight
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mClearDrawable = getResources().getDrawable(R.drawable.icon_clear, null);
            } else {
                mClearDrawable = getResources().getDrawable(R.drawable.icon_clear);
            }
        }
        // 设置Drawable大小和位置
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        // 添加焦点改变监听
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    // 焦点改变事件
    // 当焦点在时，通过判断输入值，实现隐藏和现实DrawableRight
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus = hasFocus;
        if (hasFocus) {
            // 焦点存在，而且有输入值
            setDrawableRightightVisible(getText().length() > 0);
        } else {
            // 焦点不存在时候，隐藏DrawableRight
            setDrawableRightightVisible(false);
        }
    }

    /**
     * 隐藏或显示DrawableRight
     *
     * @param visible true=显示，false=隐藏
     */
    private void setDrawableRightightVisible(boolean visible) {
        // 控件setCompoundDrawables设置Drawable，隐藏则设置null，左上右下
        Drawable drawableRight = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], drawableRight, getCompoundDrawables()[3]);
    }

    // 触摸事件-实现ClearDrawable清空功能
    // 1、如何判断Drawable被点击？——通过判断点击的位置
    // 2、什么时候实现清空？——当ClearDrawable不为空且被点击
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 判断手指是否触摸
        if (event.getAction() == MotionEvent.ACTION_UP) {
            // 判断ClearDrawable(DrawableRight)是否为空
            if (getCompoundDrawables()[2] != null) {
                // 当按下的位置 在EditText的宽度 - 图标到控件右边的间距 - 图标的宽度 与 EditText的宽度 - 图标到控件右边的间距之间，算点击了图标，竖直方向不用考虑
                // getTotalPaddingRight获取右侧图标以及右侧Padding和
                // getPaddingRight获取右侧Padding值
                boolean isTouchRight = event.getX() > (getWidth() - getTotalPaddingRight()) && (event.getX() < ((getWidth() - getPaddingRight())));
                if (isTouchRight) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }




    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFocus) {
            setDrawableVisible(s.length() > 0);
        }

        if (onTextChangedListener != null){
            onTextChangedListener.onTextChanged(s, start, count, after);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (onTextChangedListener!=null){
            onTextChangedListener.beforeTextChanged(s,start,count,after);
        }

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (onTextChangedListener!=null){
            onTextChangedListener.afterTextChanged(s);
        }
    }

    protected void setDrawableVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }



    // 文本改变接口监听
    public interface OnTextChangedListener {
        void beforeTextChanged(CharSequence s, int start, int count, int after);

        void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter);

        void afterTextChanged(Editable s);
    }

    private OnTextChangedListener onTextChangedListener;

    public void setOnTextChangedListener(OnTextChangedListener onTextChangedListener) {
        this.onTextChangedListener = onTextChangedListener;
    }






}
