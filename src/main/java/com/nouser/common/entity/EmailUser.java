package com.nouser.common.entity;

/**
 * 邮件用户对象
 * 		Send Email user this.
 * @Title: EmailUser.java
 * @Package com.nouser.common.entity
 * @author: zhoukl
 * @date: 2019年11月26日 上午11:15:40
 * @version V1.0
 */
public class EmailUser {
	private String loginName;// 登录名
	private String password;// 密码
	private String name;// 真实姓名
	private String email;// 电子邮件
	private String phone;// 手机号
	private String senderEmail;// 发件人邮箱
	private String senderEmailPassword;// 发件人邮箱密码
	private String recipientEmail;// 收件人邮箱
	private String senderName;// 发件人名称
	private String recipientName;// 收件人名称
	private String emailTheme;// 邮件主题

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getSenderEmailPassword() {
		return senderEmailPassword;
	}

	public void setSenderEmailPassword(String senderEmailPassword) {
		this.senderEmailPassword = senderEmailPassword;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getEmailTheme() {
		return emailTheme;
	}

	public void setEmailTheme(String emailTheme) {
		this.emailTheme = emailTheme;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
