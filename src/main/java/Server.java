import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

class Server {
	private int port;

	Server(int port) {
		this.port = port;
	}

	void start() throws RemoteException {
		Registry registry = LocateRegistry.createRegistry(port);
		System.out.println("Matchmaker server is listening on port " + port);
		registry.rebind("matchmaker", new MatcherServant());
	}
}