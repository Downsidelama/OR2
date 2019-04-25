package btree;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
	public static void main(String[] args) throws RemoteException, NotBoundException {
		String srvAddr = "localhost";
		int srvPort = 12345;
		BinaryTree tree = new BinaryTree(new Node(20));
		tree.insert(new Node(3));
		tree.insert(new Node(25));
		
		System.out.println(tree);

		Registry registry = LocateRegistry.getRegistry(srvAddr, srvPort);
		// Registry registry = LocateRegistry.getRegistry();

		RemoteBTreeInterface rmiServer = (RemoteBTreeInterface) (registry.lookup("btree"));
		
		BinaryTree sTree = rmiServer.switchRight(tree);
		System.out.println(sTree);
	}
}
