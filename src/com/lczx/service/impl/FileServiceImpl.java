/**
 * 文   件  名:  FileServiceImpl.java
 * 描          述：<功能模块名称>
 * 修   改  人:  Administrator
 * 修改时间:  2016年1月6日
 *         <修改描述:>
 * @author Administrator
 * @version [1.0 2016年1月6日]
 * Copyright: Copyright (c) LCZXTECH Co.,Ltd. 2015
 * Company:成都联创众享科技有限公司 www.lczxtech.com
 */
package com.lczx.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.lczx.entity.FileInfo;
import com.lczx.entity.FileInfo.FileType;
import com.lczx.entity.FileInfo.OrderType;
import com.lczx.freeMarker.FreeMarkerViewUtil;
import com.lczx.service.FileService;
import com.lczx.util.Setting;
import com.lczx.util.SettingUtils;

/**
 * Service - 文件
 * 
 */
@Service("fileServiceImpl")
public class FileServiceImpl implements FileService, ServletContextAware
{
    /** servletContext */
    private ServletContext servletContext;
    
    public void setServletContext(ServletContext servletContext)
    {
        this.servletContext = servletContext;
    }
    
    public boolean isValid(FileType fileType, MultipartFile multipartFile)
    {
        if (multipartFile == null)
        {
            return false;
        }
        Setting setting = SettingUtils.get();
        if (setting.getUploadMaxSize() != null
                && setting.getUploadMaxSize() != 0
                && multipartFile.getSize() > setting.getUploadMaxSize() * 1024L * 1024L)
        {
            return false;
        }
        String[] uploadExtensions;
        if (fileType == FileType.flash)
        {
            uploadExtensions = setting.getUploadFlashExtensions();
        }
        else if (fileType == FileType.media)
        {
            uploadExtensions = setting.getUploadMediaExtensions();
        }
        else if (fileType == FileType.file)
        {
            uploadExtensions = setting.getUploadFileExtensions();
        }
        else
        {
            uploadExtensions = setting.getUploadImageExtensions();
        }
        if (ArrayUtils.isNotEmpty(uploadExtensions))
        {
            return FilenameUtils.isExtension(multipartFile.getOriginalFilename(),
                    uploadExtensions);
        }
        return false;
    }
    
    public String upload(FileType fileType, MultipartFile multipartFile,
            boolean async)
    {
        if (multipartFile == null)
        {
            return null;
        }
        Setting setting = SettingUtils.get();
        String uploadPath;
        if (fileType == FileType.flash)
        {
            uploadPath = setting.getFlashUploadPath();
        }
        else if (fileType == FileType.media)
        {
            uploadPath = setting.getMediaUploadPath();
        }
        else if (fileType == FileType.file)
        {
            uploadPath = setting.getFileUploadPath();
        }
        else
        {
            uploadPath = setting.getImageUploadPath();
        }
        try
        {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("uuid", UUID.randomUUID().toString());
            String path = FreeMarkerViewUtil.process(uploadPath, model);
            String destPath = path
                    + UUID.randomUUID()
                    + "."
                    + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            
            File tempFile = new File(System.getProperty("java.io.tmpdir")
                    + "/upload_" + UUID.randomUUID() + ".tmp");
            if (!tempFile.getParentFile().exists())
            {
                tempFile.getParentFile().mkdirs();
            }
            multipartFile.transferTo(tempFile);
            
            try
            {
                File destFilePath = new File(servletContext.getRealPath(path));
            	if (!destFilePath.exists()) 
            	{
					destFilePath.mkdirs();
				}
                File destFile = new File(servletContext.getRealPath(destPath));
                try
                {
                    FileUtils.moveFile(tempFile, destFile);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            finally
            {
                FileUtils.deleteQuietly(tempFile);
            }
            return setting.getSiteUrl() + destPath;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public String upload(FileType fileType, MultipartFile multipartFile)
    {
        return upload(fileType, multipartFile, false);
    }
    
    public String uploadLocal(FileType fileType, MultipartFile multipartFile)
    {
        if (multipartFile == null)
        {
            return null;
        }
        Setting setting = SettingUtils.get();
        String uploadPath;
        if (fileType == FileType.flash)
        {
            uploadPath = setting.getFlashUploadPath();
        }
        else if (fileType == FileType.media)
        {
            uploadPath = setting.getMediaUploadPath();
        }
        else if (fileType == FileType.file)
        {
            uploadPath = setting.getFileUploadPath();
        }
        else
        {
            uploadPath = setting.getImageUploadPath();
        }
        try
        {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("uuid", UUID.randomUUID().toString());
            String path = FreeMarkerViewUtil.process(uploadPath, model);
            String destPath = path
                    + UUID.randomUUID()
                    + "."
                    + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            File destFile = new File(servletContext.getRealPath(destPath));
            if (!destFile.getParentFile().exists())
            {
                destFile.getParentFile().mkdirs();
            }
            multipartFile.transferTo(destFile);
            return destPath;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<FileInfo> browser(String path, FileType fileType,
            OrderType orderType)
    {
        if (path != null)
        {
            if (!path.startsWith("/"))
            {
                path = "/" + path;
            }
            if (!path.endsWith("/"))
            {
                path += "/";
            }
        }
        else
        {
            path = "/";
        }
        Setting setting = SettingUtils.get();
        String uploadPath;
        if (fileType == FileType.flash)
        {
            uploadPath = setting.getFlashUploadPath();
        }
        else if (fileType == FileType.media)
        {
            uploadPath = setting.getMediaUploadPath();
        }
        else if (fileType == FileType.file)
        {
            uploadPath = setting.getFileUploadPath();
        }
        else
        {
            uploadPath = setting.getImageUploadPath();
        }
        String browsePath = StringUtils.substringBefore(uploadPath, "${");
        browsePath = StringUtils.substringBeforeLast(browsePath, "/") + path;
        
        List<FileInfo> fileInfos = new ArrayList<FileInfo>();
        if (browsePath.indexOf("..") >= 0)
        {
            return fileInfos;
        }
      
        File directory = new File(servletContext.getRealPath(browsePath));
        if (directory.exists() && directory.isDirectory())
        {
            for (File file : directory.listFiles())
            {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setName(file.getName());
                fileInfo.setUrl(setting.getSiteUrl() + browsePath + file.getName());
                fileInfo.setIsDirectory(file.isDirectory());
                fileInfo.setSize(file.length());
                fileInfo.setLastModified(new Date(file.lastModified()));
                fileInfos.add(fileInfo);
            }
        }
        
        if (orderType == OrderType.size)
        {
            Collections.sort(fileInfos, new SizeComparator());
        }
        else if (orderType == OrderType.type)
        {
            Collections.sort(fileInfos, new TypeComparator());
        }
        else
        {
            Collections.sort(fileInfos, new NameComparator());
        }
        return fileInfos;
    }
    
    private class NameComparator implements Comparator<FileInfo>
    {
        public int compare(FileInfo fileInfos1, FileInfo fileInfos2)
        {
            return new CompareToBuilder().append(!fileInfos1.getIsDirectory(),
                    !fileInfos2.getIsDirectory())
                    .append(fileInfos1.getName(), fileInfos2.getName())
                    .toComparison();
        }
    }
    
    private class SizeComparator implements Comparator<FileInfo>
    {
        public int compare(FileInfo fileInfos1, FileInfo fileInfos2)
        {
            return new CompareToBuilder().append(!fileInfos1.getIsDirectory(),
                    !fileInfos2.getIsDirectory())
                    .append(fileInfos1.getSize(), fileInfos2.getSize())
                    .toComparison();
        }
    }
    
    private class TypeComparator implements Comparator<FileInfo>
    {
        public int compare(FileInfo fileInfos1, FileInfo fileInfos2)
        {
            return new CompareToBuilder().append(!fileInfos1.getIsDirectory(),
                    !fileInfos2.getIsDirectory())
                    .append(FilenameUtils.getExtension(fileInfos1.getName()),
                            FilenameUtils.getExtension(fileInfos2.getName()))
                    .toComparison();
        }
    }
}
