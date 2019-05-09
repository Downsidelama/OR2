import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DominoImpl extends UnicastRemoteObject implements DominoStorageIface {
	Map<String, List<Domino>> userToDominos = new HashMap<>();

	public DominoImpl() throws RemoteException {
	}
	
	@Override
	public void save(String username, List<Domino> dominos) throws RemoteException {
		userToDominos.put(username, dominos);
	}

	@Override
	public List<Domino> load(String username) throws RemoteException {
		return userToDominos.get(username);
	}

}
