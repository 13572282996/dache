package com.lczx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import net.sf.json.JSONObject;

public class TruckUtil
{
    // static String ak = "WcYcHaoaM3VPlaXCMDgaqM2pcRKX1cG1";
    // static int service_id = 201183;//注意这里是服务的id不是应用的id  
    
    // static String ak = "Q22zfwz2uWUSYksYAe9MGccbVIhYNZTN";
    // static int service_id = 201942;//注意这里是服务的id不是应用的id  
    static String ak = "lu5siENwefhfAvVAcPRnlA5rNpUthZL5";
    
    static int service_id = 201975;
    
    static String coord_type_input = "bd09ll";
    
    static String coord_type_output = "bd09ll";
    
    /**
     * 上传坐标点 
     * 
     **/
    public static String addpoint(String entity_name, String latitude,
            String longitude)
    {
        String url = "http://yingyan.baidu.com/api/v3/track/addpoint";
        long loc_time = System.currentTimeMillis() / 1000;
        String param = "ak=" + ak + "&" + "service_id=" + service_id + "&"
                + "entity_name=" + entity_name + "&" + "" + "latitude="
                + latitude + "&" + "longitude=" + longitude + "&" + "loc_time="
                + loc_time + "&" + "coord_type_input=" + coord_type_input;
        String str = PostUtil.sendPost(url, param);
        return str;
    }
    
    /**
     * 查找3公里范围内的车辆 
     * 
     **/
    public static String aroundsearch(String latitude, String longitude)
    {
        Setting setting = SettingUtils.get();
        String mil = String.valueOf((setting.getReceiveScope() * 1000));
        long loc_time = (System.currentTimeMillis() / 1000) - 120;
        String str = "ak=" + ak + "&" + "service_id=" + service_id + "&"
                + "center=" + latitude + "," + longitude + "&" + "" + "radius="
                + mil + "&" + "filter=active_time:" + loc_time + "&"
                + "sortby=" + "loc_time:desc" + "&" + "coord_type_input="
                + coord_type_input;
        String res = PostUtil.sendGet("http://yingyan.baidu.com/api/v3/entity/aroundsearch",
                str);
        return res;
    }
    
    /**
     * 计算一定时间范围内轨迹里程
     * 指定时间到当前时间段内 
     * 
     **/
    public static String getdistance(Date startDate, String entity_name)
    {
        long end_time = System.currentTimeMillis() / 1000;
        long start_time = startDate.getTime() / 1000;
        System.out.println("开始时间："+start_time+"结束时间："+end_time);
        String str = "ak=" + ak + "&" + "service_id=" + service_id + "&"
                + "entity_name=" + entity_name + "&" + "" + "start_time="
                + start_time + "&" + "end_time=" + end_time;
        //+ "&" + "coord_type_input=" + coord_type_input;
        String res = PostUtil.sendGet("http://yingyan.baidu.com/api/v3/track/getdistance",
                str);
        return res;
    }
    
    /**
     * 计算一定时间范围内轨迹里程 
     * 
     **/
    public static String getdistance(Date startDate, Date endDate,
            String entity_name)
    {
        long end_time = endDate.getTime() / 1000;
        long start_time = startDate.getTime() / 1000;
        String str = "ak=" + ak + "&" + "service_id=" + service_id + "&"
                + "entity_name=" + entity_name + "&" + "" + "start_time="
                + start_time + "&" + "end_time=" + end_time;
        String res = PostUtil.sendGet("http://yingyan.baidu.com/api/v3/track/getdistance",
                str);
        return res;
    }
    
    /**
     * 查询司机实时坐标
     * 
     * return {"status":0,"message":"成功","latest_point":{"longitude":103.99172630916,"latitude":30.634709815662,"loc_time":1529836954,"direction":220,"speed":0.014353}}
     * 
     **/
    public static String getlatestpoint(String entity_name)
    {
        String str = "ak=" + ak + "&" + "service_id=" + service_id + "&"
                + "entity_name=" + entity_name + "&" + "" + "start_time="
                //       + start_time + "&" + "end_time=" + end_time;
                + "&" + "coord_type_output=" + coord_type_output;
        String res = PostUtil.sendGet("http://yingyan.baidu.com/api/v3/track/getlatestpoint",
                str);
        return res;
    }
    
    /**
     * 查询司机实时坐标
     * 
     * return map
     * 
     **/
    public static HashMap<String, String> getpointMap(String entity_name)
    {
        HashMap<String, String> map = new HashMap<String, String>();
        String reS = getlatestpoint(entity_name);
        JSONObject jsonObject = JSONObject.fromObject(reS);
        if (jsonObject.getInt("status") == 0)
        {
            map.put("longitude", jsonObject.getJSONObject("latest_point")
                    .getString("longitude"));
            map.put("latitude", jsonObject.getJSONObject("latest_point")
                    .getString("latitude"));
        }
        return map;
    }
    
    public static HashMap<String, Double> routematrix(String startPoint,
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
    
    public static void main(String[] args)
    {
        //        String param = "";  
        //        String url = "http://yingyan.baidu.com/api/v3/track/addpoint";  
        //        String ak = "WcYcHaoaM3VPlaXCMDgaqM2pcRKX1cG1";  
        //        int service_id = 201183;//注意这里是服务的id不是应用的id  
        //        String entity_name = "15881087681";  
        //        double latitude = 30.639179;  
        //        double longitude = 104.044029;  
        //        long loc_time = System.currentTimeMillis() / 1000;  
        //        Date date = new Date();
        //        System.out.println(loc_time);
        //        System.out.println(date.getTime()/1000);
        //       // date.get
        //        String coord_type_input = "bd09ll";  
        //        param = "ak=" + ak + "&" + "service_id=" + service_id + "&"  
        //                + "entity_name=" + entity_name + "&" + "" + "latitude="  
        //                + latitude + "&" + "longitude=" + longitude + "&" + "loc_time="  
        //                + loc_time + "&" + "coord_type_input=" + coord_type_input;  
        //        String str = "2018-06-24 15:44:12";
        //        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //        // Date date = null;
        //        try
        //        {
        //            Date date = format.parse(str);
        //            String str1 = getdistance(date, "18380492823");
        //            System.out.println(str1);
        //        }
        //        catch (ParseException e)
        //        {
        //            // TODO Auto-generated catch block
        //            e.printStackTrace();
        //        }
        
        String str1 = getlatestpoint("18380492823");
        
    }
}
