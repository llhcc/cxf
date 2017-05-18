package com.llh.servlet;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;

public class Test {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
        for(int i=0; i < 100; i++){
        	new Thread(new Runnable(){
				@Override
				public void run() {
					try {
		        		HttpClient http = new DefaultHttpClient();
		        		//String smsUrl="http://localhost:8082/jcip/unsafeCountingFactorizer";  
		        		//String smsUrl="http://localhost:8082/jcip/async.do"; 
		        		//String smsUrl="http://localhost:8082/jcip/unsafeCachingFactorizer"; 
		        		String smsUrl="http://localhost:8082/jcip/cachedFactorizer"; 
		                HttpPost httppost = new HttpPost(smsUrl); 
		    			HttpResponse response = http.execute(httppost);
		    			if (response.getStatusLine().getStatusCode() == 200) {
		    				System.out.println("-------OK----");
		    			}
		    		} catch (ClientProtocolException e) {
		    			e.printStackTrace();
		    		} catch (IOException e) {
		    			e.printStackTrace();
		    		}  
				}
        	}).start();
        	
        }
        
	}

}
