import java.rmi.RemoteException;
import java.rmi.Remote;

public interface ServiceDistributeur extends Remote{

    public void enregistrerClient (ServiceCalculeInterface sc) throws RemoteException;

    public ServiceCalculeInterface distribuerServices() throws RemoteException;

    public void deconnexion(ServiceCalculeInterface sc) throws RemoteException;


}
