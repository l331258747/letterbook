package com.csxs.letterbook.entity;

import com.contrarywind.interfaces.IPickerViewData;

/**
 * @author: yeliu
 * created on 2020/5/20
 * description:
 */
public class MineDistrictE implements IPickerViewData {

    private String name;
    private String zipcode;

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

    @Override
    public String getPickerViewText() {
        return name;
    }
}
