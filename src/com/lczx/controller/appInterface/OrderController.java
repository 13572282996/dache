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

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lczx.entity.Car;
import com.lczx.entity.Driver;
import com.lczx.entity.Member;
import com.lczx.entity.Order;
import com.lczx.entity.PriceConfig;
import com.lczx.entity.Order.OrderStatus;
import com.lczx.entity.Order.SettlementStatus;
import com.lczx.service.CarService;
import com.lczx.service.DriverService;
import com.lczx.service.MemberService;
import com.lczx.service.Order2DriverService;
import com.lczx.service.OrderService;
import com.lczx.service.PriceConfigService;
import com.lczx.util.Filter;
import com.lczx.util.RSAUtils;
import com.lczx.util.TimeTools;
import com.lczx.util.TruckUtil;
import com.lczxtech.codec.binary.Base64;

@Controller("faceOrderController")
@RequestMapping("/api/order")
public class OrderController extends BaseController
{
    
    private String priKeyString = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALLSPHV6cNkJTIpvmn1Bi0WvBIDtgUOmW8qaujGp8r+d1IzQwhJnTI0X9AAh3zqi39ykqxZhcXJYsEtZTWY+k0wTJLvpNUj9FOt9KKrfEj9VTJDp/nrg9365XnlRJZRsLXztCF38Y0syQyWKR2+Xj/JMV/6ONdbnTNrgYbLwueF/AgMBAAECgYEAqBOBLi6SqPzbi3cQr1CO9kVlp0UVbZ+MwWcjQRDrEH3pzR1efGvJgQiVmBY+Ol/iqAHCqXuXZRHFSl06qbKBQLk+gbAjxnz0SRVw8imB1mF36yRpjkgBMT0u3fJjV1kHpoEHFquUIPz0O79IXsVJr5Be8gRX5hjlm9elK6YE6+ECQQDpLgEH9gIeOg7eGlFPwPIxWjrmkGyvp7F2ZvdcRofwpyGggxNunR3r+fbiolJpprn4LxHH60nc4gFJ02L3J+BxAkEAxFJbDNhMjpN87r3LFcHV5/JM2jZ73nQOlSho4xMQtBDmaTxNXVVqkuuCXYDGQ5crpQkzgtqEqLiv5iuv7yDY7wJAdoYLwCArs3GPXRXDfuZ0NOHITqnalO6IJcbwtNalAo3xacU2e2MhcnD8LPaVLV6x6JUEVLokMUIvpXbtNvPuAQJBAK/BBtAuCAOQGEVnVhtpR4V5zsGAC06wWanA3n2DQO3jP1Mw8BXBdUKIYlIxCc3S1PjPjvTzidW+WYLW049hubMCQQCtDclRc2GEs0iVvr8S5wkpO/FEa53S4v9HeuAOe6CKgKKbYJI3Zb6E87WodXgiLSADPGab8TLFqA8yP1MeLU4T";
    
    @Resource(name = "memberServiceImpl")
    private MemberService memberService;
    
    @Resource(name = "orderServiceImpl")
    private OrderService orderService;
    
    @Resource(name = "order2driverServiceImpl")
    private Order2DriverService order2DriverService;
    
    @Resource(name = "driverServiceImpl")
    private DriverService driverService;
    
    @Resource(name = "priceConfigServiceImpl")
    private PriceConfigService priceConfigService;
    
    @Resource(name = "carServiceImpl")
    private CarService carService;
    
    /**
     * 新增订单接口
     * 实时
     *  {"startPoint":"成都市","endPoint":"成都市","memberMobile":"15881087682","orderType":"realTime","longitude":"","latitude":"","endLongitude":"","endLatitude":"","estAmount":"","type":"RSA"}  
     *  预约
     *  {"startPoint":"成都市","endPoint":"成都市","memberMobile":"15881087682","orderType":"appointment","appointment":"","longitude":"","latitude":"","endLongitude":"","endLatitude":"","estAmount":"","type":"RSA"}  
     * @throws Exception 
     * 
     **/
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    public @ResponseBody
    Object addOrder(String param, String token) throws Exception
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "addOrder");
            result.put("errormsg", "token不能为空");
            return result;
        }
        //  token = "a696613f-91e7-40e9-a0b8-1497ed530a59";
        
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "addOrder");
            result.put("errormsg", "参数错误");
            return result;
        }
        Member member = memberService.findMemberByToken(token);
        if (member == null)
        {
            result.put("code", "2000");
            result.put("url", "addOrder");
            result.put("errormsg", "token不存在");
            return result;
        }
        try
        {
            byte[] buffer = Base64.decodeBase64(priKeyString);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
            
            String str = RSAUtils.decrypt(privateKey, param);
            JSONObject jsonObject = JSONObject.fromObject(str);
            String startPoint = jsonObject.getString("startPoint");
            String endPoint = jsonObject.getString("endPoint");
            String memberMobile = jsonObject.getString("memberMobile");
            String orderType = jsonObject.getString("orderType");
            String longitude = jsonObject.getString("longitude");
            String latitude = jsonObject.getString("latitude");
            String endLongitude = jsonObject.getString("endLongitude");
            String endLatitude = jsonObject.getString("endLatitude");
            
            String estAmount = jsonObject.getString("estAmount");
            if (estAmount == null || estAmount == "")
            {
                estAmount = "0";
            }
            //  String appointment = jsonObject.getString("appointment");
            if (startPoint == null || endPoint == null || memberMobile == null
                    || orderType == null || longitude == null
                    || latitude == null || endLongitude == null
                    || endLatitude == null)
            {
                result.put("code", "500");
                result.put("url", "addOrder");
                result.put("errormsg", "参数错误");
                return result;
            }
            //            if (Order.OrderType.appointment.equals(orderType)
            //                    && appointment == null)
            //            {
            //                result.put("code", "500");
            //                result.put("url", "addOrder");
            //                result.put("errormsg", "参数错误,预约订单预约时间不能为空");
            //                return result;
            //            }
            
            Order order = new Order();
            order.setStartPoint(startPoint);
            order.setEndPoint(endPoint);
            order.setMemberMobile(memberMobile);
            order.setLongitude(longitude);
            order.setLatitude(latitude);
            order.setEndLongitude(endLongitude);
            order.setEndLatitude(endLatitude);
            order.setMember(member);
            order.setMemberName(member.getName());
            order.setEstAmount(new BigDecimal(estAmount));
            order.setSettlementStatus(SettlementStatus.no);//结算状态
            
            //            if (Order.OrderType.realTime.equals(orderType))
            //            {
            //                order.setOrderType(Order.OrderType.realTime);
            //            }
            //            else
            //            {
            //                order.setOrderType(Order.OrderType.appointment);
            //              order.setAppointment(TimeTools.getDate(appointment));
            //            }
            
            orderService.save(order);
            order2DriverService.addOrder2Driver(order);
            result.put("code", "200");
            result.put("url", "addOrder");
            result.put("data", order);
            return result;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            result.put("code", "500");
            result.put("url", "addOrder");
            result.put("errormsg", "系统错误");
            return result;
        }
    }
    
    /**
     * 取消订单 
     * {"id":"1","type":"RSA"}  
     **/
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    public @ResponseBody
    Object cancelOrder(String param, String token)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        
        if (token == null)
        {
            result.put("code", "500");
            result.put("url", "cancelOrder");
            result.put("errormsg", "token不能为空");
            return result;
        }
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "cancelOrder");
            result.put("errormsg", "参数错误");
            return result;
        }
        Member member = memberService.findMemberByToken(token);
        if (member == null)
        {
            result.put("code", "500");
            result.put("url", "cancelOrder");
            result.put("errormsg", "token不存在");
            return result;
        }
        
        String string;
        try
        {
            string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String id = jsonObject.getString("id");
            Order order = orderService.find(Long.valueOf(id));
            if (order == null)
            {
                result.put("code", "500");
                result.put("url", "cancelOrder");
                result.put("errormsg", "订单信息不存在");
                return result;
            }
            
            orderService.cancelOrder(String.valueOf(order.getId()));
            
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "cancelOrder");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "cancelOrder");
        result.put("errormsg", "订单取消成功");
        return result;
    }
    
    /**
     * 查询订单列表
     *  {"mobile":"13111111111","type":"RSA"}  
     **/
    @RequestMapping(value = "/queryOrder", method = RequestMethod.POST)
    public @ResponseBody
    Object queryOrder(String token)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<Order> list = new ArrayList<Order>();
        List<Order> unlist = new ArrayList<Order>();
        if (token == null)
        {
            result.put("code", "500");
            result.put("url", "queryOrder");
            result.put("errormsg", "token不能为空");
            return result;
        }
        Member member = memberService.findMemberByToken(token);
        if (member == null)
        {
            result.put("code", "500");
            result.put("url", "queryOrder");
            result.put("errormsg", "token不存在");
            return result;
        }
        try
        {
            //已完成
            List<Filter> filters = new ArrayList<Filter>();
            filters.add(Filter.eq("member", member.getId()));
            filters.add(Filter.ne("orderStatus", OrderStatus.confirmed));
            filters.add(Filter.ne("orderStatus", OrderStatus.unconfirmed));
            filters.add(Filter.ne("orderStatus", OrderStatus.unreceive));
            list = orderService.findList(null, filters, null);
            //未完成
            List<Filter> filter1 = new ArrayList<Filter>();
            filter1.add(Filter.eq("member", member.getId()));
            filter1.add(Filter.ne("orderStatus", OrderStatus.cancelled));
            filter1.add(Filter.ne("orderStatus", OrderStatus.completed));
            unlist = orderService.findList(null, filter1, null);
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "queryOrder");
            result.put("errormsg", "系统错误");
            return result;
        }
        
        result.put("code", "200");
        result.put("url", "queryOrder");
        result.put("data", list);
        result.put("unOrder", unlist);
        return result;
    }
    
    /**
     * 查询订单详情
     * {"id":"1","type":"RSA"}  
     * */
    @RequestMapping(value = "/findOrder", method = RequestMethod.POST)
    public @ResponseBody
    Object findOrder(String param, String token)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Order order;
        Driver driver = null;
        Car car = null;
        long orderCount = 0L;
        if (token == null)
        {
            result.put("code", "500");
            result.put("url", "queryOrder");
            result.put("errormsg", "token不能为空");
            return result;
        }
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "queryOrder");
            result.put("errormsg", "参数错误");
            return result;
        }
        Member member = memberService.findMemberByToken(token);
        if (member == null)
        {
            result.put("code", "500");
            result.put("url", "queryOrder");
            result.put("errormsg", "token不存在");
            return result;
        }
        try
        {
            String string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String id = jsonObject.getString("id");
            order = orderService.find(Long.valueOf(id));
            if (order.getDirverMobile() != null)
            {
                driver = driverService.findDriverByMobile(order.getDirverMobile());
            }
            if (driver != null)
            {
                orderCount = orderService.count(Filter.eq("dirverMobile",
                        driver.getDriverPhone()));
                car = carService.findCarByDriver(driver.getDriverPhone());
            }
            
        }
        
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "queryOrder");
            result.put("errormsg", "token不存在");
            return result;
        }
        result.put("code", "200");
        result.put("url", "findOrder");
        result.put("data", order);
        result.put("car", car);
        result.put("driver", driver);
        result.put("orderCount", orderCount);
        return result;
    }
    
    /**
     * 计算订单金额 
     * {"orderId":""}
     * 
     ***/
    @RequestMapping(value = "/reckon", method = RequestMethod.POST)
    public @ResponseBody
    Object reckon(String param, String token)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        HashMap<String, Double> res = new HashMap<String, Double>();
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
            Driver driver = driverService.findDriverByToken(token);
            if (driver == null)
            {
                result.put("code", "2000");
                result.put("url", "calculation");
                result.put("errormsg", "token不存在");
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
                result.put("errormsg", "操作失败，订单不存在");
                return result;
            }
            if (order.getOrderStatus() != Order.OrderStatus.completed)
            {
                result.put("code", "500");
                result.put("url", "calculation");
                result.put("errormsg", "订单状态异常，操作失败");
                return result;
            }
            
            //计算已完成里程
            String string2 = TruckUtil.getdistance(order.getStartDate(),
                    order.getCompleteDate(),
                    driver.getDriverPhone());
            JSONObject json = JSONObject.fromObject(string2);
            //已完成里程
            double mil = 0;
            //行程所用时间
            
            if (json.getString("status").equals("0"))
            {
                mil = json.getDouble("distance");
            }
            
            res.put("amount",
                    orderPrice(order, order.getCompleteDate()).doubleValue());
            
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
        return result;
    }
    
    /**
     * 订单评分 
     * 
     **/
    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public @ResponseBody
    Object score(String param, String token)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "score");
            result.put("errormsg", "token不能为空");
            return result;
        }
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "score");
            result.put("errormsg", "参数错误");
            return result;
        }
        try
        {
            Member member = memberService.findMemberByToken(token);
            if (member == null)
            {
                result.put("code", "2000");
                result.put("url", "score");
                result.put("errormsg", "登录已过期，请重新登录");
                return result;
            }
            String string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String id = jsonObject.getString("orderId");
            String scoreStr = jsonObject.getString("score");
            
            Order order = orderService.find(Long.valueOf(id));
            if (order == null)
            {
                result.put("code", "500");
                result.put("url", "score");
                result.put("errormsg", "操作失败，订单不存在");
                return result;
            }
            if (order.getOrderStatus() != Order.OrderStatus.completed)
            {
                result.put("code", "500");
                result.put("url", "score");
                result.put("errormsg", "订单状态异常，操作失败");
                return result;
            }
            double score = Double.valueOf(scoreStr);
            if (score > 0 && score <= 5)
            {
                order.setScore(score);
                orderService.update(order);
            }
            else
            {
                result.put("code", "500");
                result.put("url", "score");
                result.put("errormsg", "评分分数异常，操作失败");
                return result;
            }
            
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "score");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "score");
        result.put("errormsg", "评分成功");
        return result;
    }
    
    /**
     * 订算订单金额 
     * 
     **/
    private BigDecimal orderPrice(Order order, Date date)
    {
        PriceConfig config = priceConfigService.find(1L);
        //计算已完成里程
        String string2 = TruckUtil.getdistance(order.getStartDate(),
                order.getCompleteDate(),
                order.getDirverMobile());
        JSONObject json = JSONObject.fromObject(string2);
        //已完成里程
        double mil = 0;
        BigDecimal amount = new BigDecimal("0");
        BigDecimal milAmount = new BigDecimal("0");
        BigDecimal timeAmount = new BigDecimal("0");
        BigDecimal haulAmount = new BigDecimal("0");
        //行程所用时间
        long time = (date.getTime() / 1000 / 60)
                - (order.getStartDate().getTime() / 1000 / 60);
        
        if (json.getString("status").equals("0"))
        {
            mil = json.getDouble("distance");
        }
        int a = config.getMiles()
                .compareTo(new BigDecimal(mil).divide(new BigDecimal("1000"), 2));
        int b = config.getBaseTime().compareTo(new BigDecimal(time));
        if (a == 1 && b == 1)
        {
            
            amount = config.getBasePrice();
        }
        else
        {
            if (a != 1)
            {
                milAmount = (new BigDecimal(mil).divide(new BigDecimal("1000"),
                        2).subtract(config.getMiles())).multiply(config.getMilPrice());
                System.out.println("里程价格:"+milAmount);
            }
            if (b != 1)
            {
                timeAmount = (new BigDecimal(time).subtract(config.getBaseTime())).multiply(config.getTimePrice());
                System.out.println("时间价格:"+timeAmount);
            }
            
            if (config.getHaulbackMil().compareTo(new BigDecimal(mil).divide(new BigDecimal("1000"))) != 1)
            {
                haulAmount = (new BigDecimal(mil).divide(new BigDecimal("1000")).subtract(config.getHaulbackMil())).multiply(config.getHaulbackPrice());
                System.out.println("返空价格:"+haulAmount);
            }
            //总价＝里程价+时间价+基础价+回空里程价
            amount = amount.add(milAmount)
                    .add(timeAmount)
                    .add(config.getBasePrice())
                    .add(haulAmount);
        }
        return amount;
    }
    
}
