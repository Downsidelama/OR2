package telnet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		new Thread(new Writer()).start();
		new Thread(new Reader()).start();
	}
}

class Reader implements Runnable {

	@Override
	public void run() {
		try (Scanner input = new Scanner(System.in)) {
			while (input.hasNextLine()) {
				System.out.println(input.nextLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class Writer implements Runnable {

	@Override
	public void run() {
		while (true) {
			System.out.println("Output");
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}