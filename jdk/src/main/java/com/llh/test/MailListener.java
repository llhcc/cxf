package com.llh.test;

import javax.mail.Address;
import javax.mail.event.TransportAdapter;
import javax.mail.event.TransportEvent;
import javax.mail.internet.InternetAddress;

public class MailListener extends TransportAdapter
{
	  public MailListener() {
	    System.out.println("监听器已启动！！！！！！！！");
	  }

	  public void messageDelivered(TransportEvent e) {
	    System.out.println("[邮件实际发送状态:]成功！");
	    Address[] addr = e.getValidSentAddresses();
	    StringBuffer sb = new StringBuffer();
	    sb.append("[邮件发送成功地址:]<");
	    addToString(sb, addr);
	    sb.append(">");
	    System.out.println(sb.toString());
	  }

	  public void messageNotDelivered(TransportEvent e) {
	    System.out.println("[邮件实际发送状态:]失败！");
	    Address[] fail = e.getValidUnsentAddresses();
	    StringBuffer sbf = new StringBuffer();
	    sbf.append("[邮件发送失败地址：]<");
	    addToString(sbf, fail);
	    sbf.append(">");
	    System.out.println(sbf.toString());

	    Address[] invalid = e.getInvalidAddresses();
	    if (invalid != null) {
	      StringBuffer sbi = new StringBuffer();
	      sbi.append("[邮件错误地址：]<");
	      addToString(sbi, invalid);
	      sbi.append(">");
	      System.out.println(sbi.toString());
	    }
	  }

	  public void messagePartiallyDelivered(TransportEvent e) {
	    System.out.println("[邮件实际发送状态:]部分发送成功！！");
	    Address[] succ = e.getValidSentAddresses();
	    StringBuffer sb = new StringBuffer();
	    sb.append("[邮件发送成功地址:]<");
	    addToString(sb, succ);
	    sb.append(">");
	    System.out.println(sb.toString());

	    Address[] fail = e.getValidUnsentAddresses();
	    StringBuffer sbf = new StringBuffer();
	    sbf.append("[邮件发送失败地址：]<");
	    addToString(sbf, fail);
	    sbf.append(">");
	    System.out.println(sbf.toString());

	    Address[] invalid = e.getInvalidAddresses();
	    if (invalid != null) {
	      StringBuffer sbi = new StringBuffer();
	      sbi.append("[邮件错误地址：]<");
	      addToString(sbi, invalid);
	      sbi.append(">");
	      System.out.println(sbi.toString());
	    }
	  }

	  private StringBuffer addToString(StringBuffer sb, Address[] addr) {
	    if (addr != null) {
	      int c = addr.length;
	      for (int i = 0; i < c; ++i) {
	        InternetAddress idd = (InternetAddress)addr[i];
	        if (i == 0)
	          sb.append(idd.toString());
	        else {
	          sb.append(";" + idd.toString());
	        }
	      }
	    }
	    return sb;
	  }
}