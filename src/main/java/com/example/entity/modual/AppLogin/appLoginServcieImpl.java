package com.example.entity.modual.AppLogin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.dao.AppLoginMapper;
import com.example.entity.po.AppLogin;
import com.example.entity.utils.codeUtils;
import com.example.entity.utils.emptyUtils;
import com.example.entity.utils.httpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class appLoginServcieImpl implements appLoginService {
    @Autowired
    private AppLoginMapper appLoginMapper;

    private static String MESSAGE_URL="";

    private static ArrayList<Map<String,String>> PHONE_CODES;

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

        for (Map<String, String> code : PHONE_CODES) {
           if(code.get("phoneNum").equals(phoneNum)&&code.get("phoneCode").equals(phoneCode)){

           }
        }

        return null;
    }

    /**
     *
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
        if (phoneCode.length()!=11){
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
        for (Map<String, String> stringMap : PHONE_CODES) {
            if(
                    stringMap.get("phoneNum").equals(appLogin.getLogin_tell())&&
                    stringMap.get("phoneCode").equals(phoneCode)
            ){
                Integer num=appLoginMapper.insert(appLogin);
                if (num==1){
                    //移出静态数组中的项
                    PHONE_CODES.remove(stringMap);
                    map.put("code",0);
                    map.put("msg","注册成功！");
                    return map;
                }
            }
        }



       /* if (appLogin.getLogin_name().isEmpty()){

        }
        if (appLogin.getLogin_password().isEmpty()){

        }
        if (appLogin.getLogin_tell().isEmpty()){

        }
        if (phoneCode.isEmpty()){

        }*/

        return null;
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
        String sendPost = httpUtils.sendPost(MESSAGE_URL, param, false);
        Object object=JSON.parseObject(sendPost);
        if (((JSONObject) object).get("code")=="0"){
            Map<String,String> map=new HashMap<>();
            map.put("phoneNum",phoneNum);
            map.put("codeValue",codeValue);
            PHONE_CODES.add(map);
            return true;
        }
        return false;
    }
}
