/*



 */
package com.lczx.template.method;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.lczx.util.SpringUtils;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 项目名称：
 * 功能模块名称：模板方法 - 多语言
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Component("messageMethod")
public class MessageMethod implements TemplateMethodModel
{
    
    @SuppressWarnings("rawtypes")
    public Object exec(List arguments) throws TemplateModelException
    {
        if (arguments != null && !arguments.isEmpty()
                && arguments.get(0) != null
                && StringUtils.isNotEmpty(arguments.get(0).toString()))
        {
            String message = null;
            String code = arguments.get(0).toString();
            if (arguments.size() > 1)
            {
                Object[] args = arguments.subList(1, arguments.size())
                        .toArray();
                message = SpringUtils.getMessage(code, args);
            }
            else
            {
                message = SpringUtils.getMessage(code);
            }
            return new SimpleScalar(message);
        }
        return null;
    }
    
}