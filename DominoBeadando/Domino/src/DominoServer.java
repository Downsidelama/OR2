import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DominoServer {
	static int fileDominoIdx = 0;

	public static void main(String[] args) throws Exception {
		args = args.length == 0 ? "2 in.txt".split(" ") : args;
		
		int clientCount = Integer.parseInt(args[0]);
		String dominoFilename = args[1];
		
		List<Domino> dominos = readDominos(dominoFilename);

		int PORT = 60504;
		try (
			ServerSocket ss = new ServerSocket(PORT);	
		) {
			ClientData[] clients = receiveClients(clientCount, ss);
			sendInitDominos(dominos, clients);
			receiveNames(clients);
			playGame(dominos, clients);
			closeClients(clients);
		}
	}

	private static void closeClients(ClientData[] clients) throws IOException {
		for (ClientData clientData : clients) {
			clientData.s.close();
		}
	}

	private static void playGame(List<Domino> dominos, ClientData[] clients) throws Exception {
		Registry reg = LocateRegistry.getRegistry(23456);
		DominoStorageIface dominoRemote = (DominoStorageIface)reg.lookup("domino");

		clients[0].pw.println("START");
		clients[0].pw.flush();


		int currIdx = 0;
		int movesSinceLast = 0;

		while (true) {			
			int nextIdx = nextClient(currIdx, clients.length);
			ClientData currClient = clients[currIdx];
			ClientData nextClient = clients[nextIdx];

			String msg = clients[currIdx].sc.nextLine();

			if (isNumber(msg)) {
				nextClient.pw.println(msg);
				nextClient.pw.flush();

				movesSinceLast = 0;
			} else if (msg.startsWith("SAVE")) {
				String[] split = msg.split(" ");
				String username = split.length > 0 ? split[1] : currClient.name;

				List<Domino> toSave = new ArrayList<>(dominos.subList(fileDominoIdx, dominos.size()));
				dominoRemote.save(username, toSave);
			} else if (msg.startsWith("LOAD")) {
				String[] split = msg.split(" ");
				String username = split.length > 0 ? split[1] : currClient.name;
				
				dominos = new ArrayList<>(dominoRemote.load(username));
				fileDominoIdx = 0;
			} else if (msg.equals("UJ")) {
				sendDomino(currClient, dominos);
				
				++movesSinceLast;
			} else if (msg.equals("VEGE")) {
				sendBye(clients, currIdx);
				break;
			} else if (movesSinceLast == clients.length) {
				sendBye(clients);
				break;
			}
			
			currIdx = nextIdx;
		}
	}

	private static void sendBye(ClientData[] clients, int... exceptedClients) {
		for (int i = 0; i < clients.length; i++) {
			if (isInArray(i, exceptedClients))   continue;

			clients[i].pw.println("VEGE");
			clients[i].pw.flush();
		}
	}

	private static boolean isInArray(int number, int[] array) {
		for (int j = 0; j < array.length; j++) {
			if (number == array[j])   return true;
		}

		return false;
	}
	
	private static boolean isNumber(String msg) {
		try {
			Integer.parseInt(msg);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private static int nextClient(int currIdx, int clientCount) {
		return (currIdx + 1) % clientCount;
	}

	private static void receiveNames(ClientData[] clients) {
		for (int i = 0; i < clients.length; i++) {
			clients[i].name = clients[i].sc.nextLine();
		}
	}

	private static void sendInitDominos(List<Domino> dominos, ClientData[] clients) {
		for (int i = 0; i < clients.length; i++) {
			for (int j = 0; j < 7; j++) {
				sendDomino(clients[i], dominos);
			}

			clients[i].pw.flush();
		}
	}

	private static void sendDomino(ClientData clientData, List<Domino> dominos) {
		if (fileDominoIdx >= dominos.size()) {
			clientData.pw.println("NINCS");
		} else {
			Domino domino = dominos.get(fileDominoIdx);
			clientData.pw.printf("%d %d%n", domino.side1, domino.side2);
			
			++fileDominoIdx;
		}

		clientData.pw.flush();
	}

	private static List<Domino> readDominos(String dominoFilename) throws FileNotFoundException {
		List<Domino> retval = new ArrayList<>();
		
		try (
			Scanner scFile = new Scanner(new File(dominoFilename));
		) {
			while (scFile.hasNextInt()) {
				int side1 = scFile.nextInt();
				int side2 = scFile.nextInt();
				retval.add(new Domino(side1, side2));
			}
		}
		
		return retval;
	}

	private static ClientData[] receiveClients(int kliensDarabszam, ServerSocket ss) throws IOException {
		ClientData clients[] = new ClientData[kliensDarabszam];

		for (int i = 0; i < clients.length; i++) {
			clients[i] = new ClientData(ss);
		}
		
		return clients;
	}
}
