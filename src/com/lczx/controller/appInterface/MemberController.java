package com.lczx.controller.appInterface;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lczx.entity.Driver;
import com.lczx.entity.Member;
import com.lczx.entity.Message.MessageType;
import com.lczx.entity.Message.Status;
import com.lczx.entity.Order;
import com.lczx.entity.PriceConfig;
import com.lczx.service.CodeService;
import com.lczx.service.MemberService;
import com.lczx.service.MessageService;
import com.lczx.service.OrderService;
import com.lczx.service.PriceConfigService;
import com.lczx.util.Filter;
import com.lczx.util.JsonUtils;
import com.lczx.util.Message;
import com.lczx.util.RSAUtils;
import com.lczx.util.StringUtil;
import com.lczx.util.TruckUtil;
import com.lczxtech.codec.binary.Base64;
import com.sun.jna.platform.unix.X11.XClientMessageEvent.Data;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Controller("faceMemberController")
@RequestMapping("/member")
public class MemberController extends BaseController
{
    
    @Resource(name = "memberServiceImpl")
    private MemberService memberService;
    
    @Resource(name = "codeServiceImpl")
    private CodeService codeService;
    
    @Resource(name = "orderServiceImpl")
    private OrderService orderService;
    
    @Resource(name = "priceConfigServiceImpl")
    private PriceConfigService priceConfigService;
    
    @Resource(name = "messageServiceImpl")
    private MessageService messageService;
    
    private String priKeyString = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALLSPHV6cNkJTIpvmn1Bi0WvBIDtgUOmW8qaujGp8r+d1IzQwhJnTI0X9AAh3zqi39ykqxZhcXJYsEtZTWY+k0wTJLvpNUj9FOt9KKrfEj9VTJDp/nrg9365XnlRJZRsLXztCF38Y0syQyWKR2+Xj/JMV/6ONdbnTNrgYbLwueF/AgMBAAECgYEAqBOBLi6SqPzbi3cQr1CO9kVlp0UVbZ+MwWcjQRDrEH3pzR1efGvJgQiVmBY+Ol/iqAHCqXuXZRHFSl06qbKBQLk+gbAjxnz0SRVw8imB1mF36yRpjkgBMT0u3fJjV1kHpoEHFquUIPz0O79IXsVJr5Be8gRX5hjlm9elK6YE6+ECQQDpLgEH9gIeOg7eGlFPwPIxWjrmkGyvp7F2ZvdcRofwpyGggxNunR3r+fbiolJpprn4LxHH60nc4gFJ02L3J+BxAkEAxFJbDNhMjpN87r3LFcHV5/JM2jZ73nQOlSho4xMQtBDmaTxNXVVqkuuCXYDGQ5crpQkzgtqEqLiv5iuv7yDY7wJAdoYLwCArs3GPXRXDfuZ0NOHITqnalO6IJcbwtNalAo3xacU2e2MhcnD8LPaVLV6x6JUEVLokMUIvpXbtNvPuAQJBAK/BBtAuCAOQGEVnVhtpR4V5zsGAC06wWanA3n2DQO3jP1Mw8BXBdUKIYlIxCc3S1PjPjvTzidW+WYLW049hubMCQQCtDclRc2GEs0iVvr8S5wkpO/FEa53S4v9HeuAOe6CKgKKbYJI3Zb6E87WodXgiLSADPGab8TLFqA8yP1MeLU4T";
    
    private String str = "YZh6mX6ZwSruFooqsx7yELRekPTks9tkZUSSMKDGL/WFXps+xuTQY2ewT5rTx6DFS5xiX00R0hjVjfz4B6i08ZNDAE0Hsrhsz93NstkBUMEu0tviLQdGWh2KDltU7DBcUqVGPlLEVp88Q4fvs/Eg7ZvKqS4z8hIpEBbNLwOhoxE=";
    
    /**
     * 检查手机号码是否被禁用或已存在
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeySpecException 
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "/check_mobile", method = RequestMethod.POST)
    public @ResponseBody
    Object checkUsername(String param) throws NoSuchAlgorithmException,
            InvalidKeySpecException, UnsupportedEncodingException
    {
        
        byte[] buffer = Base64.decodeBase64(priKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        String mob;
        JSONObject result = new JSONObject();
        HashMap<String, Object> map = new HashMap<String, Object>();
        try
        {
            String model = RSAUtils.decrypt(privateKey, param);
            System.out.println(model);
            JSONObject jsonObject = JSONObject.fromObject(model);
            
            map.put("code", "200");
            map.put("url", "check_mobile");
            map.put("data", "true");
            mob = jsonObject.getString("mobile");
            if (StringUtils.isEmpty(mob))
            {
                result.put("code", "200");
                result.put("url", "check_mobile");
                result.put("data", "false");
                return map;
            }
            if (memberService.mobileExists(mob))
            {
                result.put("code", "200");
                result.put("url", "check_mobile");
                result.put("data", "false");
                return map;
            }
            else
            {
                result.put("code", "200");
                result.put("url", "check_mobile");
                result.put("data", "true");
                return map;
            }
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "check_mobile");
            result.put("data", "true");
            
            return map;
        }
        
    }
    
    /**
     * 乘客注册
     * @throws Exception 
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    Object register(String param) throws Exception
    {
        byte[] buffer = Base64.decodeBase64(priKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        String mobilePhone = "";
        String passWord = "";
        String code = "";
        JSONObject result = new JSONObject();
        
        String str;
        try
        {
            str = RSAUtils.decrypt(privateKey, param);
            JSONObject jsonObject = JSONObject.fromObject(str);
            mobilePhone = jsonObject.getString("mobile");
            passWord = jsonObject.getString("passWord");
            code = jsonObject.getString("code");
            if (mobilePhone == null || passWord == null || code == null)
            {
                result.put("code", "500");
                result.put("url", "register");
                result.put("errormsg", "参数错误");
                return result;
            }
            if (!code.equals(codeService.queryCodes(mobilePhone)
                    .get(0)
                    .getCode()))
            {
                result.put("code", "500");
                result.put("url", "register");
                result.put("errormsg", "验证码不正确");
                return result.toString();
            }
            if (memberService.mobileExists(mobilePhone))
            {
                result.put("code", "500");
                result.put("url", "register");
                result.put("data", "手机号码已存在");
                return result;
            }
            Member member = new Member();
            member.setMobilePhone(mobilePhone);
            member.setPassWord(passWord);
            member.setName(mobilePhone);
            try
            {
                memberService.save(member);
            }
            catch (Exception e)
            {
                result.put("code", "500");
                result.put("url", "register");
                result.put("errormsg", "系统错误");
            }
            result.put("code", "200");
            result.put("url", "register");
            result.put("data", "注册成功");
            return result;
        }
        catch (UnsupportedEncodingException e)
        {
            result.put("code", "500");
            result.put("url", "register");
            result.put("errormsg", "系统错误");
            return result;
        }
        
    }
    
    /**
     * 用户登录
     * {"mobile":"13111111111","passWord":"1111111","type":"RSA"}
     * 
     * */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    Object login(String param) throws NoSuchAlgorithmException,
            InvalidKeySpecException
    {
        byte[] buffer = Base64.decodeBase64(priKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        String mobilePhone;
        String passWord;
        JSONObject result = new JSONObject();
        
        try
        {
            String str = RSAUtils.decrypt(privateKey, param);
            JSONObject jsonObject = JSONObject.fromObject(str);
            mobilePhone = jsonObject.getString("mobile");
            passWord = jsonObject.getString("passWord");
            if (mobilePhone == null || passWord == null)
            {
                result.put("code", "500");
                result.put("url", "login");
                result.put("errormsg", "参数错误");
                return result;
            }
            Member member = memberService.findMemberByMobile(mobilePhone);
            if (member == null)
            {
                result.put("code", "500");
                result.put("url", "login");
                result.put("errormsg", "用户不存在");
                return result;
            }
            if (!passWord.equals(member.getPassWord()))
            {
                result.put("code", "500");
                result.put("url", "login");
                result.put("errormsg", "密码不正确");
                return result;
            }
            else
            {
                result.put("code", "200");
                result.put("url", "login");
                result.put("data", "登录成功");
                String string = UUID.randomUUID().toString();
                result.put("token", string);
                member.setToken(string);
                memberService.update(member);
                return result;
            }
            
        }
        catch (UnsupportedEncodingException e)
        {
            result.put("code", "500");
            result.put("url", "login");
            result.put("errormsg", "系统错误");
            return result;
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "login");
            result.put("errormsg", "系统错误");
            return result;
        }
        
    }
    
    /**
     * {"mobile":"13111111111","type":"RSA"} 
     * 根据用户手机号查询用户信息
     * @throws Exception 
     **/
    
    @RequestMapping(value = "/fingMember", method = RequestMethod.POST)
    public @ResponseBody
    Object fingMember(String param) throws Exception
    {
        
        byte[] buffer = Base64.decodeBase64(priKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        String mobilePhone;
        JSONObject result = new JSONObject();
        String str = RSAUtils.decrypt(privateKey, param);
        JSONObject jsonObject = JSONObject.fromObject(str);
        mobilePhone = jsonObject.getString("mobile");
        if (mobilePhone == null || "".equals(mobilePhone))
        {
            result.put("code", "500");
            result.put("url", "fingMember");
            result.put("errormsg", "参数错误");
            return result;
        }
        //   Member member = memberService.findMemberByMobile("15881087682");
        Member member = memberService.findMemberByMobile(mobilePhone);
        if (member == null)
        {
            result.put("code", "500");
            result.put("url", "fingMember");
            result.put("errormsg", "用户不存在");
            return result;
        }
        result.put("code", "200");
        result.put("url", "fingMember");
        result.put("data", member);
        return result;
    }
    
    /**
     * 修改密码
     *   {"mobile":"13111111111","passWord":"1111111","type":"RSA"}
     * @throws Exception 
     * 
     **/
    @RequestMapping(value = "/updatePassWord", method = RequestMethod.POST)
    public @ResponseBody
    Object updatePassWord(String param) throws Exception
    {
        byte[] buffer = Base64.decodeBase64(priKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        String mobilePhone;
        String passWord;
        JSONObject result = new JSONObject();
        String str = RSAUtils.decrypt(privateKey, param);
        JSONObject jsonObject = JSONObject.fromObject(str);
        mobilePhone = jsonObject.getString("mobile");
        passWord = jsonObject.getString("passWord");
        if (mobilePhone == null || passWord == null)
        {
            result.put("code", "500");
            result.put("url", "updatePassWord");
            result.put("errormsg", "参数错误");
            return result;
        }
        Member member = memberService.findMemberByMobile(mobilePhone);
        if (member == null)
        {
            result.put("code", "500");
            result.put("url", "updatePassWord");
            result.put("errormsg", "用户不存在");
            return result;
        }
        member.setPassWord(passWord);
        memberService.update(member);
        result.put("code", "200");
        result.put("url", "updatePassWord");
        result.put("errormsg", "修改密码成功");
        return result;
        
    }
    
    /**
     *   String token
     * @throws Exception 
     * 退出登录
     **/
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public @ResponseBody
    Object logout(String token) throws Exception
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        if (token == null)
        {
            result.put("code", "500");
            result.put("url", "logout");
            result.put("errormsg", "token不能为空");
            return result;
        }
        try
        {
            Member member = memberService.findMemberByToken(token);
            member.setToken(null);
            memberService.update(member);
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "logout");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "logout");
        result.put("data", "退出登录成功");
        return result;
        
    }
    
    /**
     *  乘客端
     *  根据司机当前坐标计算实时价格，剩余里程，时间
     *   {"orderId":""}
     *   String token
     * 
     **/
    @RequestMapping(value = "/calculation", method = RequestMethod.POST)
    public @ResponseBody
    Object calculation(String param, String token)
    {
        System.out.println("----------------------乘客端价格轮询开始-------------------------------------");
        HashMap<String, Object> result = new HashMap<String, Object>();
        HashMap<String, String> res = new HashMap<String, String>();
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "calculation");
            result.put("errormsg", "token不能为空");
            return result;
        }
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "calculation");
            result.put("errormsg", "参数错误");
            return result;
        }
        try
        {
            Member member = memberService.findMemberByToken(token);
            if (member == null)
            {
                result.put("code", "2000");
                result.put("url", "calculation");
                result.put("errormsg", "登录过期，请重新登录");
                return result;
            }
            String string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String id = jsonObject.getString("orderId");
            
            Order order = orderService.find(Long.valueOf(id));
            if (order == null)
            {
                result.put("code", "500");
                result.put("url", "calculation");
                result.put("errormsg", "订单不存在");
                return result;
            }
            //获取司机实时位置
            HashMap<String, String> map = TruckUtil.getpointMap(order.getDirverMobile());
            String longitude = map.get("longitude");
            String latitude = map.get("latitude");
            System.out.println("司机当前实时位置坐标："+latitude+"|"+longitude);
            if (longitude == "" || latitude == "")
            {
                result.put("code", "500");
                result.put("url", "calculation");
                result.put("errormsg", "查询司机实时位置失败");
                return result;
            }
            
            //计算剩余里乘，时间
            HashMap<String, Double> milMap = new HashMap<String, Double>();
            if (order.getStartDate() != null
                    && order.getOrderStatus()
                            .equals(Order.OrderStatus.confirmed))
            {
                System.out.println("行程中订单-------------------------开始");
                milMap = TruckUtil.routematrix(latitude + "," + longitude,
                        order.getEndLatitude() + "," + order.getEndLongitude());
                String string2 = TruckUtil.getdistance(order.getStartDate(),
                        order.getDirverMobile());
                System.out.println("百度返回已完成里程数据："+string2);
                JSONObject json = JSONObject.fromObject(string2);
                //已完成里程
                double mil = 0;
                //已完成里程所用时间
                long time = (System.currentTimeMillis() / 1000 / 60)
                        - (order.getStartDate().getTime() / 1000 / 60);
                System.out.println("已完成里程用时："+time);
                
                if (json.getString("status").equals("0"))
                {
                    mil = json.getDouble("distance");
                }
                System.out.println("已完成里程："+mil);
                res.put("amount",
                        amount(new BigDecimal(mil).divide(new BigDecimal("1000"),
                                2),
                                new BigDecimal(time)).toString());
            }
            else if (order.getOrderStatus().equals(Order.OrderStatus.unreceive))
            {
                milMap = TruckUtil.routematrix(latitude + "," + longitude,
                        order.getLatitude() + "," + order.getLongitude());
            }
            else
            {
                milMap = TruckUtil.routematrix(order.getLatitude() + ","
                        + order.getLongitude(), order.getEndLatitude() + ","
                        + order.getEndLongitude());
            }
            //计算已完成里程
            
            res.put("longitude", map.get("longitude"));
            res.put("latitude", map.get("latitude"));
            res.put("mil", milMap.get("mil").toString());
            res.put("time", milMap.get("time").toString());
            res.put("orderStatus", order.getOrderStatus().name());
        }
        
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "calculation");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "calculation");
        result.put("data", res);
        System.out.println("----------------------乘客端价格轮询结束-------------------------------------");
        return result;
    }
    
    /**
     *   String token
     * @throws Exception 
     * 订单检测
     **/
    @RequestMapping(value = "/orderTest", method = RequestMethod.POST)
    public @ResponseBody
    Object orderTest(String token) throws Exception
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Member member = null;
        List<Order> list = new ArrayList<Order>();
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "member/orderTest");
            result.put("errormsg", "token不能为空");
            return result;
        }
        try
        {
            member = memberService.findMemberByToken(token);
            if (member == null)
            {
                result.put("code", "2000");
                result.put("url", "member/orderTest");
                result.put("errormsg", "token不存在");
                return result;
            }
            List<Filter> filters = new ArrayList<Filter>();
            filters.add(Filter.ne("orderStatus", Order.OrderStatus.unconfirmed));
            filters.add(Filter.ne("orderStatus", Order.OrderStatus.completed));
            filters.add(Filter.ne("orderStatus", Order.OrderStatus.cancelled));
            filters.add(Filter.eq("memberMobile", member.getMobilePhone()));
            list = orderService.findList(null, filters, null);
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "member/orderTest");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "member/orderTest");
        result.put("data", list);
        return result;
    }
    
    /**
     * 系统消息 
     * 
     **/
    @RequestMapping(value = "/messageList", method = RequestMethod.POST)
    public @ResponseBody
    Object messageList(String token) throws Exception
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Member member = null;
        List<com.lczx.entity.Message> list = null;
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "member/messageList");
            result.put("errormsg", "token不能为空");
            return result;
        }
        try
        {
            member = memberService.findMemberByToken(token);
            if (member == null)
            {
                result.put("code", "2000");
                result.put("url", "member/messageList");
                result.put("errormsg", "token不存在");
                return result;
            }
            List<Filter> filters = new ArrayList<Filter>();
            filters.add(Filter.eq("messageType", MessageType.member));
            filters.add(Filter.eq("status", Status.put));
            list = messageService.findList(null, filters, null);
            
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "member/messageList");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "member/messageList");
        result.put("data", list);
        return result;
    }
    
    /**
     * 计算价格 
     * 
     **/
    private BigDecimal amount(BigDecimal mil, BigDecimal time)
    {
        System.out.println("价格计算参数时间："+time+"价格计算参数里程："+mil);
        BigDecimal amount = new BigDecimal("0");
        BigDecimal milAmount = new BigDecimal("0");
        BigDecimal timeAmount = new BigDecimal("0");
        BigDecimal haulAmount = new BigDecimal("0");
        PriceConfig config = priceConfigService.find(1L);
        int a = config.getMiles().compareTo(mil);
        System.out.println("配制里程："+config.getMiles());
        System.out.println("配制时间："+config.getBaseTime());
        int b = config.getBaseTime().compareTo(time);
        if (a == 1 && b == 1)
        {
            System.out.println("起步价");
            amount = config.getBasePrice();
        }
        else
        {
            System.out.println("非起步价");
            if(a != 1)
            {
                milAmount = (mil.subtract(config.getMiles())).multiply(config.getMilPrice());
            }
            if(b!=1)
            {
                timeAmount = (time.subtract(config.getBaseTime())).multiply(config.getTimePrice());
            }
            if (config.getHaulbackMil().compareTo(mil.divide(new BigDecimal("1000"))) != 1)
            {
                haulAmount = (mil.divide(new BigDecimal("1000")).subtract(config.getHaulbackMil())).multiply(config.getHaulbackPrice());
                System.out.println("返空价格:"+haulAmount);
            }
            System.out.println(milAmount);
            System.out.println(timeAmount);
            System.out.println(haulAmount);
            System.out.println(config.getBasePrice());
            //总价＝里程价+时间价+基础价+回空里程价
            amount = amount.add(milAmount)
                    .add(timeAmount)
                    .add(config.getBasePrice())
                    .add(haulAmount);
            System.out.println("总价："+amount);
        }
        return amount;
    }
}
