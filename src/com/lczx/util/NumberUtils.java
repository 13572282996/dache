package com.lczx.util;


import java.util.Date;
import java.util.Random;


/**
 * <流水号生成类>
 * 
 * @author  
 * @version  [版本号, 2010-7-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class NumberUtils {
    public static String getSerialnumber(String prefix) {
        StringBuffer sb = new StringBuffer(prefix);
        String dateStr = TimeTools.getStringByFormat(new Date(), "yyMMddHHmmss");
        sb.append(dateStr).append(getRandom(4));
        return sb.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(NumberUtils.getSerialnumber("CKSQ"));
    }
    
    public static String getRandom(int digit) {
        StringBuffer sb = new StringBuffer();
        Random ra = new Random();
        for (int i = 0; i < digit; i++) {
            sb.append(ra.nextInt(10) + 1);
        }
        return sb.toString();
    }
    
}
