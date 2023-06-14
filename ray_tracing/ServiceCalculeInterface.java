import raytracer.*;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import java.rmi.RemoteException;
import java.rmi.Remote;

public interface ServiceCalculeInterface extends Remote{
    public Image calcule(Scene sc, int x0, int y0, int w, int h, int nr, int d) throws RemoteException;
}
