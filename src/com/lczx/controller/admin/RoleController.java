package com.lczx.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lczx.entity.Role;
import com.lczx.service.ResourceService;
import com.lczx.service.RoleService;
import com.lczx.util.Message;
import com.lczx.util.Pageable;


/**
 * 项目名称：
 * 功能模块名称：Controller - 角色
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:四川天思科技有限责任公司 www.zensoftware.com.cn
 */
@Controller("adminRoleController")
@RequestMapping("/admin/role")
public class RoleController extends BaseController
{
    
    @Resource(name = "roleServiceImpl")
    private RoleService roleService;
    
    @Resource(name = "resourceServiceImpl")
    private ResourceService resourceService;
    
    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("resources", resourceService.findAll());
        model.addAttribute("resourceGroups",
                resourceService.findResourceGroup());
        return "/admin/role/add";
    }
    
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Role role, Long[] resourceIds,
            RedirectAttributes redirectAttributes)
    {
        role.setResources(resourceService.findList(resourceIds));
        if (!isValid(role))
        {
            return ERROR_VIEW;
        }
        role.setIsSystem(false);
        role.setAdmins(null);
        roleService.save(role);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }
    
    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("role", roleService.find(id));
        model.addAttribute("resources", resourceService.findAll());
        model.addAttribute("resourceGroups",
                resourceService.findResourceGroup());
        return "/admin/role/edit";
    }
    
    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Role role, Long[] resourceIds,
            RedirectAttributes redirectAttributes)
    {
        role.setResources(resourceService.findList(resourceIds));
        if (!isValid(role))
        {
            return ERROR_VIEW;
        }
//        Role pRole = roleService.find(role.getId());
//        if (pRole == null || pRole.getIsSystem())
//        {
//            return ERROR_VIEW;
//        }
        roleService.update(role, "isSystem", "admins");
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }
    
    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", roleService.findPage(pageable));
        return "/admin/role/list";
    }
    
    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        if (ids != null)
        {
            for (Long id : ids)
            {
                Role role = roleService.find(id);
                if (role != null
                        && (role.getIsSystem() || (role.getAdmins() != null && !role.getAdmins()
                                .isEmpty())))
                {
                    return Message.error("admin.role.deleteExistNotAllowed",
                            role.getName());
                }
            }
            roleService.delete(ids);
        }
        return SUCCESS_MESSAGE;
    }
    
}