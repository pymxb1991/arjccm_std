package com.arjjs.ccm.tool;
import com.google.common.base.Preconditions; 
import org.springframework.security.authentication.encoding.Md5PasswordEncoder; 
import org.springframework.security.authentication.encoding.PasswordEncoder; 

public class PasswordUtils { 


    public static final PasswordEncoder encoder = new Md5PasswordEncoder(); 


    /** 
     * MD5加密密码 
     * 
     * @param password 
     * @return 
     */ 
    public static String createPassword(String password) { 
        Preconditions.checkNotNull(password); 
        return encoder.encodePassword(password, null); 
    } 


    /** 
     * 验证输入密码是否与MD5加密密码相符 
     * 之前的加密未采用Spring Security的内置方法，得到的结果是大写字符串，现统一为小写 
     * 
     * @param encPass MD5加密密码 
     * @param rawPass 原始密码 
     * @return 
     */ 
    public static boolean isPasswordInvalid(String encPass, String rawPass) { 
        Preconditions.checkNotNull(encPass); 
        Preconditions.checkNotNull(rawPass); 
        return encoder.isPasswordValid(encPass.toLowerCase(), rawPass, null); 
    } 
}