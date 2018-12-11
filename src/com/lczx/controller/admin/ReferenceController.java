///**
// * 文   件  名:  ReferenceController.java
// * 描          述：<功能模块名称>
// * 修   改  人:  Administrator
// * 修改时间:  2015年12月10日
// *         <修改描述:>
// * @author Administrator
// * @version [1.0 2015年12月10日]
// * Copyright: Copyright (c) LCZXTECH Co.,Ltd. 2015
// * Company:成都联创众享科技有限公司 www.lczxtech.com
// */
//package com.lczx.controller.admin;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.lczx.entity.*;
//import com.lczx.entity.BaseEntity.Save;
//import com.lczx.service.*;
//import com.lczx.util.*;
//import com.lczx.util.Filter.Operator;
//import com.lczx.util.Message;
//import com.lczx.util.Order.Direction;
//
///**
// * controller-备考专区
// */
//@Controller("adminReferenceController")
//@RequestMapping("/admin/reference")
//public class ReferenceController extends BaseController
//{
//    @Resource(name = "referenceServiceImpl")
//    private ReferenceService referenceService;
//    
//    @Resource(name = "articleCategoryServiceImpl")
//    private ArticleCategoryService articleCategoryService;
//    /**
//     * 列表
//     */
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public String list(Pageable pageable, ModelMap model)
//    {
//        Order order = new  Order("createDate", Direction.desc);
//        List<Order> orders = new ArrayList<Order>();
//        orders.add(order);
//        pageable.setOrders(orders);
//        model.addAttribute("page", referenceService.findPage(pageable));
//        return "/admin/reference/list";
//    }
//    
//    /**
//     * 添加
//     */
//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public String add(ModelMap model) {
//        model.addAttribute("articleCategory",articleCategoryService.findTree());
//        return "/admin/reference/add";
//    }
//    
//    /**
//     * 保存
//     * @throws IOException 
//     */
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String save(HttpServletRequest request,
//            HttpServletResponse response,
//            @RequestParam(value = "file", required = false)
//            MultipartFile file,Reference reference,Long articleCategoryId,RedirectAttributes redirectAttributes) throws IOException {
//        reference.setArticleCategory(articleCategoryService.find(articleCategoryId));
//        if (!isValid(reference, Save.class)) {
//            return ERROR_VIEW;
//        }
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        // 获取前台传值
//        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//      
//        MultipartFile file_pic = fileMap.get("filePath0");
//        if(!file_pic.isEmpty()) //上传文件的size是0认为是null，不进if
//        {
//            reference.setFilePath(read(file_pic, request));
//        }
//        MultipartFile class_pic = fileMap.get("referenceClass0");
//        if(!class_pic.isEmpty())
//        {
//            reference.setReferenceClass(read(class_pic, request));
//        }
//        reference.setDloaded(0);
//        referenceService.save(reference);
//        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
//        return "redirect:list.jhtml";
//    }
//    
//    /**
//     * 编辑
//     */
//    @RequestMapping(value = "/edit", method = RequestMethod.GET)
//    public String edit(Long id, ModelMap model) {
//        model.addAttribute("articleCategoryTree",
//                articleCategoryService.findTree());
//        model.addAttribute("articleCategoryRoots",
//                articleCategoryService.findRoots());
//        model.addAttribute("reference", referenceService.find(id));
//        return "/admin/reference/edit";
//    }
//    
//    /**
//     * 修改
//     * @throws IOException 
//     */
//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public String update(HttpServletRequest request,
//            HttpServletResponse response,
//            @RequestParam(value = "file", required = false)
//            MultipartFile file,Long id, Reference reference,Long articleCategoryId,
//            RedirectAttributes redirectAttributes) throws IOException {
//        reference.setArticleCategory(articleCategoryService.find(articleCategoryId));
//        if (!isValid(reference, Save.class)) {
//            return ERROR_VIEW;
//        }
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        // 获取前台传值
//        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//      
//        MultipartFile file_pic = fileMap.get("filePath0");
//        if(!file_pic.isEmpty())
//        {
//            reference.setFilePath(read(file_pic, request));
//        }else{
//            reference.setFilePath(referenceService.find(id).getFilePath());
//        }
//        MultipartFile class_pic = fileMap.get("referenceClass0");
//        if(!class_pic.isEmpty())
//        {
//            reference.setReferenceClass(read(class_pic, request));
//        }else{
//            reference.setReferenceClass(referenceService.find(id).getReferenceClass());
//        }
//        referenceService.update(reference);
//        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
//        return "redirect:list.jhtml";
//    }
//    /**
//     * 删除
//     */
//    @RequestMapping(value = "delete", method = RequestMethod.POST)
//    public @ResponseBody
//    Message delete(Long[] ids)
//    {
//        if (ids != null)
//        {
//            for (Long id : ids)
//            {
//                referenceService.delete(id, null);
//            }
//        }
//        return SUCCESS_MESSAGE;
//    }
//    
//    //尝试公共方法
//    public static String read(MultipartFile card_pic,HttpServletRequest request) throws IOException{
//        
//        String configPath = File.separator + "upload" + File.separator;
//        String ctxPath = request.getSession()
//                .getServletContext()
//                .getRealPath("/");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//        String year = sdf.format(new Date());
//        configPath += year + File.separator;
//        sdf = new SimpleDateFormat("MM");
//        String month = sdf.format(new Date());
//        configPath += month + File.separator;
//        ctxPath += configPath;
//        File f = new File(ctxPath);
//        if (!f.exists())
//        {
//            f.mkdirs();
//        }
//        
//        String degName = card_pic.getOriginalFilename();
//        String fileExt = degName.substring(degName.lastIndexOf(".") + 1).toLowerCase();
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//        String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
//        File uploadFile = new File(ctxPath + newFileName);
//        FileCopyUtils.copy(card_pic.getBytes(), uploadFile);
//        return configPath+newFileName;
//    }
//}
