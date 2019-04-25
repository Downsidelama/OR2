import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RMIDeploy {
	public static void main(String... strings) throws RemoteException {
		if (strings.length == 2) {

		} else {
			System.out.println("I need 2 parameters.");
			System.exit(-1);
		}

		List<Integer> numbers = new ArrayList<>();
		int all = Integer.parseInt(strings[0]);
		int winners = Integer.parseInt(strings[1]);

		if (Integer.parseInt(strings[0]) >= Integer.parseInt(strings[1])) {
			for (int i = 1; i <= all; i++) {
				numbers.add(i);
			}
		} else {
			System.out.println("The second parameter should be less than the first.");
			System.exit(-2);
		}

//		Collections.shuffle(numbers);
		numbers = getRandomNumbers(all, winners);

		Registry registry = LocateRegistry.createRegistry(12345);

		for (int i = 0; i < numbers.size(); i++) {
			registry.rebind(String.format("szam%s", numbers.get(i)),
					new RemoteLottoServer((i < winners) ? true : false));
		}
	}
	
	private static List<Integer> getRandomNumbers(int all, int needed) {
		Random rnd = new Random();
		List<Integer> numbers = IntStream.rangeClosed(1, all).boxed().collect(Collectors.toList());
		List<Integer> returnNumbers = new ArrayList<>();
		
		for(int i = 0; i < needed; i++) {
			returnNumbers.add(numbers.remove(rnd.nextInt(numbers.size())));
		}
		
		return returnNumbers;
	}
}
