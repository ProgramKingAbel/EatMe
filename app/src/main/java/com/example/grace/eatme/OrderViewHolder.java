package com.example.grace.eatme;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Grace on 12/4/2017.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public OrderViewHolder(View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
    }

    TextView txtOrderId,txtOrderPhone,txtOrderstatus,txtOrderAddress;
    private ItemClickListener itemClickListener;
    public OrderViewHolder(View itemView) {
        super(itemView);


        txtOrderAddress = (TextView)itemView.findViewById(R.id.order_address);
        txtOrderId = (TextView)itemView.findViewById(R.id.order_id);
        txtOrderPhone = (TextView)itemView.findViewById(R.id.order_phone);
        txtOrderstatus =(TextView)itemView.findViewById(R.id.order_status);

        //itemView.setOnClickListener(this);



    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
      itemClickListener.onClick(view,getAdapterPosition(),false);


    }
}
