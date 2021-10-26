package com.itb.itbbar.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.itb.itbbar.Adapters.Offers_RV_Adapter;
import com.itb.itbbar.R;

public class Offers_Fragment extends Fragment implements View.OnClickListener {

    RecyclerView offers_RV;
    TextView toolbar_title;
    ImageView toolbar_menu_button;
    Button toolbar_back_button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offers, container, false);
        initialisation(view);
        setAdapter();
        onCLickActions();

        return view;
    }

    private void onCLickActions() {
        toolbar_back_button.setOnClickListener(this);
    }

    private void setAdapter() {

        toolbar_menu_button.setVisibility(View.GONE);
        toolbar_back_button.setVisibility(View.VISIBLE);
        Offers_RV_Adapter offers_rv_adapter = new Offers_RV_Adapter(getContext());
        offers_RV.setAdapter(offers_rv_adapter);

    }

    private void initialisation(View view) {
        toolbar_title = view.findViewById(R.id.toolbar_title);
        toolbar_title.setText("Offer");
        offers_RV = view.findViewById(R.id.offer_recyclerView);
        toolbar_menu_button = view.findViewById(R.id.toolbar_menu_button);
        toolbar_back_button = view.findViewById(R.id.toolbar_back_button);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_back_button:
                Navigation.findNavController(v).navigate(R.id.navigate_from_offers_to_main_fragment);
                break;
        }
    }
}