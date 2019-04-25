package btree;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIDeploy {
	public static void main(String args[]) throws Exception {
		final int PORT = 12345;

		Registry registry = LocateRegistry.createRegistry(PORT);
		// Registry registry = LocateRegistry.getRegistry();

		registry.rebind("btree", new RemoteBTreeServer());
	}
}
