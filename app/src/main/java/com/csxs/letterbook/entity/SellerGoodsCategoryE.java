package com.csxs.letterbook.entity;

/**
 * @author: yeliu
 * created on 2020/5/15
 * description:
 */
public class SellerGoodsCategoryE {

    private String name;
    private boolean check;

    public SellerGoodsCategoryE(String name, boolean check) {
        this.name = name;
        this.check = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
