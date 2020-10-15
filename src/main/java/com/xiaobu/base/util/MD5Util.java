package com.xiaobu.base.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/30 13:45
 * @description V1.0
 */
public class MD5Util {
    /**
     * @author xiaobu
     * @date 2018/11/29 9:21
     * @param source 明文密码 , salt 盐
     * @return java.lang.String
     * @descprition  利用shiro自带的md5加密方式加密
     * @version 1.0
     */
    public static String getMD5Pwd(String salt,String source){
        return new Md5Hash(source,"("+salt+")").toString();
    }


    public static void main(String[] args) {
        System.out.println(getMD5Pwd("admin","111111"));
    }
}
