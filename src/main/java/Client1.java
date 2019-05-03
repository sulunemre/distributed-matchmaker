import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client1 {
	public static void main(String... args) throws RemoteException, NotBoundException, MalformedURLException {
		Client client1 = new Client("A");
		client1.askForMatch(10);
	}
}
