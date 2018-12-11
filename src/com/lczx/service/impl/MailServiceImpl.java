/*



 */
package com.lczx.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;











import com.lczx.service.MailService;
import com.lczx.service.TemplateService;
import com.lczx.util.Setting;
import com.lczx.util.SettingUtils;
import com.lczx.util.SpringUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 项目名称：
 * 功能模块名称：Service - 邮件
 * 功能描述：
 * @author yideng
 * @version 1.0 Feb 11, 2014
 * Copyright: Copyright (c) ZenSoftware Co.,Ltd. 2014
 * Company:四川天思科技有限责任公司 www.zensoftware.com.cn
 */
@Service("mailServiceImpl")
public class MailServiceImpl implements MailService
{
    
    @Resource(name = "freemarkerConfig")
    private FreeMarkerConfigurer freeMarkerConfigurer;
    
   // @Resource(name = "javaMailSender")
    private JavaMailSenderImpl javaMailSender;
    
  //  @Resource(name = "taskExecutor")
    private TaskExecutor taskExecutor;
    
    @Resource(name = "templateServiceImpl")
    private TemplateService templateService;
    
    /**
     * 添加邮件发送任务
     * 
     * @param mimeMessage
     *            MimeMessage
     */
    private void addSendTask(final MimeMessage mimeMessage)
    {
        try
        {
            taskExecutor.execute(new Runnable()
            {
                public void run()
                {
                    javaMailSender.send(mimeMessage);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void send(String smtpFromMail, String smtpHost, Integer smtpPort,
            String smtpUsername, String smtpPassword, String toMail,
            String subject, String templatePath, Map<String, Object> model,
            boolean async)
    {
        Assert.hasText(smtpFromMail);
        Assert.hasText(smtpHost);
        Assert.notNull(smtpPort);
        Assert.hasText(smtpUsername);
        Assert.hasText(smtpPassword);
        Assert.hasText(toMail);
        Assert.hasText(subject);
        Assert.hasText(templatePath);
        try
        {
            Setting setting = SettingUtils.get();
            Configuration configuration = freeMarkerConfigurer.getConfiguration();
            Template template = configuration.getTemplate(templatePath);
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template,
                    model);
            javaMailSender.setHost(smtpHost);
            javaMailSender.setPort(smtpPort);
            javaMailSender.setUsername(smtpUsername);
            javaMailSender.setPassword(smtpPassword);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                    mimeMessage, false, "utf-8");
            mimeMessageHelper.setFrom(MimeUtility.encodeWord(setting.getSiteName())
                    + " <" + smtpFromMail + ">");
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setTo(toMail);
            mimeMessageHelper.setText(text, true);
            if (async)
            {
                addSendTask(mimeMessage);
            }
            else
            {
                javaMailSender.send(mimeMessage);
            }
        }
        catch (TemplateException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
    
    public void send(String toMail, String subject, String templatePath,
            Map<String, Object> model, boolean async)
    {
        Setting setting = SettingUtils.get();
        send(setting.getSmtpFromMail(),
                setting.getSmtpHost(),
                setting.getSmtpPort(),
                setting.getSmtpUsername(),
                setting.getSmtpPassword(),
                toMail,
                subject,
                templatePath,
                model,
                async);
    }
    
    public void send(String toMail, String subject, String templatePath,
            Map<String, Object> model)
    {
        Setting setting = SettingUtils.get();
        send(setting.getSmtpFromMail(),
                setting.getSmtpHost(),
                setting.getSmtpPort(),
                setting.getSmtpUsername(),
                setting.getSmtpPassword(),
                toMail,
                subject,
                templatePath,
                model,
                true);
    }
    
    public void send(String toMail, String subject, String templatePath)
    {
        Setting setting = SettingUtils.get();
        send(setting.getSmtpFromMail(),
                setting.getSmtpHost(),
                setting.getSmtpPort(),
                setting.getSmtpUsername(),
                setting.getSmtpPassword(),
                toMail,
                subject,
                templatePath,
                null,
                true);
    }
    
    public void sendTestMail(String smtpFromMail, String smtpHost,
            Integer smtpPort, String smtpUsername, String smtpPassword,
            String toMail)
    {
        Setting setting = SettingUtils.get();
        String subject = SpringUtils.getMessage("admin.setting.testMailSubject",
                setting.getSiteName());
       com.lczx.entity.Template testMailTemplate = templateService.get("testMail");
        send(smtpFromMail,
                smtpHost,
                smtpPort,
                smtpUsername,
                smtpPassword,
                toMail,
                subject,
                testMailTemplate.getTemplatePath(),
                null,
                false);
    }
    
    public void sendFindPasswordMail(String toMail, String username
            )
    {
        Setting setting = SettingUtils.get();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("username", username);
        String subject = SpringUtils.getMessage("shop.password.mailSubject",
                setting.getSiteName());
        com.lczx.entity.Template findPasswordMailTemplate = templateService.get("findPasswordMail");
        send(toMail, subject, findPasswordMailTemplate.getTemplatePath(), model);
    }
    public void sendEmail(String toMail,
            String subject, String content,
            boolean async)
    {
       
        Assert.hasText(toMail);
        Assert.hasText(subject);
        Assert.hasText(content);
        try
        {
            Setting setting = SettingUtils.get();
            javaMailSender.setHost( setting.getSmtpHost());
            javaMailSender.setPort(setting.getSmtpPort());
            javaMailSender.setUsername(setting.getSmtpUsername());
            javaMailSender.setPassword(setting.getSmtpPassword());
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                    mimeMessage, false, "utf-8");
            mimeMessageHelper.setFrom(MimeUtility.encodeWord(setting.getSiteName())
                    + " <" + setting.getSmtpFromMail() + ">");
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setTo(toMail);
            mimeMessageHelper.setText(content, true);
            if (async)
            {
                addSendTask(mimeMessage);
            }
            else
            {
                javaMailSender.send(mimeMessage);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
}