package cn.yuhi.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cn.yuhi.dto.MimeMessageDTO;

/**
 * 类名称:  mailUtil
 * 功能描述: TODO 邮件发送例子
 * 创建人:  Gavin-Nie 
 * 创建时间: 2014-12-4 上午9:20:16 
 * @version  V1.0  
 */
public class MailUtil {
	
	/**   
	 * 变量名 userName: TODO 邮箱用户名
	 */   
	private String userName;
	
	/**   
	 * 变量名 password: TODO 邮箱地址
	 */   
	private String password;
	
	/**   
	 * 变量名 smtpHost: TODO 邮箱smtp地址，发送地址
	 */   
	private String smtpHost;
	
	/**   
	 * 变量名 targetAddress: TODO 目标邮箱地址
	 */   
	private String targetAddress;
	
	/** 
	 * 方法名: sendEmail 
	 * 功能描述: TODO 发送邮件
	 * @param: @param userName 邮箱账号
	 * @param: @param password 邮箱密码
	 * @param: @param targetAddress 目标邮箱地址
	 * @param: @param mimeDTO 邮件部分参数
	 * @return: boolean 
	 */
	public static boolean sendEmail(String userName,String password,String targetAddress,MimeMessageDTO mimeDTO){
		Properties props = new Properties();
		String hostname=SMTPUtil.SimpleMailSender(userName);
		System.out.println("当前邮箱host为:");
		props.put("mail.smtp.host", hostname);
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		if(hostname.indexOf(".qq.com")!=-1){
			props.setProperty("mail.smtp.socketFactory.port", "465");
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		}
		Session session = Session.getInstance(props, new PopupAuthenticator(userName, password));
		session.setDebug(false);
		try {
			Transport ts = session.getTransport();
			ts.connect(hostname,userName,password);
			Message message = createEmail(session,userName,targetAddress,mimeDTO);
			ts.sendMessage(message,message.getAllRecipients());
			ts.close();
			
			
		} catch (Exception mex) {
			mex.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 创建邮件
	 * @Author Casper
	 * Create_time:2015年10月17日 下午7:45:57
	 * description:
	 */
	public static Message createEmail(Session session,String userName,String regMail,MimeMessageDTO mimeDTO){
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(userName));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(regMail));
			message.setSubject(mimeDTO.getSubject());
			message.setContent(mimeDTO.getText(),"text/html;charset=UTF-8");
			message.saveChanges();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public String getTargetAddress() {
		return targetAddress;
	}
	public void setTargetAddress(String targetAddress) {
		this.targetAddress = targetAddress;
	}
}
