/*



 */
package com.lczx.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lczx.service.CacheService;

/**
 * 项目名称：
 * 功能模块名称：Controller - 缓存
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Controller("adminCacheController")
@RequestMapping("/admin/cache")
public class CacheController extends BaseController
{
    
    @Resource(name = "cacheServiceImpl")
    private CacheService cacheService;
    
    /**
     * 缓存查看
     */
    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public String clear(ModelMap model)
    {
        Long totalMemory = null;
        Long maxMemory = null;
        Long freeMemory = null;
        try
        {
            totalMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
            maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
            freeMemory = Runtime.getRuntime().freeMemory() / 1024 / 1024;
        }
        catch (Exception e)
        {
        }
        model.addAttribute("totalMemory", totalMemory);
        model.addAttribute("maxMemory", maxMemory);
        model.addAttribute("freeMemory", freeMemory);
        model.addAttribute("cacheSize", cacheService.getCacheSize());
        model.addAttribute("diskStorePath", cacheService.getDiskStorePath());
        return "/admin/cache/clear";
    }
    
    /**
     * 清除缓存
     */
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    public String clear(RedirectAttributes redirectAttributes)
    {
        cacheService.clear();
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:clear.jhtml";
    }
    
}