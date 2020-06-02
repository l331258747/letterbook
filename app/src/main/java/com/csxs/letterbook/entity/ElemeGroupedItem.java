package com.csxs.letterbook.entity;

/*
 * Copyright (c) 2018-2019. KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import com.csxs.viewlib.linkerv.bean.BaseGroupedItem;


public class ElemeGroupedItem extends BaseGroupedItem<CommodityListE> {

    public ElemeGroupedItem(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public ElemeGroupedItem(CommodityListE item) {
        super(item);
    }


    public CommodityListE commodityListE;

    public CommodityListE getCommodityListE() {
        return commodityListE;
    }

    public void setCommodityListE(CommodityListE commodityListE) {
        this.commodityListE = commodityListE;
    }

    //    public static class ItemInfo extends BaseGroupedItem.ItemInfo {
//        private String content;
//        private String imgUrl;
//        private String cost;
//        private int count;
//
//        public ItemInfo(String title, String group, String content) {
//            super(title, group);
//            this.content = content;
//        }
//
//        public ItemInfo(String title, String group, String content, String imgUrl) {
//            this(title, group, content);
//            this.imgUrl = imgUrl;
//        }
//
//        public ItemInfo(String title, String group, String content, String imgUrl, String cost) {
//            this(title, group, content, imgUrl);
//            this.cost = cost;
//        }
//
//        public String getContent() {
//            return content;
//        }
//
//        public void setContent(String content) {
//            this.content = content;
//        }
//
//        public String getImgUrl() {
//            return imgUrl;
//        }
//
//        public void setImgUrl(String imgUrl) {
//            this.imgUrl = imgUrl;
//        }
//
//        public String getCost() {
//            return cost;
//        }
//
//        public void setCost(String cost) {
//            this.cost = cost;
//        }
//
//        public int getCount() {
//            return count;
//        }
//
//        public void setCount(int count) {
//            this.count = count;
//        }
//
//        @Override
//        public String toString() {
//            return "ItemInfo{" +
//                    "content='" + content + '\'' +
//                    ", imgUrl='" + imgUrl + '\'' +
//                    ", cost='" + cost + '\'' +
//                    ", count=" + count +
//                    '}';
//        }
//    }
//
//
//    @Override
//    public String toString() {
//        return "ElemeGroupedItem{" +
//                "isHeader=" + isHeader +
//                ", info=" + info +
//                ", header='" + header + '\'' +
//                '}';
//    }
}
