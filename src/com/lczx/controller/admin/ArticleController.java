/*



 */
package com.lczx.controller.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lczx.util.Message;
import com.lczx.util.Order;
import com.lczx.util.Order.Direction;
import com.lczx.util.Pageable;
import com.lczx.entity.*;
import com.lczx.service.*;

/**
 * 项目名称：
 * 功能模块名称：Controller - 文章
 * 功能描述：
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
@Controller("adminArticleController")
@RequestMapping("/admin/article")
public class ArticleController extends BaseController
{
    
    @Resource(name = "articleServiceImpl")
    private ArticleService articleService;
    
    @Resource(name = "articleCategoryServiceImpl")
    private ArticleCategoryService articleCategoryService;
    
//    @Resource(name = "tagServiceImpl")
//    private TagService tagService;
    
    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("articleCategoryTree",
                articleCategoryService.findTree());
        model.addAttribute("articleCategoryRoots",
                articleCategoryService.findRoots());
       // model.addAttribute("tags", tagService.findList(Type.article));
        return "/admin/article/add";
    }
    
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request,
            HttpServletResponse response,
    		Article article, Long articleCategoryId, Long[] tagIds,
    		@RequestParam(value = "file", required = false)
    		MultipartFile file,
            RedirectAttributes redirectAttributes)
    {
        article.setArticleCategory(articleCategoryService.find(articleCategoryId));
        if (!isValid(article))
        {
            return ERROR_VIEW;
        }
 //       MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        // 获取前台传值
//        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//        MultipartFile file_pic = fileMap.get("pictureFile");
//        if(!file_pic.isEmpty())
//        {
//            try {
//				article.setPicture(read(file_pic, request));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
        article.setHits(0L);
//        article.setPageNumber(null);
        articleService.save(article);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }
    
    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("articleCategoryTree",
                articleCategoryService.findTree());
        model.addAttribute("articleCategoryRoots",
                articleCategoryService.findRoots());
       // model.addAttribute("tags", tagService.findList(Type.article));
        model.addAttribute("article", articleService.find(id));
        return "/admin/article/edit";
    }
    
    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "file", required = false)
    		MultipartFile file,
    		Article article, Long articleCategoryId,Long id,
            Long[] tagIds, RedirectAttributes redirectAttributes)
    {
        article.setArticleCategory(articleCategoryService.find(articleCategoryId));
        if (!isValid(article))
        {
            return ERROR_VIEW;
        }
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        // 获取前台传值
//        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//        MultipartFile file_pic = fileMap.get("pictureFile");
//        if(!file_pic.isEmpty())
//        {
//            try {
//				article.setPicture(read(file_pic, request));
//			} catch (IOException e) {
//				// 
//				e.printStackTrace();
//			}
//        }
//        else 
//        {
//			article.setPicture(articleService.find(id).getPicture());
//		}
        articleService.update(article, "hits", "pageNumber");
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.jhtml";
    }
    
    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        Order order = new  Order("createDate", Direction.desc);
        List<Order> orders = new ArrayList<Order>();
        orders.add(order);
        pageable.setOrders(orders);
        model.addAttribute("page", articleService.findPage(pageable));
        return "/admin/article/list";
    }
    
    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        articleService.delete(ids);
        return SUCCESS_MESSAGE;
    }
    
    public static String read(MultipartFile card_pic,HttpServletRequest request) throws IOException{
        
        String configPath = File.separator + "upload" + File.separator;
        String ctxPath = request.getSession()
                .getServletContext()
                .getRealPath("/");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String year = sdf.format(new Date());
        configPath += year + File.separator;
        sdf = new SimpleDateFormat("MM");
        String month = sdf.format(new Date());
        configPath += month + File.separator;
        ctxPath += configPath;
        File f = new File(ctxPath);
        if (!f.exists())
        {
            f.mkdirs();
        }
        
        String degName = card_pic.getOriginalFilename();
        String fileExt = degName.substring(degName.lastIndexOf(".") + 1).toLowerCase();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
        File uploadFile = new File(ctxPath + newFileName);
        FileCopyUtils.copy(card_pic.getBytes(), uploadFile);
        return configPath+newFileName;
    }
    
}