import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;


public class LancerServiceCentral{
    public static void main(String args[]) throws RemoteException {
        ServiceDistributeur distributeur = new ServiceCentral();
        ServiceDistributeur serviceDistributeur = (ServiceDistributeur) UnicastRemoteObject.exportObject(distributeur, 0);
        Registry reg = LocateRegistry.createRegistry(Integer.parseInt(args[0])); /* Création de l'annuaire */
        reg.rebind("calcul", serviceDistributeur);
    }
}
