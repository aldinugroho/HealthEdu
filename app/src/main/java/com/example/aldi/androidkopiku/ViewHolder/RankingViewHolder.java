package com.example.aldi.androidkopiku.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.aldi.androidkopiku.Interface.ItemClickListener;
import com.example.aldi.androidkopiku.R;

/**
 * Created by aldi on 11/30/17.
 */

public class RankingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView textName,textScore;
    private ItemClickListener itemClickListener;

    public RankingViewHolder(View itemView) {
        super(itemView);
        textName = (TextView)itemView.findViewById(R.id.textName);
        textScore = (TextView)itemView.findViewById(R.id.textScore);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
