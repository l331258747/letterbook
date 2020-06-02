package com.csxs.viewlib.cartlayout.bean;

/**
 * @author: yeliu
 * created on 2020/4/26
 * description:
 */
public class GroupDataE {
    private IGroupItem<IChildItem> itemIGroup;
    private int groupPosition;

    public IGroupItem<IChildItem> getItemIGroup() {
        return itemIGroup;
    }

    public void setItemIGroup(IGroupItem<IChildItem> itemIGroup) {
        this.itemIGroup = itemIGroup;
    }

    public int getGroupPosition() {
        return groupPosition;
    }

    public void setGroupPosition(int groupPosition) {
        this.groupPosition = groupPosition;
    }
}
