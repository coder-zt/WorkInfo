package com.working.utils;

import android.app.Activity;
import android.content.Intent;

import androidx.fragment.app.FragmentActivity;

import com.google.gson.Gson;
import com.working.MainActivity;
import com.working.activity.AddMaterialActivity;
import com.working.activity.InspectionDetailActivity;
import com.working.activity.ApprovalActivity;
import com.working.activity.ApprovalOutActivity;
import com.working.activity.BrowseActivity;
import com.working.activity.ChangePsdActivity;
import com.working.activity.InStockDetailActivity;
import com.working.activity.LoginActivity;
import com.working.activity.NoticeActivity;
import com.working.activity.NoticeListActivity;
import com.working.activity.OrderDetailActivity;
import com.working.activity.PurchaseDetailActivity;
import com.working.activity.OutStockDetailActivity;
import com.working.activity.ListActivity;
import com.working.activity.RepertoryManageActivity;
import com.working.activity.UserInfoActivity;
import com.working.domain.IndexNotice;
import com.working.domain.InspectionList;

import java.util.ArrayList;
import java.util.List;

public class AppRouter {

    public static void toMainActivity(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }


    public static void toAddInspectionActivity(Activity activity, InspectionList.DataBean.RecordsBean recordsBean){
        Intent intent = new Intent(activity, InspectionDetailActivity.class);
        if (recordsBean != null) {
            intent.putExtra("data", new Gson().toJson(recordsBean));
        }
        activity.startActivity(intent);
    }

    public static void toLoginActivity(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
    }

    public static void toUserInfoActivity(Activity activity) {
        Intent intent = new Intent(activity, UserInfoActivity.class);
        activity.startActivity(intent);
    }


    public static void toRepertoryManageActivity(Activity activity) {
        Intent intent = new Intent(activity, RepertoryManageActivity.class);
        activity.startActivity(intent);
    }


    /**
     * 列表清单activity(采购，够买，入库、出库、库存)
     * @param activity
     * @param position
     */
    public static void toListActivity(Activity activity, int position) {
        Intent intent = new Intent(activity, ListActivity.class);
        intent.putExtra("data", position);
        activity.startActivity(intent);
    }


    public static void toPurchaseDetailActivity(FragmentActivity activity, String id,String inspectionId, int approval) {
        boolean isGrant = UserDataMan.getInstance().checkFirstApprovalGrant() ||
                UserDataMan.getInstance().checkSecondApprovalGrant();
        if (isGrant) {//进行审批（具有审批职能)
                Intent intent = new Intent(activity, ApprovalActivity.class);
                intent.putExtra("data", id);
                intent.putExtra("data_inspection", inspectionId);
                activity.startActivity(intent);
            }else{//重新上报\生成购买记录\查看审核中（普通用户）
                Intent intent = new Intent(activity, PurchaseDetailActivity.class);
                intent.putExtra("data_id", id);
                intent.putExtra("data_approval", approval);
                activity.startActivity(intent);
            }
    }

    public static void toAddMaterialActivity(Activity activity, List<String> selectedItem, int requestCode) {
        Intent intent = new Intent(activity, AddMaterialActivity.class);
        intent.putStringArrayListExtra("data", (ArrayList<String>)selectedItem);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void toOrderDetailActivity(FragmentActivity activity, String id) {
        Intent intent = new Intent(activity, OrderDetailActivity.class);
        if (id != null) {
            intent.putExtra("data", id);
        }
        activity.startActivity(intent);
    }

    public static void toRepInInfoActivity(Activity activity, String id) {
        Intent intent = new Intent(activity, InStockDetailActivity.class);
        if (id != null) {
            intent.putExtra("data", id);
        }
        activity.startActivity(intent);
    }

//    public static void toRepOutInfoActivity(Activity activity, RepOutInfoBean.DataBean detailData) {
//        Intent intent = new Intent(activity, RepOutInfoActivity.class);
//        if (detailData != null) {
//            intent.putExtra("data", new Gson().toJson(detailData));
//        }
//        activity.startActivity(intent);
//    }


    public static void toNoticeActivity(Activity activity, IndexNotice.DataBean.RecordsBean detailData) {
        Intent intent = new Intent(activity, NoticeActivity.class);
        if (detailData != null) {
            intent.putExtra("data", new Gson().toJson(detailData));
        }
        activity.startActivity(intent);
    }

    public static void toNoticeListActivity(Activity activity) {
        Intent intent = new Intent(activity, NoticeListActivity.class);
        activity.startActivity(intent);
    }

    public static void toRepOutInfoActivity(FragmentActivity activity, String id) {
        boolean isGrant = UserDataMan.getInstance().checkFirstApprovalGrant() ||
                UserDataMan.getInstance().checkSecondApprovalGrant();
        if (isGrant) {//进行审批（具有审批职能）
            Intent intent = new Intent(activity, ApprovalOutActivity.class);
            if (id != null) {
                intent.putExtra("data", id);
            }
            activity.startActivity(intent);
        }else{//重新上报\生成购买记录\查看审核中（普通用户）
            Intent intent = new Intent(activity, OutStockDetailActivity.class);
            if (id != null) {
                intent.putExtra("data", id);
            }
            activity.startActivity(intent);
        }
    }

    public static void toBrowseActivity(Activity activity, String data) {
        Intent intent = new Intent(activity, BrowseActivity.class);
        intent.putExtra("data", data);
        activity.startActivity(intent);
    }

    public static void toChangePsdActivity(FragmentActivity activity) {
        Intent intent = new Intent(activity, ChangePsdActivity.class);
        activity.startActivity(intent);
    }
}
