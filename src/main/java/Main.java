import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main {
	public static void main(String... args) throws RemoteException, MalformedURLException, NotBoundException {
		new Server(2306).start();
		Client client1 = new Client("A");
		Client client2 = new Client("B");
		client1.askForMatch(5);
		client2.askForMatch(5);
	}
}
