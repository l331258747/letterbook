package com.csxs.letterbook.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/20
 * description:我的地址 选择三级联动
 */
public class ThreeLevelAddressLinkageE {

    List<ProvinceE> options1Items;
    List<List<MineCityE>> options2Items;
    List<List<List<MineDistrictE>>> options3Items;

    public List<ProvinceE> getOptions1Items() {
        return options1Items;
    }

    public void setOptions1Items(List<ProvinceE> options1Items) {
        this.options1Items = options1Items;
    }

    public List<List<MineCityE>> getOptions2Items() {
        return options2Items;
    }

    public void setOptions2Items(List<List<MineCityE>> options2Items) {
        this.options2Items = options2Items;
    }

    public List<List<List<MineDistrictE>>> getOptions3Items() {
        return options3Items;
    }

    public void setOptions3Items(List<List<List<MineDistrictE>>> options3Items) {
        this.options3Items = options3Items;
    }
}
