/*



 */
package com.lczx.template.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lczx.entity.Article;
import com.lczx.entity.ArticleCategory;
import com.lczx.service.ArticleCategoryService;
import com.lczx.service.ArticleService;
import com.lczx.util.Filter;
import com.lczx.util.FreemarkerUtils;
import com.lczx.util.Order;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 项目名称：
 * 功能模块名称：模板指令 - 文章列表
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Component("articleListDirective")
public class ArticleListDirective extends BaseDirective
{
    
    /** "文章分类ID"参数名称 */
    private static final String ARTICLE_CATEGORY_ID_PARAMETER_NAME = "articleCategoryId";
    
    /** "标签ID"参数名称 */
    private static final String TAG_IDS_PARAMETER_NAME = "tagIds";
    
    /** 变量名称 */
    private static final String VARIABLE_NAME = "articles";
    
    @Resource(name = "articleServiceImpl")
    private ArticleService articleService;
    
    @Resource(name = "articleCategoryServiceImpl")
    private ArticleCategoryService articleCategoryService;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException
    {
        Long articleCategoryId = FreemarkerUtils.getParameter(ARTICLE_CATEGORY_ID_PARAMETER_NAME,
                Long.class,
                params);
        Long[] tagIds = FreemarkerUtils.getParameter(TAG_IDS_PARAMETER_NAME,
                Long[].class,
                params);
        
        ArticleCategory articleCategory = articleCategoryService.find(articleCategoryId);
        
        List<Article> articles;
        if (articleCategoryId != null && articleCategory == null)
        {
            articles = new ArrayList<Article>();
        }
        else
        {
            boolean useCache = useCache(env, params);
            String cacheRegion = getCacheRegion(env, params);
            Integer count = getCount(params);
            List<Filter> filters = getFilters(params, Article.class);
            List<Order> orders = getOrders(params);
            if (useCache)
            {
                articles = articleService.findList(articleCategory,
                        count,
                        filters,
                        orders,
                        cacheRegion);
            }
            else
            {
                articles = articleService.findList(articleCategory,
                        count,
                        filters,
                        orders);
            }
        }
        setLocalVariable(VARIABLE_NAME, articles, env, body);
    }
    
}