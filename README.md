## java邮件发送 easymail


## 说明

在java的邮件发送上有大多的重复操作和写一大堆的对不同的邮件地址的封装库，非常不方便，该库就是为了解决程序员对不同的邮件发送上的便利

     
### 依赖
    依赖于java原生的mail包


## 测试  
目前已经对国内主流的邮箱与国外出名的邮箱(需VPN)进行了测试,发现各有一点点的不同，请大家注意密码与授权码
测试邮箱条目如下：
1. 163邮箱
2. 162邮箱
3. 139邮箱
4. 新浪邮箱
5. qq邮箱 
6. yahoo邮箱
7. 谷歌
8. outlook很遗憾没空测试

## 使用说明

三句话即可发送，暂时支持10多种邮箱发送，附件，群发 ，如果有问题请告诉我改下，后面如果有需求会增加模板发送，图文信息，定时延时等等

## 测试

### easymail下载链接:  [百度云下载](http://pan.baidu.com/s/1hs8qFBI)
### 源代码： [easymail](https://github.com/27786653/easymail/tree/master)
### 调试代码如下：
    
```
		public static void main(String[] args) {
		
		String userName = "****@139.com";   //用户邮箱地址
		String password = "*****";    //密码或者授权码
		String targetAddress = "****@139.com";	 //接受者邮箱地址


		// 设置邮件内容
		MimeMessageDTO mimeDTO = new MimeMessageDTO();
		mimeDTO.setSentDate(new Date());
		mimeDTO.setSubject("邮件的标题");
		mimeDTO.setText("邮件的内容"+targetAddress);

//		// 发送单邮件
		if (MailUtil.sendEmail(userName, password, targetAddress, mimeDTO)) {
			System.out.println("邮件发送成功！");
		} else {
			System.out.println("邮件发送失败!!!");
		}
		// 发送单邮件(附件)
		List<String> filepath=new ArrayList<String>();
		filepath.add("D:/temple.xls");
		filepath.add("D:/test.xls");
		if (MailUtil.sendEmailByFile(userName, password, targetAddress, mimeDTO,filepath)) {
			System.out.println("邮件发送成功！");
		} else {
			System.out.println("邮件发送失败!!!");
		}
		// 群发邮件
		targetAddress = "*******@qq.com,************@qq.com";
		if (MailUtil.sendGroupEmail(userName, password, targetAddress, mimeDTO)) {
			System.out.println("邮件发送成功！");
		} else {
			System.out.println("邮件发送失败!!!");
		}
//		// 群发邮件(附件)
		if (MailUtil.sendGroupEmailByFile(userName, password, targetAddress, mimeDTO,filepath)) {
			System.out.println("邮件发送成功！");
		} else {
			System.out.println("邮件发送失败!!!");
		}

	}
```

   
## 最后
    大家如果是使用spring的话，可以与spring整合