/*



 */
package com.lczx.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;





import com.lczx.entity.ArticleCategory;
import com.lczx.service.ArticleCategoryService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 项目名称：
 * 功能模块名称：模板指令 - 顶级文章分类列表
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Component("articleCategoryRootListDirective")
public class ArticleCategoryRootListDirective extends BaseDirective
{
    
    /** 变量名称 */
    private static final String VARIABLE_NAME = "articleCategories";
    
    @Resource(name = "articleCategoryServiceImpl")
    private ArticleCategoryService articleCategoryService;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException
    {
        List<ArticleCategory> articleCategories;
        boolean useCache = useCache(env, params);
        String cacheRegion = getCacheRegion(env, params);
        Integer count = getCount(params);
        if (useCache)
        {
            articleCategories = articleCategoryService.findRoots(count,
                    cacheRegion);
        }
        else
        {
            articleCategories = articleCategoryService.findRoots(count);
        }
        setLocalVariable(VARIABLE_NAME, articleCategories, env, body);
    }
    
}