package com.example.entity.modual.AppLogin;

import com.example.entity.po.AppLogin;

import java.util.Map;

public interface appLoginService {
    /*用户登录*/
    Map<String,Object> userLogin(String phoneNum,String phoneCode);

    /*用户注册*/
    Map<String,Object> userRegister(AppLogin appLogin, String phoneCode);
    /*发送验证码*/
    Boolean sendMessage(String phoneNumber);

}
