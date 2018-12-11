package com.lczx.util;

import org.apache.commons.beanutils.converters.AbstractConverter;

/**
 * 项目名称：
 * 功能模块名称：枚举类型转换
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public class EnumConverter extends AbstractConverter
{
    
    /** 枚举类型 */
    private final Class<?> enumClass;
    
    /**
     * @param enumClass
     *            枚举类型
     */
    public EnumConverter(Class<?> enumClass)
    {
        this(enumClass, null);
    }
    
    /**
     * @param enumClass
     *            枚举类型
     * @param defaultValue
     *            默认值
     */
    public EnumConverter(Class<?> enumClass, Object defaultValue)
    {
        super(defaultValue);
        this.enumClass = enumClass;
    }
    
    /**
     * 获取默认类型
     * 
     * @return 默认类型
     */
    @Override
    protected Class<?> getDefaultType()
    {
        return this.enumClass;
    }
    
    /**
     * 转换为枚举对象
     * 
     * @param type
     *            类型
     * @param value
     *            值
     * @return 枚举对象
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected Object convertToType(Class type, Object value)
    {
        String stringValue = value.toString().trim();
        return Enum.valueOf(type, stringValue);
    }
    
    /**
     * 转换为字符串
     * 
     * @param value
     *            值
     * @return 字符串
     */
    protected String convertToString(Object value)
    {
        return value.toString();
    }
    
}