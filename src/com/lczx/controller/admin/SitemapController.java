/*



 */
package com.lczx.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lczx.entity.*;
import com.lczx.service.*;

/**
 * 项目名称：
 * 功能模块名称：Controller - Sitemap
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Controller("adminSitemapController")
@RequestMapping("/admin/sitemap")
public class SitemapController extends BaseController
{
    
    @Resource(name = "templateServiceImpl")
    private TemplateService templateService;
    
    @Resource(name = "staticServiceImpl")
    private StaticService staticService;
    
    /**
     * 生成Sitemap
     */
    @RequestMapping(value = "/build", method = RequestMethod.GET)
    public String build(ModelMap model)
    {
        Template sitemapIndexTemplate = templateService.get("sitemapIndex");
        model.addAttribute("sitemapIndexPath",
                sitemapIndexTemplate.getStaticPath());
        return "/admin/sitemap/build";
    }
    
    /**
     * 生成Sitemap
     */
    @RequestMapping(value = "/build", method = RequestMethod.POST)
    public String build(RedirectAttributes redirectAttributes)
    {
        staticService.buildSitemap();
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:build.jhtml";
    }
    
}