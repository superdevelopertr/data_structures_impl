package com.ailhanli.c05.bits;

public class HelloBitOperations {

	static boolean getBit(int number, int index) {
		return (number & (1<<index))!=0;
	}
	
	static int setBit(int number, int index) {
		return (number | (1<<index));
	}
	
	static int updateBit(int number, int index, boolean bitIs1) {
		int value = bitIs1 ? 1: 0;
		
		int mask = ~(1<<index);
		return (value & mask) | (value<<index);
	}
	
	static int clearBit(int number, int index) {
		return number & (~(1<<index));
	}
	
	public static void main(String[] args) {
		System.out.println(setBit(16,3));
		System.out.println(clearBit(16,3));
		
		System.out.println(0<<13);
		
		System.out.println(updateBit(32,3,false));
	}
	
}