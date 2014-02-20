/**
 * Cette classe permet de générer la grille du Parking
 * @author Denis Mahieux, Kévin Meyer, Levy Nguema, Hamza Oukabli
 * @version 1.0 - 10/02/2014
 */
public class Grid implements Constants {
	/**
	 * Ce constructeur permet de générer exactment la même grille que celle donnée dans l'énoncé du TD
	 */
	public Grid()
	{
		new Rectangle(SIZE,SIZE,0,0,"dark_grey").makeVisible();
		new Rectangle(6*SQUARE,6*SQUARE,BORDER,BORDER,"white").makeVisible();
		new Rectangle(BORDER,SQUARE,BORDER+6*SQUARE,BORDER+2*SQUARE,"white").makeVisible();
		
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<6;j++)
			{
				new Rectangle(SQUARE-BORDER*2,SQUARE-BORDER*2,2*BORDER+j*SQUARE,2*BORDER+i*SQUARE,"grey").makeVisible();
			}
		}	
	}
}
