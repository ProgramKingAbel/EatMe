package com.example.grace.eatme;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Grace on 11/30/2017.
 */

public class foodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView foodName;
    public ImageView foodImage
            ;
    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public foodViewHolder(View itemView)
    {
        super(itemView);

        foodName = (TextView)itemView.findViewById(R.id.food_name);
        foodImage = (ImageView)itemView.findViewById(R.id.food_image);

        itemView.setOnClickListener(this);


    }

    @Override
    public void onClick(View view)
    {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
