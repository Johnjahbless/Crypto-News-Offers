package com.app.android.june.cryptonewsoffers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Habeex on 4/20/2017.
 */

public class Item {
    //API items
    @SerializedName("title")
    @Expose
    private String login;
    @SerializedName("imageurl")
    @Expose
    private String avatarUrl;
    @SerializedName("url")
    @Expose
    private String htmlUrl;
    @SerializedName("body")
    @Expose
    private String titleDetail;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("published_on")
    @Expose
    private String date;
    @SerializedName("img")
    @Expose
    private String img;

    //Create constructor
    public Item(String login, String avatarUrl, String htmlUrl, String titleDetail, String source, String date, String img) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.htmlUrl = htmlUrl;
        this.titleDetail = titleDetail;
        this.source = source;
        this.date = date;
        this.img = img;
    }

    //use getter and setter to pass the item data
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
    public String getTitleDetail() {
        return titleDetail;
    }
    public void setTitleDetail(String titleDetail) {
        this.titleDetail = titleDetail;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
