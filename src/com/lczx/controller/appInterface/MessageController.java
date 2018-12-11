package com.lczx.controller.appInterface;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Random;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.lczx.entity.Code;
import com.lczx.service.CodeService;
import com.lczx.util.RSAUtils;
import com.lczx.util.SendSMSUtils;

@Controller("faceMessageController")
@RequestMapping("/api/sms")
public class MessageController extends BaseController
{
    @Resource(name = "codeServiceImpl")
    private CodeService codeService;
    
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public @ResponseBody
    Object send(String param) throws NoSuchAlgorithmException,
            InvalidKeySpecException, UnsupportedEncodingException,
            ClientException
    {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (param == null)
        {
            map.put("code", "500");
            map.put("url", "send");
            map.put("errormsg", "参数为空");
        }
        try
        {
            String string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String mobile = jsonObject.getString("mobile");
            if (mobile == null)
            {
                map.put("code", "500");
                map.put("url", "send");
                map.put("errormsg", "参数解析错误");
            }
            Random random = new Random();
          int code =  random.nextInt(899999)+100000;
            SendSmsResponse response = SendSMSUtils.sendSms(mobile,String.valueOf(code));
            if ("OK".equals(response.getCode())
                    && "OK".equals(response.getMessage()))
            {
                Code code1 = new Code();
                code1.setCode(String.valueOf(code));
                code1.setMobile(mobile);
                codeService.save(code1);
                map.put("code", "200");
                map.put("url", "send");
                map.put("data", "短信发送成功");
            }
            return map;
        }
        catch (Exception e)
        {
            map.put("code", "500");
            map.put("url", "send");
            map.put("errormsg", "系统错误");
            return map;
        }
        
    }
}
