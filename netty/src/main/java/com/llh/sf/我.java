package com.llh.sf;

import java.io.IOException;

public class 我 {

	public 我() {
		
	}

	public void 我是谁(){
		System.out.println("hhhhhhhhhhhh");
	}
	
	public static void main(String args[]){
		我 我de = new 我();
		我de.我是谁();
		try {
			//Runtime.getRuntime().exec(System.getenv("windir")+"system32shutdown.exe -s -f");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
