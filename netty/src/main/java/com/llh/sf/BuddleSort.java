package com.llh.sf;

import java.util.Arrays;

public class BuddleSort {

	public static void main(String[] args) {
		int a[] = {2,1,3,5,4};
		for(int i=0;i<a.length;i++){
			for(int j=i;j<a.length;j++){
				int temp = 0;
				if(a[i] < a[j]){
					temp = a[i];
					a[i] = a[j];
					a[j] =temp;
				}
			}
		}
		System.out.println(Arrays.toString(a));
		/*for(int i=0;i<a.length;i++){
			System.out.print(a[i] + " ");
		}*/
	}
	
	public void bSort(){
		
	}

}
