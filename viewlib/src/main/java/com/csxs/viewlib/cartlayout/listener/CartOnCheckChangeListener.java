package com.csxs.viewlib.cartlayout.listener;


import androidx.recyclerview.widget.RecyclerView;

import com.csxs.viewlib.cartlayout.CartAdapter;
import com.csxs.viewlib.cartlayout.ParseHelper;
import com.csxs.viewlib.cartlayout.bean.GroupDataE;
import com.csxs.viewlib.cartlayout.bean.ICartItem;
import com.csxs.viewlib.cartlayout.bean.IGroupItem;

import java.util.List;

public abstract class CartOnCheckChangeListener implements OnCheckChangeListener, OnItemChangeListener {

    RecyclerView recyclerView;
    CartAdapter cartAdapter;

    public CartOnCheckChangeListener(RecyclerView recyclerView, CartAdapter adapter) {
        this.recyclerView = recyclerView;
        this.cartAdapter = adapter;
    }

    @Override
    public void onCheckedChanged(List<ICartItem> beans, int position, boolean isChecked, int itemType) {
        switch (itemType) {
            case ICartItem.TYPE_NORMAL:
                normalCheckChange(beans, position, isChecked);
                break;
            case ICartItem.TYPE_GROUP:
                groupCheckChange(beans, position, isChecked);
                break;
            case ICartItem.TYPE_CHILD:
                childCheckChange(beans, position, isChecked);
                break;
            default:
                break;
        }
    }

    /**
     * normal 选中状态变化
     *
     * @param beans     数据
     * @param position  group position
     * @param isChecked 选中状态
     */
    @Override
    public void normalCheckChange(List<ICartItem> beans, int position, boolean isChecked) {
        if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE
                && !recyclerView.isComputingLayout()) {// 避免滑动时刷新数据
            beans.get(position).setChecked(isChecked);
        }
    }

    /**
     * group 选中状态变化
     *
     * @param beans     数据
     * @param position  group position
     * @param isChecked 选中状态
     */
    @Override
    public void groupCheckChange(List<ICartItem> beans, int position, boolean isChecked) {
        if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE
                && !recyclerView.isComputingLayout()) {// 避免滑动时刷新数据
            beans.get(position).setChecked(isChecked);
            setChildCheck(beans, position, isChecked);

            int bottomPosition =ParseHelper.getBottomPosition(beans,position);
            setBottomCheck(beans,bottomPosition,isChecked);
            cartAdapter.notifyItemChanged(bottomPosition, CartAdapter.PAYLOAD_CHECKBOX);
        }
    }

    /**
     * child 选中状态变化
     *
     * @param beans     数据
     * @param position  child position
     * @param isChecked 选中状态
     */
    @Override
    public void childCheckChange(List<ICartItem> beans, int position, boolean isChecked) {

        if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE
                && !recyclerView.isComputingLayout()) {// 避免滑动时刷新数据

            beans.get(position).setChecked(isChecked);

            GroupDataE groupDataE=ParseHelper.getGroupBean(beans, position);
            int bottomPosition =ParseHelper.getBottomPosition(beans,position);

            if (!isChecked) {
                //group 为选中状态
                if (groupDataE.getItemIGroup().isChecked()) {
                    setGroupCheck(beans, groupDataE.getGroupPosition(), false);
                    setBottomCheck(beans,bottomPosition,false);
                    cartAdapter.notifyItemRangeChanged(groupDataE.getGroupPosition(),bottomPosition,CartAdapter.PAYLOAD_CHECKBOX);
                }
            } else {
                List<ICartItem> childList = ParseHelper.getChildList(beans, position);
                for (int i = 0; i < childList.size(); i++) {
                    // 只要有一个 child 没有选中，group 就不是选中
                    if (!childList.get(i).isChecked()) {
                        return;
                    }
                }

                setGroupCheck(beans, groupDataE.getGroupPosition(), true);
                setBottomCheck(beans,bottomPosition,true);
                cartAdapter.notifyItemRangeChanged(groupDataE.getGroupPosition(),bottomPosition,CartAdapter.PAYLOAD_CHECKBOX);
            }
        }
    }

    /**
     * 一次设置 group 下所有 child item 选中状态
     *
     * @param beans     整个数据 list
     * @param position  group position
     * @param isChecked 设置选中状态
     */
    private void setChildCheck(List<ICartItem> beans, int position, boolean isChecked) {
        for (int i = (position + 1); i < beans.size(); i++) {
            if (beans.get(i).getItemType() == ICartItem.TYPE_GROUP) {
                break;
            } else if (beans.get(i).getItemType() == ICartItem.TYPE_CHILD) {
                if (beans.get(i).isChecked() != isChecked) {
                    beans.get(i).setChecked(isChecked);
                    cartAdapter.notifyItemChanged(i, CartAdapter.PAYLOAD_CHECKBOX);
                }
            }
        }
    }

    /**
     * 设置 group item 选中状态
     *
     * @param beans         整个数据 list
     * @param groupPosition group 的 下标
     * @param isChecked     设置选中状态
     */
    private void setGroupCheck(List<ICartItem> beans, int groupPosition, boolean isChecked) {
        beans.get(groupPosition).setChecked(isChecked);
    }


    /**
     * 底部选中
     * @param beans
     * @param position
     * @param isChecked
     */
    private void setBottomCheck(List<ICartItem> beans, int position, boolean isChecked){
        beans.get(position).setChecked(isChecked);
    }
}