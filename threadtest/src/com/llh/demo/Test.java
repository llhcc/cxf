package com.llh.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

	public static void main(String[] args) {
		/*for(int i=0; i < 10000; i++){
			MyThread myThread = new MyThread();
			myThread.start();
		}*/
		
		/*for(int i=0; i < 10000; i++){
			MyRunnable myRunnable = new MyRunnable();
			Thread thread = new Thread(myRunnable);
			thread.start();
		}*/
		
		
		ExecutorService pool = Executors.newFixedThreadPool(2);
		List<Future> list = new ArrayList<Future>();
		for(int i=0; i < 10000; i++){
			Callable c = new MyCallable(i + "");
			Future f = pool.submit(c);
			list.add(f);
		}
		pool.shutdown();
		for(Future f : list){
			try {
				System.out.println("---" + f.get().toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
