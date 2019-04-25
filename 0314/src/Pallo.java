import java.util.Random;

public class Pallo {
	int[] status = { 0 };
	int length;

	public static void main(String[] args) throws InterruptedException {
		if (args.length != 1) {
			System.out.println("Add meg az n-t!");
			System.exit(-1);
		}

		Pallo pallo = new Pallo();

		pallo.length = Integer.parseInt(args[0]);
		pallo.status[0] = (int) Math.floor((double) pallo.length / 2);
		Thread kecske1 = new Thread(new Kecske(true, pallo.status, pallo.length));
		Thread kecske2 = new Thread(new Kecske(false, pallo.status, pallo.length));
		kecske1.start();
		kecske2.start();

		kecske1.join();
		kecske2.join();

		if (pallo.status[0] == 0) {
			System.out.println("Kecske2 wins!");
		} else {
			System.out.println("Kecske1 wins!");
		}
	}
}

class Kecske implements Runnable {

	boolean polarity;
	int[] status;
	int length;
	Random r;

	public Kecske(boolean polarity, int[] status, int length) {
		this.polarity = polarity;
		this.status = status;
		this.length = length;
		this.r = new Random();
	}

	@Override
	public void run() {
		while (status[0] > 0 && status[0] < length) {
			synchronized (status) {
				if (this.polarity) {
					status[0]++;
				} else {
					status[0]--;
				}
				System.out.println(status[0]);
			}
			try {
				Thread.sleep(r.nextInt(1500) + 500 + (this.polarity == true ? 1500 : 0));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
