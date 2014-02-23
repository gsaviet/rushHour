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
    * @param movement a string type by an user. Eg: 'XU1'
    *    where X is the vehicle's name, U the sense and 1 the distance.
    */
   public void move (String movement)
      throws IllegalMovementException, VictoryException
   {
      final String name = movement.substring(0, 1);
      final String sense = movement.substring(1, 2);
      final int dist = Integer.parseInt(movement.substring(2, 3));

      try {
         if (ParkingController.checkMovement(name, sense, dist, tableVehicule))
            getVehicule(name).move(sense, dist);
         else throw new IllegalMovementException(movement);
      }
      catch (VictoryException e) {
         getVehicule(name).move(sense, dist);
         throw e;
      }
   }

   /*
    * Permet de déplacer sur la grille du Parking le véhicule
    * @param move "XYZ" : "X" correspond au nom du véhicule, "Y" à la direction, "Z" à la distance
    */
   // public void move(String move) 
   // {
   //    char[] deplacement = move.toCharArray();

   //    Vehicule vehicule = getVehicule(Character.toString(deplacement[0]));

   //    vehicule.deplacerVehicule(Character.toString(deplacement[1]),
   //          Character.getNumericValue(deplacement[2]));
   // }
}
