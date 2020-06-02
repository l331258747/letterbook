package com.csxs.letterbook.seller.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.csxs.core.base.LazyDiffFragment;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.CommodityListE;
import com.csxs.letterbook.entity.ElemeGroupedItem;
import com.csxs.letterbook.entity.SellerGoodsCategoryDetailsE;
import com.csxs.letterbook.entity.SellerGoodsE;
import com.csxs.letterbook.seller.activity.SellerHomeActivity;
import com.csxs.letterbook.seller.adapter.SellerCarAdapter;
import com.csxs.letterbook.seller.adapter.SellerGoodsCategoryAdapter;
import com.csxs.letterbook.seller.adapter.SellerGoodsCategoryDetailsAdapter;
import com.csxs.letterbook.seller.adapter.SellerStoreDynamicAdapter;
import com.csxs.letterbook.seller.adapter.StoreGoodsPrimaryAdapterConfig;
import com.csxs.letterbook.seller.adapter.StoreGoodsSecondaryAdapterConfig;
import com.csxs.letterbook.seller.listener.AddGoodsSpac;
import com.csxs.letterbook.seller.mvp.contract.SellerStoreContract;
import com.csxs.letterbook.seller.mvp.presenter.SellerStoreOrderPresenter;
import com.csxs.letterbook.widgets.GoodsParamBottpmView;
import com.csxs.viewlib.linkerv.LinkageRecyclerView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import q.rorbin.badgeview.QBadgeView;

/**
 * @author: yeliu
 * created on 2020/4/18
 * description:商家订单
 */
public class SellerStoreOrderFragment extends LazyDiffFragment<SellerStoreOrderPresenter> implements SellerStoreContract.ISellerStoreOrderView{
//    , AddWidget.OnAddClick

    @BindView(R.id.link_rv_seller_store)
    LinkageRecyclerView sellerOrderRv;

//    @BindView(R.id.car_container)
//    LinearLayout carContainer;
//
//    @BindView(R.id.car_mainfl)
//    ShopCarView shopCarView;
//
//    @BindView(R.id.blackview)
//    View blackView;
//
//    @BindView(R.id.car_recyclerview)
//    RecyclerView carRecView;


    private SellerCarAdapter carAdapter;

    public BottomSheetBehavior behavior;


//    @BindView(R.id.iv_order_image)
//    ImageView haveGoodsImage;
//
//    @BindView(R.id.rl_order_image)
//    RelativeLayout view;

//    @BindView(R.id.rc_shop_goods_category)
//    RecyclerView rcShopGoodsCategory;

//    @BindView(R.id.fl_shop_goods)
//    FrameLayout flShopGoods;

//    @BindView(R.id.rc_shop_goods)
//    RecyclerView rcShopGoods;

//    private ItemHeaderDecoration mDecoration;

    private QBadgeView badge;

    private StoreGoodsSecondaryAdapterConfig adapterConfig;

    private StoreGoodsPrimaryAdapterConfig storeGoodsPrimaryAdapterConfig;

    private SellerStoreDynamicAdapter sellerStoreInfoAdapter;

    //左边 recylerView
    private SellerGoodsCategoryAdapter sellerGoodsCategoryAdapter;
    private boolean isMoved;
    private int targetPosition;//点击左边某一个具体的item的位置


    private SellerGoodsCategoryDetailsAdapter sellerGoodsCategoryDetailsAdapter;

    private GridLayoutManager gridLayoutManager;

    private boolean move = false;

    private int mIndex = 0;


    private List<SellerGoodsE> list;

    private List<SellerGoodsCategoryDetailsE> categoryDetails;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public void initParam() {

    }

    @Override
    protected int getLayoutId() {
//       return R.layout.fragment_seller_shop_goods;
        return R.layout.fragment_order_store_seller;

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        adapterConfig = new StoreGoodsSecondaryAdapterConfig((SellerHomeActivity) getActivity(), (AddGoodsSpac) getActivity());

        storeGoodsPrimaryAdapterConfig = new StoreGoodsPrimaryAdapterConfig();
//        sellerOrderRv.getSecondaryAdapter().getItemCount()
//        sellerOrderRv.getSecondaryAdapter().getItemId()
        badge = new QBadgeView(getContext());
//        initShopCar();
//        new  ListenerGoodsCount(){
//            @Override
//            public void add() {
//                calculationGoodsCount();
//            }
//
//            @Override
//            public void reduce() {
//                calculationGoodsCount();
//            }
//        }


//        sellerGoodsCategoryAdapter = new SellerGoodsCategoryAdapter(mContext,R.layout.item_goods_category,null);
//        linearLayoutManager = new LinearLayoutManager(mContext);
//        rcShopGoodsCategory.setLayoutManager(linearLayoutManager);
//        rcShopGoodsCategory.setAdapter(sellerGoodsCategoryAdapter);
//        sellerGoodsCategoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//             //   sellerGoodsCategoryAdapter.setSelectedPosition(position);
//                isMoved = true;
//                targetPosition = position;
//                setChecked(position, true);
//            }
//        });
//
//
//        rcShopGoods.addOnScrollListener(new RecyclerViewListener());
//        gridLayoutManager = new GridLayoutManager(mContext, 1);
//        rcShopGoods.setLayoutManager(gridLayoutManager);
//
//        sellerGoodsCategoryDetailsAdapter = new  SellerGoodsCategoryDetailsAdapter(null);
//
//        rcShopGoods.setAdapter(sellerGoodsCategoryDetailsAdapter);
//
//        mDecoration = new ItemHeaderDecoration(mContext, null);
//
//        rcShopGoods.addItemDecoration(mDecoration);
//
//        mDecoration.setCheckListener(this);




    }





    @Override
    protected void lazyInitData(boolean isFirstVisible) {
        if (isFirstVisible) {
            mPresenter.queryCommodity(15);
        }
    }

    @Override
    protected void fragmentHidden() {

    }

    public static Fragment newInstance(int status) {
        Bundle args = new Bundle();
        args.putInt("status", status);
        SellerStoreOrderFragment fragment = new SellerStoreOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }





    private void dealCar(CommodityListE commodityList) {
        HashMap<String, Long> typeSelect = new HashMap<>();//更新左侧类别badge用
        BigDecimal amount = new BigDecimal(0.0);
        int total = 0;
        boolean hasFood = false;
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            //  firstFragment.getFoodAdapter().notifyDataSetChanged();
        }
        List<CommodityListE> flist = carAdapter.getData();
        int p = -1;
        for (int i = 0; i < flist.size(); i++) {
            CommodityListE fb = flist.get(i);
            if (fb.getId() == commodityList.getId()) {
                fb = commodityList;
                hasFood = true;
                if (commodityList.getSelectCount() == 0) {
                    p = i;
                } else {
                    carAdapter.setData(i, commodityList);
                }
            }
            total += fb.getSelectCount();
            if (typeSelect.containsKey(fb.getClassifyName())) {
                typeSelect.put(fb.getClassifyName(), typeSelect.get(fb.getClassifyName()) + fb.getSelectCount());
            } else {
                typeSelect.put(fb.getClassifyName(), fb.getSelectCount());
            }

            amount = amount.add(new BigDecimal(Double.toString(fb.getOriginalPrice())).multiply(BigDecimal.valueOf(fb.getSelectCount())));
        }
        if (p >= 0) {
            carAdapter.remove(p);
        } else if (!hasFood && commodityList.getSelectCount() > 0) {
            carAdapter.addData(commodityList);
            if (typeSelect.containsKey(commodityList.getClassifyName())) {
                typeSelect.put(commodityList.getClassifyName(), typeSelect.get(commodityList.getClassifyName()) + commodityList.getSelectCount());
            } else {
                typeSelect.put(commodityList.getClassifyName(), commodityList.getSelectCount());
            }
            amount = amount.add(new BigDecimal(Double.toString(commodityList.getOriginalPrice())).multiply(BigDecimal.valueOf(commodityList.getSelectCount())));
            total += commodityList.getSelectCount();
        }
    //    shopCarView.showBadge(total);
//        firstFragment.getTypeAdapter().updateBadge(typeSelect);
      //  shopCarView.updateAmount(amount);

    }


//    public void calculationGoodsCount() {
//        int goods = 0;
//
//        for (int i = 0; i < sellerOrderRv.getSecondaryAdapter().getItems().size(); i++) {
//            ElemeGroupedItem b = (ElemeGroupedItem) sellerOrderRv.getSecondaryAdapter().getItems().get(i);
//            if (b != null && b.info != null) {
//                goods = goods + b.info.getCount();
//            }
//        }
//
//
//        if (goods == 0) {
//
//            haveGoodsImage.setImageResource(R.drawable.ic_place_order);
//            if (badge != null) {
//                badge.hide(false);
//            }
//        } else {
//
        //    haveGoodsImage.setImageResource(R.drawable.ic_place_order_light);
//            initBadgeView(badge, view, goods);
//        }
//    }

    public void initBadgeView(QBadgeView badge, View view, int num) {
        badge.setBadgeGravity(Gravity.END | Gravity.TOP);
        badge.setBadgeBackgroundColor(getResources().getColor(R.color.color_E7A124));
        badge.setShowShadow(false);
        badge.setBadgeTextSize(10, true);
        badge.setBadgePadding(4, true);
        badge.setGravityOffset(0, 4, true);
        badge.bindTarget(view);
        badge.setBadgeNumber(num);
    }

    @Override
    public void queryCommoditySuccess(List<SellerGoodsE> list) {

    //    List<String> categoryList = new ArrayList<>();
        List<ElemeGroupedItem> items = new ArrayList<>();

      //  List<SellerGoodsCategoryDetailsE> categoryDetails = new ArrayList<>();
        int size = list.size();
        if (list.size() > 0) {
            for (int i = 0; i < size; i++) {
                ElemeGroupedItem elemeGroupedItem = new ElemeGroupedItem(true, list.get(i).getClassifyName());
                items.add(elemeGroupedItem);
                List<CommodityListE> commodityList = list.get(i).getCommodityList();
                for (int k = 0; k < commodityList.size(); k++) {
                    CommodityListE commodityListE = commodityList.get(k);
                     if (commodityListE.getSpecResultList()!=null&&commodityListE.getSpecResultList().size()>0){
                         commodityListE.setGroup(commodityListE.getClassifyName());
                         ElemeGroupedItem elemeGroupedItem1 = new ElemeGroupedItem(commodityListE);
                         elemeGroupedItem1.setHeader(false);
                         items.add(elemeGroupedItem1);
                     }

                }
            }
        }

        Gson gson = new Gson();
       String json= gson.toJson(items);
       Log.e("sellerOrderRv",json);

        sellerOrderRv.init(items, storeGoodsPrimaryAdapterConfig, adapterConfig);

//        List<String> categoryList = new ArrayList<>();
//        categoryDetails = new ArrayList<>();
//        int size = list.size();
//        if (list.size() > 0) {
//            for (int i = 0; i < size; i++) {
//
//                categoryList.add(list.get(i).getClassifyName());
//
//                SellerGoodsCategoryDetailsE title=new  SellerGoodsCategoryDetailsE();
//
//                title.setTitleName(list.get(i).getClassifyName());
//
//                title.setTag(String.valueOf(i));
//
//                title.setItemType(1);
//
//                categoryDetails.add(title);
//                List<CommodityListE>  commodityList= list.get(i).getCommodityList();
//                for (int k=0;k<commodityList.size();k++){
//                    SellerGoodsCategoryDetailsE body=new  SellerGoodsCategoryDetailsE();
//                    body.setCommodityList(commodityList.get(k));
//                    body.setItemType(2);
//                    title.setTag(String.valueOf(k));
//                    categoryDetails.add(body);
//                }
//            }
//        }
//
//        sellerGoodsCategoryAdapter.setNewData(categoryList);
//
//        sellerGoodsCategoryDetailsAdapter.setNewData(categoryDetails);


    }

    @Override
    public void queryCommodityFailure(int code, String msg) {
        Log.e("queryCommoditySuccess", code + ":" + msg);
    }


}
