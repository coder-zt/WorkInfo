package com.working.utils;

import android.widget.Toast;

import com.working.WorkingApp;

public class ToastUtil {

    public static final int TOAST_SHOW_ONCE_TIME = 2000;//两次Toast显示的时间
    private static long sOldShowTime;
    private static String sOldMsg = "";
    public static void showMessage(String msg){
        long nowTime = System.currentTimeMillis();
        if (nowTime - sOldShowTime < TOAST_SHOW_ONCE_TIME && sOldMsg.equals(msg)) {
            return;
        }
        sOldShowTime = nowTime;
        sOldMsg = msg;
        Toast.makeText(WorkingApp.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
