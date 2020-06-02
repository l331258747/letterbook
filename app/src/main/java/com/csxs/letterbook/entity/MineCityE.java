package com.csxs.letterbook.entity;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/20
 * description:
 */
public class MineCityE implements IPickerViewData {
    private String name;
    private String zipcode;
    private List<MineDistrictE> district;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public List<MineDistrictE> getDistrict() {
        return district;
    }

    public void setDistrict(List<MineDistrictE> district) {
        this.district = district;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
