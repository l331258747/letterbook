package com.csxs.letterbook.mapservice;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.location.Poi;
import com.baidu.location.PoiRegion;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.csxs.letterbook.R;
import com.csxs.letterbook.mapservice.listener.LocationListener;
import com.csxs.letterbook.mapservice.listener.LocationSensorEventListener;

/**
 * 定位服务LocationClient 相关
 *
 *                 mSensorManager = (SensorManager) locationContext.getSystemService(Context.SENSOR_SERVICE);
 *                 magneticSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
 *                 accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
 * @author baidu
 */
public class LocationService implements LifecycleObserver, SensorEventListener {



    private static LocationClient client = null;
    private static LocationClientOption mOption;
    private static LocationClientOption DIYoption;
    private Object objLock;
    private LocationListener locationListener;
    private SensorManager mSensorManager;
    private Sensor magneticSensor, accelerometerSensor;

    public String TAG;


    /***
     * 初始化 LocationClient
     *
     * @param locationContext
     */
    public LocationService(Context locationContext) {
        objLock = new Object();
        synchronized (objLock) {
            if (client == null) {
                client = new LocationClient(locationContext.getApplicationContext());
                client.setLocOption(getDefaultLocationClientOption());
            }
        }
    }




    /***
     * 注册定位监听
     *
     * @param listener
     * @return
     */

    public boolean registerListener(LocationListener listener) {
        this.locationListener = listener;
        boolean isSuccess = false;
        if (listener != null) {
            client.registerLocationListener(mListener);
            isSuccess = true;
        }
        return isSuccess;
    }

    public void unregisterListener(BDAbstractLocationListener listener) {
        if (listener != null) {
            client.unRegisterLocationListener(listener);
        }
    }

    /**
     * @return 获取sdk版本
     */
    public String getSDKVersion() {
        if (client != null) {
            String version = client.getVersion();
            return version;
        }
        return null;
    }

    /***
     * 设置定位参数
     *
     * @param option
     * @return isSuccessSetOption
     */
    public static boolean setLocationOption(LocationClientOption option) {
        boolean isSuccess = false;
        if (option != null) {
            if (client.isStarted()) {
                client.stop();
            }
            DIYoption = option;
            client.setLocOption(option);
        }
        return isSuccess;
    }

    /**
     * 开发者应用如果有H5页面使用了百度JS接口，该接口可以辅助百度JS更好的进行定位
     *
     * @param webView 传入webView控件
     */
    public void enableAssistanLocation(WebView webView) {
        if (client != null) {
            client.enableAssistantLocation(webView);
        }
    }

    /**
     * 停止H5辅助定位
     */
    public void disableAssistantLocation() {
        if (client != null) {
            client.disableAssistantLocation();
        }
    }

    /***
     *
     * @return DefaultLocationClientOption  默认O设置
     */
    public LocationClientOption getDefaultLocationClientOption() {
        if (mOption == null) {
            mOption = new LocationClientOption();
            mOption.setLocationMode(LocationMode.Hight_Accuracy); // 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            mOption.setCoorType("bd09ll"); // 可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
            mOption.setScanSpan(0); // 可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
            mOption.setIsNeedAddress(true); // 可选，设置是否需要地址信息，默认不需要
            mOption.setIsNeedLocationDescribe(true); // 可选，设置是否需要地址描述
            mOption.setNeedDeviceDirect(false); // 可选，设置是否需要设备方向结果
            mOption.setLocationNotify(false); // 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
            mOption.setIgnoreKillProcess(true); // 可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop
            mOption.setIsNeedLocationDescribe(true); // 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation
            mOption.setIsNeedLocationPoiList(true); // 可选，默认false，设置是否需要POI结果，可以在BDLocation
            mOption.SetIgnoreCacheException(false); // 可选，默认false，设置是否收集CRASH信息，默认收集
            mOption.setOpenGps(true); // 可选，默认false，设置是否开启Gps定位
            mOption.setIsNeedAltitude(false); // 可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        }
        return mOption;
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(@NonNull LifecycleOwner owner) {
        //  Log.e("onCreate","onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume(@NonNull LifecycleOwner owner) {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause(@NonNull LifecycleOwner owner) {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop(@NonNull LifecycleOwner owner) {
        //   Log.e("onStop","onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(@NonNull LifecycleOwner owner) {


    }

    public void onMapDestroy(){
        if (client != null) {
            //停止定位
            client.stop();
            //注销 定位监听
            if (locationListener != null) {
                locationListener = null;
            }

            unregisterListener(mListener);

        }
    }


    /**
     * @return DIYOption 自定义Option设置
     */
    public LocationClientOption getOption() {
        if (DIYoption == null) {
            DIYoption = new LocationClientOption();
        }
        return DIYoption;
    }

    public void start() {
        synchronized (objLock) {
            if (client != null) {
                client.start();
            }
        }
    }

    public void requestLocation() {
        if (client != null) {
            client.requestLocation();
        }
    }

    public void stop() {
        synchronized (objLock) {
            if (client != null) {
                client.stop();
            }
        }
    }

    public boolean isStart() {
        return client.isStarted();
    }

    public boolean requestHotSpotState() {
        return client.requestHotSpotState();
    }

    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (locationListener == null) {
                return;
            }
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {

                locationListener.onLocationResult(location);



                int tag = 1;
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append(location.getTime());
                sb.append("\nlocType : ");// 定位类型
                sb.append(location.getLocType());
                sb.append("\nlocType description : ");// *****对应的定位类型说明*****
                sb.append(location.getLocTypeDescription());
                sb.append("\nlatitude : ");// 纬度
                sb.append(location.getLatitude());
                sb.append("\nlongtitude : ");// 经度
                sb.append(location.getLongitude());
                sb.append("\nradius : ");// 半径
                sb.append(location.getRadius());
                sb.append("\nCountryCode : ");// 国家码
                sb.append(location.getCountryCode());
                sb.append("\nProvince : ");// 获取省份
                sb.append(location.getProvince());
                sb.append("\nCountry : ");// 国家名称
                sb.append(location.getCountry());
                sb.append("\ncitycode : ");// 城市编码
                sb.append(location.getCityCode());
                sb.append("\ncity : ");// 城市
                sb.append(location.getCity());
                sb.append("\nDistrict : ");// 区
                sb.append(location.getDistrict());
                sb.append("\nTown : ");// 获取镇信息
                sb.append(location.getTown());
                sb.append("\nStreet : ");// 街道
                sb.append(location.getStreet());
                sb.append("\naddr : ");// 地址信息
                sb.append(location.getAddrStr());
                sb.append("\nStreetNumber : ");// 获取街道号码
                sb.append(location.getStreetNumber());
                sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
                sb.append(location.getUserIndoorState());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());// 方向
                sb.append("\nlocationdescribe: ");
                sb.append(location.getLocationDescribe());// 位置语义化信息
                sb.append("\nPoi: ");// POI信息
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append("poiName:");
                        sb.append(poi.getName() + ", ");
                        sb.append("poiTag:");
                        sb.append(poi.getTags() + "\n");
                    }
                }
                if (location.getPoiRegion() != null) {
                    sb.append("PoiRegion: ");// 返回定位位置相对poi的位置关系，仅在开发者设置需要POI信息时才会返回，在网络不通或无法获取时有可能返回null
                    PoiRegion poiRegion = location.getPoiRegion();
                    sb.append("DerectionDesc:"); // 获取POIREGION的位置关系，ex:"内"
                    sb.append(poiRegion.getDerectionDesc() + "; ");
                    sb.append("Name:"); // 获取POIREGION的名字字符串
                    sb.append(poiRegion.getName() + "; ");
                    sb.append("Tags:"); // 获取POIREGION的类型
                    sb.append(poiRegion.getTags() + "; ");
                    sb.append("\nSDK版本: ");
                }
               // sb.append(locationService.getSDKVersion()); // 获取SDK版本
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 速度 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());// 卫星数目
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 海拔高度 单位：米
                    sb.append("\ngps status : ");
                    sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    if (location.hasAltitude()) {// *****如果有海拔高度*****
                        sb.append("\nheight : ");
                        sb.append(location.getAltitude());// 单位：米
                    }
                    sb.append("\noperationers : ");// 运营商信息
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
                Log.e("location", sb.toString());
            } else {
                locationListener.onLocationFailure(location.getLocType());
            }
        }


        @Override
        public void onConnectHotSpotMessage(String s, int i) {
            super.onConnectHotSpotMessage(s, i);

        }

        @Override
        public void onLocDiagnosticMessage(int locType, int diagnosticType, String diagnosticMessage) {
            super.onLocDiagnosticMessage(locType, diagnosticType, diagnosticMessage);
//            int tag = 2;
//            StringBuffer sb = new StringBuffer(256);
//            sb.append("诊断结果: ");
//            if (locType == BDLocation.TypeNetWorkLocation) {
//                if (diagnosticType == 1) {
//                    sb.append("网络定位成功，没有开启GPS，建议打开GPS会更好");
//                    sb.append("\n" + diagnosticMessage);
//                } else if (diagnosticType == 2) {
//                    sb.append("网络定位成功，没有开启Wi-Fi，建议打开Wi-Fi会更好");
//                    sb.append("\n" + diagnosticMessage);
//                }
//            } else if (locType == BDLocation.TypeOffLineLocationFail) {
//                if (diagnosticType == 3) {
//                    sb.append("定位失败，请您检查您的网络状态");
//                    sb.append("\n" + diagnosticMessage);
//                }
//            } else if (locType == BDLocation.TypeCriteriaException) {
//                if (diagnosticType == 4) {
//                    sb.append("定位失败，无法获取任何有效定位依据");
//                    sb.append("\n" + diagnosticMessage);
//                } else if (diagnosticType == 5) {
//                    sb.append("定位失败，无法获取有效定位依据，请检查运营商网络或者Wi-Fi网络是否正常开启，尝试重新请求定位");
//                    sb.append(diagnosticMessage);
//                } else if (diagnosticType == 6) {
//                    sb.append("定位失败，无法获取有效定位依据，请尝试插入一张sim卡或打开Wi-Fi重试");
//                    sb.append("\n" + diagnosticMessage);
//                } else if (diagnosticType == 7) {
//                    sb.append("定位失败，飞行模式下无法获取有效定位依据，请关闭飞行模式重试");
//                    sb.append("\n" + diagnosticMessage);
//                } else if (diagnosticType == 9) {
//                    sb.append("定位失败，无法获取任何有效定位依据");
//                    sb.append("\n" + diagnosticMessage);
//                }
//            } else if (locType == BDLocation.TypeServerError) {
//                if (diagnosticType == 8) {
//                    sb.append("定位失败，请确认您定位的开关打开状态，是否赋予APP定位权限");
//                    sb.append("\n" + diagnosticMessage);
//                }
//            }
//            Log.e("locationcheng", sb.toString());

        }
    };


    @Override
    public void onSensorChanged(SensorEvent event) {


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }
}
