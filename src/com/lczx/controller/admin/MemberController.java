package com.lczx.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lczx.entity.Member;
import com.lczx.service.MemberService;
import com.lczx.service.OrderService;
import com.lczx.util.Filter;
import com.lczx.util.Message;
import com.lczx.util.Pageable;

@Controller("adminMemberController")
@RequestMapping("/admin/member")
public class MemberController extends BaseController
{
    @Resource(name = "memberServiceImpl")
    private MemberService memberService;
    
    @Resource(name = "orderServiceImpl")
    private OrderService orderService;
    
    /**
     * 列表 
     * 
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        
        model.addAttribute("page", memberService.findPage(pageable));
        
        return "/admin/member/list";
    }
    
    /**
     * 查看
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(Long id, Integer pageNumber, ModelMap model)
    {
        if (pageNumber == null)
        {
            pageNumber = 1;
        }
        Member member = memberService.find(id);
        model.addAttribute("member", member);
        Pageable pageable = new Pageable(pageNumber, 10);
        List<Filter> filters = new ArrayList<Filter>();
        filters.add(Filter.eq("memberMobile", member.getMobilePhone()));
        pageable.setFilters(filters);
        model.addAttribute("page", orderService.findPage(pageable));
        return "/admin/member/view";
    }
    
    
    /**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody 
	Message delete(Long[] ids) {
		if (ids.length >= memberService.count()) {
			return Message.error("admin.common.deleteAllNotAllowed");
		}
		memberService.delete(ids);
		return Message.success("操作成功","操作成功");
	}
    
}
