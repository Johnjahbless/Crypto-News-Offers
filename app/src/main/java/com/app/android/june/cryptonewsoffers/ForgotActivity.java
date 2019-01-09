package com.app.android.june.cryptonewsoffers;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.ads.AdRequest;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {
    private EditText edtEmail;
    private Button btnResetPassword;
    private FirebaseAuth mAuth;
    private ProgressDialog pDialog;
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtEmail = (EditText) findViewById(R.id.edt_reset_email);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
        mAuth = FirebaseAuth.getInstance();

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
displayLoader();
                String email = edtEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_SHORT).show();
                    pDialog.dismiss();
                    return;
                }

                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    pDialog.dismiss();
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ForgotActivity.this);
                                    alertDialogBuilder.setTitle("Reset Password Instructions sent Successfully");
                                    alertDialogBuilder
                                            .setCancelable(false)
                                            .setMessage("Please check your mail to reset your password")
                                            .setIcon(R.drawable.ic_done)
                                            .setPositiveButton("Ok",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            finish();
                                                            dialog.cancel();


                                                        }
                                                    });


                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();

                                    // Toast.makeText(ResetPasswordActivity.this, "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    pDialog.dismiss();
                                    //Toast.makeText(ResetPasswordActivity.this, "Fail to send reset password email!", Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ForgotActivity.this);
                                    alertDialogBuilder.setTitle("Invalid Email Address");
                                    alertDialogBuilder
                                            .setCancelable(false)
                                            .setMessage("Sorry, This email address is not registered")
                                            .setIcon(R.drawable.ic_cancel)
                                            .setPositiveButton("Ok",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            dialog.cancel();


                                                        }
                                                    });


                                    AlertDialog alertDialog = alertDialogBuilder.create();
                                    alertDialog.show();

                                }
                            }
                        });
            }
        });
    }
        private void displayLoader() {
            pDialog = new ProgressDialog(ForgotActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

        @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
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


