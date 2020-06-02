package com.csxs.viewlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * recyclerview的条目分割线，此处最普通的灰线
 */
public class OrderDividerItemDecoration extends RecyclerView.ItemDecoration {

    /*
     * RecyclerView的布局方向，默认先赋值
     * 为纵向布局
     * RecyclerView 布局可横向，也可纵向
     * 横向和纵向对应的分割想画法不一样
     * */
    private int mOrientation = LinearLayoutManager.VERTICAL;

    /**
     * item之间分割线的size，默认为1
     */
    private int mItemSize = 1;

    /**
     * 绘制item分割线的画笔，和设置其属性
     * 来绘制个性分割线
     */
    private Paint mPaint;


    private int leftMargin = 0;
    private int rightMargin = 0;
    private Rect outRect;


    private boolean lastData;

    /**
     * 构造方法传入布局方向，不可不传
     *
     * @param context
     * @param orientation
     */
    public OrderDividerItemDecoration(Context context, int orientation, int size) {
        this.mOrientation = orientation;
        this.mItemSize = size;
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("请传入正确的参数");
        }
        mItemSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, mItemSize, context.getResources().getDisplayMetrics());
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#E8E8E8"));
//        mPaint.setColor(UIUtils.getColor(R.color.lightgray));
        /*设置填充*/
        mPaint.setStyle(Paint.Style.FILL);
    }


    public OrderDividerItemDecoration(Context context, int orientation, int size, int color) {
        this.mOrientation = orientation;
        this.mItemSize = size;
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("请传入正确的参数");
        }
        mItemSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, mItemSize, context.getResources().getDisplayMetrics());
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (color == 0) {
            mPaint.setColor(Color.parseColor("#ffffff"));
        } else {
            mPaint.setColor(color);
        }

        // mPaint.setColor(UIUtils.getColor(R.color.lightgray));
        /*设置填充*/
        mPaint.setStyle(Paint.Style.FILL);
    }


    public OrderDividerItemDecoration(Context context, int orientation, int size, int color, boolean lastData) {
        this.mOrientation = orientation;
        this.mItemSize = size;
        this.lastData = lastData;
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("请传入正确的参数");
        }
        mItemSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, mItemSize, context.getResources().getDisplayMetrics());
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (color == 0) {
            mPaint.setColor(Color.parseColor("#ffffff"));
        } else {
            mPaint.setColor(Color.parseColor("#00000000"));
        }

        // mPaint.setColor(UIUtils.getColor(R.color.lightgray));
        /*设置填充*/
        mPaint.setStyle(Paint.Style.FILL);
    }


    public OrderDividerItemDecoration(Context context, int orientation, int size, int color, int leftMargin, int rightMargin) {
        this.mOrientation = orientation;
        this.mItemSize = size;
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;
        if (orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("请传入正确的参数");
        }
        mItemSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, mItemSize, context.getResources().getDisplayMetrics());
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (color == 0) {
            mPaint.setColor(Color.parseColor("#ffffff"));
        } else {
            mPaint.setColor(color);
        }

        // mPaint.setColor(UIUtils.getColor(R.color.lightgray));
        /*设置填充*/
        mPaint.setStyle(Paint.Style.FILL);
    }

    public OrderDividerItemDecoration setLeftMargin(int margin) {
        this.leftMargin = margin;
        return this;
    }


    public OrderDividerItemDecoration setRightMargin(int margin) {
        this.rightMargin = margin;
        return this;
    }

    public OrderDividerItemDecoration setDividerColor(int color) {
        mPaint.setColor(color);
        return this;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    /**
     * 绘制纵向 item 分割线
     *
     * @param canvas
     * @param parent
     */
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {

            if (i == childSize - 1) {
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int top = child.getBottom() + layoutParams.bottomMargin;
                final int bottom = top + mItemSize;
                canvas.drawRect(left + leftMargin, top, right - rightMargin, bottom, mPaint);
            }


        }
    }

    /**
     * 绘制横向 item 分割线
     *
     * @param canvas
     * @param parent
     */
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {

            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + mItemSize;
            canvas.drawRect(left, top, right, bottom, mPaint);
        }
    }

    /**
     * 设置item分割线的size
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        this.outRect = outRect;
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            int childAdapterPosition = parent.getChildAdapterPosition(view);
            int lastCount = parent.getAdapter().getItemCount() - 1;
            if (childAdapterPosition == lastCount) {
                outRect.set(0, 0, 0, mItemSize);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        } else {
            outRect.set(0, 0, mItemSize, 0);
        }
    }
}