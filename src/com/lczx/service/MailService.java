/*



 */
package com.lczx.service;

import java.util.Map;


/**
 * 项目名称： 功能模块名称：Service - 邮件 功能描述：
 * 
 * @author ZHAOBING
 * @version 1.0 Feb 11, 2014 Copyright: Copyright (c) LCZX Tech Co.,Ltd. 2014
 *          Company:成都联创众享科技有限公司 www.lczxtech.com
 */
public interface MailService {

	/**
	 * 发送邮件
	 * 
	 * @param smtpFromMail
	 *            发件人邮箱
	 * @param smtpHost
	 *            SMTP服务器地址
	 * @param smtpPort
	 *            SMTP服务器端口
	 * @param smtpUsername
	 *            SMTP用户名
	 * @param smtpPassword
	 *            SMTP密码
	 * @param toMail
	 *            收件人邮箱
	 * @param subject
	 *            主题
	 * @param templatePath
	 *            模板路径
	 * @param model
	 *            数据
	 * @param async
	 *            是否异步
	 */
	void send(String smtpFromMail, String smtpHost, Integer smtpPort,
			String smtpUsername, String smtpPassword, String toMail,
			String subject, String templatePath, Map<String, Object> model,
			boolean async);

	/**
	 * 发送邮件
	 * 
	 * @param toMail
	 *            收件人邮箱
	 * @param subject
	 *            主题
	 * @param templatePath
	 *            模板路径
	 * @param model
	 *            数据
	 * @param async
	 *            是否异步
	 */
	void send(String toMail, String subject, String templatePath,
			Map<String, Object> model, boolean async);

	/**
	 * 发送邮件(异步)
	 * 
	 * @param toMail
	 *            收件人邮箱
	 * @param subject
	 *            主题
	 * @param templatePath
	 *            模板路径
	 * @param model
	 *            数据
	 */
	void send(String toMail, String subject, String templatePath,
			Map<String, Object> model);

	/**
	 * 发送邮件(异步)
	 * 
	 * @param toMail
	 *            收件人邮箱
	 * @param subject
	 *            主题
	 * @param templatePath
	 *            模板路径
	 */
	void send(String toMail, String subject, String templatePath);

	/**
	 * 发送测试邮件
	 * 
	 * @param smtpFromMail
	 *            发件人邮箱
	 * @param smtpHost
	 *            SMTP服务器地址
	 * @param smtpPort
	 *            SMTP服务器端口
	 * @param smtpUsername
	 *            SMTP用户名
	 * @param smtpPassword
	 *            SMTP密码
	 * @param toMail
	 *            收件人邮箱
	 */
	void sendTestMail(String smtpFromMail, String smtpHost, Integer smtpPort,
			String smtpUsername, String smtpPassword, String toMail);

	/**
	 * 发送找回密码邮件
	 * 
	 * @param toMail
	 *            收件人邮箱
	 * @param username
	 *            用户名
	 * @param safeKey
	 *            安全密匙
	 */
	void sendFindPasswordMail(String toMail, String username);
	
    /**
     * 发送自己的邮件
     * 
     *
     *           
     */
    void sendEmail(String toMail,
            String subject, String content,
            boolean async);
}