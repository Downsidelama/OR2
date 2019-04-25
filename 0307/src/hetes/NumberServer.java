package hetes;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class NumberServer {
	public static void main(String[] args) throws IOException {
		
		if(args.length != 1) {
			System.exit(-100);
		}
		
		try (Scanner fr = new Scanner(new File("numbers.txt"));
				ServerSocket ss = new ServerSocket(Integer.parseInt(args[0]));
				Socket socket = ss.accept();
				Scanner socketScanner = new Scanner(socket.getInputStream());
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				) {
			while(fr.hasNextInt()) {
				int data = fr.nextInt();
				System.out.println(data);
				pw.println(data);
				pw.flush();
			}
		}
	}
}
