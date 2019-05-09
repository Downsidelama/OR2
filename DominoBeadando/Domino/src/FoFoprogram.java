public class FoFoprogram {
	public static void main(String[] args) {
		new Thread(() -> {
			try {
				DominoServer.main("2 in.txt".split(" "));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			try {
				DominoDeploy.main("".split(" "));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		
		for (int i = 0; i < 4; i++) {
			sleepSome();

			new Thread(() -> {
				DominoClient.main("1 2 3".split(" "));
			}).start();
		}
	}

	private static void sleepSome() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
