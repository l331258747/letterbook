package com.csxs.letterbook.social;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.animation.ScaleAnimation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.csxs.core.base.LazyDiffFragment;
import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.core.imageloader.listener.IImageLoaderListener;
import com.csxs.core.utils.MmkvUtlis;
import com.csxs.core.utils.ScreenUtil;
import com.csxs.core.utils.SystemUtil;
import com.csxs.letterbook.LetterApp;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.LabelE;
import com.csxs.letterbook.entity.MapSellerStoreE;
import com.csxs.letterbook.entity.MapSocialInfoE;
import com.csxs.letterbook.entity.MapSocialUserInfoE;
import com.csxs.letterbook.entity.MarkerE;
import com.csxs.letterbook.entity.UserLabelE;
import com.csxs.letterbook.mapservice.LocationService;
import com.csxs.letterbook.mapservice.listener.LocationListener;
import com.csxs.letterbook.seller.MapSellerFragment;
import com.csxs.letterbook.social.activity.PersonalHomeActivity;
import com.csxs.letterbook.social.adapter.SocialChatMsgAdapter;
import com.csxs.letterbook.social.mvp.MapSociaUserPresenter;
import com.csxs.letterbook.social.mvp.contract.MapSocialContract;
import com.csxs.letterbook.widgets.LeftSocailPopup;
import com.csxs.letterbook.widgets.MapDrawerPopupView;
import com.csxs.letterbook.widgets.SearchOptionsPopupView;
import com.csxs.viewlib.CircleImageView;
import com.csxs.viewlib.DividerItemDecoration;
import com.csxs.viewlib.LabelsView;
import com.csxs.viewlib.fab.FloatingActionMenu;
import com.csxs.viewlib.seekbar.SizeUtils;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.enums.PopupPosition;
import com.lxj.xpopup.interfaces.XPopupCallback;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.uber.autodispose.AutoDispose.autoDisposable;

/**
 * @author: yeliu
 * created on 2020/4/13
 * description:首页 地图 展示 当前 多少公里的 用户在地图上
 */
public class MapSocialUserFragment extends LazyDiffFragment<MapSociaUserPresenter> implements LocationListener, MapSocialContract.IMapSocialView {

    @BindView(R.id.map_social)
    TextureMapView mMapView;

    @Inject
    MmkvUtlis mmkvUtlis;
//
//    @BindView(R.id.layout_social_map_bottom)
//    View layoutBottom;

    @BindView(R.id.rc_chat_msg)
    RecyclerView rcChatMsg;

    @BindView(R.id.iv_left_social)
    ImageView leImageView;
    @BindView(R.id.iv_right_social)
    ImageView ivRightSocial;

    @BindView(R.id.iv_app_notice)
    ImageView ivAppNotice;

    @BindView(R.id.ll_app_notice)
    RelativeLayout llAppNotice;


    @BindView(R.id.root_layout_function)
    RelativeLayout rootlayoutfunction;

    @BindView(R.id.civ_user_header)
    CircleImageView circleImageView;
    @BindView(R.id.user_nickname)
    TextView userNickname;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.labelsview)
    LabelsView labelsView;

    @BindView(R.id.iv_user_vip)
    ImageView userVip;

    private BaiduMap mBaiduMap;

    private LocationClient mLocationClient;

    private LocationService locationService;

    private BDLocation mapLocation;

    private BitmapDescriptor mCurrentMarker;

    private View mapMarkerView;

    private BitmapDescriptor bitmapDescriptor;

    private boolean renderFinished = false;

    private boolean isFirstLoc = true;
    private ImageView bgMarker;
    private CircleImageView ivHeader;
    private ImageView iv_vip;
    private ImageView iv_expression;

    private List<MapSocialInfoE> mapSellerStoreE;

    private ScaleAnimation mScale;
    private SocialChatMsgAdapter socialChatMsgAdapter;
    private MarkerOptions markerOptions;
    private LatLng latLng;

    private MapDrawerPopupView drawerPopupView;

    private MapSocialUserInfoE mapSocialUserInfoE;

    private boolean isAnim = true;

    @BindView(R.id.layout_seller_map_bottom)
    View layoutBottom;

    private int lationY;
    private int startLationY;

    private InfoWindow mInfoWindow;

    @Override
    public void initParam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_map_social;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //地图初始化
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setOnMapDoubleClickListener(null);
        mBaiduMap.showMapPoi(false);
        mMapView.onResume();

        // 隐藏缩放控件
        mMapView.showZoomControls(false);
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }

        UiSettings uiSettings = mBaiduMap.getUiSettings();
        uiSettings.setCompassEnabled(false);

        uiSettings.setOverlookingGesturesEnabled(false);
        //获取定位服务
        locationService = ((LetterApp) activity.getApplicationContext()).locationService;
        //监听生命周期
        getOwnerLifeCycle().addObserver(locationService);

        //初始化marker
        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.ic_my_marker);

        mapMarkerView = getLayoutInflater().inflate(R.layout.view_map_socialmarker, null, false);

        bgMarker = mapMarkerView.findViewById(R.id.iv_bg_marker);

        ivHeader = mapMarkerView.findViewById(R.id.iv_header);

        iv_vip = mapMarkerView.findViewById(R.id.iv_vip);
        iv_expression = mapMarkerView.findViewById(R.id.iv_expression);

//        bitmapDescriptor = BitmapDescriptorFactory.fromView(mapMarkerView);

        //地图与定位默认参数初始化
        mBaiduMap.setMyLocationEnabled(true);
        MyLocationConfiguration locationConfiguration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker, 0, 0);
        mBaiduMap.setMyLocationConfiguration(locationConfiguration);


        rcChatMsg.setLayoutManager(new LinearLayoutManager(mContext));
        // rcChatMsg.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL, ScreenUtil.dip2px(mContext, 10), getResources().getColor(R.color.transparent)));

        socialChatMsgAdapter = new SocialChatMsgAdapter(R.layout.item_map_chat_msg);
        rcChatMsg.setAdapter(socialChatMsgAdapter);

        initMapListener();


        int statusBarHeight = ScreenUtil.getStatusBarHeight(mContext);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) llAppNotice.getLayoutParams();
        lp.setMargins(0, statusBarHeight, 0, 0);
        llAppNotice.setLayoutParams(lp);


        /*ConstraintLayout.LayoutParams lp1 = (ConstraintLayout.LayoutParams) leImageView.getLayoutParams();
        int topmargins=(ScreenUtil.dip2px(mContext,284)+statusBarHeight)/2;
        lp1.setMargins(0,topmargins,0,0);
        leImageView.setLayoutParams(lp1);
*/
        int lationX = ScreenUtil.dip2px(mContext, 61);
        leImageView.setOnClickListener(v -> {
            float x = rootlayoutfunction.getTranslationX();
            if (x != 0) {
                ObjectAnimator translationY = ObjectAnimator.ofFloat(rootlayoutfunction, "translationX", lationX, 0);
                translationY.setDuration(200);
                translationY.start();
            } else {
                ObjectAnimator translationY = ObjectAnimator.ofFloat(rootlayoutfunction, "translationX", 0, lationX);
                translationY.setDuration(200);
                translationY.start();
            }

        });

        drawerPopupView = new MapDrawerPopupView(getContext());
    }

    private void initMapListener() {

        //设置地图渲染完成回调
        mBaiduMap.setOnMapRenderCallbadk(new BaiduMap.OnMapRenderCallback() {
            /**
             * 地图渲染完成回调函数
             */
            @Override
            public void onMapRenderFinished() {

                renderFinished = true;
//                if (mMapView != null)
//                    mMapView.onResume();
            }
        });

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {

            private int shopId;

            @Override
            public boolean onMarkerClick(Marker marker) {
                Bundle bundle = marker.getExtraInfo();
                if (bundle == null) return true;
                MarkerE marker1 = bundle.getParcelable("info");
                if (marker1 == null) return true;

//                if (marker1 != null && marker1.getId() != shopId) {
//                    mPresenter.querySingleUserInfo(marker1.getId());
//                    shopId = marker1.getId();
//                }

                if (isAnim || marker1.getId() != shopId) {
                    mPresenter.querySingleUserInfo(marker1.getId());
                    shopId = marker1.getId();

                    if (layoutBottom.getVisibility() != View.VISIBLE) {
                        layoutBottom.setVisibility(View.VISIBLE);
                        if (isAnim) {
                            ObjectAnimator translationY = ObjectAnimator.ofFloat(layoutBottom, "translationY", lationY, 0);
                            translationY.setDuration(200);
                            translationY.start();
                            isAnim = false;
                        }
                    }

                    View infoWindowView = getLayoutInflater().inflate(R.layout.view_infowindow, null, false);
                    ImageView iv_img = infoWindowView.findViewById(R.id.iv_img);
                    TextView tv_content = infoWindowView.findViewById(R.id.tv_content);

                    iv_img.setImageResource("1".equals(marker1.getSex()) ? R.drawable.pop_map_man : R.drawable.pop_map_woman);

                    BitmapDescriptor mBitmap = BitmapDescriptorFactory.fromView(infoWindowView);

                    //构造InfoWindow
                    //point 描述的位置点
                    //-100 InfoWindow相对于point在y轴的偏移量
                    mInfoWindow = new InfoWindow(mBitmap, marker.getPosition(), -SizeUtils.dp2px(activity, 60), null);

                    //使InfoWindow生效
                    mBaiduMap.showInfoWindow(mInfoWindow);
                }

                return true;
            }
        });

        startLationY = ScreenUtil.dip2px(mContext, 60);
        lationY = ScreenUtil.dip2px(mContext, 150);

        //设置地图单击事件监听
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            /**
             * 地图单击事件回调函数
             *
             * @param point 点击的地理坐标
             */
            @Override
            public void onMapClick(LatLng point) {
                if (layoutBottom != null && layoutBottom.getVisibility() == View.VISIBLE) {
                    ObjectAnimator translationY = ObjectAnimator.ofFloat(layoutBottom, "translationY", 0, lationY);
                    translationY.setDuration(200);
                    translationY.start();
                    isAnim = true;

                    translationY.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            layoutBottom.setVisibility(View.GONE);
                        }
                    });
                }

                if (mInfoWindow != null)
                    mBaiduMap.hideInfoWindow(mInfoWindow);
            }


            /**
             * 地图内 Poi 单击事件回调函数
             *
             * @param mapPoi 点击的 poi 信息
             */
            @Override
            public void onMapPoiClick(MapPoi mapPoi) {

            }
        });

        //设置地图状态监听者
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {

            public void onMapStatusChangeStart(MapStatus status) {

            }

            //地图状态开始改变。
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }

            //地图状态改变结束
            public void onMapStatusChangeFinish(MapStatus status) {
                //改变结束之后，获取地图可视范围的中心点坐标
                latLng = status.target;
                Log.e("LatLng", latLng.latitude + "-----" + latLng.longitude);


            }

            public void onMapStatusChange(MapStatus status) {
            }
        });

        //设置地图加载完成回调，该接口需要在地图加载到页面之前调用，否则不会触发回调。
        mBaiduMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
                                             @Override
                                             public void onMapLoaded() {
                                                 //尺标职位
                                                 mMapView.setScaleControlPosition(new Point(SystemUtil.getScreenWidth(activity) - 150, SystemUtil.getScreenHeight(activity) - 250));
                                             }
                                         }
        );


    }


    @Override
    protected void lazyInitData(boolean isFirstVisible) {
        if (isFirstVisible) {
            double latitude = Double.parseDouble(mmkvUtlis.getMmkv().getString("latitude", "0.00"));
            double longitude = Double.parseDouble(mmkvUtlis.getMmkv().getString("longitude", "0.00"));
            if (latitude != 0.00 && longitude != 0.00) {
//
                LatLng latLng = new LatLng(latitude, longitude);
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(latLng).zoom(20f);
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(builder.build());

                mBaiduMap.animateMapStatus(mapStatusUpdate);

                mPresenter.getMapSocialInfo(2.5, latitude, longitude, 1, 20);
                List<String> strings = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    strings.add("第" + i + "消息");
                }

                socialChatMsgAdapter.setNewData(strings);

            }
        }


        locationService.registerListener(this);
        locationService.start();


        Log.e("start2", "开始社交地图定位");


    }

    Disposable disposable;

    public void addSellerMapMarker() {
        if (mapSellerStoreE != null && mapSellerStoreE.size() > 0) {
            Log.e("商户个数", "maslist.size():个数" + mapSellerStoreE.size());
            Observable<MapSocialInfoE> mapSellerStoreObservable = Observable.fromIterable(mapSellerStoreE);
            Observable<Long> timerObservable = Observable.interval(1, TimeUnit.SECONDS);

            Observable.zip(mapSellerStoreObservable, timerObservable, new BiFunction<MapSocialInfoE, Long, MarkerE>() {
                @Override
                public MarkerE apply(MapSocialInfoE mapSocialInfoE, Long aLong) throws Exception {

                    bgMarker.setImageResource("1".equals(mapSocialInfoE.getSex()) ? R.drawable.ic_map_man : R.drawable.ic_map_woman);

                    if (mapSocialInfoE.getHeadimgurl() != null && mapSocialInfoE.getHeadimgurl().length() > 0) {
                        Glide.with(mContext)
                                .load(mapSocialInfoE.getHeadimgurl())
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        //加载失败
                                        ivHeader.setImageResource(R.drawable.ic_default_circle_store_header);
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                        //加载成功，resource为加载到的图片
                                        //如果return true，则不会再回调Target的onResourceReady（也就是不再往下传递），imageView也就不会显示加载到的图片了。
                                        if (resource != null) {
                                            ivHeader.setImageDrawable(resource);
                                        } else {
                                            ivHeader.setImageResource(R.drawable.ic_default_circle_store_header);
                                        }
                                        return false;
                                    }
                                }).submit(ScreenUtil.dip2px(mContext, 38), ScreenUtil.dip2px(mContext, 38));
                    } else {
                        ivHeader.setImageResource(R.drawable.ic_default_circle_store_header);
                    }

                    iv_vip.setVisibility(View.VISIBLE);
                    iv_expression.setVisibility(View.VISIBLE);

                    bitmapDescriptor = BitmapDescriptorFactory.fromView(mapMarkerView);

                    LatLng latLng = new LatLng(mapSocialInfoE.getLatitude(), mapSocialInfoE.getLongitude());

                    MarkerOptions ooA = new MarkerOptions().position(latLng).icon(bitmapDescriptor).zIndex(9);

                    return new MarkerE(mapSocialInfoE.getId(), mapSocialInfoE.getSex(), latLng, ooA);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .as(autoDisposable(AndroidLifecycleScopeProvider.from(MapSocialUserFragment.this, Lifecycle.Event.ON_PAUSE)))
                    .subscribe(new Observer<MarkerE>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onNext(MarkerE markerE) {
                            //判断是否已经解除订阅,已经解除后 不执行
                            if (disposable != null && !disposable.isDisposed()) {
                                Marker marker = (Marker) (mBaiduMap.addOverlay(markerE.getMarkerOptions()));
                                marker.setAnimation(getScaleAnimation());
                                Bundle bundle = new Bundle();
                                bundle.putParcelable("info", markerE);
                                marker.setExtraInfo(bundle);
                                marker.startAnimation();
                                Iterator<MapSocialInfoE> iterator = mapSellerStoreE.iterator();
                                while (iterator.hasNext()) {
                                    MapSocialInfoE obj = iterator.next();
                                    if (obj.getId() == markerE.getId()) {
                                        iterator.remove();
                                    }
                                }
                            }

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }


    private Animation getScaleAnimation() {
        if (mScale == null) {
            mScale = new ScaleAnimation(0f, 1f);
            mScale.setDuration(500);
            mScale.setRepeatCount(-100);//动画重复次数
            mScale.setInterpolator(new AccelerateInterpolator());
            mScale.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart() {
                }

                @Override
                public void onAnimationEnd() {

                }

                @Override
                public void onAnimationCancel() {
                }

                @Override
                public void onAnimationRepeat() {

                }
            });
        }


        return mScale;
    }


    @Override
    public void onLocationResult(BDLocation location) {

        if (location == null || mMapView == null) {
            return;
        }

        mapLocation = location;
        updateMap();
    }

    @Override
    public void onLocationFailure(int code) {

    }

    @Override
    protected void fragmentHidden() {

    }


    @Override
    public void onResume() {
        if (mMapView != null) {
            mMapView.onResume();
            locationService.setTAG("SocialMap");
        }
        super.onResume();
    }


    @Override
    public void onPause() {
        if (mMapView != null && renderFinished) {
            mMapView.onPause();
            locationService.setTAG("SocialMap");
        }

        locationService.stop();
        super.onPause();

    }


    public void updateMap() {

        locationService.stop();

        Observable.just("location")
                //延时第一个参数是数值，第二个参数是事件单位
                .delay(100, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(AndroidLifecycleScopeProvider.from(MapSocialUserFragment.this, Lifecycle.Event.ON_PAUSE)))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("accept", "社交地图定位" + isFirstLoc);
                        if (!isFirstLoc) {
                            return;
                        }

                        isFirstLoc = false;

                        MyLocationData locData = new MyLocationData.Builder()
                                //  .accuracy(mapLocation.getRadius())
                                // 此处设置开发者获取到的方向信息，顺时针0-360
                                .direction(mapLocation.getDirection())
                                .latitude(mapLocation.getLatitude())
                                .longitude(mapLocation.getLongitude()).build();

                        mBaiduMap.setMyLocationData(locData);

                        LatLng ll = new LatLng(mapLocation.getLatitude(), mapLocation.getLongitude());
                        MapStatus.Builder builder = new MapStatus.Builder();
                        builder.target(ll).zoom(20f);
                        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                    }
                });
    }


    public static MapSocialUserFragment newInstance(String type) {
        Bundle args = new Bundle();
        MapSocialUserFragment fragment = new MapSocialUserFragment();
        fragment.setArguments(args);
        args.putString("type", type);
        return fragment;
    }

    @Override
    public void onDestroy() {
        if (locationService != null) {
            locationService.onMapDestroy();
        }

        if (mBaiduMap != null) {
            mBaiduMap.setMyLocationEnabled(false);
            mBaiduMap.setOnMapLoadedCallback(null);
            mBaiduMap.setOnMapRenderCallbadk(null);
            mBaiduMap.setOnMapClickListener(null);
        }

        if (mMapView != null) {
            mMapView.onDestroy();
            mMapView = null;
        }


        super.onDestroy();

    }


    public static Bitmap stringToBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string.split(",")[1], Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    public void mapSocialInfoSuccess(List<MapSocialInfoE> mapSellerStoreE) {
        this.mapSellerStoreE = mapSellerStoreE;
        addSellerMapMarker();
    }

    @Override
    public void mapSocialInfoFailure(int code, String msg) {

    }

    @Override
    public void mapSocialSingleUserInfoSuccess(MapSocialUserInfoE mapSocialUserInfoE) {
        this.mapSocialUserInfoE = mapSocialUserInfoE;
        if (mapSocialUserInfoE.getHeadimgurl() != null && mapSocialUserInfoE.getHeadimgurl().length() > 0) {
            ImageLoaderV4.getInstance().displayImage(mContext, mapSocialUserInfoE.getHeadimgurl(), circleImageView, R.drawable.ic_default_circle_store_header);
        } else {
            circleImageView.setImageResource(R.drawable.ic_default_circle_store_header);
        }

        userNickname.setText(getString(R.string.str_placeholder, mapSocialUserInfoE.getNickname() != null ? mapSocialUserInfoE.getNickname() : mapSocialUserInfoE.getId() + ""));
        labelsView.setSelectType(LabelsView.SelectType.NONE);
        if (mapSocialUserInfoE.getLabel() != null && mapSocialUserInfoE.getLabel().size() > 0) {
            labelsView.setVisibility(View.VISIBLE);
            tvDistance.setText(getString(R.string.distance_placeholder, "123m"));
            labelsView.setLabels(mapSocialUserInfoE.getLabel(), new LabelsView.LabelTextProvider<UserLabelE>() {
                @Override
                public CharSequence getLabelText(TextView label, int position, UserLabelE data) {
                    return data.getLabelName();
                }
            });
        } else {
            labelsView.setVisibility(View.GONE);
        }

    }

    @Override
    public void mapSocialSingleUserInfoFailure(int code, String msg) {

    }

    @OnClick({R.id.iv_my_location, R.id.iv_options, R.id.iv_right_social, R.id.layout_seller_map_bottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_my_location:
                LatLng ll = new LatLng(mapLocation.getLatitude(), mapLocation.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                break;
            case R.id.iv_options:
                new XPopup.Builder(getContext())
                        .popupAnimation(PopupAnimation.TranslateAlphaFromTop)
                        .asCustom(new SearchOptionsPopupView(getContext()))
                        .show();
                break;
            case R.id.iv_right_social:

                ivRightSocial.setVisibility(View.GONE);

                new XPopup.Builder(getContext())
                        .setPopupCallback(new XPopupCallback() {
                            @Override
                            public void onCreated() {

                            }

                            @Override
                            public void beforeShow() {

                            }

                            @Override
                            public void onShow() {

                            }

                            @Override
                            public void onDismiss() {
                                ivRightSocial.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public boolean onBackPressed() {
                                return false;
                            }
                        })
                        .hasShadowBg(false)
                        .popupPosition(PopupPosition.Right)//右边
                        .hasStatusBarShadow(false) //启用状态栏阴影
                        .asCustom(drawerPopupView)
                        .show();
                break;
            case R.id.layout_seller_map_bottom:
                if (mapSocialUserInfoE != null) {
                    Intent intent = new Intent();
                    int userid = mapSocialUserInfoE.getId();
                    intent.putExtra("UserId", userid);
                    PersonalHomeActivity.start(mContext, PersonalHomeActivity.class, intent);
                }
                break;
        }
    }
}
