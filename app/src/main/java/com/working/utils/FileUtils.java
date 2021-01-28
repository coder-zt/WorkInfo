package com.working.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.working.WorkingApp;
import com.working.setting.InspectionFiledInfo;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class FileUtils {

    private static final String TAG = "FileUtils";

    private static String getJson(String fileName) {
        StringBuilder sb = new StringBuilder();
        AssetManager am = WorkingApp.getContext().getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    am.open(fileName)));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next.trim());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            sb.delete(0, sb.length());
            ToastUtil.showMessage("解析巡检记录字段失败！");
        }
        return sb.toString().trim();
    }

    public static List<InspectionFiledInfo> getInspectFiled() {
        String json = getJson("InspectionFiled.json");
        try {
            if (json.isEmpty()) {
                return null;
            }
            JSONArray jsonArray = new JSONArray(json);
            Gson gson = new Gson();
            List<InspectionFiledInfo> filedInfo = gson.fromJson(jsonArray.toString(),
                    new TypeToken<List<InspectionFiledInfo>>(){}.getType());
            return filedInfo;
        } catch (JSONException e) {
            e.printStackTrace();
            ToastUtil.showMessage("解析json失败！");
        }
        return null;
    }

    /**
     * 计算md5的工具方法
     * @param password
     * @return 加密后的密码
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String md5Encryption(String password) {
        MessageDigest m= null;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes("UTF8"));
            byte s[ ]=m.digest( );
            String result="";
            for (int i=0; i<s.length;i++){
                result+=Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
            }
            return result;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

       return null;
    }

    public static void copyValue(Object target, Object source) {
        Class<?> targetClass = target.getClass();
        Class<?> sourceClass = source.getClass();
        for (Method targetMethod : targetClass.getMethods()) {
            String targetMethodName = targetMethod.getName();
            //获取赋值对象的set方法
            if (targetMethodName.contains("set")) {
                //得到源对象的方法名称
                String GETtMethodName = targetMethodName.replace("set", "get");
                try {
                    Method sourceClassMethod = sourceClass.getMethod(GETtMethodName,null);
                    Object invoke = sourceClassMethod.invoke(source, null);
                    targetMethod.invoke(target, invoke);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
