package com.ailhanli.c05.bits;

public class Insertion {

	public static int getBit(int number, int index) {
		return number & (1<<index);
	}
	
	public static int setBit(int number, int index) {
		return number | (1<<index);
	}
	
	public static int clearBits(int number, int index) {
		
		return number & ~(1<<index);
	}
	
	public static int updateBit(int number, int index, boolean bitisl) {
		int value = bitisl? 1: 0;
		
		int mask = ~(1<<index);
		return (number & mask) | (value<<index);
	}
	
	public static Integer insert(Integer number1,Integer number2, Integer start, Integer end) {
		if(start<0 || end<0 || start>end || (end-start)>Integer.toBinaryString(number1).length()) {
			throw new IllegalArgumentException();
		}
		
		System.out.println(Integer.toBinaryString(number1));
		System.out.println(Integer.toBinaryString(number2));
		int j=0;
		for (int i = start;i<end;i++) {
			
			int bit = getBit(number2, j++);

			number1 = updateBit(number1, i, bit==1);
			System.out.println(Integer.toBinaryString(number1));
		}
		
		
		return number1;
	}
	
	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(insert(1024, 19, 2,6)));
	}

}