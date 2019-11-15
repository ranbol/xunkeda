package com.example.entity.interceptor;





import com.example.entity.utils.jwtUtils;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.logging.Logger;

public class MyInterceptor implements HandlerInterceptor {
    private Logger logger= (Logger) Logger.getLogger(String.valueOf (MyInterceptor.class));

    @Autowired
   // private UserLoginMapper userLoginMapper;
   @Override
 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

          //请求的路径
          String url = request.getServletPath ().toString ();
          //logger.info ("url:" + url);
          System.out.println(url);
          //1、静态资源直接放行html img css js
          if (request.getServletPath ().contains (".")) {
              return true;
          }
          //2、放行登录请求
          if (request.getServletPath ().endsWith ("Login")) {
              System.out.println ("url:"+url+"放行成功！");
              return true;
          }
//3、下载pdf放行  .pdf下载
          if (request.getServletPath ().endsWith ("download")) {
              return true;
          }
          //4、如果用户已经登录，从Header中获取Authorization
          boolean authorization = jwtUtils.verify(request.getHeader ("token"));
          if (authorization){
              return true;
          }
         // System.out.println("authorization:"+authorization);
          //5 判断token是不是空
         /* if (authorization== null) {
              System.out.println ("token为空");
              return false;
          }
          */

        //这里token可以根据个人爱好，随便使用任何加密算法来实现
        /*  HttpSession session=request.getSession ();
          UserLogin user= (UserLogin) session.getAttribute ("user");
          if(user!=null){
              UserLogin userLoginInfo=userLoginMapper.selectByPrimaryKey (user.getId ());
              if (userLoginInfo!=null){
                  String token=userLoginInfo.getToken ();
                  if(user.getToken().equals(token)){
                      return true;
                  }
              }else {
                  return false;
              }
          }else {
              return false;
          }
*/

         //String token="************";
       //6、验证token是否为真实， 这里验证token签名  根据特殊秘钥  一般我们签名当前最火的是redis
       /*if (token.equals (authorization)) {
           return true;
       }*/
          //7非法请求 即这些请求需要登录后才能访问
          Response baseResponse = new Response ();
        //  baseResponse.setError ();
          baseResponse.setMessage ("权限不够");


          response.setContentType ("text/json;charset=UTF-8");
          response.setCharacterEncoding ("UTF-8");
          response.getWriter ().write (String.valueOf (baseResponse));

          System.out.println ("未匹配url，验证失败");
          return false;
      }
}
