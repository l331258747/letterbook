package com.csxs.letterbook.mine.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csxs.core.base.LazyDiffFragment;
import com.csxs.core.utils.ScreenUtil;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.BottomEmptyE;
import com.csxs.letterbook.entity.GoodsBean;
import com.csxs.letterbook.entity.OrderE;
import com.csxs.letterbook.entity.OrderGoodsTotalE;
import com.csxs.letterbook.entity.ShopBean;
import com.csxs.letterbook.mine.adapter.MineAllOrderAdapter;
import com.csxs.letterbook.mine.adapter.MineOrderAdapter;
import com.csxs.letterbook.mine.mvp.presenter.MineAllOrderPresenter;
import com.csxs.letterbook.mine.order.OrderSection;
import com.csxs.letterbook.mine.order.OrderShopE;
import com.csxs.letterbook.mine.order.OrderTotalE;
import com.csxs.letterbook.widgets.sectionadapter.SectionedRecyclerViewAdapter;
import com.csxs.viewlib.OrderDividerItemDecoration;
import com.csxs.viewlib.cartlayout.bean.CartItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author: yeliu
 * created on 2020/4/26
 * description:
 */
public class MineOrderFragment extends LazyDiffFragment<MineAllOrderPresenter> {


    @BindView(R.id.rv_all_order)
    RecyclerView rvAllOrder;
    private MineAllOrderAdapter mineAllOrderAdapter;
    private MineOrderAdapter mineOrderAdapter;
    private List<OrderE> list;
    private SectionedRecyclerViewAdapter sectionedAdapter;

    @Override
    public void initParam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all_order;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        sectionedAdapter = new SectionedRecyclerViewAdapter();
        rvAllOrder.addItemDecoration(new OrderDividerItemDecoration(mContext,LinearLayoutManager.VERTICAL,ScreenUtil.dip2px(mContext,10),getResources().getColor(R.color.white_f2f2f2),true));
        rvAllOrder.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAllOrder.setAdapter(sectionedAdapter);

//        SectionAdapter sectionAdapter =  sectionedAdapter.getAdapterForSection(section);
//        int  index=sectionAdapter.getSectionPosition();
//        if (position == list.size() - 1) {
//            //  RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(emptyViewHolder.view.getLayoutParams());
//            lp.setMargins(0, 0, 0, ScreenUtil.dp2px(emptyViewHolder.view.getContext(), 20));
//            emptyViewHolder.view.setLayoutParams(lp);
//        }else{
////                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(emptyViewHolder.view.getLayoutParams());
//            lp.setMargins(0, 0, 0, 0);
//            emptyViewHolder.view.setLayoutParams(lp);
//        }

    }


    @Override
    protected void lazyInitData(boolean isFirstVisible) {
        if (isFirstVisible) {
            for (int i = 0; i < 5; i++) {
                OrderSection orderSection = new OrderSection(mContext);
                OrderShopE orderShopE = new OrderShopE();
                orderShopE.setShopName("信书自营店" + (i + 1));
                orderSection.setOrderShopE(orderShopE);
                List<OrderE> lists = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    OrderE orderE = new OrderE();
                    orderE.setGoodsName("可乐" + (j + 1));
                    lists.add(orderE);

                }

                orderSection.setList(lists);

                OrderTotalE orderTotalE = new OrderTotalE();

                orderTotalE.setPrice(5.23);

                orderSection.setOrderTotalE(orderTotalE);

                sectionedAdapter.addSection(orderSection);
            }



            rvAllOrder.postDelayed(new Runnable() {
                @Override
                public void run() {
                    sectionedAdapter.notifyDataSetChanged();
                }
            },1000);

        }



    }

    @Override
    protected void fragmentHidden() {

    }

    public static Fragment newInstance(int status) {
        Bundle args = new Bundle();
        args.putInt("status", status);
        MineOrderFragment fragment = new MineOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private List<CartItemBean> getData() {
        List<CartItemBean> cartItemBeans = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            String shopId = "654321" + (i + 1) + "123";
            ShopBean shopBean = new ShopBean();
            shopBean.setShop_name("信书 自营" + (i + 1) + "分店");
            shopBean.setShopId(shopId);
            shopBean.setItemType(CartItemBean.TYPE_GROUP);
            cartItemBeans.add(shopBean);

            for (int j = 0; j < (i + 5); j++) {
                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setGoods_name("忘忧水 " + (j + 1) + " 代");
                goodsBean.setItemType(CartItemBean.TYPE_CHILD);
                goodsBean.setItemId((j + 1) * 10 + j);
                goodsBean.setParentId(shopId);
                goodsBean.setGoods_price(j + 1.11);
                goodsBean.setGoods_original_price(j + 5.45);
                goodsBean.setGroupId(i);
                cartItemBeans.add(goodsBean);
            }

            OrderGoodsTotalE orderGoodsTotalE = new OrderGoodsTotalE();
            orderGoodsTotalE.setItemType(CartItemBean.TYPE_TOTAL);
            orderGoodsTotalE.setGoods_count(11);
            orderGoodsTotalE.setGoods_total_price(52.23);
            cartItemBeans.add(orderGoodsTotalE);

            BottomEmptyE emptyE = new BottomEmptyE();
            emptyE.setParentId(shopId);
            emptyE.setItemType(CartItemBean.TYPE_BOTTOM_EMPTY);

            cartItemBeans.add(emptyE);
        }


        return cartItemBeans;
    }

}
