package com.itb.itbbar.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class RegisterFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    LinearLayout ll_layout;
    TextView Tv_skip;
    Animation animation;
    ImageView google_sign;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        intViews(view);
        Listner();
        ll_layout= view.findViewById(R.id.ll_layout);
        ll_layout.setVisibility(View.VISIBLE);
        animation = AnimationUtils.loadAnimation(
                getActivity(),
                R.anim.slide_in_up
        );
        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient=new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(),this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        return view;
    }

    private void Listner() {
        google_sign.setOnClickListener(this);
        Tv_skip.setOnClickListener(this);
    }

    private void intViews(View view) {
        google_sign= view.findViewById(R.id.google_sign);
        Tv_skip= view.findViewById(R.id.Tv_skip);
    }


    @Override
    public void onStart() {
        super.onStart();

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.google_sign:{
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,RC_SIGN_IN);
            }
            case R.id.Tv_skip:{
                Navigation.findNavController(getView()).navigate(R.id.navigation_From_register_to_main);
            }
        }
    }




    @Override


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            gotoProfile();
        }else{
            Toast.makeText(getActivity(),"Sign in cancel", Toast.LENGTH_LONG).show();
        }
    }
    private void gotoProfile(){
        Navigation.findNavController(getView()).navigate(R.id.navigation_From_register_to_main);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}