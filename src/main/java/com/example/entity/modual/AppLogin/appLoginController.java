package com.example.entity.modual.AppLogin;

import com.alibaba.fastjson.JSON;
import com.example.entity.dao.AppLoginMapper;
import com.example.entity.po.AppLogin;
import com.example.entity.utils.emptyUtils;
import org.omg.CORBA.MARSHAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class appLoginController {
  @Autowired
  private appLoginService appLoginService;
  @Autowired
  private AppLoginMapper appLoginMapper;

    /**
     * (APP端)  用户登录账号注册
     * @param appLogin   登录信息
     * @param phoneCode  短信验证码
     * @return
     */
  @RequestMapping("/register")
  public String userLoginAndRegister(AppLogin appLogin,String phoneCode){
     Map map=  appLoginService.userRegister(appLogin,phoneCode);
     return JSON.toJSONString(map);
  }

    @RequestMapping("/sendMessageForCodeValue")
    public String sendMessageForCodeValue(String phoneNum){
      Map<String,String> map=new HashMap<>();
      if (phoneNum.isEmpty()||!(phoneNum.length()==11)){
          map.put("code","1");
          map.put("msg","请输入正确的电话号码！");
          return JSON.toJSONString(map);
      }
        Boolean aBoolean=  appLoginService.sendMessage(phoneNum);
      if(aBoolean){
          map.put("code","0");
          map.put("msg","发送成功！");
          return JSON.toJSONString(map);
      }else {
          map.put("code","1");
          map.put("msg","发送失败！");
          return JSON.toJSONString(map);
      }

    }



  @RequestMapping("/test")
    public String testlogin(AppLogin appLogin){
        Map<String,Object> map=new HashMap<>();
        if (emptyUtils.isEmpty(appLogin)){
            map.put("code","false,don't for Empty!");
            return JSON.toJSONString(map);
        }
        if(!emptyUtils.isEmpty(appLogin.getLogin_id())){
            AppLogin login_info = appLoginMapper.selectByPrimaryKey(appLogin.getLogin_id());
            map.put("code","success");
            map.put("data",login_info);
            return JSON.toJSONString(map);
        }
        map.put("code","null");
        return JSON.toJSONString(map);
    }
    @RequestMapping("/test1")
    public String testlogins(Map obj){
        System.out.println("something wrong");
        System.out.println(JSON.toJSONString(obj));

        Map<String,Object> map=new HashMap<>();
        if (emptyUtils.isEmpty(obj)){
            map.put("code","false,don't for Empty!");
            return JSON.toJSONString(map);
        }
        if(!emptyUtils.isEmpty(obj.get("login_id"))){
            AppLogin login_info = appLoginMapper.selectByPrimaryKey((int)obj.get("login_id"));
            map.put("code","success");
            map.put("data",login_info);
            return JSON.toJSONString(map);
        }
        for (Object o : obj.keySet()) {
            System.out.println("obj:"+o.toString());
        }
        map.put("code","null");
        return JSON.toJSONString(map);
    }
}
