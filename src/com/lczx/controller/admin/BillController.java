package com.lczx.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lczx.service.BillService;
import com.lczx.util.Message;
import com.lczx.util.Pageable;

@Controller("adminBillController")
@RequestMapping("/admin/bill")
public class BillController extends BaseController
{
    @Resource(name = "billServiceImpl")
    private BillService billService;
    
    /**
     * 列表 
     * 
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        
        model.addAttribute("page", billService.findPage(pageable));
        
        return "/admin/bill/list";
    }
    /**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody 
	Message delete(Long[] ids) {
		if (ids.length >= billService.count()) {
			return Message.error("admin.common.deleteAllNotAllowed");
		}
		billService.delete(ids);
		return Message.success("操作成功","操作成功");
	}
}
