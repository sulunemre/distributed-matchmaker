import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client1 {
	public static void main(String... args) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException {
		new Client("A").askForMatch(10);
	}
}
