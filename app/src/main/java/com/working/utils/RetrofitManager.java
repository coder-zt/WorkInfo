package com.working.utils;

import android.util.Log;

import com.working.domain.LoginInfo;

import java.io.IOException;

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
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Log.e("HttpLoggingInterceptor", message));
        //显示日志
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
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
