package com.llh.sf;

public class FastSort {

	public static void main(String args[]){
		int a[] = {1,4,2,5,8,3,2};
		fastSort(a,0,a.length-1);
		for(int i=0;i<a.length;i++){
			System.out.print(a[i] + " ");
		}
	}
	
	public static void fastSort(int a[],int start,int end){
		if(start < end){
			int mid = getMid(a,start,end);
			fastSort(a,start,mid-1);
			fastSort(a,mid+1,end);
		}
	}
	
	public static int getMid(int a[],int start,int end){
		int temp = a[start];
		while(start < end){
			while(a[end] >= temp &&  start < end){
				end--;
			}
			a[start] = a[end];
			while(a[start] < temp && start < end){
				start++;
			}
			a[end] = a[start];
		}
		a[start] = temp;
		return start;
	}
	
}
