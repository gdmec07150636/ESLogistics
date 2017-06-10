package com.example.rynfar.eslogistics;

/**
 * 常量类
 * Created by rynfar on 2017/6/6.
 */

public class Const {
    /*在线支付*/
    protected final static int PAY_ONLINE = 1;
    /*离线支付*/
    protected final static int PAY_OFFLINE = 2;
    /*URL*/
    protected final static String BASE_URL = "http://10.0.2.2/ESManagement/index.php/Home/";
    /*注册*/
    protected final static String REG_SUFFIX = "Reg/reg";
    /*登录*/
    protected final static String LOGIN_SUFFIX = "Login/checkLogin";
    /*下订单*/
    protected final static String ORDER_SUFFIX = "";
    /*获取所有订单*/
    protected final static String GET_ORDER = "Orders/listOrders";
    /*附近网点*/
    protected final static String NEAR_POINT = "Index/wd_near";
    /*主页*/
    protected final static String HOME_ORDER = "Index/index";

}
