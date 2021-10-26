package com.itb.itbbar.Fragments.BottomNav_Frags;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itb.itbbar.Adapters.EventAdapter;
import com.itb.itbbar.Adapters.UpcomingEventAdapter;
import com.itb.itbbar.R;

public class Events_Fragment extends Fragment {

    RecyclerView events_RV, upcoming_events_RV;
    EventAdapter eventAdapter;
    UpcomingEventAdapter upcomingEventAdapter;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_events_, container, false);
        initialisation(view);
        settingUpRVs();
        
        return view;
    }

    private void settingUpRVs() {
        eventAdapter = new EventAdapter(context);
        events_RV.setAdapter(eventAdapter);
        eventAdapter.setClickOnInterface(new EventAdapter.itemCLickListener() {
            @Override
            public void listeners(int position) {
                Navigation.findNavController(getView()).navigate(R.id.navigate_from_main_fragment_to_eventDetails);
            }

        });
        upcomingEventAdapter = new UpcomingEventAdapter(context);
        upcoming_events_RV.setAdapter(upcomingEventAdapter);
    }

    private void initialisation(View view) {
        events_RV = view.findViewById(R.id.event_recyclerView);
        upcoming_events_RV=view.findViewById(R.id.upcoming_events_rv);
    }
}