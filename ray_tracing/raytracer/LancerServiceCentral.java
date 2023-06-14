package raytracer;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;


public class LancerServiceCentral{
    public static void main(String args[]) throws RemoteException {
        ServiceDistributeur distributeur = new ServiceCentral();
        ServiceDistributeur serviceDistributeur = (ServiceDistributeur) UnicastRemoteObject.exportObject(distributeur, 3630);
        Registry reg = LocateRegistry.createRegistry(3630); /* Création de l'annuaire */
        reg.rebind("calcul", serviceDistributeur);
    }
}
