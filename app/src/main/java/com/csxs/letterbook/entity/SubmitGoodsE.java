package com.csxs.letterbook.entity;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/5/26
 * description:
 */
public class SubmitGoodsE {


    /**
     * marchantId : 15
     * commList : [{"isSpec":1,"originalPrice":18,"commodityId":1214,"stock":16,"id":326,"itemText":"常温;加糖;小杯","number":2},{"isSpec":1,"originalPrice":17,"commodityId":1214,"stock":16,"id":327,"itemText":"常温;加糖;中杯","number":1},{"isSpec":0,"id":1214,"number":1,"commodityName":"珍珠奶茶","originalPrice":18}]
     */

    private int marchantId;
    private List<CommListBean> commoditys;

    public int getMarchantId() {
        return marchantId;
    }

    public void setMarchantId(int marchantId) {
        this.marchantId = marchantId;
    }

    public List<CommListBean> getCommoditys() {
        return commoditys;
    }

    public void setCommoditys(List<CommListBean> commoditys) {
        this.commoditys = commoditys;
    }

    public static class CommListBean {

        private int commodityId;
        private int tempSpecId;
        private int amount;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public int getTempSpecId() {
            return tempSpecId;
        }

        public void setTempSpecId(int tempSpecId) {
            this.tempSpecId = tempSpecId;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }
}
