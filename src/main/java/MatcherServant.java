import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;

public class MatcherServant extends UnicastRemoteObject implements MatcherService {
	private String waitingClient;
	private long expirationTime;

	MatcherServant() throws RemoteException {
	}

	@Override
	public String match(String name, int timeoutSecs) {
		long currentTime = System.currentTimeMillis();
		System.out.println(name + " wants to get matched in " + timeoutSecs + " seconds at " + new Timestamp(System.currentTimeMillis()));
		if (expirationTime >= currentTime && waitingClient != null) { // Match is successful
			String matchedClient = waitingClient;
			waitingClient = null;

			return matchedClient;
		} else { // Not matched
			waitingClient = name;
			expirationTime = currentTime + timeoutSecs * 1000;

			return null;
		}
	}

	String getCurrentWaitingClient() {
		return waitingClient + " is waiting until " + expirationTime;
	}
}