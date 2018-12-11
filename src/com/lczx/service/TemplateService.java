/*



 */
package com.lczx.service;

import java.util.List;

import com.lczx.entity.Template;
import com.lczx.entity.Template.Type;



/**
 * 项目名称：
 * 功能模块名称：Service - 模板
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public interface TemplateService
{
    
    /**
     * 获取所有模板
     * 
     * @return 所有模板
     */
    List<Template> getAll();
    
    /**
     * 获取模板
     * 
     * @param type
     *            类型
     * @return 模板
     */
    List<Template> getList(Type type);
    
    /**
     * 获取模板
     * 
     * @param id
     *            ID
     * @return 模板
     */
    Template get(String id);
    
    /**
     * 读取模板文件内容
     * 
     * @param id
     *            ID
     * @return 模板文件内容
     */
    String read(String id);
    
    /**
     * 读取模板文件内容
     * 
     * @param template
     *            模板
     * @return 模板文件内容
     */
    String read(Template template);
    
    /**
     * 写入模板文件内容
     * 
     * @param id
     *            Id
     * @param content
     *            模板文件内容
     */
    void write(String id, String content);
    
    /**
     * 写入模板文件内容
     * 
     * @param template
     *            模板
     * @param content
     *            模板文件内容
     */
    void write(Template template, String content);
    
}