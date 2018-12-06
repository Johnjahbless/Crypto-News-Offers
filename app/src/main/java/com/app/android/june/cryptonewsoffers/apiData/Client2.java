package com.app.android.june.cryptonewsoffers.apiData;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client2 {
    // get the client base url
    public static final String BASE_URL = "https://min-api.cryptocompare.com";
    public static Retrofit retrofit = null;

    public static Retrofit getClient2() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
