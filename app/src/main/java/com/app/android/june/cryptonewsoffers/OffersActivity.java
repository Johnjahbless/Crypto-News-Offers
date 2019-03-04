package com.app.android.june.cryptonewsoffers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class OffersActivity extends AppCompatActivity {
AdView mAdView;
InterstitialAd mInterstitialAd;
    private FloatingActionButton floatingActionButton;
    private boolean mProcessLike = false;
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Post> list;
    postAdapter adapter;
    ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reference = FirebaseDatabase.getInstance().getReference().child("Posts");
        setContentView(R.layout.activity_offers);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-7446083837533381~4348416075");
        mAdView = (AdView) findViewById(R.id.adVieww);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(OffersActivity.this, AddActivity.class));
        }
    });

        recyclerView = (RecyclerView) findViewById(R.id.post_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (isOnline()) {
            try {
                try {
                    //pDialog.dismiss();
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            list = new ArrayList<>();
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                Post s = dataSnapshot1.getValue(Post.class);
                                list.add(s);
                            }
                            adapter = new postAdapter(OffersActivity.this, list);
                            recyclerView.setAdapter(adapter);

                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(OffersActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
                            //pDialog.dismiss();
                            displayDialog();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    displayDialog();
                }
            } catch (ExceptionInInitializerError e) {
                Toast.makeText(OffersActivity.this, "Error has occured" + e, Toast.LENGTH_LONG).show();
                //pDialog.dismiss();
                displayDialog();
            }
        } else {
            displayDialog();
        }
    }
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        }else {
            return false;
        }
    }
    private void displayLoader() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading shops.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

    }
    private void displayDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("No Internet connection");
        alertDialogBuilder
                .setCancelable(false)
                .setIcon(R.drawable.ic_warning)
                .setMessage("Please enable your internet connection and try again")
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                dialog.cancel();


                            }
                        });



        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();




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
