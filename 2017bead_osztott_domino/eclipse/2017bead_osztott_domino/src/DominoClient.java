import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DominoClient {
	
	private static final int PORT = 60504;
	private static final String host = "localhost";
	
	private String name;
	private Socket connection;
	private Scanner input;
	private PrintWriter output;
	
	private List<Domino> dominoes;
	private boolean gameStarted = false;
	private boolean gameOver = false;
	
	public static void main(String... args) {
		DominoClient client = null;
		if (args.length == 1) {
			try {
				client = new DominoClient(args[0]);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					client.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else { 
			System.err.println("Add meg a neved a parameterben!");
			System.exit(-2322);
		}
	}
	
	public DominoClient(String name) throws UnknownHostException, IOException, InterruptedException {
		this.name = name;
		this.dominoes = new ArrayList<>();
		
		connecToServer();
		playTheGame();
	}

	private void playTheGame() throws InterruptedException {
		while(!gameOver) {
			
			Thread.sleep(100);
		}
	}

	private void connecToServer() throws UnknownHostException, IOException {
		connection = new Socket(host, PORT);
		input = new Scanner(connection.getInputStream());
		output = new PrintWriter(connection.getOutputStream(), true);
	}
	
	public void close() throws IOException {
		input.close();
		output.close();
		connection.close();
	}
	
}
