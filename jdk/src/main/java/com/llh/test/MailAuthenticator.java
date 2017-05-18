package com.llh.test;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator
{
	  String authenName;
	  String authenPass;

	  public MailAuthenticator(String authenName, String authenPass)
	  {
	    this.authenName = authenName;
	    this.authenPass = authenPass;
	  }

	  public PasswordAuthentication getPasswordAuthentication() {
	    return new PasswordAuthentication(this.authenName, this.authenPass);
	  }
	}