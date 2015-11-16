package com.example.administrator.materialdesign;

import java.io.Serializable;

/**
 * Created by Administrator on 08-11-2015.
 */
public class Promotions implements Serializable {

    private String promtion_title; //
    private String promotion_image_url;
    private String promotion_description;
    private String promotion_footer;
    private String promotion_button_target;
    private String promotion_button_title;


    public Promotions(){
        super();
    }

    public void setPromtion_title(String promtion_title) {
        this.promtion_title = promtion_title;
    }

    public void setPromotion_image_url(String promotion_image_url) {
        this.promotion_image_url = promotion_image_url;
    }

    public void setPromotion_footer(String promotion_footer) {
        this.promotion_footer = promotion_footer;
    }

    public void setPromotion_description(String promotion_description) {
        this.promotion_description = promotion_description;
    }

    public void setPromotion_button_title(String promotion_button_title) {
        this.promotion_button_title = promotion_button_title;
    }

    public void setPromotion_button_target(String promotion_button_target) {
        this.promotion_button_target = promotion_button_target;
    }

    public String getPromotion_image_url() {
        return promotion_image_url;
    }

    public String getPromtion_title() {
        return promtion_title;
    }

    public String getPromotion_description() {
        return promotion_description;
    }

    public String getPromotion_footer() {
        return promotion_footer;
    }

    public String getPromotion_button_target() {
        return promotion_button_target;
    }

    public String getPromotion_button_title() {
        return promotion_button_title;
    }
}
