/**
 * 文   件  名:  FileService.java
 * 描          述：<功能模块名称>
 * 修   改  人:  Administrator
 * 修改时间:  2016年1月6日
 *         <修改描述:>
 * @author Administrator
 * @version [1.0 2016年1月6日]
 * Copyright: Copyright (c) LCZXTECH Co.,Ltd. 2015
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
package com.lczx.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lczx.entity.FileInfo;
import com.lczx.entity.FileInfo.FileType;
import com.lczx.entity.FileInfo.OrderType;




/**
 * service - 文件
 */
public interface FileService
{
    /**
     * 文件验证
     * 
     * @param fileType
     *            文件类型
     * @param multipartFile
     *            上传文件
     * @return 文件验证是否通过
     */
    boolean isValid(FileType fileType, MultipartFile multipartFile);

    /**
     * 文件上传
     * 
     * @param fileType
     *            文件类型
     * @param multipartFile
     *            上传文件
     * @param async
     *            是否异步
     * @return 访问URL
     */
    String upload(FileType fileType, MultipartFile multipartFile, boolean async);

    /**
     * 文件上传(异步)
     * 
     * @param fileType
     *            文件类型
     * @param multipartFile
     *            上传文件
     * @return 访问URL
     */
    String upload(FileType fileType, MultipartFile multipartFile);

    /**
     * 文件上传至本地
     * 
     * @param fileType
     *            文件类型
     * @param multipartFile
     *            上传文件
     * @return 路径
     */
    String uploadLocal(FileType fileType, MultipartFile multipartFile);

    /**
     * 文件浏览
     * 
     * @param path
     *            浏览路径
     * @param fileType
     *            文件类型
     * @param orderType
     *            排序类型
     * @return 文件信息
     */
    List<FileInfo> browser(String path, FileType fileType, OrderType orderType);
}
