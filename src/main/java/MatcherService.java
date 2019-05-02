import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MatcherService extends Remote {
	String match(String name, int timeoutSecs) throws RemoteException;
}
