package com.working.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.working.R;
import com.working.setting.StatusData;

public class DataLoadUtilLayout extends FrameLayout {

    private View mLoading;
    private View mError;
    private View mEmpty;
    private FrameLayout mStatusView;
    private LoadingView mLvLoading;
    private OnErrorOnTry mCallback;

    public DataLoadUtilLayout(@NonNull Context context) {
        this(context, (AttributeSet)null);
    }

    public DataLoadUtilLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DataLoadUtilLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.status_container_contrllor, null, true);
        addView(view);
        mLoading = findViewById(R.id.loading);
        mError = findViewById(R.id.error);
        mEmpty = findViewById(R.id.empty);
        mLvLoading = findViewById(R.id.loading_view);
        mError.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback != null) {
                    mCallback.onTry();
                    setStatus(StatusData.LOADING);
                }
            }
        });
    }

    public DataLoadUtilLayout(Context context, FrameLayout statusView, OnErrorOnTry callback) {
        this(context);
        mStatusView = statusView;
        mStatusView.addView(this);
        mCallback = callback;
    }

    private static final String TAG = "DataLoadUtilLayout";
    public void setStatus(StatusData statusData) {
        mLoading.setVisibility(GONE);
        mEmpty.setVisibility(GONE);
        mError.setVisibility(GONE);
        mStatusView.getChildAt(0).setVisibility(GONE);
        boolean isTwink = mStatusView.getChildAt(0) instanceof TwinklingRefreshLayout;
        boolean isRecycler = mStatusView.getChildAt(0) instanceof RecyclerView;
        if (!isTwink && !isRecycler) {
            throw new IllegalArgumentException("没找到数据容器！");
        }
        if(statusData == StatusData.LOADING){
            mLoading.setVisibility(VISIBLE);
        }else if(statusData == StatusData.LOADED){
            mStatusView.getChildAt(0).setVisibility(VISIBLE);
        }else if(statusData == StatusData.EMPTY){
            mEmpty.setVisibility(VISIBLE);
        }else if(statusData == StatusData.ERROR){
            mError.setVisibility(VISIBLE);
        }
    }

    public interface OnErrorOnTry{
        void onTry();
    }

}
