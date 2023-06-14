import java.time.Instant;
import java.time.Duration;
import java.lang.Thread;

import raytracer.Disp;
import raytracer.Scene;
import raytracer.Image;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

import java.time.Instant;
import java.lang.Thread;

public class LancerRaytracer {

  public static String aide = "Raytracer : synthèse d'image par lancé de rayons (https://en.wikipedia.org/wiki/Ray_tracing_(graphics))\n\nUsage : java LancerRaytracer [fichier-scène] [largeur] [hauteur]\n\tfichier-scène : la description de la scène (par défaut simple.txt)\n\tlargeur : largeur de l'image calculée (par défaut 512)\n\thauteur : hauteur de l'image calculée (par défaut 512)\n";

    private static class RaytracerThread implements Runnable {
        private final Scene scene;
        private final ServiceDistributeur serviceDistributeur;
        private final Disp disp;
        private final int xTemp;
        private final int yTemp;
        private final int lTemp;
        private final int hTemp;

        public RaytracerThread(
            Scene scene,
            ServiceDistributeur serviceDistributeur,
            Disp disp,
            int xTemp,
            int yTemp,
            int lTemp,
            int hTemp
        ) {
            this.scene = scene;
            this.serviceDistributeur = serviceDistributeur;
            this.disp = disp;
            this.xTemp = xTemp;
            this.yTemp = yTemp;
            this.lTemp = lTemp;
            this.hTemp = hTemp;
        }

        @Override
        public void run() {
          try {
              ServiceCalculeInterface sc = serviceDistributeur.distribuerServices();
                Image image = sc.calcule(scene, xTemp, yTemp, lTemp, hTemp, 10, 1);
                synchronized(this) {
                    disp.setImage(image, xTemp, yTemp);
                }
                System.out.println("Calcul de l'image :\n - Coordonnées : " + xTemp + "," + yTemp
                        + "\n - Taille " + lTemp + "x" + hTemp);
            } catch (Exception e) {
              System.out.println(e);
            }
        }
    }


    public static void main(String args[]){

        // Le fichier de description de la scène si pas fournie
        String fichier_description="simple.txt";

        // largeur et hauteur par défaut de l'image à reconstruire
        int largeur = 512, hauteur = 512;

        try{
          Registry regLocal = LocateRegistry.getRegistry(args[1],Integer.parseInt(args[2]));
          ServiceDistributeur serviceDistributeur = (ServiceDistributeur) regLocal.lookup("calcul");




        // création d'une fenêtre
        Disp disp = new Disp("Raytracer", largeur, hauteur);

        // Initialisation d'une scène depuis le modèle
        Scene scene = new Scene(fichier_description, largeur, hauteur);

        // Calcul de l'image de la scène les paramètres :
        // - x0 et y0 : correspondant au coin haut à gauche
        // - l et h : hauteur et largeur de l'image calculée
        // Ici on calcule toute l'image (0,0) -> (largeur, hauteur)

        int l = largeur, h = hauteur;

        // Chronométrage du temps de calcul
        Instant debut = Instant.now();

        int division = Integer.parseInt(args[0]);
        // Faire non pas raccine mais carré
        for(int i =0;i<division;i++){
            for(int j =0;j<division;j++){

                ServiceCalculeInterface sc = serviceDistributeur.distribuerServices();
                try{
                int xTemp=(int)((l*i)/division);
                int yTemp=(int)((h*j)/division);
                int lTemp=(int)(l/(division) + 1);
                int hTemp=(int)(h/(division) +1);


                Runnable thread = new RaytracerThread(scene, serviceDistributeur, disp, xTemp, yTemp, lTemp, hTemp);
                    new Thread(thread).start();
                System.out.println("Calcul de l'image :\n - Coordonnées : "+xTemp+","+ yTemp
                        +"\n - Taille "+ lTemp + "x" + hTemp);
                }
              catch(Exception e){
                System.out.println(e);
              }

            }
        }
        //Image image = sc.calcule(scene,0,0 , 512, 512,10,1);
        // Affichage de l'image calculée
        //disp.setImage(image, 0, 0);
      }
      catch(Exception e){
        System.out.println(e);
      }



        //long duree = Duration.between(debut, fin).toMillis();

        //System.out.println("Image calculée en :"+duree+" ms");



    }
}
