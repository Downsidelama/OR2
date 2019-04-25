package btree;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteBTreeInterface extends Remote {
	BinaryTree switchRight(BinaryTree tree) throws RemoteException;
}
