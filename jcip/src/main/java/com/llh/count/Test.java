package com.llh.count;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Test {

	public static void main(String args[]){
		long start = System.currentTimeMillis();
		//File file = new File("E:/mtmc_log/mtmc/82/logs/server.log");
		File file = new File("E:/tmp/count/output.txt");
		long length = file.length();
		try {
			Map<String,Integer> map = new HashMap<String,Integer>();
			BufferedReader read = new BufferedReader(new FileReader(file));
			String temp = "";
			System.out.println(temp.isEmpty());
			int line = 0;
			while((temp = read.readLine()) != null){
				System.out.println("line = " + (++line));
				String str[] = temp.split(" ");
				for(String s : str){
					Integer num = map.get(s);
					if(null != num){
						map.put(s, num+1);
					}else{
						map.put(s, 1);
					}
				}
			}
			 Set<Entry<String, Integer>>  set = map.entrySet();
			 Iterator<Entry<String, Integer>> iter = set.iterator();
			 int count = 0;
			 while(iter.hasNext()){
				 Entry<String, Integer> entry = iter.next();
				 System.out.println(entry.getKey() + " ==========" + entry.getValue());
				 count = count + entry.getValue();
			 }
			System.out.println("count==" + count);
			long end = System.currentTimeMillis();
			System.out.println("time="+ (end-start) + "ms");
			
			/*count==4060000
					time=49761ms*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ThreadMain extends Thread{
	RandomAccessFile file ;
	long start;
	long end;
	String no;
	
	public ThreadMain(File file,long start,long end,String no){
		try {
			this.file = new RandomAccessFile(file,"rw");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.start = start;
		this.end = end;
		this.no = no;
	}
	
	@Override
	public void run(){
		
	}
	
	private void thread(){
		
	}
	
}
