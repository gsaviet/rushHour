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
      getVehicule(nom).visualiserVehicule();
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
    * Permet de déplacer sur la grille du Parking le véhicule
    * @param move "XYZ" : "X" correspond au nom du véhicule, "Y" à la direction, "Z" à la distance
    */
   public void move(String move) 
   {
      char[] deplacement = move.toCharArray();

      Vehicule vehicule = getVehicule(Character.toString(deplacement[0]));

      vehicule.deplacerVehicule(Character.toString(deplacement[1]),
            Character.getNumericValue(deplacement[2]));
   }
}
