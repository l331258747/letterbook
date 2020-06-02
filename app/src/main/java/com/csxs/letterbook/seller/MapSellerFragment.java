package com.csxs.letterbook.seller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import androidx.lifecycle.Lifecycle;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.animation.ScaleAnimation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.csxs.core.base.LazyDiffFragment;
import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.core.utils.MmkvUtlis;
import com.csxs.core.utils.ScreenUtil;
import com.csxs.letterbook.LetterApp;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.MapSellerDetailsInfoE;
import com.csxs.letterbook.entity.MapSellerStoreE;
import com.csxs.letterbook.entity.MarkerE;
import com.csxs.letterbook.mapservice.LocationService;
import com.csxs.letterbook.mapservice.listener.LocationListener;
import com.csxs.letterbook.mapservice.listener.LocationSensorEventListener;
import com.csxs.letterbook.seller.activity.SellerHomeActivity;
import com.csxs.letterbook.seller.activity.SellerSearchActivity;
import com.csxs.letterbook.seller.mvp.contract.MapSellerContract;
import com.csxs.letterbook.seller.mvp.presenter.MapSellerPresenter;
import com.csxs.letterbook.widgets.MapDrawerPopupView;
import com.csxs.viewlib.NiceImageView;
import com.csxs.letterbook.widgets.SearchOptionsPopupView;
import com.csxs.viewlib.DrawableCenterTextView;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.enums.PopupPosition;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

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
 * description:首页 地图 展示多少公里 商家位置fragment
 */
public class MapSellerFragment extends LazyDiffFragment<MapSellerPresenter> implements LocationListener, LocationSensorEventListener, MapSellerContract.IMapsellerView {

    @BindView(R.id.map_seller)
    TextureMapView mMapView;

    @BindView(R.id.ll_search_seller)
    RelativeLayout llSearchSeller;
    @BindView(R.id.iv_search_msg)
    ImageView ivSearchMsg;
    @BindView(R.id.line_msg)
    View lineMsg;
    @BindView(R.id.et_search)
    TextView etSearch;
    @BindView(R.id.line_search_et)
    View lineSearchEt;
    @BindView(R.id.iv_search_options)
    ImageView ivSearchOptions;

    @BindView(R.id.root)
    RelativeLayout rootView;

    @BindView(R.id.layout_seller_map_bottom)
    View layoutBottom;

    @BindView(R.id.iv_seller_header)
    NiceImageView ivSellerLogo;

    @BindView(R.id.tv_seller_store_name)
    TextView tvSellerStoreName;

    @BindView(R.id.iv_map_store_task)
    ImageView ivMapStoreTask;

    @BindView(R.id.tv_seller_store_address)
    TextView tvSellerStoreAddress;

    @BindView(R.id.tv_store_map_type_tag)
    TextView tvSellerStoreType;

    @BindView(R.id.tv_distance)
    TextView tvSellerMapDistance;

    @BindView(R.id.tv_seller_store_activies)
    TextView tvSellerStoreActivies;

    @BindView(R.id.tv_cash_gift)
    TextView tvSellerCashGift;

    @BindView(R.id.tv_seller_map_navigation)
    DrawableCenterTextView tvSellerMapNavigation;

    @Inject
    Gson gson;

    @Inject
    MmkvUtlis mmkvUtlis;

    @Inject
    ImageLoaderV4 imageLoaderV4;

    private BaiduMap mBaiduMap;

    private LocationService locationService;

    private BitmapDescriptor mCurrentMarker;
    private View mapMarkerView;
    private List<OverlayOptions> options;

    private boolean isFirstLoc = true;
    private BitmapDescriptor bitmapDescriptor;
    private LatLng ll;

    private Double lastX = 0.0;
    private int mCurrentDirection = 0;
    private float mCurrentAccracy;

    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;

    private MyLocationData locData;

    private float[] values, r, gravity, geomagnetic;


    private LayoutTransition layoutTransition;
    private int lationY;
    private int startLationY;

    private boolean isAnim = true;

    private MapDrawerPopupView drawerPopupView;


    private boolean RenderFinished = false;

    private BDLocation mapLocation;


    private ScaleAnimation mScale;

    private SearchOptionsPopupView popupView;
    private NiceImageView nivLogo;
    private ImageView ivShopType;
    private List<MapSellerStoreE> mapSellerStores;
    private MapSellerDetailsInfoE sellerDetailsInfo;

    @Override
    public void initParam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_map_seller;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setOnMapDoubleClickListener(null);
        mBaiduMap.showMapPoi(false);
        mMapView.onResume();

        // 隐藏缩放控件
        mMapView.showZoomControls(false);//缩放控件
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);//Logo 隐藏
        }

        UiSettings uiSettings = mBaiduMap.getUiSettings();
        uiSettings.setCompassEnabled(false);

        locationService = ((LetterApp) activity.getApplicationContext()).locationService;
        getOwnerLifeCycle().addObserver(locationService);

        mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.ic_my_marker);

        mapMarkerView = getLayoutInflater().inflate(R.layout.view_map_marker, null, false);

        nivLogo = mapMarkerView.findViewById(R.id.niv_logo);

        ivShopType = mapMarkerView.findViewById(R.id.iv_shop_type);

        mBaiduMap.setMyLocationEnabled(true);

        MyLocationConfiguration locationConfiguration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker,0,0);

        mBaiduMap.setMyLocationConfiguration(locationConfiguration);

        int statusBarHeight = ImmersionBar.getStatusBarHeight(this) + ScreenUtil.dip2px(mContext, 10);
        FrameLayout.LayoutParams relativeLayout = (FrameLayout.LayoutParams) llSearchSeller.getLayoutParams();
        relativeLayout.topMargin = statusBarHeight;
        llSearchSeller.setLayoutParams(relativeLayout);


        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {

            private int shopId;

            @Override
            public boolean onMarkerClick(Marker marker) {
                Bundle bundle = marker.getExtraInfo();
                MarkerE marker1 = bundle.getParcelable("info");
//                if (marker1.getId() != shopId) {
                if (isAnim || marker1.getId() != shopId) {
                    mPresenter.querySingleSellerInfo(marker1.getId(), marker1.getLatLng().latitude, marker1.getLatLng().longitude);
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
                }
                return true;
            }
        });

        startLationY = ScreenUtil.dip2px(mContext, 60);
        lationY = ScreenUtil.dip2px(mContext, 150);

        BaiduMap.OnMapClickListener listener = new BaiduMap.OnMapClickListener() {
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
            }


            /**
             * 地图内 Poi 单击事件回调函数
             *
             * @param mapPoi 点击的 poi 信息
             */
            @Override
            public void onMapPoiClick(MapPoi mapPoi) {

            }
        };
        //设置地图单击事件监听
        mBaiduMap.setOnMapClickListener(listener);


        drawerPopupView = new MapDrawerPopupView(getContext());


        BaiduMap.OnMapLoadedCallback callback = new BaiduMap.OnMapLoadedCallback() {
            /**
             * 地图加载完成回调函数
             */
            @Override
            public void onMapLoaded() {

            }
        };
        //设置地图加载完成回调
        mBaiduMap.setOnMapLoadedCallback(callback);


        /**
         * 地图渲染完成回调函数
         */
        BaiduMap.OnMapRenderCallback callback1 = () -> {
            RenderFinished = true;
            Log.e("onMapRenderFinished", "渲染完毕");
        };
        //设置地图渲染完成回调
        mBaiduMap.setOnMapRenderCallbadk(callback1);
    }


    @OnClick({R.id.iv_search_msg, R.id.et_search, R.id.iv_search_options, R.id.iv_my_location, R.id.layout_seller_map_bottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search_msg:
                break;
            case R.id.et_search:
                SellerSearchActivity.start(activity, SellerSearchActivity.class, null);
                break;
            case R.id.iv_search_options:
                if (popupView == null) {
//                    popupView = (SearchOptionsPopupView) new XPopup.Builder(getContext())
//                            .atView(view)
////                    .isCenterHorizontal(true)
//                            .autoOpenSoftInput(true)
////                    .offsetX(200)
////                .dismissOnTouchOutside(false)
//                            .setPopupCallback(new SimpleCallback() {
//                                @Override
//                                public void onShow() {
//                                  //  toast("显示了");
//                                }
//                                @Override
//                                public void onDismiss() {
////                            popupView = null;
//                                }
//                            })
//                            .asCustom(new SearchOptionsPopupView(getContext()));


                }

//                popupView.show();

                new XPopup.Builder(getContext())
                        .popupAnimation(PopupAnimation.TranslateAlphaFromTop)
                        .asCustom(new SearchOptionsPopupView(getContext()))
                        .show();

                break;
            case R.id.iv_my_location:
//                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(ll);
//                mBaiduMap.animateMapStatus(mapStatusUpdate);
//                SellerHomeActivity.start(mContext,SellerHomeActivity.class,null);


                new XPopup.Builder(getContext())
                        .hasShadowBg(false)
                        .popupPosition(PopupPosition.Right)//右边
                        .hasStatusBarShadow(false) //启用状态栏阴影
                        .asCustom(drawerPopupView)
                        .show();


                break;
            case R.id.layout_seller_map_bottom:
                if (this.sellerDetailsInfo != null && this.sellerDetailsInfo.getId() != -1) {
                    Intent intent = new Intent();
                    String json = gson.toJson(this.sellerDetailsInfo);
                    intent.putExtra("type", 1);
                    intent.putExtra("detailsinfo", json);
                    SellerHomeActivity.start(mContext, SellerHomeActivity.class, intent);
                }

                break;


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

    Disposable deaultDisposableLocation;

    @Override
    protected void lazyInitData(boolean isFirstVisible) {

        if (isFirstVisible) {

            double latitude = Double.parseDouble(mmkvUtlis.getMmkv().getString("latitude", "0.00"));
            double longitude = Double.parseDouble(mmkvUtlis.getMmkv().getString("longitude", "0.00"));

            getSellerInfo(null, 2.5, null, latitude, longitude, 1, 10);

            if (latitude != 0.00 && longitude != 0.00) {

                LatLng latLng = new LatLng(latitude, longitude);

                MapStatus.Builder builder = new MapStatus.Builder();

                builder.target(latLng).zoom(18f);

                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(builder.build());

                mBaiduMap.animateMapStatus(mapStatusUpdate);

            }

        }

        locationService.registerListener(this);

        locationService.start();

        // addSellerMapMarker();

        addSellerMapMarker();

    }


    /**
     * 查询商家地图的  商铺
     *
     * @param marchantTypeIds
     * @param distance
     * @param discount
     * @param latitude
     * @param longitude
     * @param pageCurr
     * @param pageSize
     */
    public void getSellerInfo(String marchantTypeIds, double distance, String discount, double latitude, double longitude, int pageCurr, int pageSize) {
        mPresenter.getMapSellerInfo(marchantTypeIds, distance, discount, latitude, longitude, pageCurr, pageSize);
    }


    @Override
    public void onResume() {
        if (mMapView != null) {
            mMapView.onResume();
            Log.e("onResume", "运行商家地图定位");
            locationService.setTAG("SellerMap");
        }

        super.onResume();


    }


    @Override
    public void onPause() {
        if (mMapView != null && RenderFinished) {
            mMapView.onPause();
        }

        locationService.stop();

        Log.e("onPause1", "停止商家地图定位");
        super.onPause();

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


    @Override
    public void onLocationResult(BDLocation location) {
        if (location == null || mMapView == null) {
            return;
        }

        mapLocation = location;
        updateMap();
    }


    public void updateMap() {
        //定位完成后 停止定位
        locationService.stop();
        Observable.just("location")
                //延时第一个参数是数值，第二个参数是事件单位
                .delay(100, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(autoDisposable(AndroidLifecycleScopeProvider.from(MapSellerFragment.this, Lifecycle.Event.ON_PAUSE)))
                .subscribe(s -> {
//
//                        mCurrentLat = mapLocation.getLatitude();
//                        mCurrentLon = mapLocation.getLongitude();
//                        mCurrentAccracy = mapLocation.getDirection();
//                        locData = new MyLocationData.Builder()
//                                //.accuracy(mapLocation.getRadius())
//                                .direction(mCurrentAccracy)
//                                .latitude(mapLocation.getLatitude())
//                                .longitude(mapLocation.getLongitude()).build();
//                        mBaiduMap.setMyLocationData(locData);
//                        if (!isFirstLoc) {
//                            return;
//                        }
//
//                        Log.e("isFirstLoc", isFirstLoc + "");
//                        isFirstLoc = false;
//                        ll = new LatLng(mapLocation.getLatitude(), mapLocation.getLongitude());
//                        MapStatus.Builder builder = new MapStatus.Builder();
//                        builder.target(ll).zoom(18f);
//                        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));


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
                    builder.target(ll).zoom(18f);
                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                });
    }

    Disposable disposable;

//    public void addSellerMapMarker() {
//        if (maslist.size() > 0) {
//            //  Log.e("商户个数", "maslist.size():个数" + maslist.size());
//            Observable<LatLng> listObservable = Observable.fromIterable(latLngs);
//            Observable<Long> timerObservable = Observable.interval(1, TimeUnit.SECONDS);
//
//
//            Observable.zip(listObservable, timerObservable, new BiFunction<LatLng, Long, MarkerE>() {
//                @Override
//                public MarkerE apply(LatLng latLng, Long aLong) throws Exception {
//                    MarkerOptions ooA = new MarkerOptions().position(latLng).icon(bitmapDescriptor).zIndex(9).draggable(true);
//                    return new MarkerE(latLng, ooA);
//                }
//            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                    .as(autoDisposable(AndroidLifecycleScopeProvider.from(MapSellerFragment.this, Lifecycle.Event.ON_PAUSE)))
//                    .subscribe(new Observer<MarkerE>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//                            disposable = d;
//                        }
//
//                        @Override
//                        public void onNext(MarkerE markerE) {
//
//                            //判断是否已经解除订阅,已经解除后 不执行
//                            if (disposable != null && !disposable.isDisposed()) {
//                                Marker marker = (Marker) (mBaiduMap.addOverlay(markerE.getMarkerOptions()));
//                                marker.setAnimation(getScaleAnimation());
//                                // MarkerE markerE = new MarkerE(markerOptions.getPosition(), marker, markerOptions);
//                                Bundle bundle = new Bundle();
//                                //info必须实现序列化接口
//                                MapSellerInfoE mapSellerInfo = new MapSellerInfoE(1, "测试", markerE.getLatLng());
//                                bundle.putParcelable("info", mapSellerInfo);
//                                marker.setExtraInfo(bundle);
//                                marker.startAnimation();
//                                latLngs.remove(markerE.getLatLng());
//                            }
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//
//                        }
//
//                        @Override
//                        public void onComplete() {
//                            Log.e("onComplete", "绘制完毕");
//
//                        }
//                    });
//        }
//    }

    @Override
    public void onLocationFailure(int code) {
        Toast.makeText(mContext, "定位失败：" + code, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void fragmentHidden() {

    }

    /**
     * 以自己的经纬度为中心画原
     *
     * @param latLng
     * @param radius
     */
    public void addCircle(LatLng latLng, int radius) {
        // 绘制圆
        OverlayOptions ooCircle = new CircleOptions().
                fillColor(0x264080FE)
                .center(latLng).stroke(new Stroke(2, 0xAA7AA7FF))
                .radius(radius);
        mBaiduMap.addOverlay(ooCircle);
    }


    public static MapSellerFragment newInstance(String type) {
        Bundle args = new Bundle();
        MapSellerFragment fragment = new MapSellerFragment();
        fragment.setArguments(args);
        args.putString("type", type);
        return fragment;
    }


    /**
     * 创建Marker bitmap
     *
     * @param v
     * @param width
     * @param height
     * @return
     */
    public Bitmap createBitmap(View v, int width, int height) {
        //测量使得view指定大小
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        v.measure(measuredWidth, measuredHeight);
        //调用layout方法布局后，可以得到view的尺寸大小
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        Bitmap bmp = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        c.drawColor(Color.TRANSPARENT);
        v.draw(c);
        return bmp;
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            geomagnetic = sensorEvent.values;
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            gravity = sensorEvent.values;
            getValue();
        }
    }


    public void getValue() {
        // r从这里返回
        SensorManager.getRotationMatrix(r, null, gravity, geomagnetic);
        //values从这里返回
        SensorManager.getOrientation(r, values);
        //提取数据
        double azimuth = Math.toDegrees(values[0]) - 90;

//        Log.i(TAG, values[0] + "");
//        if(values[0] >= -5&& values[0] < 5) {
//
//            Log.i(TAG, "正北");
//        }else if(values[0] >= 5&& values[0] < 85) {
//            // Log.i(TAG, "东北");
//            Log.i(TAG, "东北");
//        }else if(values[0] >= 85&& values[0] <= 95) {
//            // Log.i(TAG, "正东");
//            Log.i(TAG, "正东");
//        }else if(values[0] >= 95&& values[0] < 175) {
//            // Log.i(TAG, "东南");
//            Log.i(TAG, "东南");
//
//        }else if((values[0] >= 175&& values[0] <= 180) || (values[0]) >= -180&& values[0] < -175) {
//            // Log.i(TAG, "正南");
//            Log.i(TAG, "正南");
//
//        }else if(values[0] >= -175&& values[0] < -95) {
//            // Log.i(TAG, "西南");
//            Log.i(TAG, "西南");
//        }else if(values[0] >= -95&& values[0] < -85){
//            // Log.i(TAG, "正西");
//            Log.i(TAG, "正西");
//
//        }else if(values[0] >= -85&& values[0] < -5){
//            // Log.i(TAG, "西北");
//            Log.i(TAG, "西北");
//
//        }


        if (Math.abs(azimuth - lastX) > 1.0) {
            //Log.e("azimuth","更新方向");
            mCurrentDirection = (int) azimuth;
            locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection)
                    .latitude(mCurrentLat)
                    .longitude(mCurrentLon).build();
            mBaiduMap.setMyLocationData(locData);
        }

        lastX = azimuth;

//        if (Math.abs(azimuth - lastX) > 1.0) {
//            mCurrentDirection = (int) azimuth;
//            locData = new MyLocationData.Builder()
//                    .accuracy(mCurrentAccracy)
//                    // 此处设置开发者获取到的方向信息，顺时针0-360
//                    .direction(mCurrentDirection)
//                    .latitude(mCurrentLat)
//                    .longitude(mCurrentLon).build();
//            mBaiduMap.setMyLocationData(locData);
//        }
//
//
//        lastX = azimuth;
        //  double pitch = Math.toDegrees(values[1]);
        // double roll = Math.toDegrees(values[2]);

        //  Log.e("getValue", "Azimuth：" + (int) azimuth + "\nPitch：" + (int) pitch + "\nRoll：" + (int) roll);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    public void mapSellerInfoSuccess(List<MapSellerStoreE> mapSellerStoreE) {
        this.mapSellerStores = mapSellerStoreE;
        addSellerMapMarker();
    }


    @Override
    public void mapSellerDetailsInfoSuccess(MapSellerDetailsInfoE mapSellerDetailsInfo) {
        this.sellerDetailsInfo = mapSellerDetailsInfo;
        int shopId = mapSellerDetailsInfo.getId();
        tvSellerStoreName.setText(String.format(getString(R.string.str_placeholder), mapSellerDetailsInfo.getNickName()));
        tvSellerStoreAddress.setText(String.format(getString(R.string.str_placeholder), mapSellerDetailsInfo.getAddress()));
        tvSellerStoreType.setText(String.format(getString(R.string.str_placeholder), mapSellerDetailsInfo.getBusinessName()));
        tvSellerMapDistance.setText(String.format(getString(R.string.str_distance_placeholder), mapSellerDetailsInfo.getDistance()));

        Bitmap bitmap = stringToBitmap(mapSellerDetailsInfo.getLogoPic());
        if (bitmap != null) {
            ivSellerLogo.setImageBitmap(bitmap);
        } else {
            ivSellerLogo.setImageResource(R.drawable.default_picture);
        }

    }

    @Override
    public void mapSellerDetailsInfoFailure(int code, String msg) {
        if (this.sellerDetailsInfo != null) {
            this.sellerDetailsInfo.setId(-1);
        }

        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }


    public void addSellerMapMarker() {
        if (mapSellerStores != null && mapSellerStores.size() > 0) {
            Log.e("商户个数", "maslist.size():个数" + mapSellerStores.size());
            Observable<MapSellerStoreE> mapSellerStoreObservable = Observable.fromIterable(mapSellerStores);
            Observable<Long> timerObservable = Observable.interval(1, TimeUnit.SECONDS);

            Observable.zip(mapSellerStoreObservable, timerObservable, new BiFunction<MapSellerStoreE, Long, MarkerE>() {
                @Override
                public MarkerE apply(MapSellerStoreE mapSellerStoreE1, Long aLong) throws Exception {

                    Bitmap bitmap = stringToBitmap(mapSellerStoreE1.getLogoPic());

                    nivLogo.setImageBitmap(bitmap);

                    bitmapDescriptor = BitmapDescriptorFactory.fromView(mapMarkerView);

                    LatLng latLng = new LatLng(mapSellerStoreE1.getLatitude(), mapSellerStoreE1.getLongitude());

                    MarkerOptions ooA = new MarkerOptions().position(latLng).icon(bitmapDescriptor).zIndex(9);

                    return new MarkerE(mapSellerStoreE1.getId(), latLng, ooA);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .as(autoDisposable(AndroidLifecycleScopeProvider.from(MapSellerFragment.this, Lifecycle.Event.ON_PAUSE)))
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
                                Iterator<MapSellerStoreE> iterator = mapSellerStores.iterator();
                                while (iterator.hasNext()) {
                                    MapSellerStoreE obj = iterator.next();
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
                            Log.e("onComplete", "绘制完毕");

                        }
                    });
        }
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


}
