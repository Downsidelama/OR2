package hetes;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		int sum = 0;
		try (Socket socket = new Socket("127.0.0.1", 12345);
				Scanner scanner = new Scanner(socket.getInputStream());
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				PrintWriter filePw = new PrintWriter(new File("output.txt"));) {

			while (scanner.hasNextInt()) {
				try (Socket otherSocket = new Socket("127.0.0.1", scanner.nextInt());
						Scanner otherScanner = new Scanner(otherSocket.getInputStream());) {
					while (otherScanner.hasNextInt()) {
						sum += otherScanner.nextInt();
						System.out.println(sum);
						filePw.println(sum);
						filePw.flush();
					}
					otherSocket.close();
					otherScanner.close();
				}

			}
		}

	}
}
