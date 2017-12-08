package com.example.grace.eatme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Foodlist extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference foodList;
    String categoryId="";

    FirebaseRecyclerAdapter<Food,foodViewHolder>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);

        database= FirebaseDatabase.getInstance();
       foodList = database.getReference("food");

       recyclerView = (RecyclerView)findViewById(R.id.recycler_foodlist);
       recyclerView.setHasFixedSize(true);
       layoutManager = new LinearLayoutManager(this);

       recyclerView.setLayoutManager(layoutManager);

     if(getIntent()!=null)
//
//       {
          categoryId=getIntent().getStringExtra("CategoryId");
         //  if(categoryId.isEmpty() && categoryId!=null)
//           {
               loadListFood(categoryId);
//           }
       //}
    }

    private void loadListFood(String categoryId)
    {
    adapter = new FirebaseRecyclerAdapter<Food, foodViewHolder>(Food.class,R.layout.food_item,foodViewHolder.class,foodList.orderByChild("menuID").equalTo(categoryId)) {
        @Override
        protected void populateViewHolder(foodViewHolder viewHolder, Food model, int position) {

            viewHolder.foodName.setText(model.getName());
            Log.d("foood",model.getName());
            Picasso.with(getApplicationContext()).load(model.getImage()).into(viewHolder.foodImage);

            final Food local =model;
            viewHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                   // Toast.makeText(Foodlist.this,""+local.getName(),Toast.LENGTH_LONG).show();
                    Intent foodDetail = new Intent(Foodlist.this,FoodDetails.class);
                    foodDetail.putExtra("FoodId",adapter.getRef(position).getKey());
                    startActivity(foodDetail);

                }
            });

        }
    };
        adapter.notifyDataSetChanged();
    recyclerView.setAdapter(adapter);


    }
}
