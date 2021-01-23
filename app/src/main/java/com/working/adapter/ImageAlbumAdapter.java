package com.working.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.working.R;
import com.working.databinding.RecyclerAlbumImageLayoutBinding;
import com.working.databinding.RecyclerSelectImageLayoutBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * 清单中横向展示图片的适配器
 */
public class ImageAlbumAdapter extends RecyclerView.Adapter<ImageAlbumAdapter.SelectImageView> {

    private static final String TAG = "ImageAlbumAdapter";
    private List<String> imageUrls = new ArrayList<>();

    @NonNull
    @Override
    public SelectImageView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_album_image_layout, parent, false);
            RecyclerAlbumImageLayoutBinding mBind = DataBindingUtil.bind(inflate);
        return new SelectImageView(inflate, mBind);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectImageView holder, int position) {
        holder.getBinding().setImageUrl(imageUrls.get(position));

    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }


    /**
     * 设置整个图片集合的数据
     * @param picUrl
     */
    public void setPicUrl(String picUrl) {
        Log.d(TAG, "setPicUrl: " + picUrl);
        if (picUrl != null && picUrl.length() > 0) {
            String[] urls = picUrl.split(",");
            for (String url : urls) {
                if (url.endsWith(".jpg") || url.endsWith(".mp4")) {
                    imageUrls.add(url);
                }
            }
        }

    }


    public static class SelectImageView extends RecyclerView.ViewHolder {
        private RecyclerAlbumImageLayoutBinding binding;

        public SelectImageView(@NonNull View itemView, RecyclerAlbumImageLayoutBinding bind) {
            super(itemView);
            binding = bind;
        }

        public RecyclerAlbumImageLayoutBinding getBinding() {
            return binding;
        }
    }


}
