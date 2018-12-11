/*



 */
package com.lczx.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lczx.util.*;
import com.lczx.entity.Navigation;
import com.lczx.entity.Navigation.Position;
import com.lczx.service.*;

/**
 * 项目名称：
 * 功能模块名称：Controller - 导航
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Controller("adminNavigationController")
@RequestMapping("/admin/navigation")
public class NavigationController extends BaseController
{
    
    @Resource(name = "navigationServiceImpl")
    private NavigationService navigationService;
    
    @Resource(name = "articleCategoryServiceImpl")
    private ArticleCategoryService articleCategoryService;
    
    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("positions", Position.values());
        //        model.addAttribute("articleCategoryTree",
        //                articleCategoryService.findTree());
        model.addAttribute("navigationTree", navigationService.findTree());
        return "/admin/navigation/add";
    }
    
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Navigation navigation, Long parentId,
            RedirectAttributes redirectAttributes)
    {
        navigation.setParent(navigationService.find(parentId));
        if (!isValid(navigation))
        {
            return ERROR_VIEW;
        }
        navigation.setTreePath(null);
        navigation.setGrade(null);
        navigation.setChildren(null);
        navigationService.save(navigation);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }
    
    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        try
        {
            Navigation navigation = navigationService.find(id);
            if (navigation.getParent() != null)
            {
                model.addAttribute("navigationParentId", navigation.getParent().getId());
            }else {
                model.addAttribute("navigationParentId", null);
            }
            model.addAttribute("positions", Position.values());
            model.addAttribute("navigationTree", navigationService.findTree());
            model.addAttribute("navigation", navigation);
            model.addAttribute("children",
                    navigationService.findChildren(navigation));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "/admin/navigation/edit";
    }
    
    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Navigation navigation, Long parentId,
            RedirectAttributes redirectAttributes)
    {
        if (!isValid(navigation))
        {
            return ERROR_VIEW;
        }
        if (parentId != null)
        {
            Navigation navigationParent = navigationService.find(parentId);
            navigation.setParent(navigationParent);
        }
        navigationService.update(navigation);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }
    
    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        try
        {
            model.addAttribute("topNavigations",
                    navigationService.findTree(Position.top));
            model.addAttribute("middleNavigations",
                    navigationService.findTree(Position.middle));
            model.addAttribute("bottomNavigations",
                    navigationService.findTree(Position.bottom));
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "/admin/navigation/list";
    }
    
    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        navigationService.delete(ids);
        return SUCCESS_MESSAGE;
    }
    
}