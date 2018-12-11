package com.lczx.controller.appInterface;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import antlr.collections.List;

import com.lczx.entity.Bill;
import com.lczx.entity.Bill.BillStatus;
import com.lczx.entity.Member;
import com.lczx.service.BillService;
import com.lczx.service.MemberService;
import com.lczx.util.NumberUtils;
import com.lczx.util.RSAUtils;

@Controller("billController")
@RequestMapping("/api/bill")
public class BillController extends BaseController
{
    @Resource(name = "billServiceImpl")
    private BillService billService;
    
    @Resource(name = "memberServiceImpl")
    private MemberService memberService;
    
    /**
     * 申请开票 
     * {"billType":"company","classify":"electron","title":"","amount":"","mobile":"","email":"","dutySign":"","address":"","name":""}
     **/
    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    public @ResponseBody
    Object invoice(String param, String token)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "invoice");
            result.put("errormsg", "token不能为空");
            return result;
        }
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "invoice");
            result.put("errormsg", "参数错误");
            return result;
        }
        Member member = memberService.findMemberByToken(token);
        if (member == null)
        {
            result.put("code", "500");
            result.put("url", "invoice");
            result.put("errormsg", "登录过期请重新登录");
            return result;
        }
        String string;
        try
        {
            string = RSAUtils.decrypt(param);
            // string = "{'billType':'company','classify':'electron','title':'123','amount':'100','mobile':'13311111111','email':'22097002@qq.com','dutySign':'522222222222','address':'11111','name':'11111'}";
            JSONObject jsonObject = JSONObject.fromObject(string);
           Bill bill = (Bill) JSONObject.toBean(jsonObject, Bill.class);
            bill.setMember(member);
            bill.setBillStatus(BillStatus.uninvoice);
            bill.setNum(NumberUtils.getSerialnumber("FB"));
            billService.save(bill);
            
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "invoice");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "invoice");
        result.put("data", "申请开票成功");
        return result;
    }
    
    @RequestMapping(value = "/invoiceList", method = RequestMethod.POST)
    public @ResponseBody
    Object invoiceList(String token)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Set<Bill> bills = new HashSet<Bill>();
        Member member;
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "invoiceList");
            result.put("errormsg", "token不能为空");
            return result;
        }
        try
        {
            member = memberService.findMemberByToken(token);
            if (member == null)
            {
                result.put("code", "2000");
                result.put("url", "invoiceList");
                result.put("errormsg", "登录过期请重新登录");
                return result;
            }
            
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "invoiceList");
            result.put("errormsg", "系统错误");
            return result;
        }
        
        result.put("code", "200");
        result.put("url", "invoiceList");
        result.put("data", member.getBills());
        return result;
    }
    
}
