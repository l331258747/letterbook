package com.csxs.letterbook.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csxs.core.base.BaseDiffActivity;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.BottomEmptyE;
import com.csxs.letterbook.entity.GoodsBean;
import com.csxs.letterbook.entity.ShopBean;
import com.csxs.letterbook.mine.adapter.MineCartAdapter;
import com.csxs.letterbook.mine.mvp.presenter.MineCartShopPresenter;
import com.csxs.viewlib.cartlayout.bean.CartItemBean;
import com.csxs.viewlib.cartlayout.bean.ICartItem;
import com.csxs.viewlib.cartlayout.listener.CartOnCheckChangeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: yeliu
 * created on 2020/4/25
 * description:我的购物车
 */
public class MineCartShopActivity extends BaseDiffActivity<MineCartShopPresenter> {


    @BindView(R.id.rv_cart_shop)
    RecyclerView rvCartShop;
    @BindView(R.id.cb_all)
    CheckBox selectAllGoods;
    @BindView(R.id.btn_goods_seet)
    Button btnGoodsSeet;
    @BindView(R.id.tv_all_goods_money)
    TextView tvAllGoodsMoney;
    @BindView(R.id.tv_text_total)
    TextView tvTextTotal;
    @BindView(R.id.layout_bottom)
    RelativeLayout layoutBottom;

    private boolean isEditing;//是否处于编辑状态

    private int totalCheckedCount;//勾选的商品总数量，店铺条目不计算在内

    private int totalCount;//购物车商品ChildItem的总数量，店铺条目不计算在内

    private double totalPrice;//勾选的商品总价格


    private MineCartAdapter mineCartAdapter;

    @Override
    public void initParam() {
        super.initParam();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_cart_shop;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        initTitleBar();
        mineCartAdapter = new MineCartAdapter(mContext, null);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvCartShop.setLayoutManager(manager);
        rvCartShop.setAdapter(mineCartAdapter);
        mineCartAdapter.setOnCheckChangeListener(new CartOnCheckChangeListener(rvCartShop, mineCartAdapter) {
            @Override
            public void onCalculateChanged(ICartItem cartItemBean) {
                calculate();
            }
        });


        btnGoodsSeet.setText(getString(R.string.go_settle_cart, 0));
        tvAllGoodsMoney.setText(getString(R.string.rmb_total, 0.00));
    }

    /**
     * 初始化 顶部 titla 栏目
     */
    private void initTitleBar() {
        setCenterMainTitle("购物车", R.color.white);
        setRightText(getString(R.string.edit), R.color.white);
        setTitleBarBackgroundColor(R.color.black);
        if (topView != null) {
            topView.setBackgroundColor(getResources().getColor(R.color.black));
        }

        if (mImmersionBar != null) {
            mImmersionBar.statusBarDarkFont(false);
            mImmersionBar.init();
        }

        setLeftImage(R.drawable.ic_back_white);
    }

    /**
     * 编辑按钮点击事件
     *
     * @param v
     */
    @Override
    protected void rightClick(View v) {
        isEditing = !isEditing;
        setRightText(getString(isEditing ? R.string.edit_done : R.string.edit), R.color.white);

        btnGoodsSeet.setText(getString(isEditing ? R.string.delete_goods : R.string.go_settle_cart, totalCheckedCount));

        btnGoodsSeet.setBackgroundResource(isEditing ? R.drawable.bg_cart_delete : R.drawable.bg_cart_settle);

        btnGoodsSeet.setTextColor(isEditing ? getResources().getColor(R.color.color_red_EC1919) : getResources().getColor(R.color.white));
    }

    @Override
    protected void onInitData() {
        mineCartAdapter.setNewData(getData());

    }


    /**
     * 计算商品的价格
     */
    private void calculate() {
        totalCheckedCount = 0;
        totalCount = 0;
        totalPrice = 0.00;
        int notChildTotalCount = 0;
        if (mineCartAdapter.getData() != null) {
            for (ICartItem iCartItem : mineCartAdapter.getData()) {
                if (iCartItem.getItemType() == ICartItem.TYPE_CHILD) {
                    totalCount++;
                    if (iCartItem.isChecked()) {
                        totalCheckedCount++;
                        totalPrice += ((GoodsBean) iCartItem).getGoods_price() * ((GoodsBean) iCartItem).getGoods_amount();
                    }
                } else {
                    notChildTotalCount++;
                }
            }
        }

        btnGoodsSeet.setText(getString(isEditing ? R.string.delete_goods : R.string.go_settle_cart, totalCheckedCount));
        tvAllGoodsMoney.setText(getString(R.string.rmb_total, totalPrice));
        if (selectAllGoods.isChecked() && (totalCheckedCount == 0 || (totalCheckedCount + notChildTotalCount) != mineCartAdapter.getData().size())) {
            selectAllGoods.setChecked(false);
        }
        if (totalCheckedCount != 0 && (!selectAllGoods.isChecked()) && (totalCheckedCount + notChildTotalCount) == mineCartAdapter.getData().size()) {
            selectAllGoods.setChecked(true);
        }
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

            BottomEmptyE emptyE = new BottomEmptyE();
            emptyE.setParentId(shopId);
            emptyE.setItemType(CartItemBean.TYPE_BOTTOM_EMPTY);

            cartItemBeans.add(emptyE);
        }

        //  Gson gson=new Gson();
        // String json= gson.toJson(cartItemBeans);

        // Log.e("allorder",json);

        return cartItemBeans;
    }


    @OnClick({R.id.btn_goods_seet, R.id.cb_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_goods_seet:
                if (isEditing) {
                    if (totalCheckedCount == 0) {
                        Toast.makeText(this, "请勾选你要删除的商品", Toast.LENGTH_SHORT).show();
                    } else {
                        deleteCheckedData();
                    }
                } else {
                    //结算
                    SettlementCart();
                }
                break;
            case R.id.cb_all:
                mineCartAdapter.checkedAll(((CheckBox) view).isChecked());
                break;
        }
    }

    /**
     * 结算购物车选中的商品
     */
    private void SettlementCart() {
        if (totalCheckedCount == 0) {
            Toast.makeText(this, "你还没有选择任何商品", Toast.LENGTH_SHORT).show();
        } else {

        }

    }


    /**
     * 删除勾选商品
     */
    private void deleteCheckedData() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ICartItem iCartItem : mineCartAdapter.getData()) {
            if (iCartItem.getItemType() == ICartItem.TYPE_CHILD) {
                if (iCartItem.isChecked()) {
                    long id = ((GoodsBean) iCartItem).getItemId();
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append(id);
                }
            }
        }

        //String ids = stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
        mineCartAdapter.removeChecked();
    }
}
