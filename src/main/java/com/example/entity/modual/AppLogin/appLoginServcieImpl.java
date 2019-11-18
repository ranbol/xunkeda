package com.example.entity.modual.AppLogin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.dao.AppLoginMapper;
import com.example.entity.po.AppCodeEfficacy;
import com.example.entity.po.AppLogin;
import com.example.entity.utils.codeUtils;
import com.example.entity.utils.dateUtils;
import com.example.entity.utils.emptyUtils;
import com.example.entity.utils.httpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class appLoginServcieImpl implements appLoginService {
    @Autowired
    private AppLoginMapper appLoginMapper;

    private static String MESSAGE_URL="";

    private static ArrayList<AppCodeEfficacy> PHONE_CODES =new ArrayList<>();

    /**
     * (APP) 验证登录
     * @param phoneNum     电话号码
     * @param phoneCode    验证码
     * @return
     */
    @Override
    public Map<String, Object> userLogin(String phoneNum, String phoneCode) {
       Map<String,Object> map=new HashMap<>();
       if(phoneNum.isEmpty()||phoneNum.length()!=11){ map.put("code","1");map.put("msg","请输入正确的号码");return map;
       }
       if(phoneCode.isEmpty()){map.put("code","1");map.put("msg","请输入验证码");return  map;}

        for (AppCodeEfficacy code : PHONE_CODES) {

        }

        return null;
    }

    /**
     *(APP) 用户登录账号注册
     * @param appLogin
     * @param phoneCode
     * @return
     */
    @Override
    public Map<String, Object> userRegister(AppLogin appLogin, String phoneCode) {
        Map<String,Object> map=new HashMap<>();

        if(emptyUtils.isEmpty(appLogin)||phoneCode.isEmpty()){
            map.put("code",1);
            map.put("msg","必输项不能为空");
            return map;
        }
        /*验证电话位数*/
        if (appLogin.getLogin_tell().length()!=11){
            map.put("code",1);
            map.put("msg","请输入正确的电话号码！");
            return map;
        }
        /*验证密码格式*/
        if (!codeUtils.isLetterDigit(appLogin.getLogin_password())){
            map.put("code",1);
            map.put("msg","密码格式错误！");
            return map;
        }
        for ( AppCodeEfficacy stringMap : PHONE_CODES) {
            Date outTime=dateUtils.getDatePlusMinute(stringMap.getCodeTime(),60);
            Date date=new Date();
            System.out.println("outTime"+outTime.getTime());
            System.out.println("nowTime"+date.getTime());
            if (outTime.getTime()<date.getTime()){
                map.put("code",1);
                map.put("msg","验证码超时，请重新获取！");
                return map;
            }else {
                if(stringMap.getTellNumber().equals(appLogin.getLogin_tell())&&stringMap.getTellCode().equals(phoneCode)){
                    Integer num=appLoginMapper.insert(appLogin);
                    if (num==1){
                        //移出静态数组中的项
                        PHONE_CODES.remove(stringMap);
                        map.put("code",0);
                        map.put("msg","注册成功！");
                        return map;
                    }
                    else {
                        map.put("code",1);
                        map.put("msg","注册失败");
                        return map;
                    }
                }else {
                    map.put("code",1);
                    map.put("msg","验证码错误");
                    return map;
                }
            }
        }
        map.put("code",1);
        map.put("msg","系统错误");
        return map;
    }

    /**
     * (APP) 发送短信验证码
     * @param phoneNum 电话号码
     * @return boolean
     */
    @Override
    public Boolean sendMessage(String phoneNum) {
        if(phoneNum.isEmpty()||phoneNum.length()!=11){ return false; }
        String codeValue= codeUtils.getPhoneCodeValue(6);
        String param="phoneNUM="+phoneNum+"&codeValue="+codeValue;
        System.out.println("codeValue:"+codeValue);
/*
       // 三方短信发送
        String sendPost = httpUtils.sendPost(MESSAGE_URL, param, false);
        Object object=JSON.parseObject(sendPost);

        //验证设置
        ((JSONObject) object).put("code",0);

        if (((JSONObject) object).get("code")=="0"){
             AppCodeEfficacy codeEfficacy=new AppCodeEfficacy();
             codeEfficacy.setCodeTime(new Date());
             codeEfficacy.setTellNumber(phoneNum);
             codeEfficacy.setTellCode(codeValue);
             PHONE_CODES.add(codeEfficacy);
            return true;
        }
        return false;*/
        AppCodeEfficacy codeEfficacy=new AppCodeEfficacy();
        codeEfficacy.setCodeTime(new Date());
        codeEfficacy.setTellNumber(phoneNum);
        codeEfficacy.setTellCode(codeValue);
        PHONE_CODES.add(codeEfficacy);
        return true;
    }
}
