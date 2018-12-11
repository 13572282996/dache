package com.lczx.controller.appInterface;

import java.util.HashMap;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lczx.entity.Article;
import com.lczx.service.ArticleService;
import com.lczx.util.RSAUtils;

@Controller("faceArticleController")
@RequestMapping("/api/article")
public class ArticleController extends BaseController
{
    @Resource(name = "articleServiceImpl")
    ArticleService articleService;
    
    /**
     * 查文章 
     * {"id":""}
     **/
    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public @ResponseBody
    Object findArticleById(String param)
    {
        HashMap<String, Object> result = new HashMap<String, Object>();
        Article article = null;
        if (param == null)
        {
            result.put("code", "500");
            result.put("url", "article");
            result.put("errormsg", "参数错误");
            return result;
        }
        try
        {
            String string = RSAUtils.decrypt(param);
            JSONObject jsonObject = JSONObject.fromObject(string);
            String id = jsonObject.getString("id");
            if (id != null && id != "")
            {
                article = articleService.find(Long.valueOf(id));
            }
            else
            {
                result.put("code", "500");
                result.put("url", "article");
                result.put("errormsg", "参数错误");
                return result;
            }
        }
        catch (Exception e)
        {
            result.put("code", "500");
            result.put("url", "article");
            result.put("errormsg", "系统错误");
            return result;
        }
        result.put("code", "200");
        result.put("url", "article");
        result.put("data", article);
        return result;
    }
}
