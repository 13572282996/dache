package com.lczx.template.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lczx.entity.ArticleCategory;
import com.lczx.service.ArticleCategoryService;
import com.lczx.util.FreemarkerUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 项目名称：
 * 功能模块名称：模板指令 - 下级文章分类列表(只要第一级)
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Component("articleCategoryChildrenFirstListDirective")
public class ArticleCategoryChildrenFirstListDirective extends BaseDirective
{
	/** "文章分类ID"参数名称 */
    private static final String ARTICLE_CATEGORY_ID_PARAMETER_NAME = "articleCategoryId";
    
    /** 变量名称 */
    private static final String VARIABLE_NAME = "articleCategories";
    
    @Resource(name = "articleCategoryServiceImpl")
    private ArticleCategoryService articleCategoryService;
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] arg2,
			TemplateDirectiveBody body) throws TemplateException, IOException 
	{
		Long articleCategoryId = FreemarkerUtils.getParameter(ARTICLE_CATEGORY_ID_PARAMETER_NAME,
                Long.class,
                params);
        
        ArticleCategory articleCategory = articleCategoryService.find(articleCategoryId);
        
        List<ArticleCategory> articleCategories;
        if (articleCategoryId != null && articleCategory == null)
        {
            articleCategories = new ArrayList<ArticleCategory>();
        }
        else
        {
            boolean useCache = useCache(env, params);
            String cacheRegion = getCacheRegion(env, params);
            Integer count = getCount(params);
            if (useCache)
            {
                articleCategories = articleCategoryService.findChildrenFirst(articleCategory,
                        count,
                        cacheRegion);
                System.out.println(articleCategories.get(0).getChildren().size());
            }
            else
            {
                articleCategories = articleCategoryService.findChildrenFirst(articleCategory,
                        count);
            }
        }
        setLocalVariable(VARIABLE_NAME, articleCategories, env, body);
		
	}

}
