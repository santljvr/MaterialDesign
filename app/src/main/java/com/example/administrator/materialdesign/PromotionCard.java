package com.example.administrator.materialdesign;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;

public class PromotionCard extends Activity {

    TextView footer,description,title;
    Button button;
    ImageView image_promotion;
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_card);
        image_promotion = (ImageView) findViewById(R.id.imageView);
        footer= (TextView) findViewById(R.id.promotion_footer);
        description= (TextView) findViewById(R.id.description_promotion);
        title= (TextView) findViewById(R.id.title_promotion);
        button= (Button) findViewById(R.id.button);
        webView = (WebView) findViewById(R.id.webView);
       final Promotions data = (Promotions) getIntent().getExtras().getSerializable("Promotions");
        URL newurl = null;
        try {
            newurl =  new URL(data.getPromotion_image_url());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(String.valueOf(newurl)).into(image_promotion);
        description.setText(""+data.getPromotion_description());
        footer.setText(""+data.getPromotion_footer());
        title.setText(""+data.getPromtion_title());
        button.setText(""+data.getPromotion_button_title());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getPromotion_button_target()));
                //startActivity(intent);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.loadUrl(data.getPromotion_button_target());
            }
        });



    }
}
