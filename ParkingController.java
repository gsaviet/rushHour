import java.awt.Point;
import java.util.Hashtable;

public class ParkingController
{
   public static boolean checkMovement (Movement move,
         Hashtable<String, Vehicule> lsVehicles) throws VictoryException
   {
      final Vehicule vMoving = lsVehicles.get(move.name);

      if (!checkSense(move.sense, vMoving.getDirection()))
         return false;

      try {
         if (!checkDistance(move, vMoving.getSize(),
                  ParkingController.convertInNbSquare(vMoving.getPosition())))
            return false;

         if (!checkOverlap(lsVehicles, move, vMoving))
            return false;
      } catch (VictoryException e) {
         try {
            if (!checkOverlap(lsVehicles, move, vMoving))
               return false;
         } catch (ArrayIndexOutOfBoundsException ex) {
            throw new VictoryException("!!! VICTORY !!!");
         }
      }

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

   private static boolean checkDistance (Movement move, int size, Point pos)
      throws VictoryException
   {
      if (Constants.RIGHT.equals(move.sense))
      {
         if ((pos.x + move.dist + size) <= Constants.NB_SQUARE)
            return true;
         else if ("X".equals(move.name))
            throw new VictoryException();
      }
      else if (Constants.LEFT.equals(move.sense))
      {
         if ((pos.x - move.dist) >= 0)
            return true;
      }
      else if (Constants.DOWN.equals(move.sense))
      {
         if ((pos.y + move.dist + size) <= Constants.NB_SQUARE)
            return true;
      }
      else
      {
         if ((pos.y - move.dist) >= 0)
            return true;
      }

      return false;
   }

   private static boolean checkOverlap (Hashtable<String, Vehicule> lsVehicles,
         Movement move, Vehicule vMoving)
   {
      final boolean[][] map = ParkingController.getMap(lsVehicles);
      final Point pos = ParkingController.convertInNbSquare(vMoving.getPosition());
      final int size = vMoving.getSize();

      for (int i = 1; i <= move.dist; i++)
         if (Constants.UP.equals(move.sense))
         {
            if (map[pos.x][pos.y - i]) return false;
         }
         else if (Constants.DOWN.equals(move.sense))
         {
            if (map[pos.x][pos.y + i + size - 1]) return false;
         }
         else if (Constants.RIGHT.equals(move.sense))
         {
            if (map[pos.x + i + size - 1][pos.y]) return false;
         }
         else
         {
            if (map[pos.x - i][pos.y]) return false;
         }
      return true;
   }

   private static Point convertInNbSquare (Point p)
   {
      return new Point(p.x / Constants.SQUARE, p.y / Constants.SQUARE);
   }

   private static boolean[][] getMap (Hashtable<String, Vehicule> lsVehicles)
   {
      final boolean[][] map = new boolean[Constants.NB_SQUARE][Constants.NB_SQUARE];
      final java.util.Set<String> keys = lsVehicles.keySet();
      for(String key : keys)
      {
         final Vehicule v = lsVehicles.get(key);
         final Point pos = convertInNbSquare(v.getPosition());
         final String direction = v.getDirection();
         for (int i = 0, size = v.getSize(); i < size; ++i)
            if (Constants.HORIZONTAL.equals(direction))
               map[pos.x + i][pos.y] = true;
            else
               map[pos.x][pos.y + i] = true;
      }
      return map;
   }

   private static void printMap (boolean[][] map)
   {
      for (int i = 0; i < map.length; i++)
      {
         for (int j = 0; j < map[i].length; j++)
            System.out.print((map[j][i]) ? "1 " : "0 ");
         System.out.println();
      }
      System.out.println();
   }
}
