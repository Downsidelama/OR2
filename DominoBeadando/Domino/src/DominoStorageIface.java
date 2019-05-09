import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DominoStorageIface extends Remote {
	void save(String username, List<Domino> dominos) throws RemoteException;
	List<Domino> load(String username) throws RemoteException;
}
