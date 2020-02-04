package com.app.android.june.cryptonewsoffers;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;

public class BaseActivity extends AppCompatActivity {
    public ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        //Initialize Firebase
        Firebase.setAndroidContext(this);
    }


}
