package com.csxs.letterbook.mapservice.listener;

import com.baidu.location.BDLocation;

/**
 * @author: yeliu
 * created on 2020/4/16
 * description:
 */
public interface LocationListener {
    void onLocationResult(BDLocation location);

    void onLocationFailure(int code);
}
