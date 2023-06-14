import raytracer.*;
import java.util.*;
import java.rmi.RemoteException;

public class ServiceCentral implements ServiceDistributeur{

  public ArrayList<ServiceCalculeInterface> serviceCalcule;
  public int indiceCourant;

  public ServiceCentral(){
    this.serviceCalcule = new ArrayList<ServiceCalculeInterface>();
    this.indiceCourant=0;
  }

  public void enregistrerClient (ServiceCalculeInterface c) throws RemoteException{
    this.serviceCalcule.add(c);
    System.out.println("Client enregistrer");
  }

  public ServiceCalculeInterface distribuerServices() throws RemoteException{
      ServiceCalculeInterface retour = serviceCalcule.get(this.indiceCourant);
      this.indiceCourant++;
      if(this.indiceCourant == this.serviceCalcule.size()) this.indiceCourant=0;
      System.out.println("Demande de service");
      return retour;
  }

  public void deconnexion(ServiceCalculeInterface sc) throws RemoteException{
    this.serviceCalcule.remove(sc);
    System.out.println("Client deconnecter");
  }
}
