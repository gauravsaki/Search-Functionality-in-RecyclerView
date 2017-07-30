package com.example.rashi.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by rashi on 29/7/17.
 */

public class CustomImageSrcSetter {
    @BindingAdapter({"bind:imageUrl"})
    public  static void setImageSrc(ImageView imageView,String url){
        Glide.with(imageView.getContext())
                .load(url)
                .override(150, 150)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imageView);
    }

}
