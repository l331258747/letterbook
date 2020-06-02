package com.csxs.letterbook.widgets;


import android.content.Context;
import android.graphics.Color;

import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import com.csxs.core.utils.ScreenUtil;
import com.csxs.letterbook.R;
import com.csxs.letterbook.seller.adapter.SellerCarAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.math.BigDecimal;

import q.rorbin.badgeview.QBadgeView;

public class ShopCarView extends FrameLayout {
	public TextView car_limit, tv_amount;
	public ImageView iv_shop_car;
	public TextView car_badge;
	private BottomSheetBehavior behavior;
	public boolean sheetScrolling;
	public View shoprl;
	public int[] carLoc;
	public SellerCarAdapter carAdapter;
	public QBadgeView	badge;
	private RelativeLayout rlOrderImage;

	public void setBehavior(final BottomSheetBehavior behavior, SellerCarAdapter sellerCarAdapter) {
		this.behavior = behavior;
		this.carAdapter=sellerCarAdapter;
	}

	public void setBehavior(final BottomSheetBehavior behavior, final View blackView,SellerCarAdapter sellerCarAdapter) {
		this.behavior = behavior;
		this.carAdapter=sellerCarAdapter;
		behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
			@Override
			public void onStateChanged(@NonNull View bottomSheet, int newState) {
				sheetScrolling = false;
				if (newState == BottomSheetBehavior.STATE_COLLAPSED || newState == BottomSheetBehavior.STATE_HIDDEN) {
					blackView.setVisibility(View.GONE);
				}
			}

			@Override
			public void onSlide(@NonNull View bottomSheet, float slideOffset) {
				sheetScrolling = true;
				blackView.setVisibility(View.VISIBLE);
				ViewCompat.setAlpha(blackView, slideOffset);
			}
		});
		blackView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
				return true;
			}
		});

		badge = new QBadgeView(getContext());
		initBadgeView(badge);
	}

	public ShopCarView(@NonNull Context context) {
		super(context);
	}

	public ShopCarView(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}
	public void initBadgeView(QBadgeView badge) {
		badge.setBadgeGravity(Gravity.END | Gravity.TOP);
		badge.setBadgeBackgroundColor(getResources().getColor(R.color.color_E7A124));
		badge.setShowShadow(false);
		badge.setBadgeTextSize(10, true);
		badge.setBadgePadding(4, true);
		badge.setGravityOffset(0, 4, true);
	}


	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (iv_shop_car == null) {
			iv_shop_car = findViewById(R.id.iv_shop_car);
			rlOrderImage = findViewById(R.id.rl_order_image);
			car_badge = findViewById(R.id.car_badge);
			car_limit = findViewById(R.id.car_limit);

			tv_amount = findViewById(R.id.tv_amount);
			shoprl = findViewById(R.id.car_rl);
			shoprl.setOnClickListener(new toggleCar());
			carLoc = new int[2];
			iv_shop_car.getLocationInWindow(carLoc);
			carLoc[0] = carLoc[0] + iv_shop_car.getWidth() / 2 - ScreenUtil.dip2px(getContext(), 10);
			car_limit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
//					Toast.makeText(v.getContext(),"看看",Toast.LENGTH_SHORT).show();
					if(submitGoodsListener!=null){
						submitGoodsListener.onSettlementSellerGoods(v);
					}
				}
			});

		}
	}

	public void updateAmount(BigDecimal amount) {
//		if (amount.compareTo(new BigDecimal(0.0)) == 0) {
//			car_limit.setText("¥20 起送");
//			car_limit.setTextColor(Color.parseColor("#a8a8a8"));
//			car_limit.setBackgroundColor(Color.parseColor("#535353"));
//			findViewById(R.id.car_nonselect).setVisibility(View.VISIBLE);
//			findViewById(R.id.amount_container).setVisibility(View.GONE);
//			iv_shop_car.setImageResource(R.drawable.ic_place_order);
//			behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//		} else if (amount.compareTo(new BigDecimal(20.0)) < 0) {
//			car_limit.setText("还差 ¥" + new BigDecimal(20.0).subtract(amount) + " 起送");
//			car_limit.setTextColor(Color.parseColor("#a8a8a8"));
//			car_limit.setBackgroundColor(Color.parseColor("#535353"));
//			findViewById(R.id.car_nonselect).setVisibility(View.GONE);
//			findViewById(R.id.amount_container).setVisibility(View.VISIBLE);
//			iv_shop_car.setImageResource(R.drawable.ic_place_order);
//		} else {
//			car_limit.setText("     去结算     ");
//			car_limit.setTextColor(Color.WHITE);
//			car_limit.setBackgroundColor(Color.parseColor("#59d178"));
//			findViewById(R.id.car_nonselect).setVisibility(View.GONE);
//			findViewById(R.id.amount_container).setVisibility(View.VISIBLE);
//			iv_shop_car.setImageResource(R.drawable.ic_place_order);
//		}

		if (amount.compareTo(new BigDecimal(0.0)) == 0) {
			car_limit.setText("我选好了");
			car_limit.setBackgroundResource(R.drawable.bg_selector_no_goods_submit);
			findViewById(R.id.car_nonselect).setVisibility(View.VISIBLE);
			tv_amount.setVisibility(View.GONE);
			iv_shop_car.setImageResource(R.drawable.ic_place_order);
			behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
		}else{
			car_limit.setText("我选好了");
			//car_limit.setTextColor(Color.WHITE);
			car_limit.setBackgroundResource(R.drawable.bg_selector_goods_submit);
		//	car_limit.setBackgroundColor(Color.parseColor("#59d178"));
			findViewById(R.id.car_nonselect).setVisibility(View.GONE);
			tv_amount.setVisibility(View.VISIBLE);
			iv_shop_car.setImageResource(R.drawable.ic_place_order_light);
		}

		tv_amount.setText(getContext().getString(R.string.rmb_order_total,amount));
	}




	public void showBadge(int total) {
		car_badge.setVisibility(View.GONE);

		if (total > 0) {

			if(badge!=null){
				badge.bindTarget(rlOrderImage);
				badge.setBadgeNumber(total);
			}

//			car_badge.setVisibility(View.VISIBLE);
//			car_badge.setText(total + "");
		} else {
			//car_badge.setVisibility(View.INVISIBLE);
			if (badge != null) {
				badge.hide(false);
			}
		}
	}

	private class toggleCar implements OnClickListener {

		@Override
		public void onClick(View view) {
			if (sheetScrolling) {
				return;
			}
			if (carAdapter.getItemCount() == 0) {
				return;
			}
			if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
				behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
			} else {
				behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
			}
		}
	}

	public SettlementClickListener submitGoodsListener;
	public interface SettlementClickListener{
          void onSettlementSellerGoods(View view);
	}

	public SettlementClickListener getSubmitGoodsListener() {
		return submitGoodsListener;
	}

	public void setSubmitGoodsListener(SettlementClickListener submitGoodsListener) {
		this.submitGoodsListener = submitGoodsListener;
	}
}
