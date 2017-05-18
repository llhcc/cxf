package com.llh.jdk;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class Test {

	public static void main(String[] args) {
		//Collection c = (Collection) new List();
		Map map = new HashMap();
		/*AbstractCollection ac = 
		SortedMap
		AbstractMap
		NavigableMap
		TreeMap
		HashMap
		WeakHashMap
		Hashtable
		Dictionary
		ArrayList
		
		Vector
		Stack*/
		
		WeakHashMap w = new WeakHashMap();
		w.put("key", "test");
		System.out.println(w.get("key"));
		
		//Array
		String s = new String("22");
		char[] ch = {2,3,'w'};
		char value[] = "".toCharArray();
		//Character
		
		//StringBuilder
		//StringBuffer
		
		//Thread
		//Comparable;
		//Comparator;
		
		
		
	}

}
