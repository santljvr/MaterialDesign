package com.example.administrator.materialdesign;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 07-11-2015.
 */
public class Myadapter extends RecyclerView.Adapter<PromotionsViewHolder> {


    private MainActivity context;
    private ArrayList<Promotions> promotion_items;


    public Myadapter(MainActivity context, ArrayList<Promotions> items) {
        this.context = context;
        this.promotion_items = items;
    }

    public ArrayList<Promotions> getPromotion_items() {
        return promotion_items;
    }

    @Override
    public PromotionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.promotion_view, parent, false);
        PromotionsViewHolder holder = new PromotionsViewHolder(context, view, promotion_items);
        return holder;
    }

    public void onBindViewHolder(PromotionsViewHolder holder, int position) {
        Promotions promotions = promotion_items.get(position);
        holder.promotion.setText(promotions.getPromtion_title());
        URL newurl = null;
        try {
            newurl = new URL(promotions.getPromotion_image_url());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // holder.promotionimage.setImageBitmap(BitmapFactory.decodeStream(newurl.openConnection().getInputStream()));


        //Glide.with(context).load(newurl).into(holder.promotionimage);
        Picasso.with(context).load(String.valueOf(newurl)).into(holder.promotionimage);

    }

    @Override
    public int getItemCount() {
        //return items == null ? 0 : items.size();
        return promotion_items.size();
    }
}
