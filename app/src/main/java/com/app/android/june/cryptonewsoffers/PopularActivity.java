package com.app.android.june.cryptonewsoffers;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.android.june.cryptonewsoffers.apiData.Client2;
import com.app.android.june.cryptonewsoffers.apiData.Service2;
import com.app.android.june.cryptonewsoffers.model.Item2;
import com.app.android.june.cryptonewsoffers.model.ItemResult2;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PopularActivity extends AppCompatActivity {
    AdView mAdView;
    InterstitialAd mInterstitialAd;
    private RecyclerView recyclerView2;
    ImageView Disconnected2;
    //ProgressDialog pd2;
    private SwipeRefreshLayout swipeContainer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       MobileAds.initialize(getApplicationContext(),"ca-app-pub-7446083837533381~4348416075");
        mAdView = (AdView) findViewById(R.id.adVieww);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView2.smoothScrollToPosition(0);
        swipeContainer2 = (SwipeRefreshLayout) findViewById(R.id.swipeContainer2);
        swipeContainer2.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJSON();
            }
        });
        loadJSON();

    }

    private void loadJSON() {
        Disconnected2 = (ImageView) findViewById(R.id.disconnected2);
        try {
            swipeContainer2.setRefreshing(true);
            Client2 Client2 = new Client2();
            Service2 apiService =
                    Client2.getClient2().create(Service2.class);
            Call<ItemResult2> call = apiService.getItems();
            call.enqueue(new Callback<ItemResult2>() {
                @Override
                public void onResponse(Call<ItemResult2> call, Response<ItemResult2> response) {
                    List<Item2> items = response.body().getData();
                    recyclerView2.setAdapter(new ItemAdapter2(getApplicationContext(), items));
                    recyclerView2.smoothScrollToPosition(0);
                    swipeContainer2.setRefreshing(false);
                    Disconnected2.setVisibility(View.GONE);
                    //pd2.hide();
                    mInterstitialAd = new InterstitialAd(getApplicationContext());
                    mInterstitialAd.setAdUnitId(getString(R.string.admob_interstitial_ad));
                    AdRequest adRequest = new AdRequest.Builder().build();
                    mInterstitialAd.loadAd(adRequest);
                    mInterstitialAd.setAdListener(new AdListener() {
                        public void onAdLoaded() {
                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            }
                        }
                    });
                }

                @Override
                public void onFailure(Call<ItemResult2> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(PopularActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
                    Disconnected2.setVisibility(View.VISIBLE);
                    swipeContainer2.setRefreshing(false);
                   // pd2.hide();

                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

@Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            mInterstitialAd = new InterstitialAd(getApplicationContext());
            mInterstitialAd.setAdUnitId(getString(R.string.admob_interstitial_ad));
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
            mInterstitialAd.setAdListener(new AdListener() {
                public void onAdLoaded() {
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);


        // Inflate the menu; this adds items to the action bar if it is prese
    }

}
