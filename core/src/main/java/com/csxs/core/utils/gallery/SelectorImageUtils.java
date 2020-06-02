package com.csxs.core.utils.gallery;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.csxs.core.R;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.style.PictureWindowAnimationStyle;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/4/11
 * description:图片选择
 */
public class SelectorImageUtils {


     /**
      * 多选
      * @param activity
      * @param maxSelectNum
      * @param listener
      */
     public static void  launcher(Activity activity,int maxSelectNum,OnResultCallbackListener listener){
          launcher(activity,maxSelectNum,false,listener);
     }

     /**
      * 单选
      * @param activity
      * @param header
      * @param listener
      */
     public static void  launcher(Activity activity,boolean header,OnResultCallbackListener listener){
          launcher(activity,1,header,listener);
     }


     /**
      * 多选
      * @param fragment
      * @param maxSelectNum
      * @param listener
      */
     public static void  launcher(Fragment fragment,int maxSelectNum,OnResultCallbackListener listener){
          if(null!=fragment.getActivity()){
               launcher(fragment.getActivity(),maxSelectNum,false,listener);
          }if (null!= fragment.getContext()){
               launcher((Activity) fragment.getContext(),maxSelectNum,false,listener);
          }
     }

     /**
      * 单选
      * @param fragment
      * @param header
      * @param listener
      */
     public static void  launcher(Fragment fragment,boolean header,OnResultCallbackListener listener){
          if(null!=fragment.getActivity()){
               launcher(fragment.getActivity(),1,header,listener);
          }if (null!= fragment.getContext()){
               launcher(fragment.getActivity(),1,header,listener);
          }
     }


     private static void launcher(Activity activity,int maxSelectNum,boolean header,OnResultCallbackListener listener){
          //动画
          PictureWindowAnimationStyle mWindowAnimationStyle = new PictureWindowAnimationStyle();
          mWindowAnimationStyle.ofAllAnimation(R.anim.picture_anim_up_in, R.anim.picture_anim_down_out);

          PictureSelector.create(activity)
                 .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                 .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                 .theme(R.style.picture_Sina_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style v2.3.3后 建议使用setPictureStyle()动态方式
                 .isWeChatStyle(false)// 是否开启微信图片选择风格
                 .isUseCustomCamera(false)// 是否使用自定义相机
                 .setLanguage(-1)// 设置语言，默认中文
//                 .setPictureStyle(mPictureParameterStyle)// 动态自定义相册主题
//                 .setPictureCropStyle(mCropParameterStyle)// 动态自定义裁剪主题
                 .setPictureWindowAnimationStyle(mWindowAnimationStyle)// 自定义相册启动退出动画
                 .isWithVideoImage(false)// 图片和视频是否可以同选,只在ofAll模式下有效
                 .loadCacheResourcesCallback(GlideCacheEngine.createCacheEngine())// 获取图片资源缓存，主要是解决华为10部分机型在拷贝文件过多时会出现卡的问题，这里可以判断只在会出现一直转圈问题机型上使用
                 //.setOutputCameraPath()// 自定义相机输出目录，只针对Android Q以下，例如 Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +  File.separator + "Camera" + File.separator;
                 //.setButtonFeatures(CustomCameraView.BUTTON_STATE_BOTH)// 设置自定义相机按钮状态
                 .maxSelectNum(maxSelectNum)// 最大图片选择数量
                 .minSelectNum(1)// 最小选择数量
                // .maxVideoSelectNum(1) // 视频最大选择数量，如果没有单独设置的需求则可以不设置，同用maxSelectNum字段
                 //.minVideoSelectNum(1)// 视频最小选择数量，如果没有单独设置的需求则可以不设置，同用minSelectNum字段
                 .imageSpanCount(4)// 每行显示个数
                 .isReturnEmpty(false)// 未选择数据时点击按钮是否可以返回
                 //.isAndroidQTransform(false)// 是否需要处理Android Q 拷贝至应用沙盒的操作，只针对compress(false); && enableCrop(false);有效,默认处理
                 //.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)// 设置相册Activity方向，不设置默认使用系统
                 .isOriginalImageControl(false)// 是否显示原图控制按钮，如果设置为true则用户可以自由选择是否使用原图，压缩、裁剪功能将会失效
                 //.bindCustomPlayVideoCallback(callback)// 自定义视频播放回调控制，用户可以使用自己的视频播放界面
                 //.bindPictureSelectorInterfaceListener(interfaceListener)// 提供给用户的一些额外的自定义操作回调
                 //.cameraFileName(System.currentTimeMillis() +".jpg")    // 重命名拍照文件名、如果是相册拍照则内部会自动拼上当前时间戳防止重复，注意这个只在使用相机时可以使用，如果使用相机又开启了压缩或裁剪 需要配合压缩和裁剪文件名api
                 //.renameCompressFile(System.currentTimeMillis() +".jpg")// 重命名压缩文件名、 注意这个不要重复，只适用于单张图压缩使用
                 //.renameCropFileName(System.currentTimeMillis() + ".jpg")// 重命名裁剪文件名、 注意这个不要重复，只适用于单张图裁剪使用
                 .selectionMode(header ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选
                 .isSingleDirectReturn(false)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                 .previewImage(true)// 是否可预览图片
                // .previewVideo(cb_preview_video.isChecked())// 是否可预览视频
                 //.querySpecifiedFormatSuffix(PictureMimeType.ofJPEG())// 查询指定后缀格式资源
                // .enablePreviewAudio(cb_preview_audio.isChecked()) // 是否可播放音频
                 .isCamera(false)// 是否显示拍照按钮
                 //.isMultipleSkipCrop(false)// 多图裁剪时是否支持跳过，默认支持
                 //.isMultipleRecyclerAnimation(false)// 多图裁剪底部列表显示动画效果
                 .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                 //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                 .enableCrop(true)// 是否裁剪
                 //.basicUCropConfig()//对外提供所有UCropOptions参数配制，但如果PictureSelector原本支持设置的还是会使用原有的设置
                 .compress(true)// 是否压缩
                 //.compressQuality(80)// 图片压缩后输出质量 0~ 100
                 .synOrAsy(false)//同步true或异步false 压缩 默认同步
                 //.queryMaxFileSize(10)// 只查多少M以内的图片、视频、音频  单位M
                 //.compressSavePath(getPath())//压缩图片保存地址
                 //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效 注：已废弃
                 //.glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度 注：已废弃
                 .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                // .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
                 .isGif(false)// 是否显示gif图片
                 .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
                 .circleDimmedLayer(false)// 是否圆形裁剪
                 //.setCircleDimmedColor(ContextCompat.getColor(getContext(), R.color.app_color_white))// 设置圆形裁剪背景色值
                 //.setCircleDimmedBorderColor(ContextCompat.getColor(getApplicationContext(), R.color.app_color_white))// 设置圆形裁剪边框色值
                 //.setCircleStrokeWidth(3)// 设置圆形裁剪边框粗细
                 .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                 .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                 .openClickSound(false)// 是否开启点击声音
                 //.selectionMedia(mAdapter.getData())// 是否传入已选图片
                 //.isDragFrame(false)// 是否可拖动裁剪框(固定)
                 //.videoMinSecond(10)
                 //.videoMaxSecond(15)
                 //.recordVideoSecond(10)//录制视频秒数 默认60s
                 //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                 //.cropCompressQuality(90)// 注：已废弃 改用cutOutQuality()
                 .cutOutQuality(90)// 裁剪输出质量 默认100
                 .minimumCompressSize(100)// 小于100kb的图片不压缩
                 //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                 //.cropImageWideHigh()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                 //.rotateEnabled(false) // 裁剪是否可旋转图片
                 //.scaleEnabled(false)// 裁剪是否可放大缩小图片
                 //.videoQuality()// 视频录制质量 0 or 1
                 //.videoSecond()//显示多少秒以内的视频or音频也可适用
                 .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                // .forResult(listener);

     }


     /**
      * 拍照
      * @param fragment
      */
     public static void  launcherCamera(Fragment fragment){
          if(null!=fragment.getActivity()){
               launcherCamera(fragment.getActivity());
          }if (null!= fragment.getContext()){
               launcherCamera(fragment.getActivity());
          }
     }

     /**
      * 拍照
      */
     public static void launcherCamera(Activity activity){
          //动画
//          PictureWindowAnimationStyle mWindowAnimationStyle = new PictureWindowAnimationStyle();
//          mWindowAnimationStyle.ofAllAnimation(R.anim.picture_anim_up_in, R.anim.picture_anim_down_out);
          PictureSelector.create(activity)
                  .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                  .theme(R.style.picture_Sina_style)// 主题样式设置 具体参考 values/styles
                  .loadImageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                 // .setPictureStyle(mPictureParameterStyle)// 动态自定义相册主题
                 // .setPictureCropStyle(mCropParameterStyle)// 动态自定义裁剪主题
                  //.setPictureWindowAnimationStyle(mWindowAnimationStyle)// 自定义相册启动退出动画
                  .maxSelectNum(1)// 最大图片选择数量
                  .isUseCustomCamera(false)// 是否使用自定义相机
                  //.setOutputCameraPath()// 自定义相机输出目录，只针对Android Q以下，例如 Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +  File.separator + "Camera" + File.separator;
                  .minSelectNum(1)// 最小选择数量
                  //.querySpecifiedFormatSuffix(PictureMimeType.ofPNG())// 查询指定后缀格式资源
                  .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                  //.cameraFileName(System.currentTimeMillis() + ".jpg")// 使用相机时保存至本地的文件名称,注意这个只在拍照时可以使用
                  //.renameCompressFile(System.currentTimeMillis() + ".jpg")// 重命名压缩文件名、 注意这个不要重复，只适用于单张图压缩使用
                  //.renameCropFileName(System.currentTimeMillis() + ".jpg")// 重命名裁剪文件名、 注意这个不要重复，只适用于单张图裁剪使用
                  .loadCacheResourcesCallback(GlideCacheEngine.createCacheEngine())// 获取图片资源缓存，主要是解决华为10部分机型在拷贝文件过多时会出现卡的问题，这里可以判断只在会出现一直转圈问题机型上使用
                  .previewImage(true)// 是否可预览图片
                  .previewVideo(false)// 是否可预览视频
                  .enablePreviewAudio(false) // 是否可播放音频
                  .isCamera(false)// 是否显示拍照按钮
                  .enableCrop(true)// 是否裁剪
                  //.basicUCropConfig()//对外提供所有UCropOptions参数配制，但如果PictureSelector原本支持设置的还是会使用原有的设置
                  .compress(true)// 是否压缩
                  .synOrAsy(false)//同步true或异步false 压缩 默认同步
                  .compressQuality(60)// 图片压缩后输出质量
                  //.glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                  .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                  //.hideBottomControls(!cb_hide.isChecked())// 是否显示uCrop工具栏，默认不显示
                  //.isGif(cb_isGif.isChecked())// 是否显示gif图片
                  .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
                  .circleDimmedLayer(false)// 是否圆形裁剪
                  //.setCircleDimmedColor(ContextCompat.getColor(this, R.color.app_color_white))// 设置圆形裁剪背景色值
                  //.setCircleDimmedBorderColor(ContextCompat.getColor(this, R.color.app_color_white))// 设置圆形裁剪边框色值
                  //.setCircleStrokeWidth(3)// 设置圆形裁剪边框粗细
                  .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                  .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                //  .openClickSound(cb_voice.isChecked())// 是否开启点击声音
                 // .selectionMedia(mAdapter.getData())// 是否传入已选图片
                  //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                  //.cropCompressQuality(90)// 废弃 改用cutOutQuality()
                  .cutOutQuality(90)// 裁剪输出质量 默认100
                  .minimumCompressSize(100)// 小于100kb的图片不压缩
                  //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                  //.cropImageWideHigh()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                  //.rotateEnabled() // 裁剪是否可旋转图片
                  //.scaleEnabled()// 裁剪是否可放大缩小图片
                  //.videoQuality()// 视频录制质量 0 or 1
                  //.videoSecond()////显示多少秒以内的视频or音频也可适用
                  .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
//                        .forResult(new OnResultCallbackListener() {
//                            @Override
//                            public void onResult(List<LocalMedia> result) {
//                                for (LocalMedia media : result) {
//                                    Log.i(TAG, "是否压缩:" + media.isCompressed());
//                                    Log.i(TAG, "压缩:" + media.getCompressPath());
//                                    Log.i(TAG, "原图:" + media.getPath());
//                                    Log.i(TAG, "是否裁剪:" + media.isCut());
//                                    Log.i(TAG, "裁剪:" + media.getCutPath());
//                                    Log.i(TAG, "是否开启原图:" + media.isOriginal());
//                                    Log.i(TAG, "原图路径:" + media.getOriginalPath());
//                                    Log.i(TAG, "Android Q 特有Path:" + media.getAndroidQToPath());
//                                }
//                                mAdapter.setList(result);
//                                mAdapter.notifyDataSetChanged();
//                            }
//
//                            @Override
//                            public void onCancel() {
//                                Log.i(TAG, "PictureSelector Cancel");
//                            }
//                        });


     }

}
