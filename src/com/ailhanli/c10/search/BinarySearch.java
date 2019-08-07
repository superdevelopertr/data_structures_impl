package com.ailhanli.c10.search;

public class BinarySearch {

	public static boolean find(int[]a, int number) {
		return findUtil(a,number, 0, a.length-1);
	}
	
	private static boolean findUtil(int[] a, int number, int start, int end) {
		if(start>end) {
			return false;
		}
		
		int middle = (start + end ) / 2;
		if(number == a[middle]) {
			return true;
		}else if(number> a[middle]) {
			return findUtil(a, number, middle+1, end); 
		}else {
			return findUtil(a, number, start, middle-1); 
		}
	}
	
	public static void main(String[] args) {
		int[] numbers = {10,20,30,40,50,60,70,80,90};
		System.out.println(find(numbers, 55));

	}
}