import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;


public class LancerServiceCalcule{
    public static void main(String args[]) throws RemoteException {
      try {
          ServiceCalcule serviceCalcule = new ServiceCalcule();
          ServiceCalculeInterface serviceCalculeObject = (ServiceCalculeInterface) UnicastRemoteObject.exportObject(serviceCalcule, 0);



          Registry regLocal = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));

          ServiceDistributeur serviceDistributeur = (ServiceDistributeur) regLocal.lookup("calcul");
          serviceDistributeur.enregistrerClient(serviceCalculeObject);

          System.out.println("serviceDistributeur");
        } catch (Exception e) {
          System.out.println(e);
        }

    }
}
