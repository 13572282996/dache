package com.lczx.controller.appInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lczx.entity.CancelCauseConfig;
import com.lczx.entity.CancelCauseConfig.CauseType;
import com.lczx.service.CancelCauseConfigService;
import com.lczx.util.Filter;
import com.lczx.util.RSAUtils;

@Controller("causeConfigController")
@RequestMapping("/api/cause")
public class CauseConfigController extends BaseController
{
    @Resource(name = "cancelCauseServiceImpl")
    private CancelCauseConfigService cancelCauseConfigService;
    
    /**
     * 订单取消原因 
     * {'causeType':'member'} 可选值：member,driver
     **/
    @RequestMapping(value = "/causeList", method = RequestMethod.POST)
    public @ResponseBody
    Object causeList(String param)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<CancelCauseConfig> causes = new ArrayList<CancelCauseConfig>();
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "causeList");
            result.put("errormsg", "参数错误");
            return result;
        }
        try
        {
            String string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String str = jsonObject.getString("causeType");
            List<Filter> list = new ArrayList<Filter>();
            if (CauseType.member.name().equals(str))
            {
                list.add(Filter.eq("causeType", CauseType.member));
            }
            else if (CauseType.driver.name().equals(str))
            {
                list.add(Filter.eq("causeType", CauseType.driver));
            }
            else
            {
                result.put("code", "500");
                result.put("url", "causeList");
                result.put("errormsg", "参数错误");
                return result;
            }
            list.add(Filter.eq("causeStatus", true));
            causes = cancelCauseConfigService.findList(null, list, null);
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "causeList");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "causeList");
        result.put("data", causes);
        return result;
    }
}
