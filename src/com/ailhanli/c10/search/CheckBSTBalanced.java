package com.ailhanli.c10.search;

public class CheckBSTBalanced {

	public boolean isBalanced(Node root) {
		if (root == null) {
			return true;
		}

		int heightDiff = Math.abs(height(root.left) - height(root.right));
		if (heightDiff > 1) {
			return false;
		}

		return isBalanced(root.left) && isBalanced(root.right);
	}

	private int height(Node node) {
		if (node == null) {
			return -1;
		}

		return 1 + Math.max(height(node.left), height(node.right));
	}
	
	public boolean isBalancedV2(Node root) {
		return isBalancedV2(root,new Height());
	}

	private boolean isBalancedV2(Node root, Height height) {
		if(root == null) {
			height.setValue(0);
			return true;
		}
		Height lh = new Height(), rh=new  Height();
		boolean lb = isBalancedV2(root.left, lh);
		boolean rb = isBalancedV2(root.right, rh);
		
		height.setValue(Math.max(lh.getValue(), rh.getValue())+1);

		if(Math.abs(lh.getValue()-rh.getValue())>2) {
			return false;
		}
		
		return lb & rb;
	}

	public static void main(String[] args) {
		Node node = new Node(1);
		node.right = new Node(2);
		node.right.right = new Node(3);
		CheckBSTBalanced bst = new CheckBSTBalanced();
		System.out.println(bst.isBalanced(node));
		
		System.out.println(Integer.MIN_VALUE);
	}
}