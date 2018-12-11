package com.lczx.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <功能简述>
 * 时间操作工具类
 * <功能详细描述>
 * 
 * @author  yuanwenbo
 * @version  [版本号, 2010-7-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TimeTools
{
    /**
     * Description: 通过"yyyy-MM-dd HH:mm:ss"字符串获得时间<br>
     * 
     * @param format
     * @return Date (异常返回null)
     */
    public static Date getDate(String dateString)
    {
        if (dateString == null || "".equals(dateString))
        {
            return null;
        }
        
        Date date = null;
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = df.parse(dateString);
        }
        catch (Exception e)
        {
            date = null;
        }
        return date;
    }
    
    public static Date getDateBySqlDate(java.sql.Date sqlDate)
    {
        if (sqlDate == null)
        {
            return null;
        }
        
        return new Date(sqlDate.getTime());
    }
    
    /**
     * Description: 把string转化为数据库使用的时间(yyyy-MM-dd HH:mm:ss)<br>
     * 
     * @param dateString
     * @return java.sql.Date
     */
    public static java.sql.Date getSqlDate(String dateString)
    {
        if (dateString == null || "".equals(dateString))
        {
            return null;
        }
        Date date = null;
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = df.parse(dateString);
        }
        catch (Exception e)
        {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }
    
    /**
     * Description:通过制定的格式化方法获得date <br>
     * 
     * @param dateString
     *            日期的字符表现
     * @param format
     *            格式化的字符串 eg："yyyy-MM-dd HH:mm:ss"
     * @return Date (异常返回null)
     */
    public static Date getDateByFormat(String dateString, String format)
    {
        Date date = null;
        try
        {
            SimpleDateFormat df = new SimpleDateFormat(format);
            date = df.parse(dateString);
        }
        catch (Exception e)
        {
            date = null;
        }
        
        return date;
    }
    
    /**
     * Description:获得数据库可以认识的当前时间 <br>
     * 
     * @return java.sql.Date
     */
    public static java.sql.Date getSqlNow()
    {
        return new java.sql.Date(new Date().getTime());
    }
    
    /**
     * Description: 获得sql里面的时间<br>
     * 
     * @param dateString
     * @param format
     * @return java.sql.Date
     */
    public static java.sql.Date getSqlDateByFormat(String dateString,
            String format)
    {
        if (dateString == null || "".equals(dateString))
        {
            return null;
        }
        Date date = null;
        try
        {
            SimpleDateFormat df = new SimpleDateFormat(format);
            date = df.parse(dateString);
        }
        catch (Exception e)
        {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }
    
    /**
     * Description:通过时间获得格式化的字符串 <br>
     * 
     * @param date
     *            时间
     * @return String 格式化后的字符串(异常返回空)
     */
    public static String getString(Date date)
    {
        String dateString = "";
        if (date == null)
        {
            return dateString;
        }
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            dateString = df.format(date);
        }
        catch (Exception e)
        {
            dateString = "";
        }
        return dateString;
    }
    
    /**
     * Description: 获得当前的yyyy-MM-dd HH:mm:ss<br>
     *
     * @return String 
     */
    public static String now()
    {
        return getString(new Date());
    }
    
    /**
     * Description: 根据指定的格式获得当前的时间字符形式<br>
     *
     * @return String 
     */
    public static String now(String format)
    {
        return getStringByFormat(new Date(), format);
    }
    
    /**
     * Description: 通过制定的格式化形式获得时间的字符串<br>
     * 
     * @param date
     *            需要格式化的时间
     * @param format
     *            格式化类型 eg:yyyy-MM-dd HH:mm:ss
     * @return String 格式化后的字符串(异常返回空)
     */
    public static String getStringByFormat(Date date, String format)
    {
        String dateString = "";
        if (date == null)
        {
            return dateString;
        }
        try
        {
            SimpleDateFormat df = new SimpleDateFormat(format);
            dateString = df.format(date);
        }
        catch (Exception e)
        {
            dateString = "";
        }
        return dateString;
    }
    
    /** 
     *功能简述：<功能简述>
     *详细描述：<功能详细描述>
     * @param min
     * @return [参数说明]
     * 
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static Date getLastDateByNum(int min)
    {
        long currentTime = System.currentTimeMillis() + 30 * 60 * 1000;
        Date date = new Date(currentTime);
        return date;
    }
    
    /**
     * Description: 通过"yyyy-MM-dd"字符串获得时间<br>
     * 
     * @param format
     * @return Date (异常返回null)
     */
    public static Date getShortDate(String dateString)
    {
        if (dateString == null || "".equals(dateString))
        {
            return null;
        }
        
        Date date = null;
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            date = df.parse(dateString);
        }
        catch (Exception e)
        {
            date = null;
        }
        return date;
    }
    
    /**
     * Description: 判断两个日期是否相同 <br>
     * Implement: <br>
     * 1、… <br>
     * 2、… <br>
     * [参数列表，说明每个参数用途]
     * 
     * @param 
     * @return boolean
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isSameDate(Date date1, Date date2)
    {
        if (date1 == null || date2 == null)
        {
            return false;
        }
        
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        
        cal1.setTime(date1);
        cal2.setTime(date2);
        
        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
                && (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH))
                && (cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH));
    }
    
    /**
     * Description: 比较两个日期的大小 <br>
     * Implement: <br>
     * 1、… <br>
     * 2、… <br>
     * [参数列表，说明每个参数用途]
     * 
     * @param 
     * @return int 两日期相等返回0,dateString1早于dateString2返回小于0的值,
     *          dateString1晚于dateString2返回大于0的值
     * @exception/throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static int compareToShortDate(String dateString1, String dateString2)
    {
        Date date1 = getShortDate(dateString1);
        Date date2 = getShortDate(dateString2);
        return date1.compareTo(date2);
    }
    
    /**
     * Description: 通过制定的格式化形式获得24小时前的时间的字符串<br>
     * 
     * @param date
     *            需要格式化的时间
     * @param format
     *            格式化类型 eg:yyyyMMdd
     * @return String 格式化后的字符串(异常返回空)
     */
    public static String getTomorrowDate(String format)
    {
        long time = System.currentTimeMillis();
        
        time = time - 24 * 60 * 60 * 1000;
        
        Date d = new Date(time);
        
        String dateString = "";
        try
        {
            SimpleDateFormat df = new SimpleDateFormat(format);
            dateString = df.format(d);
        }
        catch (Exception e)
        {
            dateString = "";
        }
        return dateString;
    }
    
    /**
     * Description: 通过制定的格式化形式获得上月的字符串<br>
     * 
     * @return String 格式化后的字符串(异常返回空)
     */
    public static String getLastMonth()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date d = new Date();
        String y = sdf.format(d).toString();
        int year = Integer.parseInt(y.substring(0, 4));
        int month = Integer.parseInt(y.substring(4, 6));
        if (month == 1)
        {
            year--;
            month = 12;
        }
        else
        {
            month--;
        }
        
        String tmpYear = String.valueOf(year);
        String tmpMontn = month < 10 ? "0" + String.valueOf(month)
                : String.valueOf(month);
        
        return tmpYear + tmpMontn;
    }
    
    /**
     * 
     * Description: 返回一个当前日期字符串<br>
     *
     * @return String 
     * @exception/throws [违例类型] [违例说明]
     */
    public static String getFormatDate()
    {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    
    /**
     * 
     * Description: 返回一个当前日期字符串<br>
     *
     * @return String 
     * @exception/throws [违例类型] [违例说明]
     */
    public static String getStrDate()
    {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }
    
    /**
     * 
     * Description: 返回一个当前日期字符串<br>
     *
     * @return String 
     * @exception/throws [违例类型] [违例说明]
     */
    public static String getDate()
    {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }
    
    /**
     * Description: 通过制定的格式化形式获得时间的字符串<br>
     * 
     * @param date
     *            需要格式化的时间
     * @param format
     *            格式化类型 eg:yyyy-MM-dd HH:mm:ss
     * @return String 格式化后的字符串(异常返回空)
     */
    public static String getStringByFormat(String strDate, String format)
    {
        String dateString = "";
        if (strDate == null)
        {
            return dateString;
        }
        Date date = getShortDate(strDate);
        try
        {
            SimpleDateFormat df = new SimpleDateFormat(format);
            dateString = df.format(date);
        }
        catch (Exception e)
        {
            dateString = "";
        }
        return dateString;
    }
    
    /**
     * Description: 通过"MM/dd/yy"字符串获得时间<br>
     * @param String
     * @return Date (异常返回null)
     */
    public static Date getQueryDate(String dateString)
    {
        if (dateString == null || "".equals(dateString))
        {
            return null;
        }
        
        Date date = null;
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
            date = df.parse(dateString);
        }
        catch (Exception e)
        {
            date = null;
        }
        return date;
    }
    
    /**
     * @author thl
     * 日期大小的比较
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compare_date(String DATE1, String DATE2)
    {
        int result = 0;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try
        {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime())
            {
                result = 1;
            }
            else if (dt1.getTime() < dt2.getTime())
            {
                result = -1;
            }
            else
            {
                result = 0;
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return result;
    }
    
    /**
     * @author thl
     * 日期大小的比较
     * @param DATE1
     * @param DATE2
     * @return
     */
    public static int compare_date(Date dt1, Date dt2)
    {
        int result = 0;
        try
        {
            if (dt1.getTime() > dt2.getTime())
            {
                result = 1;
            }
            else if (dt1.getTime() < dt2.getTime())
            {
                result = -1;
            }
            else
            {
                result = 0;
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return result;
    }
    
}
