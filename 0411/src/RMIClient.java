import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class RMIClient {
	static String host = "localhost";
	static int port = 12345;

	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.getRegistry(host, port);

		Arrays.stream(registry.list()).filter(x -> {
			try {
				return (((LottoInterface) registry.lookup(x)).nyeroszamE());
			} catch (RemoteException | NotBoundException e1) {
				e1.printStackTrace();
			}
			return false;
		}).forEach(x -> System.out.println(x));

	}

}
