package com.llh.test;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Test1 {

	public static void main(String[] args) throws Exception {  
        //创建session对象  
        Properties props = new Properties();  
        props.setProperty("mail.smtp.auth", "true");  
        props.setProperty("mail.transport.protocol", "smtp");//没写的时候  javax.mail.NoSuchProviderException: Invalid protocol: null  
        Session session = Session.getInstance(props);  
        session.setDebug(true);  
          
        //创建message对象  
        Message msg = new MimeMessage(session);  
        msg.setText("你好吗？");  
        msg.setFrom(new InternetAddress("corporate@mangocity.com"));  
        Transport transport = session.getTransport();  
        transport.connect("smtp.mangocity.com",25, "corporate@mangocity.com", "hn#24671");  
        transport.sendMessage(msg,new Address[]{new InternetAddress("longhui.lan@mangocity.com")});  
        //transport.send(msg,new Address[]{new InternetAddress("lisi@sina.com")});  
        transport.close();  
    }  
}
