package com.lczx.entity;

import java.util.List;


/**
 * 统一返回JSON数据对象 
 * 
 **/
public class JsonResult
{
    /**状态码*/
    private String code;
    /**接口名称*/
    private String url;
    /**错误编码*/
    private String errorCode;
    /**错误信息*/
    private String errorMsg;
    private List<Object> list;
}
