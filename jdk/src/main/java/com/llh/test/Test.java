package com.llh.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String userName = "corporate@mangocity.com";
		String password = "hn#24671";
		
		MailAuthenticator ma = new MailAuthenticator(userName, password);
		 InputStream fis = null;
		    try {
		      fis = new FileInputStream("/usr/local/tomcat/22.eml");
		    }
		    catch (Exception e)
		    {
			      try {
					throw new Exception("载入eml文件出错！", null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		    }
		    Properties props = System.getProperties();
		    
		    props.put("mail.smtp.host", "smtp.mangocity.com");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.debug", "false");
		    
		    Session s = Session.getInstance(props, ma);
		    s.setDebug(false);
		    Transport transport = null;
		    try {
		    MimeMessage mimeMessage = new MimeMessage(s, fis);
		    Address[] adds1= mimeMessage.getFrom();
		    for(Address aa : adds1){
		    	  InternetAddress t = (InternetAddress) aa;
		    	  System.out.println("======" + t.getAddress());
		      }
		      transport = s.getTransport("smtp");
		      transport.addTransportListener(new MailListener());
		      transport.connect();
		      Address[] adds = mimeMessage.getAllRecipients();
		      for(Address a : adds){
		    	  InternetAddress t = (InternetAddress) a;
		    	  System.out.println("======" + t.getAddress());
		      }
		      transport.sendMessage(mimeMessage, new Address[]{new InternetAddress("longhui.lan@mangocity.com")});
		    }
		    catch (Exception me) {
		      try {
				throw new Exception(me.getMessage(), me);
			} catch (Exception e) {
				e.printStackTrace();
			}
		    }
		    finally {
		      try {
		        transport.close();
		        fis.close();
		        System.gc();
		      } catch (Exception e) {
		        try {
					throw new Exception(e.getMessage(), e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		      }
		    }
		    
		    
	}

}
