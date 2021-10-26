package com.itb.itbbar.Fragments.Nav_Drawer_Frags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itb.itbbar.Adapters.Order_History_RV_Adapter;
import com.itb.itbbar.R;

public class OrderHistory extends Fragment {

    private RecyclerView order_history_RV;
    private Order_History_RV_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);

        initialisation(view);
        settingRecyclerView();

        return view;
    }

    private void settingRecyclerView() {
        adapter=new Order_History_RV_Adapter(getContext());
        order_history_RV.setAdapter(adapter);
    }

    private void initialisation(View view) {
        order_history_RV=view.findViewById(R.id.order_history_RV);

    }
}