package com.working.view;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UniformDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "UniformDecoration";
    private final int mSpace;
    private final int mChildCount;

    public UniformDecoration(int space, int count){
        mSpace = space;
        mChildCount = count;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        if(position < mChildCount - 1){
            outRect.left = 0;
            outRect.right = mSpace/(mChildCount-1);
        }

    }
}
