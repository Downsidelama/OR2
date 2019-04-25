import java.util.Random;

public class Pallo3 {
	int[] status;
	int length;

	public Pallo3(int[] status) {
		this.status = status;
	}

	public static void main(String[] args) throws InterruptedException {
		if (args.length != 1) {
			System.out.println("Add meg az n-t!");
			System.exit(-1);
		}

		Pallo3 pallo = new Pallo3(new int[] { 0, Integer.parseInt(args[0]) - 3 });

		pallo.length = Integer.parseInt(args[0]);
		Thread kecske2 = new Thread(new Kecske3(false, pallo.status, pallo.length));
		Thread kecske1 = new Thread(new Kecske3(true, pallo.status, pallo.length));
		kecske2.start();
		kecske1.start();

		kecske2.join();
		kecske1.join();

		if (pallo.status[0] == 0) {
			System.out.println("Kecske2 wins!");
		} else {
			System.out.println("Kecske1 wins!");
		}
	}
}

class Kecske3 implements Runnable {
	boolean polarity;
	int[] status;
	int length;
	Random r;

	public Kecske3(boolean polarity, int[] status, int length) {
		this.polarity = polarity;
		this.status = status;
		this.length = length;
		this.r = new Random();
	}

	@Override
	public void run() {
		while (status[0] < length - 1 && status[1] > 0) {
			synchronized (status) {
				if (this.polarity) {
					ramLeft(status);
				} else {
					ramRight(status);
				}
				printGoats(status, length);
			}
			try {
				Thread.sleep(r.nextInt(1500) + 500 + (this.polarity ? 1500 : 0));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void printGoats(int[] status2, int length2) {
		for (int i = 0; i < length2; i++) {
			if (status2[0] == i || status2[1] == i) {
				System.out.print("O");
			} else {
				System.out.print("_");
			}
		}
		System.out.println();
	}

	private void ramRight(int[] status2) {
		status2[0]++;
		if (status2[0] == status2[1]) {
			status2[1]++;
		}
	}

	private void ramLeft(int[] status2) {
		status2[1]--;
		if (status2[1] == status2[0]) {
			status2[0]--;
		}
	}
}