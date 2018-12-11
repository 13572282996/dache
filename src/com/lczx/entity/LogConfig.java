/*



 */
package com.lczx.entity;

import java.io.Serializable;

/**
 * 项目名称：
 * 功能模块名称：日志配置
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public class LogConfig implements Serializable
{
    
    private static final long serialVersionUID = -1108848647938408402L;
    
    /** 操作名称 */
    private String operation;
    
    /** 请求URL */
    private String urlPattern;
    
    /**
     * 获取操作名称
     * 
     * @return 操作名称
     */
    public String getOperation()
    {
        return operation;
    }
    
    /**
     * 设置操作名称
     * 
     * @param operation
     *            操作名称
     */
    public void setOperation(String operation)
    {
        this.operation = operation;
    }
    
    /**
     * 获取请求URL
     * 
     * @return 请求URL
     */
    public String getUrlPattern()
    {
        return urlPattern;
    }
    
    /**
     * 设置请求URL
     * 
     * @param urlPattern
     *            请求URL
     */
    public void setUrlPattern(String urlPattern)
    {
        this.urlPattern = urlPattern;
    }
    
}