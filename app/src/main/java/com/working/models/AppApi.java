package com.working.models;

import com.working.domain.ApprovalRecord;
import com.working.domain.InStockList;
import com.working.domain.IndexNotice;
import com.working.domain.InspectionDetail;
import com.working.domain.InspectionList;
import com.working.domain.MaterialList;
import com.working.domain.MaterialListData;
import com.working.domain.Order;
import com.working.domain.OrderDetail;
import com.working.domain.PostedFileBean;
import com.working.domain.Purchase;
import com.working.domain.PurchaseDetail;
import com.working.domain.RepBalData;
import com.working.domain.RepBalInfoData;
import com.working.domain.OutStockList;
import com.working.domain.OutStockDetail;
import com.working.domain.InStockDetail;
import com.working.domain.LoginInfo;
import com.working.domain.ClientResponse;
import com.working.domain.RepairContent;
import com.working.domain.RepairMethod;
import com.working.domain.StatBean;
import com.working.domain.UserInfo;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface AppApi {

    //获取认证令牌
    @FormUrlEncoded
    @POST("blade-auth/oauth/token")
    Call<LoginInfo> getUserAuth(@Field("username") String username, @Field("password")String password, @Field("tenantId")String tenantId);

    //刷新认证令牌
    @FormUrlEncoded
    @Headers("'Tenant-Id': tenantId")
    @POST("blade-auth/oauth/token")
    Call<LoginInfo> refreshUserAuth(@Field("refresh_token")String token, @Field("tenantId")String tenantId,
                                    @Field("grant_type")String grant_type, @Field("scope")String scope);

    //查询用户信息
    @GET("blade-user/info?")
    Call<UserInfo> getUserInfo( @Query("id")String id);

    //文件上传
    @Multipart
    @POST("blade-resource/oss/endpoint/put-file")
    Call<PostedFileBean> uploadFile(@Part MultipartBody.Part partList);

    //获取巡检记录的列表
    @GET("blade-road/inspectionrecord/list")
    Call<InspectionList> getInspectionList(@Query("current") int page, @Query("size")int pageSize);

    //根据筛选时间去获取巡检记录的列表
    @GET("blade-road/inspectionrecord/list")
    Call<InspectionList> getInspectionListByTime(@Query("current") int page, @Query("size")int pageSize,
                                                 @Query("startTime")String startTime, @Query("endTime")String endTime);

    //获取巡检记录的详情
    @GET("blade-road/inspectionrecord/detail?")
    Call<InspectionDetail> getInspectionData(@Query("id") String id);

    //提交巡检记录
    @POST("blade-road/inspectionrecord/submit")
    Call<ClientResponse> uploadInspection( @Body RequestBody route);

    //获取维修方法
    @GET("/blade-road/damagerepair/selectRepairMethod?")
    Call<RepairMethod> getRepairMethod(@Query("damageType") int damageType);

    //获取维修内容
    @GET("/blade-road/damagerepair/selectRepairContent?")
    Call<RepairContent> getRepairContent(@Query("damageType") int damageType, @Query("repairMethodId") int methodId);

    //获取首页公告信息
    @GET("blade-desk/notice/list?")
    Call<IndexNotice> getIndexNotice(@Query("current") int page, @Query("size")int pageSize);

    //获取采购列表信息
    @GET("blade-road/purchase/list?")
    Call<Purchase> getPurchaseList(@Query("current") int page, @Query("size")int pageSize, @Query("status")int status, @Query("startTime")String startTime, @Query("endTime")String endTime);

    //获取采购单个信息的详情
    @GET("blade-road/purchase/detail?")
    Call<PurchaseDetail> getPurchaseDetail(@Query("id")String id);

    //获取采购清单详情中添加用品的列表
    @GET("blade-road/commonmaterial/select")
    Call<MaterialListData> getMaterialistData();

    //提交采购数据
    @POST("blade-road/purchase/submit")
    Call<ClientResponse> uploadPurchase(@Body RequestBody route);

    //审批采购数据数据
    @POST("blade-road/purchase/approval")
    Call<ClientResponse> approvalPurchase( @Body RequestBody route);

    //采购数据创建订单数据
    @POST("blade-road/purchase/commit")
    Call<ClientResponse> createOrder( @Body RequestBody route);

    //获取购买列表信息
    @GET("blade-road/order/list?")
    Call<Order> getOrderList(@Query("current") int page, @Query("size")int pageSize, @Query("status")int status, @Query("startTime")String startTime, @Query("endTime")String endTime);

    //获取购买单个信息的详情
    @GET("blade-road/order/detail?")
    Call<OrderDetail> getOrderDetail(@Query("id")String id);

    //购买数据的提交
    @POST("blade-road/order/submit")
    Call<ClientResponse> uploadOrder( @Body RequestBody route);

    //获取入库清单列表信息
    @GET("blade-road/instock/list")
    Call<InStockList> getRepertoryInList(@Query("current") int page, @Query("size")int pageSize, @Query("status")int status, @Query("startTime")String startTime, @Query("endTime")String endTime);

    //获取单个入库记录的详情
    @GET("blade-road/instock/detail?")
    Call<InStockDetail> getRepertoryInDetail(@Query("id")String id);

    //入库数据的提交
    @POST("blade-road/instock/submit")
    Call<ClientResponse> uploadRepertoryIn( @Body RequestBody route);

    //获取出库清单列表信息
    @GET("blade-road/outstock/list")
    Call<OutStockList> getRepOutList(@Query("current") int page, @Query("size")int pageSize, @Query("status")int status, @Query("startTime")String startTime, @Query("endTime")String endTime);

    //获取出库清单记录的详情
    @GET("blade-road/outstock/detail?")
    Call<OutStockDetail> getRepOutDetail(@Query("id")String id);

    //出库数据的提交
    @POST("blade-road/outstock/submit")
    Call<ClientResponse> uploadRepOut( @Body RequestBody route);

    //审批采购数据数据
    @POST("blade-road/outstock/approval")
    Call<ClientResponse> approvalOut(@Body RequestBody route);

    //获取库存清单列表信息
    @GET("blade-road/material/list")
    Call<RepBalData> getRepBalList(@Query("current") int page, @Query("size")int pageSize, @Query("startTime")String startTime, @Query("endTime")String endTime);

    //获取库存清单记录的详情
    @GET("blade-road/material/detail?")
    Call<RepBalInfoData> getRepBalDetail(@Query("id")String id);

    //库存数据的提交
    @POST("blade-road/material/submit")
    Call<ClientResponse> uploadRepBal(@Body RequestBody route);

    //获取库存管理清单列表
    @GET("blade-road/material/select")
    Call<MaterialList> getMaterialList();

    //获取审批清单列表信息
    @GET("blade-road/approvalrecord/list")
    Call<ApprovalRecord> getApprovalRecord(@Query("approvalUser")String id, @Query("current") int page, @Query("size")int pageSize, @Query("startTime")String startTime, @Query("endTime")String endTime);

    //修改密码
    @FormUrlEncoded
    @POST("blade-user/update-password")
    Call<ClientResponse> changePassword(@FieldMap Map<String, String> map);

    //获取库存管理清单列表
    @GET("blade-road/inspectionrecord/chart")
    Call<StatBean> getStatInfo(@Query("startTime")String startTime, @Query("endTime")String endTime);

}
