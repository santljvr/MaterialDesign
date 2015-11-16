package com.example.administrator.materialdesign;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private Myadapter mAdapter;

    private ArrayList<Promotions> promotionSet = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        if(isNetworkAlive()) {
            downloadPromotions downloadPromotions = new downloadPromotions();
            downloadPromotions.execute("http://www.abercrombie.com/anf/nativeapp/Feeds/promotions.json");
        } else
        {
            //we will use SQLite. Not implementing due to time.
        }

    }

    protected Boolean isNetworkAlive(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    protected String getString(InputStream input) {
        String string = null;

        StringBuilder s = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            while ((string = br.readLine()) != null)
                s.append(string + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return s.toString();
    }

    public void populatePromotionsList(String input) throws JSONException {
        Promotions promotions = new Promotions();
        JSONObject json = new JSONObject(input);
        if (json != null) {
            JSONArray promotionArray = json.getJSONArray("promotions");
            if (promotionArray != null) {
                for (int i = 0; i < promotionArray.length(); i++) {
                    JSONObject object = promotionArray.getJSONObject(i);
                    Object buttonObj = object.get("button");
                    if (buttonObj instanceof JSONArray) {
                        JSONArray buttonArray = (JSONArray) buttonObj;
                        for (int j = 0; j < buttonArray.length(); j++) {
                            JSONObject buttonObject = buttonArray.getJSONObject(j);
                            Log.d("target", "" + buttonObject.getString("target"));
                            Log.d("title", "" + buttonObject.getString("title"));
                            promotions.setPromotion_button_target(buttonObject.getString("target"));
                            promotions.setPromotion_button_title(buttonObject.getString("title"));
                        }
                    } else if (buttonObj instanceof JSONObject) {
                        JSONObject buttonObject2 = (JSONObject) buttonObj;
                        Log.d("target", "" + buttonObject2.getString("target"));
                        Log.d("title", "" + buttonObject2.getString("title"));
                        promotions.setPromotion_button_target(buttonObject2.getString("target"));
                        promotions.setPromotion_button_title(buttonObject2.getString("title"));
                    }

                    if (object.has("title")) {
                        Log.d("Promotion title", "" + object.getString("title"));
                        promotions.setPromtion_title(object.getString("title"));
                    } else
                        promotions.setPromtion_title("NULL");

                    if (object.has("image")) {
                        Log.d("Promotion image", "" + object.getString("image"));
                        promotions.setPromotion_image_url(object.getString("image"));
                    } else
                        promotions.setPromotion_image_url("NULL");

                    if (object.has("description")) {
                        Log.d("Promotion description", "" + object.getString("description"));
                        promotions.setPromotion_description(object.getString("description"));
                    } else
                        promotions.setPromotion_description("NULL");

                    if (object.has("footer")) {
                        Log.d("Promotion footer", "" + object.getString("footer"));
                        promotions.setPromotion_footer(object.getString("footer"));
                    } else
                        promotions.setPromotion_footer("NULL");
                    promotionSet.add(promotions);
                    promotions = new Promotions();
                }
            }
        }
    }


    public class downloadPromotions extends AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... params) {
            String url = params[0];
            String string;
            InputStream input = null;

            if (url.isEmpty()) {
                return null;
            } else {
                try {

                    input = new URL(url).openStream();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                string = getString(input);
                try {
                    populatePromotionsList(string);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mAdapter = new Myadapter(MainActivity.this, promotionSet);
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        }
    }
}



