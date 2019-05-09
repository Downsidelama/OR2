import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class ClientData {
	Socket s;
	Scanner sc;
	PrintWriter pw;
	String name;
	
	ClientData(ServerSocket ss) throws IOException {
		s = ss.accept();
		sc = new Scanner(s.getInputStream());
		pw = new PrintWriter(s.getOutputStream());
	}
}
