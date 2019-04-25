package nyolcas;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
	List<Socket> connections = new ArrayList<>();
	ServerSocket ss;
	String[] names = new String[2];
	String[] messages = new String[2];
	
	public void start() throws IOException {
		ss = new ServerSocket(12345);
		
		for(int i = 0; i < 2; i++) {
			connections.add(ss.accept());
		}
		
		for(int i = 0; i < 2; i++) {
			Scanner scn = new Scanner(connections.get(i).getInputStream());
			if (scn.hasNextLine()) {
				names[i] = scn.nextLine();
			}
			scn.close();
		}
		
		for(int i = 0; i < 2; i++) {
			Scanner scn = new Scanner(connections.get(i).getInputStream());
			if (scn.hasNextLine()) {
				messages[i] = scn.nextLine();
			}
			scn.close();
		}
		
		for(int i = 0; i < 2; i++) {
			System.out.println(names[i]);
		}
		
		ss.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Server().start();
	}
}
