package com.ailhanli.c04.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BSTPrint {

	public static void print(Node root) {
		Node node = root;
		if(node!=null) {
			Queue<Node> holder = new LinkedList<>();
			holder.add(node);
			while(!holder.isEmpty()) {
				Node temp = holder.poll();

				System.out.print(temp.value+" ");
				if(temp.left!=null) holder.add(temp.left);
				if(temp.right!=null) holder.add(temp.right);
			}
		}
	}
	
}
