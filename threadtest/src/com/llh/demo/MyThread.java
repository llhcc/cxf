package com.llh.demo;

public class MyThread extends Thread{

	private static int i = 0;
	
	@Override
	public void run() {
		i++;
		System.out.println("MyThread.run();" + i);
	}

	
}
