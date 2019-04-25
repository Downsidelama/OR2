package btree;

import java.io.Serializable;

public class Node implements Serializable {
	private static final long serialVersionUID = -4858478045710710947L;
	int value;
	Node left = null;
	Node right = null;

	Node(int value) {
		this.value = value;
	}

	public void insert(Node node) {
		System.out.println(node.value);
		if (node.value < this.value) {
			if (left == null) {
				left = node;
			} else {
				left.insert(node);
			}
		} else if (node.value > this.value) {
			if (right == null) {
				right = node;
			} else {
				right.insert(node);
			}
		}
	}
	
	@Override
	public String toString() {
		return "value: " + value +
				" left: " + (left == null ? "null" : left.toString()) +
				" right: " + (right == null ? "null" :  right.toString());
	}
}
