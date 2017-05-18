package com.llh.servlet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FinalTest {

	final static int i = 0;
	
	static final int j = 0;
	
	final static Test test = new Test();
	
	public static void main(String[] args) {
		final String s ;
		  Integer a = 128;
		  Integer b = 128;
		  boolean flag = (a==b);
		System.out.println(a==b);
		s = "321";
		//a = 100;
		//test = new Test();
		final StringBuffer sb = new StringBuffer("ss");
		//sb = new StringBuffer();
        sb.append("2");// 改变对象的内容,对  
        System.out.println(sb.toString());
        
        Map<String,String> map = new HashMap<String,String>();
        Set<Entry<String,String>> set = map.entrySet();
        Iterator<Entry<String,String>> item = set.iterator();
        
	}

}
