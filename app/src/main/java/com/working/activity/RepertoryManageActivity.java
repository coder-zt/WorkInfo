package com.working.activity;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.working.R;
import com.working.base.BaseActivity;
import com.working.databinding.ActivityRepertoryManageLayoutBinding;
import com.working.utils.AppConfig;
import com.working.utils.AppRouter;

public class RepertoryManageActivity extends BaseActivity {

    private ActivityRepertoryManageLayoutBinding mDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_repertory_manage_layout);
        mDataBinding.setActivity(this);
    }

   /**
     * 去查看入库记录
     */
    public void toInRecord(){
        AppRouter.toListActivity(this, AppConfig.ACTIVITY_STOCK_IN);
    }

    /**
     * 去查看出库记录
     */
    public void toOutRecord(){
            AppRouter.toListActivity(this, AppConfig.ACTIVITY_STOCK_OUT);
    }

    /**
     * 去查看库存清单
     */
    public void toBalanceRecord(){
        AppRouter.toListActivity(this, AppConfig.ACTIVITY_MATERIAL);
    }

}
