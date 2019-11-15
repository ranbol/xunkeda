package com.example.entity.utils;

import java.util.concurrent.atomic.AtomicReference;

public class codeUtils {

    private static String PASSWORD_ZZ="^[a-zA-Z0-9]{6,12}$";
    /**
     * 获取num的随机数
     * @param num  （随机数位数）
     * @return
     */
    public static String getPhoneCodeValue(Integer num){
        AtomicReference<String> rtnValue= new AtomicReference<>("");
        for (int i = 0; i <num ; i++) {
            rtnValue.updateAndGet(v -> v + (int) (Math.random() * (10 - 1) + 1));
        }
        return rtnValue.get();
    }

    /**
     * (密码格式验证) 包含数字和字符的6-12未正则表达式
     * 包含大小写字母及数字且在6-12位
     * 是否包含
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
       /* String regex = "^[a-zA-Z0-9]{6,12}$";*/
        boolean isRight = isDigit && isLetter && str.matches(PASSWORD_ZZ);
        return isRight;
    }

}
