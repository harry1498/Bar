package com.itb.itbbar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itb.itbbar.R;

public class Offers_RV_Adapter  extends RecyclerView.Adapter<Offers_RV_Adapter.ViewHolder> {

    Context context;

    public Offers_RV_Adapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.offers_rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Glide.with(context).load().into(holder.offers_IV);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView offers_IV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            offers_IV=itemView.findViewById(R.id.offers_item_IV);
        }
    }
}
