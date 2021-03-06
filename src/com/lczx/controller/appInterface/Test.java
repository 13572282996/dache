package com.lczx.controller.appInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import com.lczx.entity.Order;

public class Test
{
    public static void main(String[] args)
    {
//        String param = "";  
//        String url = "http://yingyan.baidu.com/api/v3/track/addpoint";  
//        String ak = "Q22zfwz2uWUSYksYAe9MGccbVIhYNZTN";  
//        int service_id = 201942;//注意这里是服务的id不是应用的id  
//        String entity_name = "18380492823";  
//        double latitude = 33.639179;  
//        double longitude = 105.444029;  
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
//        String str = sendPost(url, param);  
//        System.out.println(str); 
        System.out.println(Order.OrderType.realTime);
    }
    
    public static String sendPost(String url, String param) {  
        PrintWriter out = null;  
        BufferedReader in = null;  
        String result = "";  
        try {  
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            // 设置通用的请求属性  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent",  
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            // 获取URLConnection对象对应的输出流  
            out = new PrintWriter(conn.getOutputStream());  
            // 发送请求参数  
            out.print(param);  
            // flush输出流的缓冲  
            out.flush();  
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(  
                    new InputStreamReader(conn.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送 POST 请求出现异常！" + e);  
            e.printStackTrace();  
        }  
        // 使用finally块来关闭输出流、输入流  
        finally {  
            try {  
                if (out != null) {  
                    out.close();  
                }  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }  
}
