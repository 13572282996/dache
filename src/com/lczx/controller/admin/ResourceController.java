/**
 * 文 件 名:  ResourceController.java
 * 版       权:  Copyright (c) ZenSoftware Co.,Ltd. 2014
 * 描       述: 
 * 修  改   人:yideng
 * 修改时间:Oct 24, 2014
 * 修改描述:
 */
package com.lczx.controller.admin;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lczx.entity.Resource;
import com.lczx.service.ResourceService;
import com.lczx.util.Message;
import com.lczx.util.Pageable;
import com.lczx.util.SpringUtils;



/**
 * 项目名称：
 * 功能模块名称：资源管理
 * 功能描述：
 * @author yideng
 * @version 1.0 Oct 24, 2014
 * Copyright: Copyright (c) ZenSoftware Co.,Ltd. 2014
 * Company:四川天思科技有限责任公司 www.zensoftware.com.cn
 */
@Controller("adminResourceController")
@RequestMapping("/admin/resource")
public class ResourceController extends BaseController
{
    @javax.annotation.Resource(name = "resourceServiceImpl")
    private ResourceService resourceService;
    
    @javax.annotation.Resource
    private ShiroFilterFactoryBean shiroFilterFactoryBean;
    
    /**
     * 检查资源名称是否存在
     */
    @RequestMapping(value = "/check_name", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkName(String previousName, String name)
    {
        if (StringUtils.isEmpty(name))
        {
            return false;
        }
        if (resourceService.nameUnique(previousName, name))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * 检查资源标识是否存在
     */
    @RequestMapping(value = "/check_mark", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkMark(String previousMark, String mark)
    {
        if (StringUtils.isEmpty(mark))
        {
            return false;
        }
        if (resourceService.markUnique(previousMark, mark))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * 检查资源url是否存在
     */
    @RequestMapping(value = "/check_resourceurl", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkResourceUrl(String previousUrl, String url)
    {
        if (StringUtils.isEmpty(url))
        {
            return false;
        }
        if (resourceService.resourceUrlUnique(previousUrl, url))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        return "/admin/resource/add";
    }
    
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Resource resource, RedirectAttributes redirectAttributes)
    {
        if (!isValid(resource))
        {
            return ERROR_VIEW;
        }
        resourceService.save(resource);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        createFilterChains();
        return "redirect:list.jhtml";
    }
    
    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("resource", resourceService.find(id));
        return "/admin/resource/edit";
    }
    
    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Resource resource,
            RedirectAttributes redirectAttributes)
    {
        if (!isValid(resource))
        {
            return ERROR_VIEW;
        }
        resourceService.update(resource);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        createFilterChains();
        return "redirect:list.jhtml";
    }
    
    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", resourceService.findPage(pageable));
        return "/admin/resource/list";
    }
    
    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        List<Resource> resources = resourceService.findList(ids);
        for (Resource resource : resources)
        {
            if (resource.getRoles() != null && !resource.getRoles().isEmpty())
            {
                return Message.error("admin.resource.deleteExistNotAllowed",
                        resource.getName());
            }
        }
        resourceService.delete(ids);
        createFilterChains();
        return SUCCESS_MESSAGE;
    }
    
    /**
     * 复制
     */
    @RequestMapping(value = "/copy", method = RequestMethod.GET)
    public String copy(Long id, ModelMap model)
    {
        Resource resource = resourceService.find(id);
        resource.setId(null);
        model.addAttribute("resource", resource);
        return "/admin/resource/copy";
    }
    
    private synchronized void createFilterChains()
    {
        try
        {
            Section section = SpringUtils.getBean("chainDefinitionSectionMetaSource",
                    Section.class);
            AbstractShiroFilter shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            //清空老的权限控制
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(section);
            //重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet())
            {
                String url = entry.getKey();
                String chainDefinition = entry.getValue()
                        .trim()
                        .replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
