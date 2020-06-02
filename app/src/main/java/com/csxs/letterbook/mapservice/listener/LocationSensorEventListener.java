package com.csxs.letterbook.mapservice.listener;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

/**
 * @author: yeliu
 * created on 2020/4/17
 * description:
 */
public interface LocationSensorEventListener {

    void onSensorChanged(SensorEvent event);

    void onAccuracyChanged(Sensor sensor, int accuracy);
}
