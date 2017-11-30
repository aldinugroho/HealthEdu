package com.example.aldi.androidkopiku.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aldi.androidkopiku.Interface.ItemClickListener;
import com.example.aldi.androidkopiku.R;

/**
 * Created by aldi on 11/19/17.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView menuName;
    public ImageView menuImage;
    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);
        menuName = (TextView)itemView.findViewById(R.id.menu_name);
        menuImage = (ImageView)itemView.findViewById(R.id.menu_image);

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
