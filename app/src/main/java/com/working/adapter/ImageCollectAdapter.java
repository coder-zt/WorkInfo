package com.working.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.working.R;
import com.working.databinding.RecyclerSelectImageLayoutBinding;
import com.working.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 图片集合的适配器
 */
public class ImageCollectAdapter extends RecyclerView.Adapter {


    private List<String> imageUrls = new ArrayList<>();
    private final OnImageClickListener mCallback;
    private boolean mCommitted;

    public ImageCollectAdapter(OnImageClickListener listener){
        mCallback = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 1){
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_empty_image_layout, parent, false);
            return new EmptyImageView(inflate);
        }
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_select_image_layout, parent, false);
        RecyclerSelectImageLayoutBinding mBind = DataBindingUtil.bind(inflate);
        return new SelectImageView(inflate, mBind);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  SelectImageView){
            if (imageUrls.get(position).equals("add_url")) {
                if (mCommitted) {
                    if(imageUrls.size() == 1){
                        ((SelectImageView)holder).getBinding().ivFin.setImageResource(R.mipmap.empty_icon);
                    }
                }else{
                    ((SelectImageView)holder).getBinding().ivFin.setImageResource(R.mipmap.add_pic_btn);
                }
            }
            ((SelectImageView)holder).getBinding().setImageUrl(imageUrls.get(position));
            ((SelectImageView)holder).getBinding().setClickObject(url -> {
                if (url == null) {
                    return;
                }
                if (url.equals("add_url") && !mCommitted) {
                    if (mCallback != null) {
                        mCallback.addRecord();
                    }
                }else if(url.endsWith("mp4")){
                    if (mCallback != null) {
                        mCallback.onVideoClicked(url);
                    }
                }else if(url.endsWith("jpg")){
                    if (mCallback != null) {
                        mCallback.onImageClicked(url);
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (imageUrls.get(0).equals("add_url") && mCommitted) {
            return 1;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public void addImage(String url){
        Log.d("相机", "addImage: " + url);
        if (!url.isEmpty()) {
            imageUrls.add(imageUrls.size() - 1, url);
        }
        notifyDataSetChanged();
    }

    /**
     * 设置整个图片集合的数据
     * @param urls
     */
    /**
     * 设置整个图片集合的数据
     * @param picUrl
     */
    public void setImageCollect(String picUrl) {
        imageUrls.clear();
        if (picUrl != null && picUrl.length() > 0) {
            String[] urls = picUrl.split(",");
            for (String url : urls) {
                if (url.endsWith(".jpg") || url.endsWith(".mp4")) {
                    imageUrls.add(url);
                }
            }
        }
        if (!mCommitted || (mCommitted && imageUrls.size() == 0)) {
            imageUrls.add("add_url");
        }
    }

    /**
     * 获取图片集合的路径
     *
     * @return
     */
    public String getImageCollect(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < imageUrls.size() - 1; i++) {
            if (imageUrls.get(i).length() == 0) {
                continue;
            }
            sb.append(imageUrls.get(i));
            if(i < imageUrls.size() - 2){
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public void setIsCommit(boolean commit) {
        mCommitted = commit;
    }

    public static class SelectImageView extends RecyclerView.ViewHolder {
        private RecyclerSelectImageLayoutBinding binding;

        public SelectImageView(@NonNull View itemView, RecyclerSelectImageLayoutBinding bind) {
            super(itemView);
            binding = bind;
        }

        public RecyclerSelectImageLayoutBinding getBinding() {
            return binding;
        }
    }

        public static class EmptyImageView extends RecyclerView.ViewHolder {

        public EmptyImageView(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener{
        void onImageClicked(String url);
    }

    public interface OnImageClickListener{
        void onImageClicked(String url);

        void addRecord();

        void onVideoClicked(String url);
    }
}
