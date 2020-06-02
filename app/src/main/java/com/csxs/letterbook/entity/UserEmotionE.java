package com.csxs.letterbook.entity;

import com.contrarywind.interfaces.IPickerViewData;

/**
 * @author: yeliu
 * created on 2020/5/20
 * description:
 */
public class UserEmotionE implements IPickerViewData {


    /**
     * emotion : 单身
     * id : 1
     */

    private String emotion;
    private int id;

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getPickerViewText() {
        return emotion;
    }
}
