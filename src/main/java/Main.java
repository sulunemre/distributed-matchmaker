import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.SecureRandom;

public class Main {
	public static void main(String... args) throws RemoteException, InterruptedException {
		new Server(2306).start();
		for (int i = 0; i < 5; i++) {
			new Thread(new ClientSimulator()).start();
			Thread.sleep(1);
		}
	}
}

class ClientSimulator implements Runnable {

	@Override
	public void run() {
		Client client = new Client(generateRandomClientName());
		try {
			client.askForMatch(5);
		} catch (RemoteException | NotBoundException | MalformedURLException ignored) {
		}
	}

	private String generateRandomClientName() {
		int len = 3;
		final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		SecureRandom rnd = new SecureRandom();

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
}
