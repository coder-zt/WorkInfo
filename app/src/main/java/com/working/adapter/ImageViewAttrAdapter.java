package com.working.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.working.R;

public class ImageViewAttrAdapter {
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        if (url == null) {
            view.setImageResource(R.mipmap.ic_launcher);
        } else {
            // Glide代替Volley
            Glide.with(view.getContext()).load(url).into(view);
        }
    }

}
