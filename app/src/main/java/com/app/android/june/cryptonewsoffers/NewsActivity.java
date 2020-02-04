package com.app.android.june.cryptonewsoffers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class NewsActivity extends AppCompatActivity {
    private String htmlUrl, title, detail;
    AdView mAdView;
    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" +
            "AAAA2v4SEzM:APA91bEhETxwoy4Aeex3PfgqkPHRMyS-YibbadHces3M0MFVrMJtlF6k5ELMK70sajF03Zge7MMgr-qkI67jCAgr5T8ot-0A7eD9WWnM8Mi70P1Bb5jfjRj9xUcpbwOUXagPNGwB7ixA";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";
    private static final String SUBSCRIBE_TO = "userCRT";

    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        final Toolbar toolbar = findViewById(R.id.activity_detail_toolbar);
        setSupportActionBar(toolbar);
        title = getIntent().getStringExtra("title");
        detail = getIntent().getStringExtra("detail");
        String imgUrl = getIntent().getStringExtra("imgUrl");
        String source = getIntent().getStringExtra("source");
        String date = getIntent().getStringExtra("date");
        String img = getIntent().getStringExtra("img");
        htmlUrl = getIntent().getStringExtra("htmlUrl");
        //long num = Long.parseLong(date);
       // long timeStamp = mes.getTime();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(date));
        String cal[] = calendar.getTime().toString().split(" ");
        String time_of_message = cal[1]+","+cal[2]+"  "+cal[3].substring(0,5);
        ImageView newImage = findViewById(R.id.image);
        ImageView sourceImage = findViewById(R.id.image2);
        TextView titletext = findViewById(R.id.title);
        TextView datetext = findViewById(R.id.date);
        TextView detailtext = findViewById(R.id.text);
        TextView sourcetext = findViewById(R.id.source);


        titletext.setText(title);
        datetext.setText(time_of_message);
        detailtext.setText(detail);
        sourcetext.setText(source);
        Picasso.with(this)
                .load(imgUrl)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(newImage);
        Picasso.with(this)
                .load(imgUrl)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(sourceImage);
        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-3986775143456319~2583827908");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        sendNotification();
    }

    private void sendNotification() {
        TOPIC = "/topics/userCRT"; //topic has to match what the receiver subscribed to
        NOTIFICATION_TITLE = title;
        NOTIFICATION_MESSAGE = detail;

        JSONObject notification = new JSONObject();
        JSONObject notifcationBody = new JSONObject();
        try {
            notifcationBody.put("title", NOTIFICATION_TITLE);
            notifcationBody.put("message", NOTIFICATION_MESSAGE);

            notification.put("to", TOPIC);
            notification.put("data", notifcationBody);
        } catch (JSONException e) {
            Log.e(TAG, "onCreate: " + e.getMessage() );
            //Toast.makeText(ChatActivity.this, "err  " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        sendNotification(notification);
    }

    private void sendNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());
                        // Toast.makeText(ChatActivity.this, "res  " + response.toString(), Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(MainActivity.this, "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }




    public void onChecked(View view) {
    }

    public void more(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(htmlUrl));
        startActivity(intent);
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



}
