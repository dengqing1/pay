package com.myd.util;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

import java.util.Properties;
/**
 * 邮件工具类
 */
public class MailUtil {
	
	
    private static final String MAIL_HOST = "smtp.163.com";
    private static final String MAIL_TRANSPORT_PROTOCOL = "smtp";
    private static final String USER = "z_francis@163.com";
    // 这个秘密不是邮箱的密码，是要去163邮箱开通申请得到的
    private static final String PASSWORD = "zkt13579";

	
    /**
     * 发送邮件
     * @param to 给谁发
     * @param text 发送内容
     * @throws Exception 
     */
    public static void send_mail(String to,String text) throws MessagingException, Exception {
        //创建连接对象 连接到邮件服务器
        Properties prop = new Properties();
        //设置发送邮件的基本参数
     // 开启debug调试，以便在控制台查看
//        prop.setProperty("mail.debug", "true"); 
        // 设置邮件服务器主机名
        prop.setProperty("mail.host", MAIL_HOST);
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", MAIL_TRANSPORT_PROTOCOL);

        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        
     // 创建session
        Session session = Session.getInstance(prop);
        // 通过session得到transport对象
        Transport ts = session.getTransport();
        // 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
        ts.connect(MAIL_HOST,USER, PASSWORD);
        // 创建邮件
        Message message = createSimpleMail(session,to,text);
        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
        
    }
    
    /**
    * @Method: createSimpleMail
    * @Description: 创建一封只包含文本的邮件
    */
    public static MimeMessage createSimpleMail(Session session,String to,String text)
    throws Exception {
    // 创建邮件对象
    MimeMessage message = new MimeMessage(session);
    // 指明邮件的发件人
    message.setFrom(new InternetAddress(USER));
    // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
    message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
    // 邮件的标题
    message.setSubject("邮箱验证码");
    // 邮件的文本内容
    message.setContent(text, "text/html;charset=UTF-8");
    // 返回创建好的邮件对象
    return message;
    }
    
    
    public static void main(String[] args) throws Exception {
        try {
            MailUtil.send_mail("179647302@qq.com", "1234");
            System.out.println("邮件发送成功!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}