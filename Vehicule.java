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
	 * Direction du véhicule, doit correspondre à l'une des constantes
	 */
	private String direction;
	
	/**
	 * Longueur du véhicule en nombre de case sur la grille
	 */
	private int longueur;

	/**
	 * Constructeur de la classe véhicule, permet de construire le Rectangle correspondant au véhicule
	 * dont les paramètres sont donnés en paramètre
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
	
	/**
	 * Permet d'afficher le véhicule sur le canvasS
	 */
	public void visualiserVehicule() 
	{
		makeVisible();
	}

	/**
	 * Permet de déplacer un véhicule dans une direction pour une distance donnée
	 * @param direction Direction du déplacement (doit correspondre à l'une des constantes)
	 * @param distance Distance en nombre de case du déplacement
	 */
	public void deplacerVehicule(String direction,int distance) 
	{
		if(direction.equals(LEFT)) slowMoveHorizontal(-distance*SQUARE);
		else if(direction.equals(RIGHT)) slowMoveHorizontal(distance*SQUARE);
		else if(direction.equals(UP)) slowMoveVertical(-distance*SQUARE);
		else if(direction.equals(DOWN)) slowMoveVertical(distance*SQUARE);
	}

}
