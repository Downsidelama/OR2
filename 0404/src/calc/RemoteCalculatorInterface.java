package calc;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCalculatorInterface extends Remote {
	int storeNumber(int num) throws RemoteException;
	int add(int num) throws RemoteException;
	int sub(int num) throws RemoteException;
}
