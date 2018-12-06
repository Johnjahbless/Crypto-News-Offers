package com.app.android.june.cryptonewsoffers.apiData;

import com.app.android.june.cryptonewsoffers.model.ItemResult;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Habeex on 4/20/2017.
 */

public interface Service {
    //search for user's that write java language which their location is Lagos
    @GET("/data/v2/news/?categories=Technology,Blockchain&excludeCategories=Regulation,Mining")
    Call<ItemResult> getItems();
}
