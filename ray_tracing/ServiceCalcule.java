import raytracer.*;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;

public class ServiceCalcule implements ServiceCalculeInterface {

        // calcul d'un imagette (x0,y0) (x0+w, y0+h)
    public Image calcule(Scene sc, int x0, int y0,
		  int w, int h, int nr, int d){
        System.out.println("Execution");
        Image image = sc.compute(x0,y0,w,h);
        System.out.println("Fin execution");
        return image;
    }
}
