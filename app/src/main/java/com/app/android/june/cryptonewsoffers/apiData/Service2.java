package com.app.android.june.cryptonewsoffers.apiData;

import com.app.android.june.cryptonewsoffers.model.ItemResult2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service2 {
    //search for user's that write java language which their location is Lagos
    @GET("/data/v2/news/?categories=BTC,ETH,regulation,sponsored")
    Call<ItemResult2> getItems();
}