import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

class Server {
	public static void main(String... args) throws RemoteException {
		final int PORT = 2306;

//		System.setProperty("java.rmi.server.hostname", "localhost"); // For remote connections, change localhost with IP and uncomment this line
		LocateRegistry.createRegistry(PORT).rebind("matchmaker", new MatcherServant());

		System.out.println("Matchmaker server is listening on port " + PORT);
	}
}