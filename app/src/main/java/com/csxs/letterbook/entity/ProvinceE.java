package com.csxs.letterbook.entity;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/20
 * description:
 */
public class ProvinceE implements IPickerViewData {
    private String name;
    private String zipcode;
    private List<MineCityE> city;


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

    public List<MineCityE> getCity() {
        return city;
    }

    public void setCity(List<MineCityE> city) {
        this.city = city;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
