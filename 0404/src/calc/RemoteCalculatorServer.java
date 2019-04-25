package calc;

import java.rmi.RemoteException;

public class RemoteCalculatorServer
extends java.rmi.server.UnicastRemoteObject
implements RemoteCalculatorInterface{
	private static final long serialVersionUID = 1032397692094673217L;
	int num;
	
	protected RemoteCalculatorServer() throws RemoteException {
		super();
	}
	
	public RemoteCalculatorServer(int num) throws RemoteException {
		this.num = num;
	}

	@Override
	public int storeNumber(int num) throws RemoteException {
		this.num = num;
		return num;
	}

	@Override
	public int add(int num) throws RemoteException {
		this.num += num;
		return this.num;
	}

	@Override
	public int sub(int num) throws RemoteException {
		this.num -= num;
		return this.num;
	}

}
