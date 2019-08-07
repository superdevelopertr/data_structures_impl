package com.ailhanli.c04.tree;

public class IsTreeABST {

	public boolean isBST(Node node) {
		return isBSTUtil(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public boolean isBSTUtil(Node node, Integer min, Integer max) {
		if (node == null) {
			return true;
		}

		if ((min != null && node.value < min) || (max != null && node.value > max)) {
			return false;
		}

		if (!isBSTUtil(node.left, min, node.value) || !isBSTUtil(node.right, node.value, max)) {
			return false;
		}

		return true;
	}

}