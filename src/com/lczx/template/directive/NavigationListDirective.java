/*



 */
package com.lczx.template.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;








import org.springframework.stereotype.Component;

import com.lczx.entity.Navigation;
import com.lczx.service.NavigationService;
import com.lczx.util.Filter;
import com.lczx.util.Order;
import com.lczx.util.Filter.Operator;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 项目名称：
 * 功能模块名称：模板指令 - 导航列表
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Component("navigationListDirective")
public class NavigationListDirective extends BaseDirective
{
    
    /** 变量名称 */
    private static final String VARIABLE_NAME = "navigations";
    
    @Resource(name = "navigationServiceImpl")
    private NavigationService navigationService;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException
    {
        List<Navigation> navigations;
        boolean useCache = useCache(env, params);
        String cacheRegion = getCacheRegion(env, params);
        Integer count = getCount(params);
        List<Filter> filters = getFilters(params, Navigation.class);
        List<String> nullPropertys = new ArrayList<String>();
        List<Filter> filtersList = new ArrayList<Filter>();
        for (int i = 0; i < filters.size(); i++)
        {
            if (null == filters.get(i).getValue())
            {
                nullPropertys.add(filters.get(i).getProperty());
            }
            else
            {
				filtersList.add(filters.get(i));
			}
        }
        if (nullPropertys.size() > 0)
        {
            for (int i = 0; i < nullPropertys.size(); i++)
            {
                Filter filter = new Filter(nullPropertys.get(i), Operator.isNull, null);
                filtersList.add(filter);
            }
        }
        List<Order> orders = getOrders(params);
        if (useCache)
        {
            navigations = navigationService.findList(count,
                    filtersList,
                    orders,
                    cacheRegion);
        }
        else
        {
            navigations = navigationService.findList(count, filters, orders);
        }
        setLocalVariable(VARIABLE_NAME, navigations, env, body);
    }
    
}