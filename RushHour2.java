/**
 * @author BRESSAN Romain, DUCOURNEAU Jonathan, LEBLOND Xavier, YAHYAOUI Hichem
 * @version 1.0 - 10/02/2014
 */
public class RushHour2
{
   public static void main(String[] args) throws IllegalMovementException
   {
      Parking park = ParkingFactory.getParkingFactory().createParking(0, 1);
      //Suite des déplacements permettant la résolution du casse-tête
      park.move("PU3");
      park.move("OU2");
      park.move("CR2");
      park.move("AR3");
      park.move("OD3");
      park.move("XR1");
      park.move("BU4");
      park.move("XL1");
      park.move("OU3");
      park.move("CL3");
      park.move("AL3");
      park.move("PD3");
      park.move("OD3");
      park.move("XR5");
      park = ParkingFactory.getParkingFactory().createParking(4, 1);

      Player p = new Player("toto");
      p.setScore(3,2,1);
      p.setScore(3,2,4);
   }
}
