import java.util.Hashtable;

/**
 * Cette classe permet de créer un Parking,
 * Un parking est un ensemble de Véhicules représentés sur une Grille
 * @author Denis Mahieux, Kévin Meyer, Levy Nguema, Hamza Oukabli
 * @version 1.0 - 10/02/2014
 */
public class Parking
{
   /**
    * Correspond à l'ensemble des véhicules du parking
    */
   private Hashtable<String,Vehicule> tableVehicule;

   /**
    * Permet de construire un Parking avec une grille vide de véhicule
    */
   public Parking() 
   {
      new Grid();
      tableVehicule = new Hashtable<String, Vehicule>();
   }

   /**
    * Permet d'ajouter un véhicule au parking et de l'afficher sur la grille
    * @param nom Nom du véhicule à ajouter
    * @param couleur Couleur du véhicule à ajouter
    * @param longueur Longueur du véhicule à ajouter
    * @param posX Position x de la première case du véhicule à ajouter
    * @param posY Position y de la première case du véhicule à ajouter
    * @param direction Direction du véhicule à ajouter
    */
   public void addVehicule(String nom, String couleur, int longueur,
         int posX, int posY, String direction)
   {
      tableVehicule.put(nom,new Vehicule(nom,couleur,longueur,posX,posY,direction));
      // Fix use of useless function.
      getVehicule(nom).makeVisible();
   }

   /**
    * Permet de récupérer le Véhicule correspondant au nom donné
    * @param nom Nom du véhicule recherché
    * @return Le véhicule correspondant au nom
    */
   public Vehicule getVehicule(String nom) 
   {
      return tableVehicule.get(nom);
   }

   /**
    * Move a vehicle in the parking if movement is correct.
    * @param m a valid Movement.
    * @throws IllegalMovementException when players try to make a bad move.
    * @throws VictoryException when players finish a level.
    */
   public void move (Movement m) throws IllegalMovementException, VictoryException
   {
      try {
         if (ParkingController.checkMovement(m, tableVehicule))
            getVehicule(m.name).move(m);
         else throw new IllegalMovementException();
      }
      catch (VictoryException e) {
         getVehicule(m.name).move(m);
         throw e;
      }
   }
}
