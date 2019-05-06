import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

class Client implements Remote, Serializable {
	private String name;

	Client(String name) {
		this.name = name;
	}

	void askForMatch(int timeout) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException {
		MatcherService service = (MatcherService) Naming.lookup("rmi://localhost:2306/matchmaker");
		String matchedClient = service.match(name, timeout);
		if (matchedClient == null) {
			System.out.println("CLIENT " + name + ": I could not get matched.");
		} else {
			System.out.println("CLIENT " + name + ": I am matched with " + matchedClient);

			if (name.compareTo(matchedClient) > 0) { // Handshake request is done according to lexicographical order
				System.out.println("CLIENT " + name + ": I am waiting for handshake");
				LocateRegistry.getRegistry(2306).rebind("handshakeReceiver", this);
			} else {
				Thread.sleep(500); // Handshake receiver may not be binded yet, wait a bit
				Client otherClient = (Client) Naming.lookup("rmi://localhost:2306/handshakeReceiver"); // Get remote reference of other client
				handshake(otherClient, true);
			}
		}
	}

	private void handshake(Client oppositeClient, boolean isFirst) {
		System.out.println("CLIENT " + name + ": I am sending a handshake to " + oppositeClient.name);
		if (isFirst) {
			oppositeClient.handshake(this, false);
		}
	}
}
