package com.ailhanli.c04.tree;

import java.awt.font.NumericShaper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BinaryTree {

	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	@Data
	static class Node {
		Node left;
		Node right;
		Integer value;

		public Node(Integer value) {
			this.value = value;
		}
	}

	private Node root;

	public void insert(Integer value) {
		Node temp = root;
		root = insertUtil(temp, value);
	}

	public Node insertUtil(Node node, Integer value) {
		if (node == null) {
			node = new Node(value);
			return node;
		}

		if (value > node.value) {
			node.right = insertUtil(node.right, value);
		} else {
			node.left = insertUtil(node.left, value);
		}

		return node;
	}

	public void print() {
		Node temp = root;
		print(temp);
	}

	public static void print(Node temp) {
		if (temp == null) {
			return;
		}
		Queue<Node> holder = new LinkedList<>();
		holder.offer(temp);

		while (!holder.isEmpty()) {
			Node node = holder.poll();
			System.out.print(node.value + " ");
			if (node.left != null) {
				holder.offer(node.left);
			}
			if (node.right != null) {
				holder.offer(node.right);
			}
		}
	}

	public void write(Node temp, String whereToWrite) {
		if (temp == null) {
			return;
		}
		Queue<Node> holder = new LinkedList<>();
		holder.offer(temp);

		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(whereToWrite)))) {
			while (!holder.isEmpty()) {
				Node node = holder.poll();
				pw.print(node.value + " ");
				if (node.left != null) {
					holder.offer(node.left);
				}
				if (node.right != null) {
					holder.offer(node.right);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readFrom(String readFrom) {
		if (Files.exists(Paths.get(readFrom))) {
			String lines = null;
			try (BufferedReader reader = Files.newBufferedReader(Paths.get(readFrom))) {
				lines = reader.readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (lines != null) {
				for (String line : lines.trim().split(" ")) {
					insert(Integer.valueOf(line));
				}
			}
		}
	}

	public boolean remove(Integer numberToRemove) {
		Node parent = root;
		Node node = root;
		return removeUtil(parent, node, numberToRemove);
	}

	public boolean removeUtil(Node parent, Node node, Integer numberToRemove) {
		if (node == null) {
			return false;
		}

		if (numberToRemove > node.value) {
			return removeUtil(node, node.right, numberToRemove);
		} else if (numberToRemove < node.value) {
			return removeUtil(node, node.left, numberToRemove);
		} else {
			if (node.left == null && node.right == null) { // removing leaf
				if (numberToRemove > parent.value) {
					parent.right = null;
				} else {
					parent.left = null;
				}

			} else if (node.left == null && node.right != null) {
				parent.right = node.right;
			} else if (node.right == null && node.left != null) {
				parent.left = node.left;
			} else {
				Integer rightBigOne = pollRightSmallest(node, node);
				if (rightBigOne != 1) {
					node.value = rightBigOne;
				}
			}
			return true;
		}
	}

	public Integer pollRightSmallOne() {
		if (root == null || root.right==null ) {
			return -1;
		}
		return pollRightSmallest(root, root);
	}

	private Integer pollRightSmallest(Node parent, Node node) {

		if(node.right==null) {
			return -1;
		}else {
			Node temp = node.right;
			
			if(temp.left==null && temp.right==null) {
				parent.right = null;
				return temp.value;
			}else if(temp.left==null && temp.right!=null) {
				parent.left = temp.right;
			}
			return pollRightSmallest(temp, temp.left);
		}
	}

	@Test
	public void testInsert() {
		System.out.println("case testInsert is started");
		BinaryTree tree = new BinaryTree();
		tree.insert(12);
		tree.insert(10);
		tree.insert(20);
		tree.insert(5);
		tree.insert(11);
		tree.insert(25);
		tree.insert(18);
		tree.print();
		System.out.println("case testInsert is finished");
	}

	@Test
	public void testfindRightSmallOne() {
		System.out.println("case findRightSmallOne is started");
		BinaryTree tree = new BinaryTree();
		tree.insert(12);
		tree.insert(10);
		tree.insert(20);
		tree.insert(5);
		tree.insert(11);
		tree.insert(25);
		tree.insert(18);
		tree.print();
		System.out.println(tree.pollRightSmallOne());
		tree.print();
		System.out.println("case findRightSmallOne is finished");
	}

	@Test
	public void testRemove() {
		System.out.println("case testRemove is started");
		BinaryTree tree = new BinaryTree();
		tree.insert(12);
		tree.insert(10);
		tree.insert(20);
		tree.insert(5);
		tree.insert(11);
		tree.insert(25);
		tree.insert(18);
		tree.print();
		tree.remove(20);
		System.out.println();
		tree.print();
		System.out.println("case testRemove is finished");
	}

	@Test
	public void testWriteReadFromFile() {
		System.out.println("case testWriteReadFromFile is started");
		String file = "C:\\Users\\abdullah.ilhanli\\Documents\\data\\tree.txt";
		BinaryTree tree = new BinaryTree();
		tree.insert(12);
		tree.insert(10);
		tree.insert(20);
		tree.insert(5);
		tree.insert(11);
		tree.insert(25);
		tree.insert(18);
		tree.print();
		System.out.println();
		Node node = tree.root;
		tree.write(node, file);

		BinaryTree tree2 = new BinaryTree();
		tree2.readFrom(file);
		tree2.print();
		System.out.println("case testWriteReadFromFile is finished");
	}

}