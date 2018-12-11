package com.lczx.util;

/**
 * 项目名称：
 * 功能模块名称：公共参数
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public final class CommonAttributes
{
    
    /** 日期格式配比 */
    public static final String[] DATE_PATTERNS = new String[] { "yyyy",
            "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd",
            "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss",
            "yyyy/MM/dd HH:mm:ss" };
    
    /** definedweek.xml文件路径 */
    public static final String DEFINEDWEEK_XML_PATH = "/definedweek.xml";
    
    /** definedweek.properties文件路径 */
    public static final String DEFINEDWEEK_PROPERTIES_PATH = "/definedweek.properties";
    
    /**
     * 不可实例化
     */
    private CommonAttributes()
    {
    }
    
}