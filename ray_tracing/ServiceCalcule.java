import raytracer.*;

import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;

public class ServiceCalcule implements ServiceCalculeInterface {

        // calcul d'un imagette (x0,y0) (x0+w, y0+h)
    public Image calcule(Scene sc, int x0, int y0,int w, int h, int nr, int d){
        try{
        String clientIP = RemoteServer.getClientHost();
        System.out.println("Début de l'exécution par : " + clientIP);
        }
        catch(ServerNotActiveException e){
            System.out.println("Erreur pendant l'exécution");
        }
        Image image = sc.compute(x0,y0,w,h);
        try{
            String clientIP = RemoteServer.getClientHost();
            System.out.println("Fin execution par : " + clientIP);
        }
        catch(ServerNotActiveException e){
            System.out.println("Erreur pendant l'ex\u00E9cution");
        }
        System.out.println("Fin execution");
        return image;
    }
}
