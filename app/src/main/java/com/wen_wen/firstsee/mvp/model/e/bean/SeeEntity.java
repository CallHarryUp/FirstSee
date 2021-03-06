package com.wen_wen.firstsee.mvp.model.e.bean;

/**
 * Created by WeLot on 2017/12/14.
 */

public class SeeEntity {
    private String title;
    private String desc;
    private String imgUrl;
    private String detailUrl;
    private String username;
    private String count;
    private String createTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    //测试数据用
    @Override
    public String toString() {
        return "SeeEntity{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                ", username='" + username + '\'' +
                ", count='" + count + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
