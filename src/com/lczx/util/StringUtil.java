/**
  * 文  件   名:  StringUtils.java
  * 功能描述：<描述>
  *	修改时间:  2015年9月6日
  * 修   改  人:Administrator
  * 版  本   号： 1.0 2015年9月6日
  * Copyright: Copyright (c) LCZX Co.,Ltd.
  * Company: 成都联创众享科技有限公司 www.lczxtech.com
  */
package com.lczx.util;

import java.util.regex.Pattern;

/**
 * 功能描述：<描述>
 * 详细描述：<功能详细描述>
 * 修改时间:  2015年9月6日
 * @author  Administrator
 * @version  [版本号, 2015年9月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */

public class StringUtil
{
    /**
     * 检查是否为手机号码
     * @param phone
     * @return
     */
    public static boolean isPhoneNum(String phone)
    {
        if (null == phone || "".equals(phone))
        {
            return false;
        }
        else
        {
            return Pattern.matches("^0?1\\d{10}$", phone);
        }
        
    }
    
    /**
     * 检查是否为身份证号码
     * @param phone
     * @return
     */
    public static boolean isIdcard(String idcard)
    {
        if (null == idcard || "".equals(idcard))
        {
            return false;
        }
        else
        {
            return Pattern.matches("^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$", idcard);
        }
        
    }
    
    /**
     * 检查是否为数字
     * @param phone
     * @return
     */
    public static boolean isNumber(String number)
    {
        if (null == number || "".equals(number))
        {
            return false;
        }
        else
        {
            for (int i = 0; i < number.length(); i++){
                if (!Character.isDigit(number.charAt(i))){
                 return false;
                }
               }
               return true;
        }
        
    }
    
    public static boolean isEmail(String str)
    {
        if (str == null || str == "")
        {
            return false;
        }
        String reg = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (str.matches(reg))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static void main(String[] args)
    {
        String str = "051072419920104087";
        System.out.println(isNumber(str));
    }
    
}
