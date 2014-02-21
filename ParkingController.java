import java.util.Hashtable;

public class ParkingController
{
   public static boolean verif (Hashtable<String, Vehicule> tableVehicule,
         String nom, String direction, int move)
   {
      Vehicule car = tableVehicule.get(nom);
      String direct = car.getDirection();
      int x = car.getPosition().x;
      int y = car.getPosition().y;

      if(verifDirection(direct, direction))
         if(verifMove(move, direct, x, y, direction))
            if(verifE())
               return true;

      return false;
   }

   private static boolean verifDirection (String direct, String sens)
   {
      if (Constants.HORIZONTAL.equals(direct))
         if (Constants.RIGHT.equals(sens) || Constants.LEFT.equals(sens))
            return true;
         else
            return false;
      else
         if (Constants.UP.equals(sens) || Constants.DOWN.equals(sens))
            return true;
         else
            return false;
   }

   private static boolean verifMove (int move, String direct,
         int x, int y, String sens)
   {
      if (Constants.HORIZONTAL.equals(direct))
         if (Constants.RIGHT.equals(sens))
            if (((x + move) * Constants.SQUARE) < 6)
               return true;
            else return false;
         else 
            if(((x - move) * Constants.SQUARE) > 0)
               return true;
            else return false;
      else
         if(Constants.DOWN.equals(sens))
            if(((y + move) * Constants.SQUARE) < 6)
               return true;
            else return false;

         else 
            if(((y - move) * Constants.SQUARE) > 0)
               return true;
            else return false;
   }

   private static boolean verifE()
   {
      return false;
   }
}
