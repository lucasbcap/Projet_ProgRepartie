package raytracer;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;

public interface ServiceCalculeInterface extends Remote{
    public Image calcule(Scene sc, int x0, int y0, int w, int h, int nr, int d);
}