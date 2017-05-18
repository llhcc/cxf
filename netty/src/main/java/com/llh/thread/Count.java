package com.llh.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Count implements Runnable {

	private AtomicInteger count = new AtomicInteger(0);
	
	private AtomicLong count1 = new AtomicLong(0L);
	
	@Override
	public void run(){
		System.out.println(count.incrementAndGet());
		
		System.out.println("Long=" + count1.incrementAndGet());
	}
}
