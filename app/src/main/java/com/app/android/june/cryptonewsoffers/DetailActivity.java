package com.app.android.june.cryptonewsoffers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.messaging.FirebaseMessaging;


public class DetailActivity extends AppCompatActivity {
    AdView mAdView;
    ToggleButton alarmToggle;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-3986775143456319~1819155348");
        mAdView = (AdView) findViewById(R.id.adVieww);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);

        checkNotifi();



        alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String toastMessage;
                if(isChecked){
                    SharedPreferences sharedPreferences = getSharedPreferences("notify", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("notifykey", 1);
                    editor.apply();

                    //Set the toast message for the "on" case
                    toastMessage = getString(R.string.notification_on);
                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("userCRT");
                    SharedPreferences sharedPreferences = getSharedPreferences("notify", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("notifykey", 2);
                    editor.apply();

                    //Set the toast message for the "off" case
                    toastMessage = getString(R.string.notification_off);
                }

                //Show a toast to say the alarm is turned on or off
                Toast.makeText(DetailActivity.this, toastMessage, Toast.LENGTH_SHORT)
                        .show();
            }
        });


    }

    private void checkNotifi() {
        SharedPreferences sharedPreferences = getSharedPreferences("notify", MODE_PRIVATE);
        Integer value = sharedPreferences.getInt("notifykey", 1);
        if (value == 1){
            alarmToggle.setChecked(true);
            FirebaseMessaging.getInstance().subscribeToTopic("userCRT");

        }else {
            Toast.makeText(this, "Notification is off", Toast.LENGTH_LONG).show();
            alarmToggle.setChecked(false);
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

        }
        return super.onOptionsItemSelected(item);


        // Inflate the menu; this adds items to the action bar if it is prese
    }

}
