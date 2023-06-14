import java.util.*;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;

public class ServiceCentral implements ServiceDistributeur{

  public ArrayList<ServiceCalculeInterface> serviceCalcule;
  public int indiceCourant;

  public ServiceCentral(){
    this.serviceCalcule = new ArrayList<ServiceCalculeInterface>();
    this.indiceCourant=0;
  }

  public void enregistrerClient (ServiceCalculeInterface c) throws RemoteException{
    if (!serviceCalcule.contains(c)) {
          this.serviceCalcule.add(c);
          try {
              String clientIP = RemoteServer.getClientHost();
              System.out.println("Client enregistré, adresse IP du client : " + clientIP);
          } catch (ServerNotActiveException e) {
              System.out.println("Erreur pendant la connexion");
          }
      } else {
          System.out.println("Le client est déjà enregistré");
      }
  }

  public ServiceCalculeInterface distribuerServices() throws RemoteException{
      ServiceCalculeInterface retour = serviceCalcule.get(this.indiceCourant);
      this.indiceCourant++;
      if(this.indiceCourant == this.serviceCalcule.size()) this.indiceCourant=0;
      try{
        String clientIP = RemoteServer.getClientHost();
        System.out.println("Demande de service, adresse IP du client : " + clientIP);
      }
      catch(ServerNotActiveException e){
        System.out.println("Erreur pendant la connexion");
      }
      return retour;
  }

public void deconnexion(ServiceCalculeInterface sc) throws RemoteException {
    synchronized (serviceCalcule) {
        int index = serviceCalcule.indexOf(sc);
        if (index != -1) {
            serviceCalcule.remove(index);
            if (indiceCourant >= index) {
                indiceCourant--;
            }
            System.out.println("Client déconnecté. Nombre de services restants : " + serviceCalcule.size());
        } else {
            System.out.println("Le service à déconnecter n'a pas été trouvé.");
        }
    }
}

}
