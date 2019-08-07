package com.ailhanli.c04.tree;

import java.util.LinkedList;
import java.util.List;

public class BSTLevelHolder {

	public static List<List<Node>> getNodesLevelByLevel(Node root) {
		if (root == null) {
			return null;
		}

		List<List<Node>> result = new LinkedList<>();
		List<Node> current = new LinkedList<>();
		current.add(root);
		while (current.size() > 0) {
			result.add(current);
			List<Node> parent = current;
			current = new LinkedList<>();
			for (Node node : parent) {
				if (node.left != null) {
					current.add(node.left);
				}
				if (node.right != null) {
					current.add(node.right);
				}
			}
		}

		return result;
	}

	public static List<LinkedList<Node>> getNodesLevelByLevel2(Node root) {
		List<LinkedList<Node>> holder = new LinkedList<LinkedList<Node>>();
		getNodesLevelByLevel2Util(root, holder, 0);
		return holder;
	}

	private static void getNodesLevelByLevel2Util(Node root, List<LinkedList<Node>> holder, int level) {
		if (root == null) {
			return;
		}

		LinkedList<Node> currentHolder = null;
		if (holder.size() == level) {
			currentHolder = new LinkedList<Node>();
			holder.add(currentHolder);
		} else {
			currentHolder = holder.get(level);
		}
		currentHolder.add(root);

		getNodesLevelByLevel2Util(root.left, holder, level + 1);
		getNodesLevelByLevel2Util(root.right, holder, level + 1);
	}

	public static void main(String[] args) {

		Node root = BinaryTreeGenerate.generate(new int[] { 10, 20, 30, 40, 50, 60, 70, 80, 90 });
		List<List<Node>> result = getNodesLevelByLevel(root);
		result.get(2).forEach(n -> System.out.println(n.value));

		System.out.println();
		List<LinkedList<Node>> result2 = getNodesLevelByLevel2(root);
		result2.get(2).forEach(n -> System.out.println(n.value));
	}
}