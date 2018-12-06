package com.app.android.june.cryptonewsoffers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemResult2 {
    //get Data on APIData into List
    @SerializedName("Data")
    @Expose
    private List<Item2> Data;

    public List<Item2> getData() {
        return Data;
    }

    public void setData(List<Item2> Data) {
        this.Data = Data;
    }
}
