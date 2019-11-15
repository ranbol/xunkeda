package com.example.entity.modual.AppLogin;

import com.alibaba.fastjson.JSON;
import com.example.entity.dao.AppLoginMapper;
import com.example.entity.po.AppLogin;
import com.example.entity.utils.emptyUtils;
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

  @RequestMapping("/register")
  public String userLoginAndRegister(AppLogin appLogin,String phoneCode){
        System.out.println(appLogin.toString());
      System.out.println(phoneCode);
        return null;
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
