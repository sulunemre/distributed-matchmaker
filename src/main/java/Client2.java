import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client2 {
	public static void main(String... args) throws RemoteException, NotBoundException, MalformedURLException, InterruptedException {
		new Client("B").askForMatch(5);
	}
}

