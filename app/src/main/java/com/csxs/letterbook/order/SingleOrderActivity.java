package com.csxs.letterbook.order;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csxs.core.base.BaseDiffActivity;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.BottomEmptyE;
import com.csxs.letterbook.entity.BottomFunctionE;
import com.csxs.letterbook.entity.GoodsTotalBean;
import com.csxs.letterbook.entity.OrderGoodsE;
import com.csxs.letterbook.entity.OrderShopE;
import com.csxs.letterbook.entity.SingleOrderE;
import com.csxs.letterbook.entity.TopReciveAddressE;
import com.csxs.letterbook.order.adapter.SingleOrderAdapter;
import com.csxs.letterbook.order.mvp.contract.SingleOrderContract;
import com.csxs.letterbook.order.mvp.presenter.SingleOrderPresenter;
import com.csxs.viewlib.cartlayout.bean.CartItemBean;
import com.csxs.viewlib.cartlayout.bean.ICartItem;
import com.csxs.viewlib.cartlayout.listener.CartOnCheckChangeListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author: yeliu
 * created on 2020/5/28
 * description:
 */
public class SingleOrderActivity extends BaseDiffActivity<SingleOrderPresenter> implements SingleOrderContract.ISingleOrderView{

    @BindView(R.id.rc_order_goods)
    RecyclerView singleOrderRc;

    private SingleOrderE singleOrderE;

    private String addressShopping;
    private SingleOrderAdapter singleOrderAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_single_order;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        setCenterMainTitle("确认订单");
        singleOrderRc.setLayoutManager(new LinearLayoutManager(this));
        singleOrderAdapter = new  SingleOrderAdapter(this,null);
        singleOrderAdapter.setOnCheckChangeListener(new CartOnCheckChangeListener(singleOrderRc, singleOrderAdapter) {
            @Override
            public void onCalculateChanged(ICartItem cartItemBean) {

            }
        });
        singleOrderRc.setAdapter(singleOrderAdapter);
    }

    @Override
    protected void onInitData() {
        String goodsinfo = getIntent().getStringExtra("goodsinfo");
        mPresenter.querySingleOrderInfo(goodsinfo);
    }

    @Override
    public void singleOrderInfoSuccess(SingleOrderE singleOrderE) {
        this.singleOrderE = singleOrderE;

        ArrayList<CartItemBean> cartItemBeans = new ArrayList<>();

        TopReciveAddressE topReciveAddressE=new TopReciveAddressE();
        topReciveAddressE.setItemType(CartItemBean.TYPE_NORMAL);
        topReciveAddressE.setAddress(singleOrderE.getShipping());

        if (singleOrderE.getShipping() != null) {
            addressShopping = singleOrderE.getShipping().getShippingId() + "";
        }

        if (singleOrderE.getShipping() != null && singleOrderE.getShipping().getAddress() != null) {
            StringBuilder stringBuilder = new StringBuilder(singleOrderE.getShipping().getProvincesName());
            stringBuilder.append(singleOrderE.getShipping().getCityName() != null ? singleOrderE.getShipping().getCityName() : "");
            stringBuilder.append(singleOrderE.getShipping().getAreaName() != null ? singleOrderE.getShipping().getAreaName() : "");
            stringBuilder.append(singleOrderE.getShipping().getAddress() != null ? singleOrderE.getShipping().getAddress() : "");
            topReciveAddressE.getAddress().setAddressDetais(stringBuilder.toString());
        } else {
            topReciveAddressE.setAddressTip(getString(R.string.location_order_str));
        }

        cartItemBeans.add(topReciveAddressE);

        OrderShopE shopBean = new OrderShopE();
        shopBean.setShopName(singleOrderE.getMarchantName() != null ? singleOrderE.getMarchantName() : "暂无店铺名称");
        shopBean.setItemType(CartItemBean.TYPE_GROUP);
        cartItemBeans.add(shopBean);

        for (int i=0;i<singleOrderE.getCommList().size();i++){
            OrderGoodsE orderGoodsE=new OrderGoodsE();
            orderGoodsE.setCommListBean(singleOrderE.getCommList().get(i));
            orderGoodsE.setItemType(CartItemBean.TYPE_CHILD);
            cartItemBeans.add(orderGoodsE);
        }

        GoodsTotalBean goodsTotalBean = new GoodsTotalBean();
        goodsTotalBean.setItemType(CartItemBean.TYPE_TOTAL);
        goodsTotalBean.setGoodsCount(singleOrderE.getCommSum());
        goodsTotalBean.setGoodsTotalPrice(singleOrderE.getSubtotals());
        cartItemBeans.add(goodsTotalBean);

        singleOrderAdapter.setNewData(cartItemBeans);

    }

    @Override
    public void singleOrderInfoFailure(int code, String msg) {

    }
}
