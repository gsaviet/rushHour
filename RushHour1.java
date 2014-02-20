/**
 * Cette classe permet de tester la première version des classes Grid, Vehicule et Parking 
 * @author Denis Mahieux, Kévin Meyer, Levy Nguema, Hamza Oukabli
 * @version 1.0 - 10/02/2014
 */
public class RushHour1 {
	public static void main(String[] args) {
		//Création du parking
		Parking parking = new Parking();
		
		//Ajout des différents objets Vehicule dans le Parking
		parking.addVehicule("X","red",2,1,2,"H");
		parking.addVehicule("A","green",2,1,3,"H");
		parking.addVehicule("B","orange",2,1,4,"V");
		parking.addVehicule("C","blue",2,2,5,"H");
		parking.addVehicule("O","light_yellow",3,3,2,"V");
		parking.addVehicule("P","magenta",3,5,3,"V");
		
		//Suite des déplacements permettant la résolution du casse-tête
		parking.move("PU3");
		parking.move("OU2");
		parking.move("CR2");
		parking.move("AR3");
		parking.move("OD3");
		parking.move("XR1");
		parking.move("BU4");
		parking.move("XL1");
		parking.move("OU3");
		parking.move("CL3");
		parking.move("AL3");
		parking.move("PD3");
		parking.move("OD3");
		parking.move("XR6");
	}
}

