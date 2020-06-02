/**
 * Copyright 2016 bingoogolapple
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.csxs.letterbook.widgets.wallphoto;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;


public class LauncherImage {
    private static final String TAG = LauncherImage.class.getSimpleName();
    private static LauncherImageLoader sImageLoader;

    private LauncherImage() {
    }

    private static final LauncherImageLoader getImageLoader() {
        if (sImageLoader == null) {
            synchronized (LauncherImage.class) {
                if (sImageLoader == null) {
                    if (isClassExists("com.bumptech.glide.Glide")) {
                        sImageLoader = new GlideImageLoader();
                    }
//                    else if (isClassExists("com.squareup.picasso.Picasso")) {
//                        sImageLoader = new BGAPicassoImageLoader();
//                    } else if (isClassExists("com.nostra13.universalimageloader.core.ImageLoader")) {
//                        sImageLoader = new BGAUILImageLoader();
//                    } else if (isClassExists("org.xutils.x")) {
//                        sImageLoader = new BGAXUtilsImageLoader();
//                    }
                    else {
                        throw new RuntimeException("必须在你的build.gradle文件中配置「Glide」中的某一个图片加载库的依赖");
                    }
                }
            }
        }
        return sImageLoader;
    }

    /**
     * 设置开发者自定义 ImageLoader
     *
     * @param imageLoader
     */
    public static void setImageLoader(LauncherImageLoader imageLoader) {
        sImageLoader = imageLoader;
    }

    private static final boolean isClassExists(String classFullName) {
        try {
            Class.forName(classFullName);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static void display(ImageView imageView, @DrawableRes int loadingResId, @DrawableRes int failResId, Uri path, int width, int height, final LauncherImageLoader.DisplayDelegate delegate) {
        try {
            getImageLoader().display(imageView, path, loadingResId, failResId, width, height, delegate);
        } catch (Exception e) {
            Log.d(TAG, "显示图片失败：" + e.getMessage());
        }
    }

    public static void display(ImageView imageView, @DrawableRes int placeholderResId, Uri path, int width, int height, final LauncherImageLoader.DisplayDelegate delegate) {
        display(imageView, placeholderResId, placeholderResId, path, width, height, delegate);
    }

    public static void display(ImageView imageView, @DrawableRes int placeholderResId, Uri path, int width, int height) {
        display(imageView, placeholderResId, path, width, height, null);
    }

    public static void display(ImageView imageView, @DrawableRes int placeholderResId, Uri path, int size) {
        display(imageView, placeholderResId, path, size, size);
    }

    public static void download(String path, final LauncherImageLoader.DownloadDelegate delegate) {
        try {
            getImageLoader().download(path, delegate);
        } catch (Exception e) {
            Log.d(TAG, "下载图片失败：" + e.getMessage());
        }
    }

    /**
     * 暂停加载
     *
     * @param activity
     */
    public static void pause(Activity activity) {
        getImageLoader().pause(activity);
    }

    /**
     * @param activity
     */
    public static void resume(Activity activity) {
        getImageLoader().resume(activity);
    }
}
