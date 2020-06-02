package com.csxs.viewlib.cartlayout;



import com.csxs.viewlib.cartlayout.bean.GroupDataE;
import com.csxs.viewlib.cartlayout.bean.ICartItem;
import com.csxs.viewlib.cartlayout.bean.IChildItem;
import com.csxs.viewlib.cartlayout.bean.IGroupItem;

import java.util.ArrayList;
import java.util.List;

public class ParseHelper {

    /**
     * 取出 list 中的 groupBean
     *
     * @param beans
     * @param childPosition
     * @return
     */
    public static GroupDataE getGroupBean(List<ICartItem> beans, int childPosition) {
//        for (int i = childPosition; i >= 0; i--) {
//            if (beans.get(i).getItemType() == ICartItem.TYPE_GROUP) {
//                return ((IGroupItem) beans.get(i));
//            }
//        }
//        return null;

        GroupDataE groupDataE=new GroupDataE();
        int groupPosition = 0;
        for (int i = childPosition; i >= 0; i--) {
            if (beans.get(i).getItemType() == ICartItem.TYPE_GROUP) {
                groupPosition = i;
                groupDataE.setItemIGroup(((IGroupItem) beans.get(i)));
                break;
            }
        }
        groupDataE.setGroupPosition(groupPosition);

        return groupDataE;
    }

    /**
     * 获取 group 下的 child list
     *
     * @param beans    整个数据 list
     * @param position 当前 group 的 position
     */
    public static List<ICartItem> getChildList(List<ICartItem> beans, int position) {
        List<ICartItem> childList = new ArrayList<>();
        for (int i = position; i < beans.size(); i++) {
            if (beans.get(i).getItemType() == ICartItem.TYPE_GROUP) {
                break;
            } else if (beans.get(i).getItemType() == ICartItem.TYPE_CHILD) {
                childList.add(beans.get(i));
            }
        }

        for (int i = position - 1; i >= 0; i--) {
            if (beans.get(i).getItemType() == ICartItem.TYPE_GROUP) {
                break;
            } else if (beans.get(i).getItemType() == ICartItem.TYPE_CHILD) {
                childList.add(beans.get(i));
            }
        }

        return childList;
    }

    /**
     * 根据 itemId 获取 child 所在的 group 的 position
     *
     * @param beans         整个数据 list
     * @param childPosition child 的 下标
     * @return group 的 position
     */
    public static int getGroupPosition(List<ICartItem> beans, int childPosition) {
        int groupPosition = 0;
        for (int i = childPosition; i >= 0; i--) {
            if (beans.get(i).getItemType() == ICartItem.TYPE_GROUP) {
                groupPosition = i;
                break;
            }
        }
        return groupPosition;
    }


    /**
     * 获取bottom
     * @param beans
     * @param childPosition
     * @return
     */
    public static int getBottomPosition(List<ICartItem> beans, int childPosition) {
        int bottomPosition=0;
        for (int i = childPosition; i<=beans.size(); i++) {
            if (beans.get(i).getItemType() == ICartItem.TYPE_BOTTOM_EMPTY) {
                bottomPosition = i;
                break;
            }
        }
        return bottomPosition;
    }
}
