package com.working.utils;

import android.util.Log;

import com.working.domain.LoginInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 网络请求数据工具 Retrofit框架
 */
public class RetrofitManager {

    private Retrofit mRetrofit = null;
    private static RetrofitManager mRetrofitManager = null;

    private RetrofitManager(){
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器

                .baseUrl(AppConfig.BASE_URL) //设置网络请求的Url地址
                .client(initClient())
                .build();
    }
    /**
     * 设置拦截器 打印日志
     *
     * @return
     */
    private Interceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> {
            Log.i("HTTP日志", message);
            if (message.startsWith("{") && message.endsWith("}")) {//json信息
                printJson("", message, 0, false);
            }else{
                Log.i("HTTP日志", message);
            }
        });
        //显示日志
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    private void printJson(String name, String message, int grade, boolean add) {
        if (name.length() > 0) {
            name = "\"" + name + "\":";
        }
            Log.i("HTTP日志", getTab(grade) + name + "{");
            try {
                JSONObject jsonObject = new JSONObject(message);
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    String value = String.valueOf(jsonObject.get(key));
                    if (value.startsWith("{") && value.endsWith("}")){
                        printJson(key, value, grade + 1, true);
                    }else if (value.startsWith("[") && value.endsWith("]")){
                        printJsonArray(key, value, grade + 1);
                    }else{
                        StringBuilder sbInfo = new StringBuilder(getTab(grade + 1));
                        sbInfo.append("\"");
                        sbInfo.append(key);
                        sbInfo.append("\":");
                        if (jsonObject.get(key) instanceof String) {
                            sbInfo.append("\"");
                            sbInfo.append(value);
                            sbInfo.append("\"");
                        }else{
                            sbInfo.append(value);
                        }
                        if(keys.hasNext()){
                            sbInfo.append(",");
                        }
                        Log.i("HTTP日志",  sbInfo.toString());
                    }
                }
            } catch (JSONException e) {
                Log.i("HTTP日志", message);
                e.printStackTrace();
            }
            Log.i("HTTP日志", getTab(grade) + "}" + (add?",":""));
    }

    private void printJsonArray(String key, String message, int grade) {
        Log.i("HTTP日志", getTab(grade) + "\"" + key + "\":" + "[");
        try {
            JSONArray jsonArray = new JSONArray(message);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject o = (JSONObject)jsonArray.get(i);
                printJson("", o.toString(), grade, i < jsonArray.length() - 1);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HTTP日志", getTab(grade) + "],");
    }

    private String getTab(int grade) {
        StringBuilder sbTab = new StringBuilder();
        for (int i = 0; i < grade; i++) {
            sbTab.append("\t");
        }
        return sbTab.toString();
    }

    private OkHttpClient initClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        //设置拦截器
        httpClient.addInterceptor(getInterceptor());
        LoginInfo loginInfo = UserDataMan.getInstance().getLoginInfo();
        String accessToken = "";
        if ( loginInfo != null) {
            accessToken = loginInfo.getAccess_token();
        }
        final String token = accessToken;
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", AppConfig.AUTHORIZATION)
                        .header("Tenant-Id", AppConfig.TENANID)
                        .header("Blade-Auth", token!=null?token:"")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }

        });
        return httpClient.build();
    }

    public static synchronized RetrofitManager getRetrofitManager(){
        if(mRetrofitManager == null){
            synchronized (RetrofitManager.class){
                mRetrofitManager = new RetrofitManager();
            }
        }
        return mRetrofitManager;
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public void resetting() {
        mRetrofitManager = null;
        mRetrofit = null;
    }
}
