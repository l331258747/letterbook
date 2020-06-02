package com.csxs.letterbook.entity;

/**
 * @author: yeliu
 * created on 2020/5/21
 * description:
 */
public class LabelE {

    /**
     * addTime : 2020-05-08T16:00:00.000+0000
     * id : 1
     * labelName : 养宠物
     */

    private String addTime;
    private int id;
    private String labelName;

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}
