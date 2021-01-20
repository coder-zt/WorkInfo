package com.working.utils;

import android.widget.Toast;

import com.working.WorkingApp;

public class ToastUtil {

    public static void showMessage(String msg){
        Toast.makeText(WorkingApp.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
