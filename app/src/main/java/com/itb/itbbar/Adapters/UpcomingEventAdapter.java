package com.itb.itbbar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itb.itbbar.R;

public class UpcomingEventAdapter extends RecyclerView.Adapter<UpcomingEventAdapter.MyViewHolder> {
    View view;
    Context context;


    public UpcomingEventAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public UpcomingEventAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_event_rv_item, parent, false);
        return new UpcomingEventAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingEventAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}