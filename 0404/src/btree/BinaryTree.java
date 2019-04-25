package btree;

import java.io.Serializable;

public class BinaryTree implements Serializable {
	private static final long serialVersionUID = -7266145205395388556L;
	Node root;

	BinaryTree(Node root) {
		this.root = root;
	}
	
	public void insert(Node node) {
		root.insert(node);
	}
	
	@Override
	public String toString() {
		return root.toString();
	}
}
