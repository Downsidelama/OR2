import java.util.Arrays;
import java.util.Random;

public class Pallo2 {
	int[] status;
	int length;

	public static void main(String[] args) throws InterruptedException {
		if (args.length != 1) {
			System.out.println("Add meg az n-t!");
			System.exit(-1);
		}

		Pallo2 pallo = new Pallo2();

		pallo.length = Integer.parseInt(args[0]);
		pallo.status = new int[pallo.length];
		int half = (int) Math.floor((double) pallo.length / 2);
		for (int i = 0; i < pallo.length; i++) {
			if (i == half) {
				pallo.status[i] = 1;
			} else {
				pallo.status[i] = 0;
			}
		}
		Thread kecske1 = new Thread(new Kecske2(true, pallo.status, pallo.length));
		Thread kecske2 = new Thread(new Kecske2(false, pallo.status, pallo.length));
		kecske1.start();
		kecske2.start();

		kecske1.join();
		kecske2.join();

		if (pallo.status[0] == 1) {
			System.out.println("Kecske2 wins!");
		} else if (pallo.status[pallo.status.length - 1] == 1) {
			System.out.println("Kecske1 wins!");
		}
	}
}

class Kecske2 implements Runnable {

	boolean polarity;
	int[] status;
	int length;
	Random r;

	public Kecske2(boolean polarity, int[] status, int length) {
		this.polarity = polarity;
		this.status = status;
		this.length = length;
		this.r = new Random();
	}

	@Override
	public void run() {
		while (status[0] == 0 && status[status.length - 1] == 0) {
			synchronized (status) {
				if (this.polarity) {
					moveRight(status);
				} else {
					moveLeft(status);
				}
				printKecskeStatus(status);
			}
			try {
				Thread.sleep(r.nextInt(1500) + 500 + (this.polarity == true ? 1500 : 0));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void printKecskeStatus(int[] status2) {
		for (int i : status2) {
			if (i == 0)
				System.out.print("_");
			else
				System.out.print("OO");
		}
		System.out.println();
	}

	private void moveLeft(int[] status) {
		for (int i = 0; i < status.length; i++) {
			if (status[i] == 1) {
				if (i - 1 >= 0) {
					status[i - 1] = 1;
					status[i] = 0;
				}
			}
		}
	}

	private void moveRight(int[] status) {
		for (int i = 0; i < status.length; i++) {
			if (status[i] == 1) {
				if (i + 1 < status.length) {
					status[i + 1] = 1;
					status[i] = 0;
					break;
				}
			}
		}
	}

}
