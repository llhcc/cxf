package com.llh.demo;

public class MyRunnable implements Runnable	{

	private static int i = 0;
	
	@Override
	public void run() {
		i++;
		System.out.println("MyRunnable.run();" + i);
	}

}
