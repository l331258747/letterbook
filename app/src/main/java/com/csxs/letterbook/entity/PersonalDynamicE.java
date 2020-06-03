package com.csxs.letterbook.entity;

import android.net.Uri;

import java.util.List;

/**
 * @author: yeliu
 * created on 2020/6/2
 * description:
 */
public class PersonalDynamicE {
    String headImg;
    String nickName;
    boolean isCheck;
    int sex;
    int age;
    String location;
    String content;
    List<Uri> imgs;
    String time;
    String range;

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Uri> getImgs() {
        return imgs;
    }

    public void setImgs(List<Uri> imgs) {
        this.imgs = imgs;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
