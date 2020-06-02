package com.csxs.letterbook.widgets.comment;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.csxs.core.utils.ScreenUtil;
import com.csxs.letterbook.LetterApp;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.ReplytoreplyE;
import com.csxs.letterbook.widgets.comment.spannable.CircleMovementMethod;
import com.csxs.letterbook.widgets.comment.spannable.SpannableClickable;

import java.util.ArrayList;
import java.util.List;


public class CommentListView extends LinearLayout {
    private int itemColor;
    private int itemContentColor;
    private int itemSelectorColor;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private List<ReplytoreplyE> mDatas;
    private LayoutInflater layoutInflater;
    private int parentId;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return onItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setDatas(List<ReplytoreplyE> datas) {
        if (datas == null) {
            datas = new ArrayList<ReplytoreplyE>();
        }
        mDatas = datas;
        notifyDataSetChanged();
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<ReplytoreplyE> getDatas() {
        return mDatas;
    }

    public CommentListView(Context context) {
        super(context);
    }

    public CommentListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public CommentListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    protected void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.PraiseListView, 0, 0);
        try {
            //textview的默认颜色
            itemColor = typedArray.getColor(R.styleable.PraiseListView_item_color, getResources().getColor(R.color.praise_item_default));

//            itemContentColor= typedArray.getColor(R.styleable.PraiseListView_item_color, getResources().getColor(R.color.praise_item_text_default));

            itemSelectorColor = typedArray.getColor(R.styleable.PraiseListView_item_selector_color, getResources().getColor(R.color.praise_item_selector_default));

        } finally {
            typedArray.recycle();
        }
    }

    public void notifyDataSetChanged() {

        removeAllViews();
        if (mDatas == null || mDatas.size() == 0) {
            return;
        }
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < mDatas.size(); i++) {
            final int index = i;
            View view = getView(index);
            if (view == null) {
                throw new NullPointerException("listview item layout is null, please check getView()...");
            }

            addView(view, index, layoutParams);
        }

    }

    private View getView(final int position) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(getContext());
        }
        View convertView = layoutInflater.inflate(R.layout.layout_item_comment, null, false);

        TextView commentTv = (TextView) convertView.findViewById(R.id.commentTv);
        final CircleMovementMethod circleMovementMethod = new CircleMovementMethod(itemSelectorColor, itemSelectorColor);

        final ReplytoreplyE bean = mDatas.get(position);
        String name = bean.getUserInfo().getNickname();
        int id = bean.getId();
        String toReplyName = "";
        if (bean.getParentInfo() != null) {
            toReplyName = bean.getParentInfo().getNickname();
        }

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(setClickableSpan(name, String.valueOf(id)));

//        if (!TextUtils.isEmpty(toReplyName)) {
//            bean.getParentId()==
//            builder.append(" 回复 ");
//            builder.append(setClickableSpan(toReplyName, String.valueOf(bean.getParentInfo().getId())));
//        }

        if (parentId != 0 && parentId != bean.getParentInfo().getId()) {
            builder.append(" 回复 ");
            builder.append(setClickableSpan(toReplyName, String.valueOf(bean.getParentInfo().getId())));
        }
        builder.append(": ");
        //转换表情字符
        String contentBodyStr = bean.getContent();
        builder.append(SpannableUrlUtils.formatUrlString(contentBodyStr));
        commentTv.setText(builder);

        commentTv.setMovementMethod(circleMovementMethod);
        commentTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circleMovementMethod.isPassToTv()) {
                    if (onItemClickListener != null) {
                        v.setTag(bean);
                        onItemClickListener.onItemClick(v, position);
                    }
                }
            }
        });
        commentTv.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (circleMovementMethod.isPassToTv()) {
                    if (onItemLongClickListener != null) {
                        onItemLongClickListener.onItemLongClick(position);
                    }
                    return true;
                }
                return false;
            }
        });

        return convertView;
    }

    @NonNull
    private SpannableString setClickableSpan(final String textStr, final String id) {
        SpannableString subjectSpanText = new SpannableString(textStr);
        subjectSpanText.setSpan(new SpannableClickable(itemColor) {
                                    @Override
                                    public void onClick(View widget) {
                                        Toast.makeText(LetterApp.appConext, textStr + " &id = " + id, Toast.LENGTH_SHORT).show();
                                    }
                                }, 0, subjectSpanText.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return subjectSpanText;
    }

    @NonNull
    private SpannableString setStringSpan(final String textStr, int color, int size) {
        SpannableString subjectSpanText = new SpannableString(textStr);

        //背景颜色
        // BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(color);
        //字体的相对大小
//        RelativeSizeSpan ab=new RelativeSizeSpan(textSize);
        AbsoluteSizeSpan as = new AbsoluteSizeSpan(size);
        // ForegroundColorSpan foregroundColorSpan=new ForegroundColorSpan(color);
        // stringBuilder.setSpan(foregroundColorSpan, start, end, mode);
        subjectSpanText.setSpan(as, 0, textStr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return subjectSpanText;
    }


    public static interface OnItemClickListener {
        public void onItemClick(View v, int position);
    }

    public static interface OnItemLongClickListener {
        public void onItemLongClick(int position);
    }


}
