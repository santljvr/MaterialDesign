package com.example.administrator.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 08-11-2015.
 */
public class PromotionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public Context context;
    public TextView promotion;
    public ImageView promotionimage;
    private ArrayList<Promotions> promotion_items;


    public PromotionsViewHolder(Context context, View itemView, ArrayList<Promotions> promotion_items) {
        super(itemView);
        this.context = context;
        promotion = (TextView) itemView.findViewById(R.id.promotion_title);
        promotionimage = (ImageView) itemView.findViewById(R.id.promotion_image);
        this.promotion_items = promotion_items;
        promotionimage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.promotion_image) {
            Intent intent;
            intent = new Intent(context, PromotionCard.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Promotions", this.promotion_items.get(getAdapterPosition()));
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }
}
