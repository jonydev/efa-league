package com.apsoft.scfb.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2016/8/10.
 */
public class NetSCFBLogin {
    public static final String s_loginurl = NetSetting.s_accountbaseddress + "/account/login/";
    public static final String s_logouturl = NetSetting.s_accountbaseddress + "/account/logout/";
    public static final String s_createuserurl = NetSetting.s_accountbaseddress + "/account/create_user/";
    public static final String s_updateinforurl = NetSetting.s_accountbaseddress + "/account/update_info/";
    public static final String s_getuserinforurl = NetSetting.s_accountbaseddress + "/account/get_user_info/";
    public static final String s_setuserphotourl = NetSetting.s_accountbaseddress + "/account/set_photo/";
    public static final String s_sendadviceurl = NetSetting.s_accountbaseddress + "/account/send_advise/";

    public static void requestLogin(String username, String password, BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        okHttpHelper.post(s_loginurl, params, callback);
    }

    public static void requestLogout(BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        okHttpHelper.get(s_logouturl, callback);
    }

    public static void requestCreateUser(String username, String password, String sSms, BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        okHttpHelper.post(s_createuserurl, params, callback);
    }

    public static void requestGetUserInfo(BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        okHttpHelper.get(s_getuserinforurl, callback);
    }

    public static void setUserPhoto(String photo, BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        Map<String, String> params = new HashMap<>();
        params.put("photo", photo);
        okHttpHelper.post(s_setuserphotourl, params, callback);
    }

    public static void requestUpdateUserInfo(String name, String idcard, String address, String phone, Integer sex, Integer height, Integer weight, Integer age, String position, String photo, String policyNo, BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        Map<String, String> params = new HashMap<>();
        if(name!=null && name.length()>0)
            params.put("name", name);
        if(idcard!=null )
            params.put("idcard", idcard);
        if(address!=null)
            params.put("address", address);
        if(phone!=null)
            params.put("phone", phone);
        if(sex!=null)
            params.put("sex", String.valueOf(sex));
        if(height!=null)
            params.put("height", String.valueOf(height));
        if(weight!=null)
            params.put("weight", String.valueOf(weight));
        if(age!=null)
            params.put("age", String.valueOf(age));
        if(position!=null && position.length()>0)
            params.put("position", position);
        if(photo != null){
            params.put("photo", photo);
        }
        if(policyNo!=null){
            params.put("policyno", policyNo);
        }
        okHttpHelper.post(s_updateinforurl, params, callback);
    }

    public static void requestSendAdvice(String content, String image, BaseCallback callback){
        OkHttpHelper okHttpHelper = OkHttpHelper.getInstance();
        Map<String, String> params = new HashMap<>();
        if(content!=null)
            params.put("content", content);
        if(image!=null)
            params.put("image", image);
        okHttpHelper.post(s_sendadviceurl, params, callback);
    }

}
