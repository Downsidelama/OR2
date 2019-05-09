import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DominoDeploy {
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		Registry reg = LocateRegistry.createRegistry(23456);

		reg.bind("domino", new DominoImpl());
	}
}
