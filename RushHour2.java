/**
 * @author BRESSAN Romain, DUCOURNEAU Jonathan, LEBLOND Xavier, YAHYAOUI Hichem
 * @version 1.0 - 10/02/2014
 */
public class RushHour2
{
   private static final java.util.Scanner sc = new java.util.Scanner(System.in);
   private static final java.util.Random rand =
      new java.util.Random(System.currentTimeMillis());

   public static void main(String[] args)
   {
      // Create player.
      System.out.println("What's your name?");
      if (!sc.hasNextLine()) RushHour2.exit(0);
      Player p = new Player(sc.nextLine());

      while (true)
      {
         String input = "";
         // Choose level
         System.out.print("Level of difficulty available: ");
         for (String lvl : ParkingFactory.getParkingFactory().getListLevels())
            System.out.println("	 - "+ lvl);
         System.out.println("[Enter to let the computer choose]");
         System.out.print("Choose a level of difficulty: ");
         if (!sc.hasNextLine()) RushHour2.exit(0);
         input = sc.nextLine();
         if ("".equals(input))
            System.out.println("TOTOTOO");
         final int level = Integer.parseInt(input) - 1;

         // Choose Config
         System.out.println("[Enter to let the computer choose]");
         System.out.println("The first is 1 and the last is "
               + ParkingFactory.getParkingFactory().getNumberOfConfig(level) + ".");
         System.out.print("Choose a config: ");
         if (!sc.hasNextLine()) RushHour2.exit(0);
         input = sc.nextLine();
         int config;
         if ("".equals(input))
            config = rand.nextInt(ParkingFactory.getParkingFactory().getNumberOfConfig(level));
         else
            config = Integer.parseInt(input) - 1;

         // Play
         final int score = p.play(level, config);
         p.setScore(level, config, score);

         // Continue ?
         System.out.print("Continue [y or n]? ");
         if (!sc.hasNextLine()) RushHour2.exit(0);
         if ("N".equals(sc.nextLine().toUpperCase()))
            RushHour2.exit(0);
         System.out.println();
         System.out.println();
      }
   }

   public static void exit (int code)
   {
      if (RushHour2.sc != null) sc.close();
      System.exit(code);
   }
}
