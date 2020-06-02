package com.csxs.letterbook.entity;

/**
 * @author: yeliu
 * created on 2020/5/12
 * description:动态 用户 信息
 */
public class DynamicUserinfoE {
    private String logoPic;
    private String nickname;
    private String birthDay;
    private String sex;
    private String headimgurl;
    private int gradeLv;
    private String gradeName;
    private int gradeId;
    private String gradeImages;
    private int  id;
    private String brief;

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogoPic() {
        return logoPic;
    }

    public void setLogoPic(String logoPic) {
        this.logoPic = logoPic;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public int getGradeLv() {
        return gradeLv;
    }

    public void setGradeLv(int gradeLv) {
        this.gradeLv = gradeLv;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeImages() {
        return gradeImages;
    }

    public void setGradeImages(String gradeImages) {
        this.gradeImages = gradeImages;
    }

    @Override
    public String toString() {
        return "DynamicUserinfoE{" +
                "logoPic='" + logoPic + '\'' +
                ", nickname='" + nickname + '\'' +
                ", birthDay='" + birthDay + '\'' +
                ", sex='" + sex + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", gradeLv=" + gradeLv +
                ", gradeName='" + gradeName + '\'' +
                ", gradeId=" + gradeId +
                ", gradeImages='" + gradeImages + '\'' +
                ", id=" + id +
                '}';
    }
}
