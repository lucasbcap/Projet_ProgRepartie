public class ServiceCentral implements ServiceDistributeur{

  public ArrayList<ServiceTableauBlanc> stb;
  public ArrayList<Dessin> d;

  public ServiceCentral(){
    stb = new ArrayList<ServiceTableauBlanc>;
    d = new ArrayList<Dessin>;
  }

  public void enregistrerClient (ServiceTableauBlanc c){
    stb.add(c);
  }

  public void distribuerMessage(Dessin d){
    for (int i = 0; i<sd.lenght(); i++){
      sd.get(i).afficherMessage();
    }
  }
}
