package com.app.android.june.cryptonewsoffers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Habeex on 4/20/2017.
 */

public class ItemResult {
    //get Promoted on APIData into List
    @SerializedName("Data")
    @Expose
    private List<Item> Promoted;

    public List<Item> getPromoted() {
        return Promoted;
    }

    public void setPromoted(List<Item> Promoted) {
        this.Promoted = Promoted;
    }
}
