public class ParkingController
{
   public static boolean checkMovement (String name, String sense, int distance,
         java.util.Hashtable<String, Vehicule> lsVehicles)
   {
      final Vehicule v = lsVehicles.get(name);
      final String direction = v.getDirection();
      final java.awt.Point pos = v.getPosition();

      System.out.println("0");

      if (!checkSense(sense, direction))
         return false;

      System.out.println("1");
      if (!checkDistance(distance, sense, direction, pos))
         return false;

      System.out.println("2");
      if (!checkOverlap())
         return false;

      System.out.println("3");
      return true;
   }

   private static boolean checkSense (String sense, String direction)
   {
      if (Constants.HORIZONTAL.equals(direction))
         if (Constants.RIGHT.equals(sense) || Constants.LEFT.equals(sense))
            return true;
         else
            return false;
      else
         if (Constants.UP.equals(sense) || Constants.DOWN.equals(sense))
            return true;
         else
            return false;
   }

   // sans direction + voiture X peut sortir.
   private static boolean checkDistance (int distance, String sense,
         String direction, java.awt.Point pos)
   {
      if (Constants.HORIZONTAL.equals(direction))
         if (Constants.RIGHT.equals(sense))
            if (((pos.x + distance) * Constants.SQUARE) < 6)
               return true;
            else return false;
         else 
            if (((pos.x - distance) * Constants.SQUARE) > 0)
               return true;
            else return false;
      else
         if (Constants.DOWN.equals(sense))
            if (((pos.y + distance) * Constants.SQUARE) < 6)
               return true;
            else return false;

         else 
            if (((pos.y - distance) * Constants.SQUARE) > 0)
               return true;
            else return false;
   }

   private static boolean checkOverlap ()
   {
      return true;
   }
}
