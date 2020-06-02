package com.csxs.letterbook.widgets.imagewatcher;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.Toast;

import com.csxs.core.BaseCoreApp;
import com.csxs.core.utils.MmkvUtlis;
import com.csxs.letterbook.LetterApp;
import com.csxs.letterbook.R;
import com.tencent.mmkv.MMKV;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/4/24
 * description:
 */
public class ImageWatcherUtils {

    private DecorationLayout   layDecoration;
    private ImageWatcherHelper iwHelper;
    private Activity activity;



    public ImageWatcherUtils(Activity activity){
        this.activity=activity;
    }


    public void init() {


            layDecoration = new DecorationLayout(activity);

            iwHelper = ImageWatcherHelper.with(activity, new GlideSimpleLoader())

                    .setTranslucentStatus(0)

                    .setErrorImageRes(R.drawable.ic_image_pholder_dynamic)

                    .setOnPictureLongPressListener(new ImageWatcher.OnPictureLongPressListener() {
                        @Override
                        public void onPictureLongPress(ImageView v, Uri uri, int pos) {
                            // 长按图片的回调，你可以显示一个框继续提供一些复制，发送等功能
                            Toast.makeText(v.getContext().getApplicationContext(), "长按了第" + (pos + 1) + "张图片", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setOnStateChangedListener(new ImageWatcher.OnStateChangedListener() {
                        @Override
                        public void onStateChangeUpdate(ImageWatcher imageWatcher, ImageView clicked, int position, Uri uri, float animatedValue, int actionTag) {
//                        Log.e("IW", "onStateChangeUpdate [" + position + "][" + uri + "][" + animatedValue + "][" + actionTag + "]");
                        }

                        @Override
                        public void onStateChanged(ImageWatcher imageWatcher, int position, Uri uri, int actionTag) {
//                        if (actionTag == ImageWatcher.STATE_ENTER_DISPLAYING) {
//                            Toast.makeText(mContext, "点击了图片 [" + position + "]" + uri + "", Toast.LENGTH_SHORT).show();
//                        } else if (actionTag == ImageWatcher.STATE_EXIT_HIDING) {
//                            Toast.makeText(mContext, "退出了查看大图", Toast.LENGTH_SHORT).show();
//                        }
                        }
                    })
                    .setOtherView(layDecoration)
                    .addOnPageChangeListener(layDecoration);

    }


    public ImageWatcherHelper getIwHelper() {
        return iwHelper;
    }

    public void setIwHelper(ImageWatcherHelper iwHelper) {
        this.iwHelper = iwHelper;
    }

    public void onDestroy(){

        if(iwHelper!=null){
            iwHelper=null;
            layDecoration=null;
        }

    }
}
