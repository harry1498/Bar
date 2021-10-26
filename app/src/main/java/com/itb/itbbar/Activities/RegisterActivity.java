package com.itb.itbbar.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.itb.itbbar.R;

public class RegisterActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    LinearLayout ll_layout;
    TextView Tv_skip;
    Animation animation;
    ImageView google_sign;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        intViews();
        onClickActions();
        ll_layout = findViewById(R.id.ll_layout);
        ll_layout.setVisibility(View.VISIBLE);
        animation = AnimationUtils.loadAnimation(
                this,
                R.anim.slide_in_up
        );
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


    }

    private void onClickActions() {
        google_sign.setOnClickListener(this);
        Tv_skip.setOnClickListener(this);
    }

    private void intViews() {
        google_sign = findViewById(R.id.google_sign);
        Tv_skip = findViewById(R.id.Tv_skip);
    }


    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.google_sign: {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, RC_SIGN_IN);
            }
            case R.id.Tv_skip: {
                Intent intent = new Intent(this, DashBoard.class);
                startActivity(intent);
            }
        }
    }


    @Override


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            gotoProfile();
        } else {
            Toast.makeText(this, "Sign in cancel", Toast.LENGTH_LONG).show();
        }
    }

    private void gotoProfile() {
//        Navigation.findNavController().navigate(R.id.navigation_From_register_to_main);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}
