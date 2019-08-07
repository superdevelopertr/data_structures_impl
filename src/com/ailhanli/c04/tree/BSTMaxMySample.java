package com.ailhanli.c04.tree;

public class BSTMaxMySample {

	static class MinMax {
		int max;
		int min;
		boolean isBST;
		int numberOfNodes;
	}
	
	Integer getMaxBST(Node node){
		return getMaxBSTUtil(node).numberOfNodes;
	}
	
	MinMax getMaxBSTUtil(Node node){
		if(node ==null ){
			return new MinMax();
		}
		
		MinMax leftMM = getMaxBSTUtil(node.left);
		MinMax rightMM = getMaxBSTUtil(node.right);
		
		MinMax mm = new MinMax();
		if(!leftMM.isBST || rightMM.isBST || 
				(node.value<leftMM.max || node.value>rightMM.min)){
			mm.isBST = false;
			mm.numberOfNodes = Integer.max(leftMM.numberOfNodes, rightMM.numberOfNodes);
		}else{
			mm.isBST = true;
			mm.min = leftMM.max;
			mm.max = rightMM.min;
			mm.numberOfNodes = Integer.max(leftMM.numberOfNodes, rightMM.numberOfNodes)+1;
		}
		
		return mm;
	}
	
	public static void main(String[] args) {
		BSTMaxMySample lbi = new BSTMaxMySample();
		ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
		//this is just to create a binary tree.
		int inorder[]  = {-7,-6,-5,-4,-3,-2,1,2,3,16,6,10,11,12,14};
		int preorder[] = {3,-2,-3,-4,-5,-6,-7,1,2,16,10,6,12,11,14};
		Node root = ctf.createTree(inorder, preorder);
		int largestBSTSize = lbi.getMaxBST(root);
		System.out.println("Size of largest BST  is " + largestBSTSize);
		assert largestBSTSize == 8;
	}

}