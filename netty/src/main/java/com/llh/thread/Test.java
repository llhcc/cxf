package com.llh.thread;

public class Test {

	public static void main(String[] args) {
		Count count = new Count();
		
		for(int i=0;i<1000;i++){
			Thread thread = new Thread(count);
			thread.start();
		}
		
	}

}
