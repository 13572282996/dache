/*



 */
package com.lczx.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;






import org.springframework.stereotype.Component;

import com.lczx.entity.FriendLink;
import com.lczx.service.FriendLinkService;
import com.lczx.util.Filter;
import com.lczx.util.Order;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 项目名称：
 * 功能模块名称：模板指令 - 友情链接列表
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Component("friendLinkListDirective")
public class FriendLinkListDirective extends BaseDirective
{
    
    /** 变量名称 */
    private static final String VARIABLE_NAME = "friendLinks";
    
    @Resource(name = "friendLinkServiceImpl")
    private FriendLinkService friendLinkService;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException
    {
        List<FriendLink> friendLinks;
        boolean useCache = useCache(env, params);
        String cacheRegion = getCacheRegion(env, params);
        Integer count = getCount(params);
        List<Filter> filters = getFilters(params, FriendLink.class);
        List<Order> orders = getOrders(params);
        if (useCache)
        {
            friendLinks = friendLinkService.findList(count,
                    filters,
                    orders,
                    cacheRegion);
        }
        else
        {
            friendLinks = friendLinkService.findList(count, filters, orders);
        }
        setLocalVariable(VARIABLE_NAME, friendLinks, env, body);
    }
    
}