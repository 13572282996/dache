/*



 */
package com.lczx.template.directive;

import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.lczx.entity.AdPosition;
import com.lczx.service.AdPositionService;

import freemarker.core.Environment;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 项目名称：
 * 功能模块名称：模板指令 - 广告位
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Component("adPositionDirective")
public class AdPositionDirective extends BaseDirective
{
    
    /** 变量名称 */
    private static final String VARIABLE_NAME = "adPosition";
    
    @Resource(name = "freemarkerConfig")
    private FreeMarkerConfigurer freemarkerConfig;
    
    @Resource(name = "adPositionServiceImpl")
    private AdPositionService adPositionService;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException
    {
        AdPosition adPosition;
        Long id = getId(params);
        boolean useCache = useCache(env, params);
        String cacheRegion = getCacheRegion(env, params);
        if (useCache)
        {
            adPosition = adPositionService.find(id, cacheRegion);
        }
        else
        {
            adPosition = adPositionService.find(id);
        }
        if (body != null)
        {
            setLocalVariable(VARIABLE_NAME, adPosition, env, body);
        }
        else
        {
            if (adPosition != null && adPosition.getTemplate() != null)
            {
                try
                {
                    Map<String, Object> model = new HashMap<String, Object>();
                    model.put(VARIABLE_NAME, adPosition);
                    Writer out = env.getOut();
                    new Template("adTemplate", new StringReader(
                            adPosition.getTemplate()),
                            freemarkerConfig.getConfiguration()).process(model,
                            out);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
}