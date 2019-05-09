
public class DominoMain {
	public static void main(String[] args) {
		new Thread(() -> {
			DominoServer.main("a", "b", "c");
		}).start();

		Thread.sleep(100);
		
		new Thread(() -> {
			DominoClient.main("a", "b", "c");
			DominoClient.main("a b c".split(" "));
		}).start();
	}
}
