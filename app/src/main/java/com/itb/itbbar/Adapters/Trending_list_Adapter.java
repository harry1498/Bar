package com.itb.itbbar.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itb.itbbar.R;

import java.util.ArrayList;
import java.util.List;

public class Trending_list_Adapter extends RecyclerView.Adapter<Trending_list_Adapter.MyViewHolder> {
    Context context;
    View view;
    List<String> textList= new ArrayList<>();
    TypedArray typedArray;
    Trending_list_Adapter.itemCLickListener clickOnItem;

    public Trending_list_Adapter(Context context,List<String> textList,TypedArray typedArray) {
        this.context=context;
        this.textList=textList;
        this.typedArray= typedArray;
    }
    @NonNull
    @Override
    public Trending_list_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_list, parent, false);
        return new Trending_list_Adapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Trending_list_Adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_name.setText(textList.get(position));
       holder.img_list.setImageResource(typedArray.getResourceId(position,0));
       holder.ll_layout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               clickOnItem.listeners(position);

           }
       });


    }

    @Override
    public int getItemCount() {
        return textList.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_list;
        TextView tv_name;
        LinearLayout ll_layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_layout=itemView.findViewById(R.id.ll_layout);
            tv_name=itemView.findViewById(R.id.tv_name);
            img_list=itemView.findViewById(R.id.img_list);

        }
    }

    public interface itemCLickListener{
        public void listeners(int position);


    }
    public void setClickOnInterface(itemCLickListener clickOnItem){
        this.clickOnItem=clickOnItem;
    }




}
