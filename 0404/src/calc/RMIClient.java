package calc;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RMIClient {
	public static void main(String args[]) throws Exception {
		String srvAddr = "localhost";
		int srvPort = 12345;
		String srvName = args[0];
		String num = args[1];

		Registry registry = LocateRegistry.getRegistry(srvAddr, srvPort);
		// Registry registry = LocateRegistry.getRegistry();

		RemoteCalculatorInterface rmiServer = (RemoteCalculatorInterface) (registry.lookup(srvName));

		System.out.println(rmiServer.getClass().getName());

		int[] nums = { 10, 20, 30, 13 };

		int reply = rmiServer.add(nums[0]);

		System.out.println(reply);
		
		reply = rmiServer.sub(nums[1]);

		System.out.println(reply);
		
		reply = rmiServer.add(nums[2]);

		System.out.println(reply);
		
		reply = rmiServer.sub(nums[3]);

		System.out.println(reply);

	}
}
