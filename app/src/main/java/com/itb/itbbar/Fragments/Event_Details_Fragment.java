package com.itb.itbbar.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.itb.itbbar.R;

public class Event_Details_Fragment extends Fragment implements View.OnClickListener {

    Button toolbar_back_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event__details_, container, false);

        initialisation(view);
        toolbar_back_button.setVisibility(View.VISIBLE);
        onClickAction();
        return view;
    }

    private void onClickAction() {
        toolbar_back_button.setOnClickListener(this);
    }

    private void initialisation(View view) {
        toolbar_back_button = view.findViewById(R.id.toolbar_back_button);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back_button:
                Navigation.findNavController(view).navigate(R.id.navigate_from_eventDetails_to_main_fragment);
        }
    }
}