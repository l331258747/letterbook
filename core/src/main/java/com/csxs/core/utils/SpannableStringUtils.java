package com.csxs.core.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import androidx.annotation.ColorInt;

/**
 * @author: yeliu
 * created on 2020/4/22
 * description:
 */
public class SpannableStringUtils {

    /**
     *
     * @param str 文本
     * @param color 颜色
     * @param start 开始位置
     * @param end   结束文字
     * @param textSize  文字大小
     * @param mode   模式
     * @return
     */
    public static SpannableStringBuilder textFormat(String str, @ColorInt int color, int start, int end, float textSize,int mode) {

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(str);
        //背景颜色
       // BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(color);
        //字体的相对大小
        RelativeSizeSpan ab=new RelativeSizeSpan(textSize);
        ForegroundColorSpan foregroundColorSpan=new ForegroundColorSpan(color);
        stringBuilder.setSpan(foregroundColorSpan, start, end, mode);
        stringBuilder.setSpan(ab,start,end,mode);
        return stringBuilder;
    }


    public static void addDrawableInEnd(TextView textView, Context context, Drawable drawable, String str) {

        if (drawable == null) {
            return;
        }
        TextPaint paint = textView.getPaint();// 获取文本控件字体样式
        Paint.FontMetrics fm = paint.getFontMetrics();
        int textFontHeight = (int) Math.ceil(fm.descent - fm.top) + 2;// 计算字体高度座位图片高度
        int imageWidth = drawable.getIntrinsicWidth() * textFontHeight
                / drawable.getIntrinsicHeight();// 计算图片根据字体大小等比例缩放后的宽度
        drawable.setBounds(0, ScreenUtil.dip2px(context, 1), drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());// 缩放图片  也可根据实际需求

        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM); //   ImageSpan.ALIGN_BASELINE放置位置
        String space = " ";
        str = str + space;
        int strLength = str.length();
        SpannableString ss = new SpannableString(str);
        ss.setSpan(span, strLength - 1, strLength, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(ss.subSequence(0, strLength));
    }

    public static void addDrawableInEnd2(TextView textView, Context context, Drawable drawable, String str) {

        if (drawable == null) {
            return;
        }
        TextPaint paint = textView.getPaint();// 获取文本控件字体样式
        Paint.FontMetrics fm = paint.getFontMetrics();
        int textFontHeight = (int) Math.ceil(fm.descent - fm.top) + 2;// 计算字体高度座位图片高度
        int imageWidth = drawable.getIntrinsicWidth() * textFontHeight
                / drawable.getIntrinsicHeight();// 计算图片根据字体大小等比例缩放后的宽度
//        drawable.setBounds(0, ScreenUtil.dip2px(context, 1), imageWidth,
//                textFontHeight);// 缩放图片  也可根据实际需求
        drawable.setBounds(0, ScreenUtil.dip2px(context, 1), drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());// 缩放图片  也可根据实际需求

        VerticalImageSpan span = new VerticalImageSpan(drawable); //   ImageSpan.ALIGN_BASELINE放置位置
        String space = " ";
        str = str + space;
        int strLength = str.length();
        SpannableString ss = new SpannableString(str);
        ss.setSpan(span, strLength - 1, strLength, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(ss.subSequence(0, strLength));
    }



    public static void addDrawableInEndHtml(TextView textView, Activity context, int image, String str){
        String html=str+" <img src='%1$s'>";
        html=String.format(html,image);
        textView.setText(Html.fromHtml(html, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                 int id= Integer.valueOf(source);
                 Drawable drawable=  context.getResources().getDrawable(id);
                drawable.setBounds(0, ScreenUtil.dip2px(context, 2), drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight());// 缩放图片  也可根据实际需求
                return drawable;
            }
        },null));
    }

}
