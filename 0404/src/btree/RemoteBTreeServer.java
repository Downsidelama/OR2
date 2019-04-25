package btree;

import java.rmi.RemoteException;

public class RemoteBTreeServer extends java.rmi.server.UnicastRemoteObject implements RemoteBTreeInterface {
	private static final long serialVersionUID = -4943924520393549576L;
	BinaryTree tree;

	protected RemoteBTreeServer() throws RemoteException {
		super();
		tree = new BinaryTree(new Node(10));
		tree.insert(new Node(3));
		tree.insert(new Node(1));
		tree.insert(new Node(15));
		tree.insert(new Node(14));
	}

	@Override
	public BinaryTree switchRight(BinaryTree tree) {
		Node current = this.tree.root.right;
		this.tree.root.right = tree.root.right;
		tree.root.right = current;
		return tree;
	}
}
