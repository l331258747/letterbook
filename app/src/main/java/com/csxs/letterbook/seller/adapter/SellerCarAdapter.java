package com.csxs.letterbook.seller.adapter;


import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.csxs.letterbook.R;
import com.csxs.letterbook.entity.CommodityListE;
import com.csxs.letterbook.widgets.numbtn.AddWidget;


import java.math.BigDecimal;
import java.util.List;

public class SellerCarAdapter extends BaseQuickAdapter<CommodityListE, BaseViewHolder> {
	private AddWidget.OnAddClick onAddClick;

	public SellerCarAdapter(@Nullable List<CommodityListE> data, AddWidget.OnAddClick onAddClick) {
		super(R.layout.item_bottom_car, data);
		this.onAddClick = onAddClick;
	}

	@Override
	protected void convert(BaseViewHolder helper, CommodityListE item) {
		helper.setText(R.id.car_name, item.getCommodityName())
//				.setText(R.id.car_price, item.getStrPrice(mContext, item.getPrice().multiply(BigDecimal.valueOf(item.getSelectCount()))))
		;
		AddWidget addWidget = helper.getView(R.id.car_addwidget);
//		addWidget.setData(this, helper.getAdapterPosition(), onAddClick);
		addWidget.setData(onAddClick,item);
	}
}
