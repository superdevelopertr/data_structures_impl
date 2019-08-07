package com.ailhanli.c04.tree;

public class BinaryTreeGenerate {

	public static Node generate(int[] a) {
		return generateUtil(a, 0, a.length - 1);
	}

	private static Node generateUtil(int[] a, int start, int end) {
		if (start > end) {
			return null;
		}

		int middle = (start + end) / 2;
		Node node = new Node(a[middle]);
		node.left = generateUtil(a, start, middle-1);
		node.right = generateUtil(a, middle + 1, end);
		return node;
	}

	public static void main(String[] args) {
		int[] numbers = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
		Node node = generate(numbers);
		BSTPrint.print(node);

	}
}