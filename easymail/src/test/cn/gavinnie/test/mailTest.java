package test.cn.gavinnie.test;

import java.util.Date;

import org.junit.Test;

import cn.yuhi.dto.MimeMessageDTO;
import cn.yuhi.util.MailUtil;

public class mailTest {

	@Test
	public void testSendEmail() {
		
//		//测试163邮箱
		//测试qq邮箱 
		//新浪邮箱 13077403326m@sina.cn
		//139邮箱 13077403326@139.com
		
		
		String userName = "****@139.com";   //用户邮箱地址
		String password = "*****";    //密码或者授权码
		String targetAddress = "****@139.com";	 //接受者邮箱地址
		
		//设置邮件内容
		MimeMessageDTO mimeDTO = new MimeMessageDTO();
		mimeDTO.setSentDate(new Date());
		mimeDTO.setSubject("邮件的标题");
		mimeDTO.setText("邮件的内容"+targetAddress);
		
		//发送邮件
		if(MailUtil.sendEmail(userName, password, targetAddress, mimeDTO)){
			System.out.println("邮件发送成功！");
		}else{
			System.out.println("邮件发送失败!!!");
		}
		
	}

}
