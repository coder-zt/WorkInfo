package com.working.models;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.working.domain.ApprovalBean;
import com.working.domain.ApprovalOutBean;
import com.working.domain.ApprovalRecord;
import com.working.domain.ChangePsdBean;
import com.working.domain.ClientResponse;
import com.working.domain.InStockList;
import com.working.domain.IndexNotice;
import com.working.domain.InspectionDetail;
import com.working.domain.InspectionFormData;
import com.working.domain.InspectionList;
import com.working.domain.LoginInfo;
import com.working.domain.MaterialList;
import com.working.domain.MaterialListData;
import com.working.domain.Order;
import com.working.domain.OrderDetail;
import com.working.domain.OutStockDetail;
import com.working.domain.PostedFileBean;
import com.working.domain.Purchase;
import com.working.domain.PurchaseDetail;
import com.working.domain.RepBalData;
import com.working.domain.RepBalInfoData;
import com.working.domain.InStockDetail;
import com.working.domain.OutStockList;
import com.working.domain.UserInfo;
import com.working.other.MessageEvent;
import com.working.utils.AppConfig;
import com.working.utils.FileUtils;
import com.working.utils.RetrofitManager;
import com.working.utils.UserDataMan;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AppModels {

    private static final String TAG = "AppModels";
    private static AppModels instance = null;

    private AppModels(){

    }

    public static synchronized AppModels getInstance(){
        if (instance == null) {
            instance  = new AppModels();
        }
        return instance;
    }


    private AppApi getAppApi() {
        Retrofit retrofit = RetrofitManager.getRetrofitManager().getRetrofit();
        return retrofit.create(AppApi.class);
    }

    private void requestFail(String msg, Handler.Callback callback) {
        Message message = Message.obtain();
        message.obj = msg;
        message.what = -1;
        callback.handleMessage(message);
    }

    private void requestSuccess(Object data, Handler.Callback callback) {
        Message message = Message.obtain();
        message.obj = data;
        message.what = 1;
        callback.handleMessage(message);
    }

    private void printErrorLog(Response<?> response) {
        try {
            if (response.errorBody()==null) {
                return;
            }
            if (response.errorBody().string().contains("请求未授权")) {
                String token = "";
                if (UserDataMan.getInstance().getLoginInfo() == null) {
                    Log.d(TAG, "printErrorLog: " + "初始化用户信息！");
                    UserDataMan.getInstance().init( new LoginInfo(),false);
                }
                token = UserDataMan.getInstance().getLoginInfo().getRefresh_token();
                refreshUserAuth(token, new OnUserAuthListener() {
                    @Override
                    public void onUserAuthLoaded(LoginInfo loginInfo) {
                        Log.d(TAG, "onUserAuthLoaded: " + "初始化用户信息！");
                        RetrofitManager.getRetrofitManager().resetting();
                        UserDataMan.getInstance().init(loginInfo, true);
                    }

                    @Override
                    public void onLoadedFail(String msg) {

                    }
                });
            }
            Log.d(TAG, "printErrorLog: " + response.errorBody().string());
        } catch (IOException e) {

        }
    }


    /***************
     * 用户数据相关 *
     ***************/

    /**
     * 用户登录授权
     *
     * @param userName
     * @param pwd
     * @param listener
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getUserAuth(String userName, String pwd, final OnUserAuthListener listener){
        AppApi appApi = getAppApi();
        String sign = FileUtils.md5Encryption(pwd);
        Log.d(TAG, "getUserAuth: sing:" + sign + "  userName" + userName);
        Call<LoginInfo> userAuth = appApi.getUserAuth(userName,  sign, AppConfig.TENANID);
        userAuth.enqueue(new Callback<LoginInfo>() {
            @Override
            public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                printErrorLog(response);
                RetrofitManager.getRetrofitManager().resetting();
                listener.onUserAuthLoaded(response.body());
            }

            @Override
            public void onFailure(Call<LoginInfo> call, Throwable t) {
                listener.onLoadedFail(t.getMessage());
            }
        });
    }

    /**
     * 刷新登录授权
     *
     * @param refreshToken
     * @param listener
     */
    public void refreshUserAuth(String refreshToken, final OnUserAuthListener listener){
        AppApi appApi = getAppApi();
        Call<LoginInfo> userAuth = appApi.refreshUserAuth(refreshToken, AppConfig.TENANID, "refresh_token", "all");
        userAuth.enqueue(new Callback<LoginInfo>() {
            @Override
            public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                printErrorLog(response);
                if (listener != null) {
                    listener.onUserAuthLoaded(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginInfo> call, Throwable t) {
                if (listener != null) {
                    listener.onLoadedFail(t.getMessage());
                }
            }
        });
    }

    public interface OnUserAuthListener{
        void onUserAuthLoaded(LoginInfo loginInfo);

        void onLoadedFail(String msg);
    }

    public void getUserInfo(String id, final OnUserInfoLoadListener listener){
        AppApi appApi = getAppApi();
        Call<UserInfo> userAuth = appApi.getUserInfo(id);
        userAuth.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                printErrorLog(response);
                if (listener != null) {
                    listener.onUserInfoLoaded(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                if (listener != null) {
                    listener.onLoadedFail(t.getMessage());
                }
            }
        });
    }

    /**
     * 库存清单列表
     * @param data
     * @param callback
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void changePassword(ChangePsdBean data, final Handler.Callback callback){
        AppApi appApi = getAppApi();
//        StringBuilder sbJson = new StringBuilder("{\"oldPassword\":\"")
//                    .append(FileUtils.md5Encryption(data.getOldPassword()))
//                    .append("\",\"newPassword\":\"")
//                    .append(FileUtils.md5Encryption(data.getNewPassword()))
//                    .append("\",\"newPassword1\":\"")
//                    .append(FileUtils.md5Encryption(data.getNewPassword1()))
//                    .append("\"}");
//        Log.d(TAG, "updateInspection: " + sbJson);
//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), sbJson.toString());
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("oldPassword", FileUtils.md5Encryption(data.getOldPassword()));
        dataMap.put("newPassword", FileUtils.md5Encryption(data.getNewPassword()));
        dataMap.put("newPassword1", FileUtils.md5Encryption(data.getNewPassword1()));
        Call<ClientResponse> inspectionListCall = appApi.changePassword(dataMap);
        inspectionListCall.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                handleClientResponse(response, callback);
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                requestFail("网络错误！", callback);
            }
        });
    }

    public interface OnUserInfoLoadListener{
        void onUserInfoLoaded(UserInfo userInfo);

        void onLoadedFail(String msg);
    }


    /***************
     * 文件上传相关 *
     ***************/

    /**
     * 上传文件接口
     */
    public  void uploadFile(String path, final Handler.Callback callback){
        AppApi appApi = getAppApi();
        File file = new File(path);
        RequestBody imageBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), imageBody);
        Call<PostedFileBean> postedFileBeanCall = appApi.uploadFile(part);
        postedFileBeanCall.enqueue(new Callback<PostedFileBean>() {
            @Override
            public void onResponse(Call<PostedFileBean> call, Response<PostedFileBean> response) {
                if (response.code()!=200) {
                    printErrorLog(response);
                    requestFail(response.body().getMsg(), callback);
                    return;
                }
                requestSuccess(response.body(), callback);
                EventBus.getDefault().post(new MessageEvent(MessageEvent.upload_file_success));
            }

            @Override
            public void onFailure(Call<PostedFileBean> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    /***************
     * 巡检记录相关 *
     ***************/

    /**
     * 巡检记录的数据请求
     */
    public void getInspectionList(int page, int pageSize, final Handler.Callback callback){
        AppApi appApi = getAppApi();
        Call<InspectionList> userAuth = appApi.getInspectionList(page, pageSize);
        userAuth.enqueue(new Callback<InspectionList>() {
            @Override
            public void onResponse(Call<InspectionList> call, Response<InspectionList> response) {
                if (response.code()!=200) {
                    printErrorLog(response);
                    requestFail("巡检记录请求失败！", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<InspectionList> call, Throwable t) {
                String msg = "巡检记录请求失败！";
                requestFail(msg, callback);
            }
        });
    }


    /**
     * 巡检记录的数据请求---加上时间筛选
     */
    public void getInspectionList(int page, int pageSize, String startTime, String endTime,
                                  final Handler.Callback callback){
        AppApi appApi = getAppApi();
        Call<InspectionList> userAuth = appApi.getInspectionListByTime(page, pageSize,startTime,endTime);
        userAuth.enqueue(new Callback<InspectionList>() {
            @Override
            public void onResponse(Call<InspectionList> call, Response<InspectionList> response) {
                if (response.code()!=200) {
                    printErrorLog(response);
                    requestFail("巡检记录请求失败！", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<InspectionList> call, Throwable t) {
                String msg = "巡检记录请求失败！";
                requestFail(msg, callback);
            }
        });
    }

    /**
     * 获取一条巡检信息
     *
     * @param id
     * @param callback
     */
    public void getInspectionInfo(String id, final Handler.Callback callback){
        AppApi appApi = getAppApi();
        Call<InspectionDetail> userAuth = appApi.getInspectionData(id);
        userAuth.enqueue(new Callback<InspectionDetail>() {
            @Override
            public void onResponse(Call<InspectionDetail> call, Response<InspectionDetail> response) {
                if (response.code()!=200) {
                    printErrorLog(response);
                    requestFail("巡检记录请求失败！", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<InspectionDetail> call, Throwable t) {
                String msg = "巡检记录请求失败！";
                requestFail(msg, callback);
            }
        });
    }

    /**
     * 上传/更新巡检记录
     * @param information
     * @param callback
     */
    public void uploadInspection(InspectionFormData information, final Handler.Callback callback) {
        AppApi appApi = getAppApi();
        final String dataJson = new Gson().toJson(information);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), dataJson);
        Log.d(TAG, "updateInspection: " + dataJson);
        Call<ClientResponse> inspectionListCall = appApi.uploadInspection(body);
        inspectionListCall.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                handleClientResponse(response, callback);
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                requestFail("网络错误！", callback);
            }
        });
    }


    /***************
     * 首页相关信息 *
     ***************/

    /**
     * 获取首页的公告信息
     * @param page
     * @param pageSize
     */
    public void getIndexNotice(int page, int pageSize, final Handler.Callback callback){
        AppApi api = getAppApi();
        Call<IndexNotice> indexNotice = api.getIndexNotice(page, pageSize);
        indexNotice.enqueue(new Callback<IndexNotice>() {
            @Override
            public void onResponse(Call<IndexNotice> call, Response<IndexNotice> response) {
                if (response.code()!=200) {
                    printErrorLog(response);
                    requestFail("获取公告信息失败！", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<IndexNotice> call, Throwable t) {
                requestFail("获取公告信息失败！", callback);
            }
        });
    }


    /***************
     * 采购清单相关 *
     ***************/

    /**
     * 获取采购列表信息
     * @param page
     * @param pageSize
     * @param status
     * @param callback
     */
    public void getPurchaseList(int page, int pageSize, String startTime, String endTime,
                                final int status, final Handler.Callback callback){
        AppApi api = getAppApi();
        Call<Purchase> call = api.getPurchaseList(page, pageSize, status, startTime, endTime);
        call.enqueue(new Callback<Purchase>() {

            @Override
            public void onResponse(Call<Purchase> call, Response<Purchase> response) {
                if (response.code()!=200) {
                    printErrorLog(response);
                    requestFail("获取" + (status == 1 ? "未上报":"上报") + "采购清单失败！", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<Purchase> call, Throwable t) {
                requestFail("获取" + (status == 1 ? "未上报":"上报") + "采购清单失败！", callback);
            }
        });
    }

    /**
     * 获取采购清单的详情
     * @param id
     * @param callback
     */
    public void getPurchaseDetail(String id, final Handler.Callback callback){
        AppApi api = getAppApi();
        Call<PurchaseDetail> purchaseDetail = api.getPurchaseDetail(id);
        purchaseDetail.enqueue(new Callback<PurchaseDetail>() {
            @Override
            public void onResponse(Call<PurchaseDetail> call, Response<PurchaseDetail> response) {
                if (response.code()!=200) {
                    printErrorLog(response);
                    requestFail("获取采购清单详情信息失败！", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<PurchaseDetail> call, Throwable t) {
                requestFail("获取采购清单详情信息失败！", callback);
            }
        });
    }


    /**
     * 获取新增用品的列表
     * @param callback
     */
    public void getPullDownMaterialList(final Handler.Callback callback){
        AppApi appApi = getAppApi();
        Call<MaterialListData> materialistData = appApi.getMaterialistData();
        materialistData.enqueue(new Callback<MaterialListData>() {
            @Override
            public void onResponse(Call<MaterialListData> call, Response<MaterialListData> response) {
                if (response.code()!=200) {
                    printErrorLog(response);
                    requestFail("获取新增用品的列表失败！", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<MaterialListData> call, Throwable t) {
                requestFail("获取新增用品的列表失败！", callback);
            }
        });
    }

    /**
     * 提交采购清单(数据响应)
     * @param callback
     */
    public void uploadPurchase(PurchaseDetail.DataBean commitBean, final Handler.Callback callback) {
        AppApi appApi = getAppApi();
        final String dataJson = new Gson().toJson(commitBean);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), dataJson);
        Log.d(TAG, "updateInspection: " + dataJson);
        Call<ClientResponse> inspectionListCall = appApi.uploadPurchase(body);
        inspectionListCall.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                handleClientResponse(response, callback);
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                requestFail("网络错误！", callback);
            }
        });
    }

    /**
     * 审批采购清单(响应数据)
     * @param callback
     */
    public void approvalPurchase(ApprovalBean approvalBean, final Handler.Callback callback) {
        AppApi appApi = getAppApi();
        String dataJson = new Gson().toJson(approvalBean);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), dataJson);
        Call<ClientResponse> inspectionListCall = appApi.approvalPurchase(body);
        inspectionListCall.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                handleClientResponse(response, callback);
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                requestFail("网络错误！", callback);
            }
        });
    }

    /**
     * 创建购买的订单(响应数据)
     * @param callback
     */
    public void createOrder(ApprovalBean approvalBean, final Handler.Callback callback) {
        AppApi appApi = getAppApi();
        final String dataJson = new Gson().toJson(approvalBean);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), dataJson);
        Log.d(TAG, "updateInspection: " + dataJson);
        Call<ClientResponse> inspectionListCall = appApi.createOrder(body);
        inspectionListCall.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                handleClientResponse(response, callback);
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                requestFail("网络错误！", callback);
            }
        });
    }

    /***************
     * 购买清单相关 *
     ***************/

    /**
     * 获取购买列表信息
     * @param page
     * @param pageSize
     * @param status
     * @param callback
     */
    public void getOrderList(int page, int pageSize, String startTime, String endTime,
                             final int status, final Handler.Callback callback){
        AppApi api = getAppApi();
        Call<Order> call = api.getOrderList(page, pageSize, status, startTime, endTime);
        call.enqueue(new Callback<Order>() {

            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.code()!=200) {
                    printErrorLog(response);
                    requestFail("获取" + (status == 1 ? "未上报":"上报") + "购买清单失败！", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                requestFail("获取" + (status == 1 ? "未上报":"上报") + "购买清单失败！", callback);
            }
        });
    }

    /**
     * 获取购买清单的详情
     * @param id
     * @param callback
     */
    public void getOrderDetail(String id, final Handler.Callback callback){
        AppApi api = getAppApi();
        Call<OrderDetail> purchaseDetail = api.getOrderDetail(id);
        purchaseDetail.enqueue(new Callback<OrderDetail>() {
            @Override
            public void onResponse(Call<OrderDetail> call, Response<OrderDetail> response) {
                if (response.code()!=200) {
                    printErrorLog(response);
                    requestFail("获取购买清单详情信息失败！", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<OrderDetail> call, Throwable t) {
                requestFail("获取购买清单详情信息失败！", callback);
            }
        });
    }

    /**
     * 提交购买订单
     * @param information
     * @param callback
     */
    public void uploadOrder(OrderDetail.DataBean information, final Handler.Callback callback) {
        AppApi appApi = getAppApi();
        final String dataJson = new Gson().toJson(information);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), dataJson);
        Log.d(TAG, "uploadOrder: " + dataJson);
        Call<ClientResponse> inspectionListCall = appApi.uploadOrder(body);
        inspectionListCall.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
               handleClientResponse(response, callback);
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                requestFail("网络错误", callback);
            }
        });
    }

    /***************
     * 入库清单相关 *
     ***************/

    /**
     * 入库清单列表
     * @param page
     * @param pageSize
     * @param status
     * @param callback
     */
    public void getRepertoryInList(int page, int pageSize, String startTime, String endTime,
                                   final int status, final Handler.Callback callback){
        AppApi api = getAppApi();
        Call<InStockList> call = api.getRepertoryInList(page, pageSize, status, startTime, endTime);
        call.enqueue(new Callback<InStockList>() {

            @Override
            public void onResponse(Call<InStockList> call, Response<InStockList> response) {
                if (response.code()!=200) {
                    printErrorLog(response);
                    requestFail("获取" + (status == 1 ? "未上报":"上报") + "购买清单失败！", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<InStockList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                requestFail("获取" + (status == 1 ? "未上报":"上报") + "购买清单失败！", callback);
            }
        });
    }

    /**
     * 获取入库清单的详情
     * @param id
     * @param callback
     */
    public void getRepertoryInDetail(String id, final Handler.Callback callback){
        AppApi api = getAppApi();
        Call<InStockDetail> purchaseDetail = api.getRepertoryInDetail(id);
        purchaseDetail.enqueue(new Callback<InStockDetail>() {
            @Override
            public void onResponse(Call<InStockDetail> call, Response<InStockDetail> response) {
                if (response.code()!=200) {
                    printErrorLog(response);
                    requestFail("获取入库清单详情信息失败！", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<InStockDetail> call, Throwable t) {
                requestFail("获取入库清单详情信息失败！", callback);
            }
        });
    }

    /**
     * 提交入库清单（响应数据）
     * @param information
     * @param callback
     */
    public void uploadRepertoryIn(InStockDetail.DataBean information, final Handler.Callback callback) {
        AppApi appApi = getAppApi();
        final String dataJson = new Gson().toJson(information);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), dataJson);
        Log.d(TAG, "updateInspection: " + dataJson);
        Call<ClientResponse> inspectionListCall = appApi.uploadRepertoryIn(body);
        inspectionListCall.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                handleClientResponse(response, callback);
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                requestFail("网络错误！", callback);
            }
        });
    }

    /***************
     * 出库清单相关 *
     ***************/

    /**
     * 出库清单列表
     * @param page
     * @param pageSize
     * @param status
     * @param callback
     */
    public void getRepOutList(int page, int pageSize, String startTime, String endTime,
                              final int status, final Handler.Callback callback){
        AppApi api = getAppApi();
        Call<OutStockList> call = api.getRepOutList(page, pageSize, status, startTime, endTime);
        call.enqueue(new Callback<OutStockList>() {

            @Override
            public void onResponse(Call<OutStockList> call, Response<OutStockList> response) {
                if (response.code()!=200) {
                    printErrorLog(response);
                    requestFail("获取出库清单列表信息失败！", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<OutStockList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                requestFail("获取" + (status == 1 ? "未上报":"上报") + "购买清单失败！", callback);
            }
        });
    }

    /**
     * 获取出库清单的详情
     * @param id
     * @param callback
     */
    public void getRepOutInfo(String id, final Handler.Callback callback){
        AppApi api = getAppApi();
        Call<OutStockDetail> purchaseDetail = api.getRepOutDetail(id);
        purchaseDetail.enqueue(new Callback<OutStockDetail>() {
            @Override
            public void onResponse(Call<OutStockDetail> call, Response<OutStockDetail> response) {
                if (response.code()!=200) {
                    printErrorLog(response);
                    requestFail("获取入库清单详情信息失败！", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<OutStockDetail> call, Throwable t) {
                requestFail("获取入库清单详情信息失败！", callback);
            }
        });
    }

    /**
     * 提交出库清单
     * @param information
     * @param callback
     */
    public void uploadRepOut(OutStockDetail.DataBean information, final Handler.Callback callback) {
        AppApi appApi = getAppApi();
        final String dataJson = new Gson().toJson(information);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), dataJson);
        Log.d(TAG, "updateInspection: " + dataJson);
        Call<ClientResponse> inspectionListCall = appApi.uploadRepOut(body);
        inspectionListCall.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                handleClientResponse(response, callback);
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                requestFail("上传失败, data:" + dataJson, callback);
            }
        });
    }

    /**
     * 提交审批清单(响应数据)
     * @param callback
     */
    public void approvalOut(ApprovalOutBean approvalBean, final Handler.Callback callback) {
        AppApi appApi = getAppApi();
        final String dataJson = new Gson().toJson(approvalBean);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), dataJson);
        Log.d(TAG, "updateInspection: " + dataJson);
        Call<ClientResponse> inspectionListCall = appApi.approvalOut(body);
        inspectionListCall.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                handleClientResponse(response, callback);
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                requestFail("网络错误！" , callback);
            }
        });
    }

    /**
     * 处理提交数据的响应数据
     *
     * @param response
     * @param callback
     */
    private void handleClientResponse(Response<ClientResponse> response, Handler.Callback callback) {
        if (response.code()==200) {
            ClientResponse body = response.body();
            if(body != null){
                if (body.getCode() == 200) {
                    requestSuccess(null, callback);
                }else{
                    requestFail(body.getMsg(), callback);
                }
                return;
            }
        }
        try {
            String errorMsg = response.errorBody().string();
            JSONObject jsonObject = new JSONObject(errorMsg);
            requestFail((String)jsonObject.get("msg"), callback);
            return;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        requestFail("未知错误！", callback);
    }

    /***************
     * 库存清单相关 *
     ***************/

    /**
     * 库存清单列表
     * @param page
     * @param pageSize
     * @param callback
     */
    public void getRepBalList(int page, int pageSize, String startTime, String endTime, final Handler.Callback callback){
        AppApi api = getAppApi();
        Call<RepBalData> call = api.getRepBalList(page, pageSize, startTime, endTime);;
        call.enqueue(new Callback<RepBalData>() {

            @Override
            public void onResponse(Call<RepBalData> call, Response<RepBalData> response) {
                printErrorLog(response);
                if (response.code() != 200) {
                    requestFail("获取结库存清单列表失败！", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<RepBalData> call, Throwable t) {
                requestFail("获取库存库存清单失败！", callback);
            }
        });
    }

    /**
     * 获取库存清单的详情
     * @param id
     * @param callback
     */
    public void getRepBalInfo(String id, final Handler.Callback callback){
        AppApi api = getAppApi();
        Call<RepBalInfoData> purchaseDetail = api.getRepBalDetail(id);
        purchaseDetail.enqueue(new Callback<RepBalInfoData>() {
            @Override
            public void onResponse(Call<RepBalInfoData> call, Response<RepBalInfoData> response) {
                printErrorLog(response);
                if (response.code() != 200) {
                    requestFail("获取库存清单的详情", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<RepBalInfoData> call, Throwable t) {
                requestFail("获取入库清单详情信息失败！", callback);
            }
        });
    }

    /**
     * 提交库存清单(数据响应)
     * @param information
     * @param callback
     */
    public void uploadRepBal(RepBalData.DataBean.RecordsBean information, final Handler.Callback callback) {
        AppApi appApi = getAppApi();
        final String dataJson = new Gson().toJson(information);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), dataJson);
        Log.d(TAG, "uploadRepBal: " + dataJson);
        Call<ClientResponse> inspectionListCall = appApi.uploadRepBal(body);
        inspectionListCall.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                handleClientResponse(response, callback);
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                requestFail("网络错误", callback);
            }
        });
    }

    /**
     * 获取物料库存接口
     * @param callback
     */
    public void getMaterialList(final Handler.Callback callback){
        AppApi api = getAppApi();
        Call<MaterialList> materialList = api.getMaterialList();
        materialList.enqueue(new Callback<MaterialList>() {
            @Override
            public void onResponse(Call<MaterialList> call, Response<MaterialList> response) {
                printErrorLog(response);
                if (response.code()!=200) {
                    requestFail("获取物料列表失败!", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<MaterialList> call, Throwable t){
                requestFail("获取物料列表失败!", callback);
            }
        });
    }

    /***************
     * 审批记录相关 *
     ***************/

    /**
     * 库存清单列表
     * @param page
     * @param pageSize
     * @param callback
     */
    public void getApprovalRecord(int page, int pageSize, String startTime, String endTime, final Handler.Callback callback){
        AppApi api = getAppApi();
        String user_id = UserDataMan.getInstance().getLoginInfo().getUser_id();
        Call<ApprovalRecord> call = api.getApprovalRecord(user_id, page, pageSize, startTime, endTime);;
        call.enqueue(new Callback<ApprovalRecord>() {

            @Override
            public void onResponse(Call<ApprovalRecord> call, Response<ApprovalRecord> response) {
                printErrorLog(response);
                if (response.code() != 200) {
                    requestFail("获取审批记录列表失败！", callback);
                    return;
                }
                requestSuccess(response.body(), callback);
            }

            @Override
            public void onFailure(Call<ApprovalRecord> call, Throwable t) {
                requestFail("获取审批记录列表失败！", callback);
            }
        });
    }
}
