import java.time.Instant;
import java.time.Duration;

import raytracer.Disp;
import raytracer.Scene;
import raytracer.Image;

public class LancerRaytracer {

    public static String aide = "Raytracer : synthèse d'image par lancé de rayons (https://en.wikipedia.org/wiki/Ray_tracing_(graphics))\n\nUsage : java LancerRaytracer [fichier-scène] [largeur] [hauteur]\n\tfichier-scène : la description de la scène (par défaut simple.txt)\n\tlargeur : largeur de l'image calculée (par défaut 512)\n\thauteur : hauteur de l'image calculée (par défaut 512)\n";

    public static void main(String args[]){

        // Le fichier de description de la scène si pas fournie
        String fichier_description="simple.txt";

        // largeur et hauteur par défaut de l'image à reconstruire
        int largeur = 512, hauteur = 512;

        if(args.length > 2){
            fichier_description = args[4];
            if(args.length > 1){
                largeur = Integer.parseInt(args[1]);
                if(args.length > 2)
                    hauteur = Integer.parseInt(args[2]);
            }
        }else{
            System.out.println(aide);
        }

        Registry regLocal = LocateRegistry.getRegistry("localhost");
        ServiceDistributeur serviceDistributeur = (ServiceDistributeur) regLocal.lookup("calcul");
        ServiceCalcule sc = serviceDistributeur.distribuerServices();


        // création d'une fenêtre
        Disp disp = new Disp("Raytracer", largeur, hauteur);

        // Initialisation d'une scène depuis le modèle
        Scene scene = new Scene(fichier_description, largeur, hauteur);

        // Calcul de l'image de la scène les paramètres :
        // - x0 et y0 : correspondant au coin haut à gauche
        // - l et h : hauteur et largeur de l'image calculée
        // Ici on calcule toute l'image (0,0) -> (largeur, hauteur)

        int x0 = 0, y0 = 0;
        int l = largeur, h = hauteur;

        // Chronométrage du temps de calcul
        Instant debut = Instant.now();

        int division = Integer.parseInt(args[0]);
        for(int i =0;i<Math.sqrt(division);i++){
            for(int j =0;j<Math.sqrt(division);j++){
                Image image = sc.calcule(scene,x0+(int)((l*i)/Math.sqrt(division)), y0+(int)((j*h)/Math.sqrt(division)), (int)(l/(Math.sqrt(division)) + 1), (int)(h/Math.sqrt(division)) +1,10,1);
                // Affichage de l'image calculée
                disp.setImage(image, x0+(int)((l*i)/Math.sqrt(division)), y0+(int)((j*h)/Math.sqrt(division)));
                System.out.println("Calcul de l'image :\n - Coordonnées : "+(l/division)*i+","+(h/division)*j
                        +"\n - Taille "+ (int)(l/(Math.sqrt(division))) + "x" + (int)(h/Math.sqrt(division)));
            }
        }


        //long duree = Duration.between(debut, fin).toMillis();

        //System.out.println("Image calculée en :"+duree+" ms");



    }
}
