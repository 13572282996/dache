package com.lczx.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lczx.service.OrderService;
import com.lczx.util.Message;
import com.lczx.util.Pageable;

@Controller("adminOrderController")
@RequestMapping("/admin/order")
public class OrderController
{
    @Resource(name = "orderServiceImpl")
    private OrderService orderService;
    
    
    /**
     * 列表 
     * 
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        
        model.addAttribute("page", orderService.findPage(pageable));
        
        return "/admin/order/list";
    }
    
    /**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody 
	Message delete(Long[] ids) {
		if (ids.length >= orderService.count()) {
			return Message.error("admin.common.deleteAllNotAllowed");
		}
		orderService.delete(ids);
		return Message.success("操作成功","操作成功");
	}
}
