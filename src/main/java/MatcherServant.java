import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MatcherServant extends UnicastRemoteObject implements MatcherService {
	MatcherServant() throws RemoteException {
	}

	@Override
	public String match(String name, int timeoutSecs) {
		System.out.println(name + " wants to get matched in " + timeoutSecs + " seconds");
		return null;
	}
}
