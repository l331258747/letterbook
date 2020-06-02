package com.csxs.letterbook.entity;

/**
 * @author: yeliu
 * created on 2020/5/15
 * description:
 */
public class SpecListE {
    /**
     * "id":251,
     * "itemText":"常温"
     */
    private int id;
    private String itemText;
    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }
}
