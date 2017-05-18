package com.llh.sierpinski;

public class Test_hnt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		move(3,"x","y","z");
	}
	
	public static void move(int n,String x,String y,String z){
		if(n==1){
			System.out.println(x + "-->" + z);
		}else{
			move(n-1,x,z,y);
			System.out.println(x + "-->" + z);
			move(n-1,y,x,z);
		}
	}

}
