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

import com.lczx.entity.AdPosition;
import com.lczx.service.AdPositionService;
import com.lczx.util.Message;
import com.lczx.util.Pageable;


/**
 * 项目名称：
 * 功能模块名称：Controller - 广告位
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Controller("adminAdPositionController")
@RequestMapping("/admin/ad_position")
public class AdPositionController extends BaseController
{
    
    @Resource(name = "adPositionServiceImpl")
    private AdPositionService adPositionService;
    
    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        return "/admin/ad_position/add";
    }
    
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(AdPosition adPosition,
            RedirectAttributes redirectAttributes)
    {
        if (!isValid(adPosition))
        {
            return ERROR_VIEW;
        }
        adPosition.setAds(null);
        adPositionService.save(adPosition);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }
    
    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("adPosition", adPositionService.find(id));
        return "/admin/ad_position/edit";
    }
    
    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(AdPosition adPosition,
            RedirectAttributes redirectAttributes)
    {
        if (!isValid(adPosition))
        {
            return ERROR_VIEW;
        }
        adPositionService.update(adPosition, "ads");
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }
    
    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", adPositionService.findPage(pageable));
        return "/admin/ad_position/list";
    }
    
    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        adPositionService.delete(ids);
        return SUCCESS_MESSAGE;
    }
    
}