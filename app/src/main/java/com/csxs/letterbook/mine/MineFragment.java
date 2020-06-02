package com.csxs.letterbook.mine;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.csxs.core.base.LazyDiffFragment;
import com.csxs.core.imageloader.ImageLoaderV4;
import com.csxs.core.utils.SpannableStringUtils;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.MineInfoE;
import com.csxs.letterbook.mine.activity.MineCartShopActivity;
import com.csxs.letterbook.mine.activity.MineOrderActivity;
import com.csxs.letterbook.mine.activity.ModifyMineInfoActivity;
import com.csxs.letterbook.mine.mvp.MinePresenter;
import com.csxs.letterbook.mine.mvp.contract.MineContract;
import com.csxs.viewlib.CircleImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: yeliu
 * created on 2020/4/13
 * description:首页 我的
 */
public class MineFragment extends LazyDiffFragment<MinePresenter> implements MineContract.IMineView {


    @BindView(R.id.tv_user_sign)
    TextView tvSign;
    @BindView(R.id.iv_my_bg)
    ImageView ivMyBg;
    @BindView(R.id.iv_user_header)
    CircleImageView ivUserHeader;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_text_my_order)
    TextView tvTextMyOrder;
    @BindView(R.id.tv_all_order)
    TextView tvAllOrder;
    @BindView(R.id.tv_wait_pay)
    TextView tvWaitPay;
    @BindView(R.id.tv_wait_send_goods)
    TextView tvWaitSendGoods;
    @BindView(R.id.tv_wait_accept_goods)
    TextView tvWaitAcceptGoods;
    @BindView(R.id.tv_wait_use_order)
    TextView tvWaitUseOrder;
    @BindView(R.id.tv_refund_order)
    TextView tvRefundOrder;
    @BindView(R.id.layout_my_order)
    ConstraintLayout layoutMyOrder;
    @BindView(R.id.tv_mine_dymaic)
    TextView tvMineDymaic;
    @BindView(R.id.tv_mine_cart)
    TextView tvMineCart;
    @BindView(R.id.tv_mine_authentication)
    TextView tvMineAuthentication;
    @BindView(R.id.tv_mine_task)
    TextView tvMineTask;
    @BindView(R.id.tv_mine_card)
    TextView tvMineCard;
    @BindView(R.id.tv_mine_address)
    TextView tvMineAddress;
    @BindView(R.id.tv_mine_customer)
    TextView tvMineCustomer;
    private Drawable drawable;

    @Override
    public void initParam() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void lazyInitData(boolean isFirstVisible) {
        if (isFirstVisible){
            drawable = getResources().getDrawable(R.drawable.ic_user_modify_sign);
            requestUserInfo();
        }
    }

    public void  requestUserInfo(){
        if(mPresenter!=null){
            mPresenter.getMineInfo();
        }

    }


    @Override
    protected void fragmentHidden() {

    }


    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    public static MineFragment newInstance(String type) {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        args.putString("type", type);
        return fragment;
    }

    @OnClick({R.id.iv_user_header, R.id.tv_text_my_order, R.id.tv_all_order, R.id.tv_wait_pay, R.id.tv_wait_send_goods, R.id.tv_wait_accept_goods, R.id.tv_wait_use_order, R.id.tv_refund_order, R.id.tv_mine_dymaic, R.id.tv_mine_cart, R.id.tv_mine_authentication, R.id.tv_mine_task, R.id.tv_mine_card, R.id.tv_mine_address, R.id.tv_mine_customer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_header:
                ModifyMineInfoActivity.start(mContext, ModifyMineInfoActivity.class, null);
                break;
            case R.id.tv_text_my_order:
                break;
            case R.id.tv_all_order:
                MineOrderActivity.start(mContext, MineOrderActivity.class, null);
                break;
            case R.id.tv_wait_pay:
                break;
            case R.id.tv_wait_send_goods:
                break;
            case R.id.tv_wait_accept_goods:
                break;
            case R.id.tv_wait_use_order:
                break;
            case R.id.tv_refund_order:
                break;
            case R.id.tv_mine_dymaic:
                break;
            case R.id.tv_mine_cart:
                MineCartShopActivity.start(mContext, MineCartShopActivity.class, null);
                break;
            case R.id.tv_mine_authentication:
                break;
            case R.id.tv_mine_task:
                break;
            case R.id.tv_mine_card:
                break;
            case R.id.tv_mine_address:
                break;
            case R.id.tv_mine_customer:
                break;
        }
    }

    @Override
    public void queryMineInfoSuccess(MineInfoE mineInfoE) {

        if (mineInfoE.getHeadimgurl() != null && mineInfoE.getHeadimgurl().length() > 0) {
            ImageLoaderV4.getInstance().displayImage(mContext, mineInfoE.getHeadimgurl(), ivUserHeader, R.drawable.ic_default_circle_store_header);
        } else {
            ivUserHeader.setImageResource(R.drawable.ic_default_circle_store_header);
        }

        if (mineInfoE.getNickname() != null) {
            tvUserName.setText(getResources().getString(R.string.str_placeholder, mineInfoE.getNickname()));
        } else {
            tvUserName.setText(getResources().getString(R.string.int_placeholder,mineInfoE.getId()));
        }




        if (mineInfoE.getSignature() != null && mineInfoE.getSignature().length() > 0) {

            SpannableStringUtils.addDrawableInEnd(tvSign, activity, drawable, mineInfoE.getSignature());
           // tvSign.setText(getResources().getString(R.string.str_placeholder, mineInfoE.getSignature()));
        } else {
            SpannableStringUtils.addDrawableInEnd(tvSign, activity, drawable, "主人很懒什么都没留下");
        }





    }

    @Override
    public void queryMineInfoFailure(int code, String msg) {

    }
}
