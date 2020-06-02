package com.csxs.letterbook.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

/**
 * @author: yeliu
 * created on 2020/4/21
 * description:地图商家信息
 */
public class MapSellerInfoE implements Parcelable {


    public int id;
    public String name;
    public LatLng latLng;
    public MarkerOptions markerOptions;

    public MapSellerInfoE(int id, String name, LatLng latLng) {
        this.id = id;
        this.name = name;
        this.latLng = latLng;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.latLng, 0);
    }

    public static final Creator<MapSellerInfoE> CREATOR = new Parcelable.Creator<MapSellerInfoE>(){

        @Override
        public MapSellerInfoE createFromParcel(Parcel source) {
            return new MapSellerInfoE(source);
        }

        @Override
        public MapSellerInfoE[] newArray(int size) {
            return new MapSellerInfoE[size];
        }
    };


    private MapSellerInfoE(Parcel in){
        id = in.readInt();
        name = in.readString();
        latLng=in.readParcelable(Thread.currentThread().getContextClassLoader());
    }

}
