import java.rmi.RemoteException;

public class RemoteLottoServer extends java.rmi.server.UnicastRemoteObject implements LottoInterface {

	boolean winner;

	protected RemoteLottoServer(boolean b) throws RemoteException {
		super();
		this.winner = b;
	}

	private static final long serialVersionUID = 399303342881417114L;

	@Override
	public boolean nyeroszamE() throws RemoteException {
		// TODO Auto-generated method stub
		return winner;
	}

}
