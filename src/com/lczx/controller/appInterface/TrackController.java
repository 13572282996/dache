package com.lczx.controller.appInterface;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lczx.entity.PriceConfig;
import com.lczx.service.PriceConfigService;
import com.lczx.util.PostUtil;
import com.lczx.util.RSAUtils;
import com.lczx.util.Setting;
import com.lczx.util.SettingUtils;

/**
 * 百度鹰眼相关接口 
 * 
 **/
@Controller("faceTrackController")
@RequestMapping("/api/track")
public class TrackController extends BaseController
{
    //    private String ak = "WcYcHaoaM3VPlaXCMDgaqM2pcRKX1cG1";
    //    
    //    private int service_id = 201183;
   // private String ak = "Q22zfwz2uWUSYksYAe9MGccbVIhYNZTN";
    
   //private int service_id = 201942;
    
   static String ak = "lu5siENwefhfAvVAcPRnlA5rNpUthZL5";
    
    static int service_id = 201975;
    
    private String coord_type_input = "bd09ll";
    
    private String coord_type_output = "bd09ll";
    
    // long loc_time = System.currentTimeMillis() / 1000;  
    
    @Resource(name = "priceConfigServiceImpl")
    private PriceConfigService priceConfigService;
    
    /**
     * 按区域搜索车辆 
     * {"longitude":"104.044029","latitude":"30.639179"}
     **/
    @RequestMapping(value = "/aroundsearch", method = RequestMethod.POST)
    public @ResponseBody
    Object aroundsearch(String param)
    {
        routematrix("", "");
        HashMap<String, Object> result = new HashMap<String, Object>();
        String string = "";
        String res = "";
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "aroundsearch");
            result.put("errormsg", "参数错误");
            return result;
        }
        try
        {
            string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String longitude = jsonObject.getString("longitude");
            String latitude = jsonObject.getString("latitude");
            if ("".equals(longitude) || "".equals(latitude))
            {
                result.put("code", "500");
                result.put("url", "aroundsearch");
                result.put("errormsg", "参数错误，经纬度不能为空");
                return result;
            }
            long loc_time = (System.currentTimeMillis() / 1000) - 120;
            Setting setting = SettingUtils.get();
            String mil = String.valueOf((setting.getReceiveScope() * 1000));
            
            String str = "ak=" + ak + "&" + "service_id=" + service_id + "&"
                    + "center=" + latitude + "," + longitude + "&" + ""
                    + "radius=" + mil + "&" + "filter=active_time:" + loc_time
                    + "&" + "sortby=" + "loc_time:desc" + "&"
                    + "coord_type_input=" + coord_type_input;
            res = PostUtil.sendGet("http://yingyan.baidu.com/api/v3/entity/aroundsearch",
                    str);
            
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "aroundsearch");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "aroundsearch");
        result.put("data", JSONObject.fromObject(res));
        return result;
        
    }
    
    /**
     * 上传轨迹
     * {"longitude":"20.11","latitude":"30.01"}
     **/
    @RequestMapping(value = "/addPoint", method = RequestMethod.POST)
    public @ResponseBody
    Object addPoint(String param, String token)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        String string = "";
        String res = "";
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "addPoint");
            result.put("errormsg", "参数错误");
            return result;
        }
        
        try
        {
            string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String longitude = jsonObject.getString("longitude");
            String latitude = jsonObject.getString("latitude");
            if ("".equals(longitude) || "".equals(latitude))
            {
                result.put("code", "500");
                result.put("url", "addPoint");
                result.put("errormsg", "参数错误，经纬度不能为空");
                return result;
            }
            //  param = "ak=" + ak + "&" + "service_id=" + service_id + "&"  
            //          + "entity_name=" + entity_name + "&" + "" + "latitude="  
            //          + latitude + "&" + "longitude=" + longitude + "&" + "loc_time="  
            //          + loc_time + "&" + "coord_type_input=" + coord_type_input;  
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "addPoint");
            result.put("errormsg", "系统错误");
            return result;
        }
        
        return result;
        
    }
    
    /**
     * 预估金额
     * {"longitude":"20.11","latitude":"30.01","endLongitude":"21.11","endLatitude":"32.01"}
     **/
    @RequestMapping(value = "/estAmount", method = RequestMethod.POST)
    public @ResponseBody
    Object estAmount(String param, String token)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        String string = "";
        String res = "";
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "addPoint");
            result.put("errormsg", "参数错误");
            return result;
        }
        
        try
        {
            string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String longitude = jsonObject.getString("longitude");
            String latitude = jsonObject.getString("latitude");
            String endLongitude = jsonObject.getString("endLongitude");
            String endLatitude = jsonObject.getString("endLatitude");
            if ("".equals(longitude) || "".equals(latitude)
                    || "".equals(endLongitude) || "".equals(endLatitude))
            {
                result.put("code", "500");
                result.put("url", "addPoint");
                result.put("errormsg", "参数错误，经纬度不能为空");
                return result;
            }
            HashMap<String, Double> map = routematrix(latitude + ","
                    + longitude, endLatitude + "," + endLongitude);
            if (map != null)
            {
                
                map.put("amount",
                        amount(new BigDecimal(map.get("mil")).divide(new BigDecimal(
                                "1000"),
                                2),
                                new BigDecimal(map.get("time")).divide(new BigDecimal(
                                        "60"),
                                        2)).doubleValue());
                result.put("code", "200");
                result.put("url", "estAmount");
                result.put("data", map);
            }
            
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "addPoint");
            result.put("errormsg", "系统错误");
            return result;
        }
        
        return result;
        
    }
    
    /**
     *  根据司机当前坐标计算实时价格，剩余里程，时间
     *   {"longitude":"20.11","latitude":"30.01","orderId":""}
     * 
     **/
    
    /**
     * 计算里程时间 
     * 
     **/
    public HashMap<String, Double> routematrix(String startPoint,
            String endPoint)
    {
        String url = "http://api.map.baidu.com/routematrix/v2/driving";
        String prem = "output=json&origins=" + startPoint + "&destinations="
                + endPoint + "&ak=" + ak;
        String reString = PostUtil.sendGet(url, prem);
        System.out.println(reString);
        JSONObject jsonObject = JSONObject.fromObject(reString);
        
        HashMap<String, Double> map = new HashMap<String, Double>();
        if (jsonObject.getInt("status") == 0)
        {
            map.put("mil", jsonObject.getJSONArray("result")
                    .getJSONObject(0)
                    .getJSONObject("distance")
                    .getDouble("value"));
            map.put("time", jsonObject.getJSONArray("result")
                    .getJSONObject(0)
                    .getJSONObject("duration")
                    .getDouble("value"));
        }
        else
        {
            return null;
        }
        return map;
    }
    
    /**
     * 计算价格 
     * 
     **/
    private BigDecimal amount(BigDecimal mil, BigDecimal time)
    {
        BigDecimal amount = new BigDecimal("0");
        PriceConfig config = priceConfigService.find(1L);
        int a = config.getMiles().compareTo(mil);
        int b = config.getBaseTime().compareTo(time);
        if (a == 1 && b == 1)
        {
            
            amount = config.getBasePrice();
        }
        else
        {
            BigDecimal milAmount = (mil.subtract(config.getMiles())).multiply(config.getMilPrice());
            BigDecimal timeAmount = (time.subtract(config.getBaseTime())).multiply(config.getTimePrice());
            BigDecimal haulAmount = new BigDecimal("0");
            if (config.getHaulbackMil().compareTo(mil) != 1)
            {
                haulAmount = (mil.subtract(config.getHaulbackMil())).multiply(config.getHaulbackPrice());
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
