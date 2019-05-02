import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main {
	public static void main(String... args) throws RemoteException, MalformedURLException, NotBoundException {
		new Server(2306).start();
		Client client1 = new Client("A");
		Client client2 = new Client("B");
		Client client3 = new Client("C");
		Client client4 = new Client("D");
		Client client5 = new Client("E");
		client1.askForMatch(4);
		client2.askForMatch(5);
		client3.askForMatch(5);
		client4.askForMatch(5);
		client5.askForMatch(5);
	}
}
