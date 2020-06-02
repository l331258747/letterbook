package com.csxs.letterbook.entity;

import com.csxs.viewlib.cartlayout.bean.ChildItemBean;

/**
 * @author: yeliu
 * created on 2020/5/28
 * description:
 */
public class OrderGoodsE extends ChildItemBean {

    private CommListBean commListBean;

    public CommListBean getCommListBean() {
        return commListBean;
    }

    public void setCommListBean(CommListBean commListBean) {
        this.commListBean = commListBean;
    }
}
