/*



 */
package com.lczx.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.lczx.dao.ArticleDao;
import com.lczx.entity.Article;
import com.lczx.entity.Template;
import com.lczx.freeMarker.FreeMarkerViewUtil;
import com.lczx.service.StaticService;
import com.lczx.service.TemplateService;



/**
 * 项目名称： 功能模块名称：Service - 静态化 功能描述：
 * 
 * @author yideng
 * @version 1.0 Feb 11, 2014 Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 *          Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Service("staticServiceImpl")
public class StaticServiceImpl implements StaticService, ServletContextAware
{
    
    /** Sitemap最大地址数 */
    private static final Integer SITEMAP_MAX_SIZE = 40000;
    
    /** servletContext */
    private ServletContext servletContext;
    
    @Resource(name = "freemarkerConfig")
    private FreeMarkerConfigurer freeMarkerConfigurer;
    
    @Resource(name = "templateServiceImpl")
    private TemplateService templateService;
    
    @Resource(name = "articleDaoImpl")
    private ArticleDao articleDao;
    
    public void setServletContext(ServletContext servletContext)
    {
        this.servletContext = servletContext;
    }
    
    @Transactional(readOnly = true)
    public int build(String templatePath, String staticPath,
            Map<String, Object> model)
    {
        Assert.hasText(templatePath);
        Assert.hasText(staticPath);
        
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        Writer writer = null;
        try
        {
            freemarker.template.Template template = freeMarkerConfigurer.getConfiguration()
                    .getTemplate(templatePath);
            File staticFile = new File(servletContext.getRealPath(staticPath));
            File staticDirectory = staticFile.getParentFile();
            if (!staticDirectory.exists())
            {
                staticDirectory.mkdirs();
            }
            fileOutputStream = new FileOutputStream(staticFile);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream,
                    "UTF-8");
            writer = new BufferedWriter(outputStreamWriter);
            template.process(model, writer);
            writer.flush();
            return 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly(writer);
            IOUtils.closeQuietly(outputStreamWriter);
            IOUtils.closeQuietly(fileOutputStream);
        }
        return 0;
    }
    
    @Transactional(readOnly = true)
    public int build(String templatePath, String staticPath)
    {
        return build(templatePath, staticPath, null);
    }
    
    @Transactional(readOnly = true)
    public int build(Article article)
    {
        Assert.notNull(article);
        
        delete(article);
        Template template = templateService.get("articleContent");
        int buildCount = 0;
        if (article.getIsPublication())
        {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("article", article);
           
                buildCount += build(template.getTemplatePath(),
                        article.getPath(),
                        model);
        }
        return buildCount;
    }
    
    @Transactional(readOnly = true)
    public int buildIndex()
    {
        Template template = templateService.get("index");
        return build(template.getTemplatePath(), template.getStaticPath());
    }
    
    @SuppressWarnings("unused")
    @Transactional(readOnly = true)
    public int buildSitemap()
    {
        int buildCount = 0;
        //Template sitemapIndexTemplate = templateService.get("sitemapIndex");
        Template sitemapTemplate = templateService.get("sitemap");
        Map<String, Object> model = new HashMap<String, Object>();
        List<String> staticPaths = new ArrayList<String>();
        for (int step = 0, index = 0, first = 0, count = SITEMAP_MAX_SIZE;;)
        {
            try
            {
                model.put("index", index);
                String templatePath = sitemapTemplate.getTemplatePath();
                String staticPath = FreeMarkerViewUtil.process(sitemapTemplate.getStaticPath(),
                        model);
                if (step == 0)
                {
                    List<Article> articles = articleDao.findList(first,
                            count,
                            null,
                            null);
                    model.put("articles", articles);
                    if (articles.size() < count)
                    {
                        step++;
                        first = 0;
                        count -= articles.size();
                    }
                    else
                    {
                        buildCount += build(templatePath, staticPath, model);
                        articleDao.clear();
                        articleDao.flush();
                        staticPaths.add(staticPath);
                        model.clear();
                        index++;
                        first += articles.size();
                        count = SITEMAP_MAX_SIZE;
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    @Transactional(readOnly = true)
    public int buildOther()
    {
        int buildCount = 0;
        Template shopCommonJsTemplate = templateService.get("shopCommonJs");
        Template adminCommonJsTemplate = templateService.get("adminCommonJs");
        buildCount += build(shopCommonJsTemplate.getTemplatePath(),
                shopCommonJsTemplate.getStaticPath());
        buildCount += build(adminCommonJsTemplate.getTemplatePath(),
                adminCommonJsTemplate.getStaticPath());
        return buildCount;
    }
    
    @Transactional(readOnly = true)
    public int buildAll()
    {
        int buildCount = 0;
        for (int i = 0; i < articleDao.count(); i += 20)
        {
            List<Article> articles = articleDao.findList(i, 20, null, null);
            for (Article article : articles)
            {
                buildCount += build(article);
            }
            articleDao.clear();
        }
        buildIndex();
        buildSitemap();
        buildOther();
        return buildCount;
    }
    
    @Transactional(readOnly = true)
    public int delete(String staticPath)
    {
        Assert.hasText(staticPath);
        
        File staticFile = new File(servletContext.getRealPath(staticPath));
        if (staticFile.exists())
        {
            staticFile.delete();
            return 1;
        }
        return 0;
    }
    
    @Transactional(readOnly = true)
    public int delete(Article article)
    {
        Assert.notNull(article);
        
        int deleteCount = 0;
        
            int count = delete(article.getPath());
            
            deleteCount += count;
        return deleteCount;
    }
    
    @Transactional(readOnly = true)
    public int deleteIndex()
    {
        Template template = templateService.get("index");
        return delete(template.getStaticPath());
    }
    
    @Transactional(readOnly = true)
    public int deleteOther()
    {
        int deleteCount = 0;
        Template shopCommonJsTemplate = templateService.get("shopCommonJs");
        Template adminCommonJsTemplate = templateService.get("adminCommonJs");
        deleteCount += delete(shopCommonJsTemplate.getStaticPath());
        deleteCount += delete(adminCommonJsTemplate.getStaticPath());
        return deleteCount;
    }
   
}