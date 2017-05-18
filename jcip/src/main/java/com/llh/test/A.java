package com.llh.test;

import java.util.Random;

public class A {

	public static void main(String args[]){
		int cpu = Runtime.getRuntime().availableProcessors();
		System.out.println(cpu);
		Random rnd = new Random();
		System.out.println(rnd.nextInt(5));
	}
}
