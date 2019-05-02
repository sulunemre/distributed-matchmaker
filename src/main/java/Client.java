import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

class Client {
	private String name;

	Client(String name) {
		this.name = name;
	}

	void askForMatch(int timeout) throws RemoteException, NotBoundException, MalformedURLException {
		MatcherService service = (MatcherService) Naming.lookup("rmi://localhost:2306/matchmaker");
		System.out.println(service.match(name, timeout));
	}
}
