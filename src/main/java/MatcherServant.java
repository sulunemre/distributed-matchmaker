import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;

public class MatcherServant extends UnicastRemoteObject implements MatcherService {
	private String waitingClientName;
	private Thread waitingClientThread;
	private String newClientName;

	MatcherServant() throws RemoteException {
	}

	@Override
	public String match(String name, int timeoutSecs) {
		long currentTime = System.currentTimeMillis();
		System.out.println(name + " wants to get matched in " + timeoutSecs + " seconds at " + new Timestamp(currentTime));

		if (waitingClientName == null) { // Wait for a match if no one is waiting
			waitingClientName = name;
			System.out.println(name + " is waiting for a match.");
			try {
				waitingClientThread = Thread.currentThread();
				Thread.sleep(timeoutSecs * 1000);
			} catch (InterruptedException e) {
				System.out.println(name + " found a match after waiting.");
				return newClientName;
			}


			System.out.println(name + " could not find a match");
			waitingClientName = null;
			waitingClientThread = null;

			return null;

		} else { // Match with the waiting client
			newClientName = name;
			waitingClientThread.interrupt();

			String matchedClient = waitingClientName;
			System.out.println(name + " is matched with " + matchedClient + " immediately.");

			waitingClientName = null;
			waitingClientName = null;

			return matchedClient;
		}
	}
}