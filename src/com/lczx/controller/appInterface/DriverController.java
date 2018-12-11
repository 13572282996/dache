package com.lczx.controller.appInterface;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lczx.entity.Car;
import com.lczx.entity.Code;
import com.lczx.entity.Driver;
import com.lczx.entity.InCome;
import com.lczx.entity.Member;
import com.lczx.entity.Order;
import com.lczx.entity.Order2Driver;
import com.lczx.entity.Message.MessageType;
import com.lczx.entity.Message.Status;
import com.lczx.entity.PriceConfig;
import com.lczx.service.CarService;
import com.lczx.service.CodeService;
import com.lczx.service.DriverService;
import com.lczx.service.InComeService;
import com.lczx.service.MessageService;
import com.lczx.service.Order2DriverService;
import com.lczx.service.OrderService;
import com.lczx.service.PriceConfigService;
import com.lczx.util.Filter;
import com.lczx.util.RSAUtils;
import com.lczx.util.SettingUtils;
import com.lczx.util.TruckUtil;

@Controller("faceDriverController")
@RequestMapping("/api/driver")
public class DriverController extends BaseController
{
    @Resource(name = "driverServiceImpl")
    private DriverService driverService;
    
    @Resource(name = "codeServiceImpl")
    private CodeService codeService;
    
    @Resource(name = "order2driverServiceImpl")
    private Order2DriverService order2DriverService;
    
    @Resource(name = "orderServiceImpl")
    private OrderService orderService;
    
    @Resource(name = "carServiceImpl")
    private CarService carService;
    
    @Resource(name = "priceConfigServiceImpl")
    private PriceConfigService priceConfigService;
    
    @Resource(name = "messageServiceImpl")
    private MessageService messageService;
    
    @Resource(name = "inComeServiceImpl")
    private InComeService inComeService;
    
    /**
     *  {"mobile":"13511111111","code":"12345"}
     * 
     **/
    @RequestMapping(value = "/activation", method = RequestMethod.POST)
    public @ResponseBody
    Object activation(String param)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        String string = "";
        String res = "";
        Driver driver;
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "activation");
            result.put("errormsg", "参数错误");
            return result;
        }
        try
        {
            string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String mobile = jsonObject.getString("mobile");
            String code = jsonObject.getString("code");
            if ("".equals(mobile) || "".equals(code))
            {
                result.put("code", "500");
                result.put("url", "activation");
                result.put("errormsg", "手机号码或验证码不能为空");
                return result;
            }
            driver = driverService.findDriverByMobile(mobile);
            
            if (driver == null)
            {
                result.put("code", "500");
                result.put("url", "activation");
                result.put("errormsg", "司机信息不存在！");
                return result;
            }
            List<Code> list = codeService.queryCodes(mobile);
            if (list == null || list.size() == 0)
            {
                result.put("code", "500");
                result.put("url", "activation");
                result.put("errormsg", "验码错误");
                return result;
            }
            if (!code.equals(list.get(0).getCode()))
            {
                result.put("code", "500");
                result.put("url", "activation");
                result.put("errormsg", "验码错误");
                return result;
            }
            
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "activation");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "activation");
        result.put("data", driver);
        return result;
        
    }
    
    /**
     * 修改密码
     *  {"mobile":"","passWord":""}
     * 
     **/
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public @ResponseBody
    Object updatePassword(String param)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        String string = "";
        String res = "";
        Driver driver;
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "updatePassword");
            result.put("errormsg", "参数错误");
            return result;
        }
        try
        {
            string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String mobile = jsonObject.getString("mobile");
            String passWord = jsonObject.getString("passWord");
            if (mobile == null)
            {
                result.put("code", "500");
                result.put("url", "updatePassword");
                result.put("errormsg", "mobile不能为空");
                return result;
            }
            driver = driverService.findDriverByMobile(mobile);
            if (driver == null)
            {
                result.put("code", "500");
                result.put("url", "updatePassword");
                result.put("errormsg", "司机信息不存在");
                return result;
            }
            driver.setPassWord(passWord);
            driverService.update(driver);
            
        }
        catch (Exception e)
        {
            
            result.put("code", "500");
            result.put("url", "receive");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "updatePassword");
        result.put("data", "密码设置成功");
        return result;
    }
    
    /**
     * 听单
     * {"longitude":"20.11","latitude":"30.01"}
     * String token
     * 
     **/
    @RequestMapping(value = "/receive", method = RequestMethod.POST)
    public @ResponseBody
    Object receive(String param, String token)
    {
        
        HashMap<String, Object> result = new HashMap<String, Object>();
        String string = "";
        String res = "";
        Driver driver;
        List<Order2Driver> list;
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "receive");
            result.put("errormsg", "token不能为空");
            return result;
        }
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "receive");
            result.put("errormsg", "param参数不能为空");
            return result;
        }
        try
        {
            string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String longitude = jsonObject.getString("longitude");
            String latitude = jsonObject.getString("latitude");
            System.out.println(longitude + "|" + latitude);
            
            if (longitude == null || latitude == null)
            {
                result.put("code", "500");
                result.put("url", "receive");
                result.put("errormsg", "司机坐标不能为空");
                return result;
            }
            driver = driverService.findDriverByToken(token);
            
            if (driver == null)
            {
                result.put("code", "2000");
                result.put("url", "receive");
                result.put("errormsg", "登录过期，请重新登录");
                return result;
            }
            System.out.println(driver.getDriverPhone());
            res = TruckUtil.addpoint(driver.getDriverPhone(),
                    latitude,
                    longitude);
            list = order2DriverService.queryOrder2DriversByMobile(driver.getDriverPhone());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            result.put("code", "500");
            result.put("url", "receive");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "receive");
        result.put("data", list);
        return result;
    }
    
    /**
     * 
     * 司机登录
     * {"mobile":"","passWord":""} 
     * 
     **/
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    Object login(String param)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        String string = "";
        String res = "";
        Driver driver;
        String token = UUID.randomUUID().toString();
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "login");
            result.put("errormsg", "参数错误");
            return result;
        }
        
        try
        {
            string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String mobile = jsonObject.getString("mobile");
            String passWord = jsonObject.getString("passWord");
            if (mobile == null || passWord == null)
            {
                result.put("code", "500");
                result.put("url", "login");
                result.put("errormsg", "账号或密码不能为空");
                return result;
            }
            driver = driverService.findDriverByMobile(mobile);
            if (driver == null)
            {
                result.put("code", "500");
                result.put("url", "login");
                result.put("errormsg", "手机号码不正确");
                return result;
            }
            
            if (!passWord.equals(driver.getPassWord()))
            {
                result.put("code", "500");
                result.put("url", "login");
                result.put("errormsg", "密码错误");
                return result;
            }
            driver.setToken(token);
            driverService.update(driver);
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "login");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "login");
        result.put("token", token);
        result.put("data", "登录成功");
        
        return result;
    }
    
    /**
     * 抢单
     * {"orderId":""}
     * String token
     * 
     **/
    @RequestMapping(value = "/receipt", method = RequestMethod.POST)
    public @ResponseBody
    Object receipt(String param, String token)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        String string = "";
        Order order;
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "receive");
            result.put("errormsg", "token不能为空");
            return result;
        }
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "login");
            result.put("errormsg", "参数错误");
            return result;
        }
        try
        {
            Driver driver = driverService.findDriverByToken(token);
            if (driver == null)
            {
                result.put("code", "2000");
                result.put("url", "receive");
                result.put("errormsg", "登录过期，请重新登录");
                return result;
            }
            string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String orderId = jsonObject.getString("orderId");
            order = orderService.find(Long.valueOf(orderId));
            if (order == null)
            {
                result.put("code", "500");
                result.put("url", "receipt");
                result.put("errormsg", "订单不存在");
                return result;
            }
            if (!order.getOrderStatus().equals(Order.OrderStatus.unconfirmed))
            {
                result.put("code", "500");
                result.put("url", "receipt");
                result.put("errormsg", "订单已被抢");
                return result;
            }
            order = orderService.receiptOrder(driver, order.getId().toString());
            
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "receipt");
            result.put("errormsg", "参数错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "receipt");
        result.put("data", order);
        return result;
    }
    
    /**
     * 订单完成 
     * 
     **/
    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    public @ResponseBody
    Object complete(String param, String token)
    {
        
        HashMap<String, Object> result = new HashMap<String, Object>();
        String string = "";
        Order order;
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "receive");
            result.put("errormsg", "token不能为空");
            return result;
        }
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "login");
            result.put("errormsg", "参数错误");
            return result;
        }
        
        try
        {
            string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String orderId = jsonObject.getString("orderId");
            order = orderService.find(Long.valueOf(orderId));
            if (order == null)
            {
                result.put("code", "500");
                result.put("url", "receipt");
                result.put("errormsg", "订单不存在");
                return result;
            }
            if (order.getOrderStatus() != Order.OrderStatus.confirmed)
            {
                result.put("code", "500");
                result.put("url", "receipt");
                result.put("errormsg", "订单状态异常，操作失败");
                return result;
            }
            order.setOrderStatus(Order.OrderStatus.completed);
            order.setCompleteDate(new Date());
            order.setAmount(orderPrice(order, new Date()));
            orderService.update(order);
            InCome inCome = new InCome();
            inCome.setOrderId(order.getId());
            inCome.setDriver(order.getDirvers());
            inCome.setOrderAmount(order.getAmount());
            inCome.setFeeProportion(SettingUtils.get().getFeeProportion());
            inCome.setAmount(order.getAmount().subtract(order.getAmount()
                    .multiply(SettingUtils.get().getFeeProportion())));
            
        }
        
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "receipt");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "receipt");
        result.put("errormsg", "订单完成成功");
        return result;
    }
    
    /**
     *　司机取消订单 
     * 
     **/
    @RequestMapping(value = "/cancell", method = RequestMethod.POST)
    public @ResponseBody
    Object cancell(String param, String token)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        String string = "";
        Order order;
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "receive");
            result.put("errormsg", "token不能为空");
            return result;
        }
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "login");
            result.put("errormsg", "参数错误");
            return result;
        }
        try
        {
            string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String orderId = jsonObject.getString("orderId");
            order = orderService.find(Long.valueOf(orderId));
            if (order == null)
            {
                result.put("code", "500");
                result.put("url", "receipt");
                result.put("errormsg", "订单不存在");
                return result;
            }
            if (order.getOrderStatus() != Order.OrderStatus.unreceive)
            {
                result.put("code", "500");
                result.put("url", "receipt");
                result.put("errormsg", "订单状态异常，操作失败");
                return result;
            }
            order.setOrderStatus(Order.OrderStatus.cancelled);
            order.setCancellType(Order.CancellType.dirver);
            orderService.update(order);
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "receipt");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "receipt");
        result.put("errormsg", "订单取消成功");
        return result;
    }
    
    /**
     * 乘客上车 
     * 
     **/
    @RequestMapping(value = "/startTour", method = RequestMethod.POST)
    public @ResponseBody
    Object startTour(String param, String token)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        String string = "";
        Order order;
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "receive");
            result.put("errormsg", "token不能为空");
            return result;
        }
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "login");
            result.put("errormsg", "参数错误");
            return result;
        }
        try
        {
            string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String orderId = jsonObject.getString("orderId");
            order = orderService.find(Long.valueOf(orderId));
            if (order == null)
            {
                result.put("code", "500");
                result.put("url", "receipt");
                result.put("errormsg", "订单不存在");
                return result;
            }
            if (order.getOrderStatus() != Order.OrderStatus.unreceive)
            {
                result.put("code", "500");
                result.put("url", "receipt");
                result.put("errormsg", "订单状态异常，操作失败");
                return result;
            }
            order.setOrderStatus(Order.OrderStatus.confirmed);
            // order.setCancellType(Order.CancellType.dirver);
            order.setStartDate(new Date());
            orderService.update(order);
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "receipt");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "receipt");
        result.put("errormsg", "订单取消成功");
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
        Driver driver = driverService.findDriverByToken(token);
        if (driver == null)
        {
            result.put("code", "2000");
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
        }
        
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "queryOrder");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "findOrder");
        result.put("data", order);
        return result;
    }
    
    /**
     *  根据司机当前坐标计算实时价格，剩余里程，时间
     *   {"longitude":"20.11","latitude":"30.01","orderId":""}
     *   String token
     * 
     **/
    
    @RequestMapping(value = "/calculation", method = RequestMethod.POST)
    public @ResponseBody
    Object calculation(String param, String token)
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
            String latitude = jsonObject.getString("latitude");
            String longitude = jsonObject.getString("longitude");
            Order order = orderService.find(Long.valueOf(id));
            if (order == null)
            {
                result.put("code", "500");
                result.put("url", "calculation");
                result.put("errormsg", "操作失败，订单不存在");
                return result;
            }
            //上传坐标
            TruckUtil.addpoint(driver.getDriverPhone(), latitude, longitude);
            //行程中计算剩余里乘，时间
            if (order.getOrderStatus().equals(Order.OrderStatus.confirmed))
            {
                res = TruckUtil.routematrix(latitude + "," + longitude,
                        order.getEndLatitude() + "," + order.getEndLongitude());
                //计算已完成里程
                String string2 = TruckUtil.getdistance(order.getStartDate(),
                        driver.getDriverPhone());
                JSONObject json = JSONObject.fromObject(string2);
                //已完成里程
                double mil = 0;
                //已完成里程所用时间
                long time = (System.currentTimeMillis() / 1000 / 60)
                        - (order.getStartDate().getTime() / 1000 / 60);
                
                if (json.getString("status").equals("0"))
                {
                    mil = json.getDouble("distance");
                }
                res.put("amount",
                        amount(new BigDecimal(mil).divide(new BigDecimal("1000"),
                                2),
                                new BigDecimal(time)).doubleValue());
            }
            //待接驾计算司机到乘客间的距离和时间
            else if (order.getOrderStatus().equals(Order.OrderStatus.unreceive))
            {
                res = TruckUtil.routematrix(latitude + "," + longitude,
                        order.getLatitude() + "," + order.getLongitude());
            }
            //其它状态计算起点与终点这间的距离与时间
            else
            {
                res = TruckUtil.routematrix(order.getLatitude() + ","
                        + order.getLongitude(),
                        order.getEndLatitude() + "," + order.getEndLongitude());
            }
            
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
     * 司机端订单列表 
     * 
     **/
    @RequestMapping(value = "/queryOrder", method = RequestMethod.POST)
    public @ResponseBody
    Object queryOrder(String token)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<Order> orderList;
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "queryOrder");
            result.put("errormsg", "token不能为空");
            return result;
        }
        try
        {
            Driver driver = driverService.findDriverByToken(token);
            if (driver == null)
            {
                result.put("code", "2000");
                result.put("url", "queryOrder");
                result.put("errormsg", "token不存在");
                return result;
            }
            List<Filter> list = new ArrayList<Filter>();
            list.add(Filter.eq("dirverMobile", driver.getDriverPhone()));
            orderList = orderService.findList(null, list, null);
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
        result.put("data", orderList);
        return result;
    }
    
    /**
     * 司机端首页信息
     * 
     **/
    @RequestMapping(value = "/driverInfo", method = RequestMethod.POST)
    public @ResponseBody
    Object driverInfo(String token)
    {
        
        HashMap<String, Object> result = new HashMap<String, Object>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "driverInfo");
            result.put("errormsg", "token不能为空");
            return result;
        }
        //司机信息
        Driver driver = null;
        //车辆信息
        Car car = null;
        //订单总数
        Long orderCount = 0L;
        
        try
        {
            driver = driverService.findDriverByToken(token);
            if (driver == null)
            {
                result.put("code", "2000");
                result.put("url", "queryOrder");
                result.put("errormsg", "token不存在");
                return result;
            }
            car = carService.findCarByDriver(driver.getDriverPhone());
            orderCount = orderService.count(Filter.eq("dirverMobile",
                    driver.getDriverPhone()));
            map.put("driver", driver);
            map.put("car", car);
            map.put("orderCount", orderCount);
            map.put("monthCount",
                    orderService.orderCountByMonth(driver.getDriverPhone()));
            map.put("todayCount",
                    orderService.orderCountByToday(driver.getDriverPhone()));
            map.put("monthIncome",
                    inComeService.driverInComeSum(driver.getId(), "month"));
            map.put("todayIncome",
                    inComeService.driverInComeSum(driver.getId(), "today"));
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "driverInfo");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "driverInfo");
        result.put("data", map);
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
        Driver driver = null;
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "driver/logout");
            result.put("errormsg", "token不能为空");
            return result;
        }
        try
        {
            driver = driverService.findDriverByToken(token);
            if (driver == null)
            {
                result.put("code", "2000");
                result.put("url", "driver/logout");
                result.put("errormsg", "token不存在");
                return result;
            }
            driver.setToken(null);
            driverService.update(driver);
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "driver/logout");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "driver/logout");
        result.put("data", "退出登录成功");
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
        Driver driver = null;
        List<Order> list = new ArrayList<Order>();
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "orderTest");
            result.put("errormsg", "token不能为空");
            return result;
        }
        try
        {
            driver = driverService.findDriverByToken(token);
            if (driver == null)
            {
                result.put("code", "2000");
                result.put("url", "orderTest");
                result.put("errormsg", "token不存在");
                return result;
            }
            List<Filter> filters = new ArrayList<Filter>();
            filters.add(Filter.ne("orderStatus", Order.OrderStatus.unconfirmed));
            filters.add(Filter.ne("orderStatus", Order.OrderStatus.completed));
            filters.add(Filter.ne("orderStatus", Order.OrderStatus.cancelled));
            filters.add(Filter.eq("dirverMobile", driver.getDriverPhone()));
            list = orderService.findList(null, filters, null);
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "orderTest");
            result.put("errormsg", "系统错误");
            return result;
        }
        
        result.put("code", "200");
        result.put("url", "orderTest");
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
        Driver driver = null;
        List<com.lczx.entity.Message> list = null;
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "driver/messageList");
            result.put("errormsg", "token不能为空");
            return result;
        }
        try
        {
            driver = driverService.findDriverByToken(token);
            if (driver == null)
            {
                result.put("code", "2000");
                result.put("url", "driver/messageList");
                result.put("errormsg", "token不存在");
                return result;
            }
            List<Filter> filters = new ArrayList<Filter>();
            filters.add(Filter.eq("messageType", MessageType.driver));
            filters.add(Filter.eq("status", Status.put));
            list = messageService.findList(null, filters, null);
            
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "driver/messageList");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "driver/messageList");
        result.put("data", list);
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
        System.out.println("行程所用时间：" + time);
        
        if (json.getString("status").equals("0"))
        {
            mil = json.getDouble("distance");
        }
        System.out.println("行程距离："+mil);
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
    
    /**
     * 计算价格 
     * 
     **/
    private BigDecimal amount(BigDecimal mil, BigDecimal time)
    {
        BigDecimal amount = new BigDecimal("0");
        BigDecimal milAmount = new BigDecimal("0");
        BigDecimal timeAmount = new BigDecimal("0");
        BigDecimal haulAmount = new BigDecimal("0");
        PriceConfig config = priceConfigService.find(1L);
        int a = config.getMiles().compareTo(mil);
        int b = config.getBaseTime().compareTo(time);
        if (a == 1 && b == 1)
        {
            
            amount = config.getBasePrice();
        }
        else
        {
            if (a != 1)
            {
                milAmount = (mil.subtract(config.getMiles())).multiply(config.getMilPrice());
            }
            if (b != 1)
            {
                timeAmount = (time.subtract(config.getBaseTime())).multiply(config.getTimePrice());
            }
            
            if (config.getHaulbackMil().compareTo(mil.divide(new BigDecimal("1000"))) != 1)
            {
                haulAmount = (mil.divide(new BigDecimal("1000")).subtract(config.getHaulbackMil())).multiply(config.getHaulbackPrice());
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
