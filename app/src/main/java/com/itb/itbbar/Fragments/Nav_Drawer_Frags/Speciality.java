package com.itb.itbbar.Fragments.Nav_Drawer_Frags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itb.itbbar.Adapters.Speciality_RV_Adapter;
import com.itb.itbbar.R;

public class Speciality extends Fragment {

    RecyclerView speciality_RV;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_speciality, container, false);

        initialisation(view);
        Speciality_RV_Adapter adapter=new Speciality_RV_Adapter(getContext());
        speciality_RV.setAdapter(adapter);
        return view;
    }

    private void initialisation(View view) {
        speciality_RV=view.findViewById(R.id.speciality_recyclerView);
    }
}