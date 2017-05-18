package com.llh.acegi;

import java.io.BufferedReader;

public class Test {

	public static void main(String[] args) {
		String queryString = "http://tmc1.mangocity.com/mtmc/f/fly.shtml?arrDate=20170110&depDate=20170107&timeStamp=20170106105412&aliasId=010000209826644&classLevel=&categoryCode=0000000100&isNegotiatedPrice=N&type=01&depCity=%E5%B9%BF%E5%B7%9E&companyCode=PNW&arrCity=%E4%B8%8A%E6%B5%B7&lineType=rt&cmd=list&source=ck_888010&deptCode=WL&encryptKey=DF5A0997CBC0FFA182173E6795E38E03";
	    boolean signCheck = checkMd5(queryString);
	    System.out.println(signCheck);
	}
		
		private static boolean checkMd5(String postData) {
			try {
				String[] data = postData.split("&encryptKey=");
				String queryString = data[0];
				String encryptKey = data[1];
				String encryptKey2 = MD5Utils.sign(queryString + "&FCC3251D1DA02B5841C1666F0604EE1A").toUpperCase();
				System.out.println("encryptKey:"+encryptKey+"  encryptKey2:"+encryptKey2);
				if (encryptKey2.equals(encryptKey)) {
					return true;
				}
			} catch (Exception e) {
			}
			return false;
		}	

}
