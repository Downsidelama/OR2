package hetes;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class PortServer {

	public static void main(String[] args) throws IOException {
		try (Scanner scanner = new Scanner(new File("ports.txt"));
				ServerSocket ss = new ServerSocket(12345);
				Socket socket = ss.accept();
				Scanner socketScanner = new Scanner(socket.getInputStream());
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				) {
			while(scanner.hasNextInt()) {
				int data = scanner.nextInt();
				System.out.println(data);
				pw.println(data);
				pw.flush();
			}
			
			while(socketScanner.hasNextLine()) {
				System.out.println(socketScanner.nextLine());
			}
		}
	}
}
