package com.itb.itbbar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itb.itbbar.R;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    Context context;
    View view;
    itemCLickListener clickOnInterface;


    public EventAdapter(Context context) {
        this.context=context;
    }


    @NonNull
    @Override
    public EventAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_rv_item, parent, false);
        return new EventAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.MyViewHolder holder, int position) {

        holder.ll_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnInterface.listeners(holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_layout;
        TextView tv_eventName,tv_Available;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_layout=itemView.findViewById(R.id.ll_layout);
            tv_eventName=itemView.findViewById(R.id.tv_eventName);
            tv_Available=itemView.findViewById(R.id.tv_Available);
        }
    }

    public interface itemCLickListener{
        public void listeners(int position);
    }
    public void setClickOnInterface(itemCLickListener clickOnInterface){
        this.clickOnInterface=clickOnInterface;
    }
}
