package com.csxs.viewlib.cartlayout.bean;

import java.util.List;



public class GroupItemBean extends CartItemBean implements IGroupItem<ChildItemBean> {
    private List<ChildItemBean> childs;

    public List<ChildItemBean> getChilds() {
        return childs;
    }

    public void setChilds(List<ChildItemBean> childs) {
        this.childs = childs;
    }
}
