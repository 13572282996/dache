/**
 * 文   件  名:  FileController.java
 * 描          述：<功能模块名称>
 * 修   改  人:  Administrator
 * 修改时间:  2016年1月6日
 *         <修改描述:>
 * @author Administrator
 * @version [1.0 2016年1月6日]
 * Copyright: Copyright (c) LCZXTECH Co.,Ltd. 2015
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
package com.lczx.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lczx.util.JsonUtils;
import com.lczx.util.Message;
import com.lczx.entity.*;
import com.lczx.entity.FileInfo.FileType;
import com.lczx.entity.FileInfo.OrderType;
import com.lczx.service.*;



/**
 * 
 */
@Controller("adminFileController")
@RequestMapping("/admin/file")
public class FileController extends BaseController
{
    @Resource(name = "fileServiceImpl")
    private FileService fileService;

    /**
     * 上传
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public void upload(FileType fileType, MultipartFile file, HttpServletResponse response) {
        Map<String, Object> data = new HashMap<String, Object>();
        if (!fileService.isValid(fileType, file)) {
            data.put("message", Message.warn("admin.upload.invalid"));
        } else {
            String url = fileService.upload(fileType, file, false);
            if (url == null) {
                data.put("message", Message.warn("admin.upload.error"));
            } else {
                data.put("message", SUCCESS_MESSAGE);
                data.put("url", url);
            }
        }
        try {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 浏览
     */
    @RequestMapping(value = "/browser", method = RequestMethod.GET)
    public @ResponseBody
    List<FileInfo> browser(String path, FileType fileType, OrderType orderType) {
        return fileService.browser(path, fileType, orderType);
    }

}
