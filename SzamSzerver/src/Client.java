import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {
	private final static int port = 12345;
	private final static String host = "127.0.0.1";
	private final static ArrayList<String> numbers = new ArrayList<>();

	public static void main(String... args) throws InterruptedException {
		try (Socket s = new Socket(host, port);
				Scanner sc = new Scanner(new File("input.txt"));
				BufferedReader socketReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter socketWriter = new PrintWriter(s.getOutputStream());
				FileWriter fw = new FileWriter("output.txt")) {
			while (sc.hasNext()) {
				String data = sc.next();
				System.out.printf("Sending: %s\r\n", data);
				socketWriter.println(data);
				socketWriter.flush();
			}

			socketWriter.println("END");
			socketWriter.flush();

			String data = "";
			List<String> dataList = new ArrayList<>();
			while ((data = socketReader.readLine()) != null) {
				dataList.add(data);
				System.out.println(data);
			}

			fw.write(Arrays.toString(dataList.toArray(new String[dataList.size()])));
			

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}