/**
 * Cette classe permet de créer un véhicule
 * @author Denis Mahieux, Kévin Meyer, Levy Nguema, Hamza Oukabli
 * @version 1.0 - 10/02/2014
 */
public class Vehicule extends Rectangle implements Constants {

   /**
    * Nom du véhicule, constitué d'une seule lettre majuscule
    */
   private String nom;

   /**
    * Direction du véhicule, doit correspondre à l'une des constantes.
    * "H" or "V".
    */
   private String direction;

   /**
    * Longueur du véhicule en nombre de case sur la grille.
    */
   private int longueur;

   /**
    * Constructeur de la classe véhicule,
    * permet de construire le Rectangle correspondant au véhicule
    * dont les paramètres sont donnés en paramètre.
    * @param nom Nom du véhicule
    * @param couleur Couleur du véhicule
    * @param longueur Longueur en nombre de case du véhicule
    * @param posX Position x de la première case du véhicule sur la grille
    * @param posY Position y de la première case du véhicule sur la grille
    * @param direction Direction du véhicule (horizontale ou verticale)
    */
   public Vehicule(String nom, String couleur, int longueur, int posX, int posY, String direction)
   {
      // Fix bug : bad vehicule position.
      super(nom,longueur*SQUARE,SQUARE,BORDER+SQUARE*(posX),BORDER+SQUARE*(posY),couleur);

      this.direction=direction;
      this.longueur=longueur;

      if(direction.equals(VERTICAL))
      {
         changeSize(SQUARE,longueur*SQUARE);
      }
   }

   ////////////////// MODIF //////////////////////////////////////
   /**
    * @return this vehicule's size (2 or 3).
    */
   public int getSize ()
   {
      return longueur;
   }

   /**
    * @return this vehicule's direction. ("H" or "V").
    */
   public String getDirection ()
   {
      return direction;
   }

   // Remove useless function visualiserVehicule().

   // Rename the method to move
   /**
    * Move this vehicle.
    * @param m movement's info.
    */
   public void move (Movement m)
   {
      if (LEFT.equals(m.sense))
         slowMoveHorizontal(-m.dist * SQUARE);
      else if (RIGHT.equals(m.sense))
         slowMoveHorizontal(m.dist * SQUARE);
      else if (UP.equals(m.sense))
         slowMoveVertical(-m.dist * SQUARE);
      else if (DOWN.equals(m.sense))
         slowMoveVertical(m.dist * SQUARE);
   }
}
