package com.lczx.controller.appInterface;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.lczx.entity.Member;
import com.lczx.entity.Order;
import com.lczx.entity.Order.OrderStatus;
import com.lczx.service.MemberService;
import com.lczx.service.OrderService;
import com.lczx.util.PayConfig;
import com.lczx.util.RSAUtils;

@Controller("facePayController")
@RequestMapping("/api/pay")
public class PayController extends BaseController
{
    @Resource(name = "orderServiceImpl")
    private OrderService orderService;
    
    @Resource(name = "memberServiceImpl")
    private MemberService memberService;
    
    /**
     * 
     * {"orderId":""}
     *  String token 
     * 
     **/
    @RequestMapping(value = "/paySign", method = RequestMethod.POST)
    public @ResponseBody
    Object paySign(String param, String token)
    {
        
        HashMap<String, Object> result = new HashMap<String, Object>();
        String string = "";
        String orderStr = "";
        if (token == null)
        {
            result.put("code", "2000");
            result.put("url", "receive");
            result.put("errormsg", "token不能为空");
            return result;
        }
       // token= "386859c6-1118-49e1-83ce-617efbe2ccd6";
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "receive");
            result.put("errormsg", "param参数不能为空");
            return result;
        }
        Member member = memberService.findMemberByToken(token);
        if (member == null)
        {
            result.put("code", "2000");
            result.put("url", "receive");
            result.put("errormsg", "登录过期，请重新登录");
            return result;
        }
        try
        {
            string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String orderId = jsonObject.getString("orderId");
            Order order = orderService.find(Long.valueOf(orderId));
            if (order == null)
            {
                result.put("code", "500");
                result.put("url", "paySign");
                result.put("errormsg", "查询订单信息失败！");
                return result;
            }
            if (order.getPaymentStatus() != Order.PaymentStatus.unpaid)
            {
                result.put("code", "500");
                result.put("url", "paySign");
                result.put("errormsg", "订单支付状态异常");
                return result;
            }
            
            //支付宝支付签名开始
            AlipayClient alipayClient = new DefaultAlipayClient(
                    "https://openapi.alipay.com/gateway.do",
                    PayConfig.ALI_APPID, PayConfig.ALI_APP_PRIVATE_KEY, "json",
                    PayConfig.CHARSET, PayConfig.ALI_ALIPAY_PUBLIC_KEY, "RSA2");
            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            model.setPassbackParams(URLEncoder.encode("小孙专车服务费"));
            ; //描述信息  添加附加数据
            model.setSubject("小孙专车-" + order.getDirverName() + "师傅"); //商品标题
            model.setOutTradeNo(order.getNum()); //商家订单编号
            model.setTimeoutExpress("30m"); //超时关闭该订单时间
            model.setTotalAmount(order.getAmount().toString()); //订单总金额
            model.setProductCode("QUICK_MSECURITY_PAY"); //销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
            request.setBizModel(model);
            request.setNotifyUrl(PayConfig.ALI_NOTIFYURL); //回调地址
            
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            orderStr = response.getBody();
            System.out.println(orderStr);//就是orderString 可以直接给客户端请求，无需再做处理。
            
        }
        
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "paySign");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "paySign");
        result.put("data", orderStr);
        return result;
    }
    
    @RequestMapping(value = "/notify", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String notify(HttpServletRequest request)
    {
        
        Map requestParams = request.getParameterMap();
        
        JSONObject json = JSONObject.fromObject(requestParams);
        
        String trade_status = json.get("trade_status")
                .toString()
                .substring(2, json.get("trade_status").toString().length() - 2);
        String out_trade_no = json.get("out_trade_no")
                .toString()
                .substring(2, json.get("out_trade_no").toString().length() - 2);
        String notify_id = json.get("notify_id")
                .toString()
                .substring(2, json.get("notify_id").toString().length() - 2);
        
        System.out.println("====================================================");
        System.out.println(json.toString());
        System.out.println("支付宝回调地址！");
        System.out.println("商户的订单编号：" + out_trade_no);
        System.out.println("支付的状态：" + trade_status);
        
        if (trade_status.equals("TRADE_SUCCESS"))
        {
            
            /**
             *支付成功之后的业务处理
             */
            Order order = orderService.queryOrderbyNum(out_trade_no);
            if (order != null)
            {
                order.setPaymentStatus(Order.PaymentStatus.paid);
                orderService.update(order);
            }
            
            return "SUCCESS";
        }
        else
        {
            
            /**
             *支付失败后的业务处理
             */
            
            return "SUCCESS";
            
        }
    }
    
}
