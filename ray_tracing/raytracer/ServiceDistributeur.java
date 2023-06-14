package raytracer;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;

public interface ServiceDistributeur extends Remote{

    public void enregistrerClient (ServiceCalcule sc);

    public ServiceCalcule distribuerServices();

    public void deconnexion(ServiceCalcule sc);


}