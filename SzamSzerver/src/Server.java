import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
	public static void main(String... args) {
		int port = 12345;
		List<String> cache = new ArrayList<>();
		for (;;) {
			try (ServerSocket ss = new ServerSocket(port);
					Socket socket = ss.accept();
					BufferedReader scanner = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter pw = new PrintWriter(socket.getOutputStream());) {
				String data = "";
				while ((data = scanner.readLine()) != null) {
					if(data.equals("END")) {
						break;
					}
					System.out.println(data);
					cache.add(data);
				}
				
				for(int i = 0; i < cache.size(); i++) {
					pw.println(Integer.parseInt(cache.get(i).trim()) * 2);
					pw.flush();
				}
				
				cache.clear();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
