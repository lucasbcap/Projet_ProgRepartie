package raytracer;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;


public class LancerServiceCalcule{
    public static void main(String args[]) throws RemoteException {
      try {
          ServiceCalcule serviceCalcule = new ServiceCalcule();
          ServiceCalculeInterface serviceCalculeObject = (ServiceCalculeInterface) UnicastRemoteObject.exportObject(serviceCalcule, 3630);

          Registry regLocal = LocateRegistry.getRegistry("localhost", 3630);
          ServiceDistributeur serviceDistributeur = (ServiceDistributeur) regLocal.lookup("calcul");
          serviceDistributeur.enregistrerClient((ServiceCalcule)serviceCalculeObject);
        } catch (Exception e) {
          System.out.println("Erreur");
        }
    }
}
