package com.working.setting;

import android.graphics.drawable.Drawable;

/**
 * 首页-我的-菜单item
 */
public class MineMenuItem {
    public Drawable resId;
    public String itemName;
    public OnItemClickedListener callback;

    public MineMenuItem(Drawable resId, String itemName,OnItemClickedListener listener) {
        this.resId = resId;
        this.itemName = itemName;
        callback = listener;
    }

    public interface OnItemClickedListener{
        void onItemClick();
    }
}
