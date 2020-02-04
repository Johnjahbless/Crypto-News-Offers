package com.app.android.june.cryptonewsoffers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemResult2 {
    //get Data on APIData into List
    @SerializedName("Data")
    @Expose
    private List<Item> Data;

    public List<Item> getData() {
        return Data;
    }

    public void setData(List<Item> Data) {
        this.Data = Data;
    }
}
