package com.working.utils;

public class AppConfig {
    //一、角色身份id
    //1. 大队（一级审核）
    public static final String DADUI_ID = "1123598816738675205";
    //2. 支队（二级审核）
    public static final String ZHIDUI_ID = "1342844970570158082";
    //3. 材料管理员（库存管理）
    public static final String MATERIAL_ID = "1123598816738675203";
    //服务器地址
    public static final String BASE_URL = "http://106.13.8.187/";
    //sharedPreferences名称
    public static final String SP_KEY = "SP_KEY";
    //用户信息的key
    public static final String SP_USER_KEY = "SP_USER_KEY";
    //授权相关
    public static final String TENANID = "000000";
    public static final String AUTHORIZATION = "Basic c3dvcmQ6c3dvcmRfc2VjcmV0";
    //清单的编号
    //巡检记录
    public static final int ACTIVITY_INSPECTION = 1;
    //采购清单
    public static final int ACTIVITY_PURCHASE = 2;
    //购买记录
    public static final int ACTIVITY_ORDER = 3;
    //入库记录
    public static final int ACTIVITY_STOCK_IN = 4;
    //出库记录
    public static final int ACTIVITY_STOCK_OUT = 5;
    //库存清单
    public static final int ACTIVITY_MATERIAL = 6;
    //审批清单
    public static final int ACTIVITY_APPROVAL = 7;

    public static final int TYPE_MATERIAL = 0;
    public static final int TYPE_PICTURE = 1;
    public static final int TYPE_APPROVAL = 2;


}
