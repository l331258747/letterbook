package com.csxs.letterbook.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;

/**
 * @author: yeliu
 * created on 2020/4/17
 * description:
 */
public class MarkerE implements Parcelable {
    private int id;
    private LatLng latLng;
    private MarkerOptions markerOptions;
    private String sex;

    public MarkerE(int id,LatLng latLng,MarkerOptions markerOptions) {
        this.id=id;
        this.latLng = latLng;
        this.markerOptions=markerOptions;
    }

    public MarkerE(int id,String sex,LatLng latLng,MarkerOptions markerOptions) {
        this.id=id;
        this.sex=sex;
        this.latLng = latLng;
        this.markerOptions=markerOptions;
    }


    protected MarkerE(Parcel in) {
        id = in.readInt();
        latLng = in.readParcelable(LatLng.class.getClassLoader());
    }

    public static final Creator<MarkerE> CREATOR = new Creator<MarkerE>() {
        @Override
        public MarkerE createFromParcel(Parcel in) {
            return new MarkerE(in);
        }

        @Override
        public MarkerE[] newArray(int size) {
            return new MarkerE[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getSex() {
        return sex;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }


    public MarkerOptions getMarkerOptions() {
        return markerOptions;
    }

    public void setMarkerOptions(MarkerOptions markerOptions) {
        this.markerOptions = markerOptions;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeParcelable(this.latLng, 0);
    }


}
