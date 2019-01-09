package com.app.android.june.cryptonewsoffers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddActivity extends AppCompatActivity {

    private EditText mPostTitle;
    private EditText mPostDesc;
    private EditText mPostLink;
    private DatabaseReference mDatabase;
    private ProgressDialog mprogressbar;
    private DatabaseReference mDatabaseUSer;
    FirebaseDatabase firebaseDatabase;
    private String Title, Descr, Link, userId, fullName;
    ProgressDialog pDialog;
    FirebaseUser user;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            startActivity(new Intent(AddActivity.this, SignActivity.class));
            finish();
        }else {
            userId = user.getUid();
        }
       mPostTitle = findViewById(R.id.editText1);
       mPostDesc = findViewById(R.id.editText2);
       mPostLink = findViewById(R.id.editText3);
        Button mSubmitBtn = findViewById(R.id.btn);
        mDatabaseUSer = FirebaseDatabase.getInstance().getReference().child("Posts");
        //firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        getUser();
        //clickEvents();
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Title = mPostTitle.getText().toString();
              Descr = mPostDesc.getText().toString();
              Link = mPostLink.getText().toString();
              if (TextUtils.isEmpty(Title) || TextUtils.isEmpty(Descr) || TextUtils.isEmpty(Link)){
                  Toast.makeText(AddActivity.this, "Please fill all fields", Toast.LENGTH_LONG).show();
              }else {
                  displayLoader();
                  Add(Title, Descr, Link, fullName);
              }
            }
        });

    }

    private void getUser() {
        try {
            mDatabase.child(userId).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   User user = dataSnapshot.getValue(User.class);
                    fullName = user.getFullName();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }catch (Exception e){
            Toast.makeText(AddActivity.this, "Error occured" + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void Add(String Title, String Descr, String Link, String fullName) {
        Post user = new Post(Title, Descr, Link, fullName);
        mDatabaseUSer.child(userId).setValue(user)

                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        pDialog.dismiss();
                        Toast.makeText(AddActivity.this, "Offer added successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddActivity.this, OffersActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pDialog.dismiss();
                        Toast.makeText(AddActivity.this, "An error has occured" + e.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }
    private void displayLoader() {
        pDialog = new ProgressDialog(AddActivity.this);
        pDialog.setMessage("Adding Offer.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

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
