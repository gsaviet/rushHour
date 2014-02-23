/**
 * The main class which creates a Player, ask the user
 * the level he wants to play, then plays it.
 * @author BRESSAN Romain, DUCOURNEAU Jonathan, LEBLOND Xavier, YAHYAOUI Hichem.
 * @version 1.0 - 10/02/2014
 */
public class RushHour2
{
   // scanner use for reading user's input.
   private static final java.util.Scanner sc = new java.util.Scanner(System.in);
   // use to generate random number.
   private static final java.util.Random rand =
      new java.util.Random(System.currentTimeMillis());

   // player that is currently playing.
   private static Player p;

   // level chosen by the player
   private static int lvl;
   // config chosen by the player
   private static int conf;

   /**
    * Main loop
    */
   public static void main (String[] args)
   {
      RushHour2.p = RushHour2.createPlayer();

      while (true)
         RushHour2.play();
   }

   /**
    * Exit the program.
    */
   public static void exit (int code)
   {
      System.out.println();
      Canvas.getCanvas().close();
      if (RushHour2.sc != null) sc.close();
      System.exit(code);
   }

   /**
    * Create a player and ask the user for a name.
    */
   private static Player createPlayer ()
   {
      // Create player.
      System.out.println("What's your name?");
      if (!sc.hasNextLine()) RushHour2.exit(0);
      return new Player(sc.nextLine());
   }

   /**
    * Ask the user for a level to play.
    */
   private static void chooseLevel ()
   {
      final String[] lsLvl = ParkingFactory.getParkingFactory().getListLevels();
      // Display level available.
      System.out.print("Level of difficulty available: ");
      for (String lvl : lsLvl)
         System.out.println("	 - "+ lvl);

      // Ask user.
      System.out.println("[Enter to let the computer choose]");
      System.out.print("Choose a level of difficulty: ");
      if (!sc.hasNextLine()) RushHour2.exit(0);
      final String input = sc.nextLine();

      // Check input.
      if ("".equals(input)) // Let the computer choose.
      {
         RushHour2.lvl = RushHour2.p.getHardestLvlDone();
         RushHour2.conf = RushHour2.chooseConfig(true);
         return;
      }
      else if (!isInteger(input))
      {
         System.err.println("Invalid input: it's not a number.");
         chooseLevel();
         return;
      }

      final int level = Integer.parseInt(input) - 1;

      if (level >= lsLvl.length)
      {
         System.err.println("Invalid input: max is " + lsLvl.length + ".");
         chooseLevel();
         return;
      }
      else if (level < 0)
      {
         System.err.println("Invalid input: min is 1.");
         chooseLevel();
         return;
      }

      RushHour2.lvl = level;
      RushHour2.conf = RushHour2.chooseConfig(false);
   }

   /**
    * @param randomly instead of asking the player, choose randomly.
    * @return the config chosen by the player.
    */
   private static int chooseConfig (boolean randomly)
   {
      final int nbConf = ParkingFactory.getParkingFactory().getNumberOfConfig(RushHour2.lvl);
      if (randomly) return rand.nextInt(nbConf);
      System.out.println("[Enter to let the computer choose]");
      System.out.println("The first is 1 and the last is " + nbConf + ".");
      // Ask user.
      System.out.print("Choose a config: ");
      if (!sc.hasNextLine()) RushHour2.exit(0);
      final String input = sc.nextLine();

      // Check input.
      if ("".equals(input)) // Let the computer choose.
         return chooseConfig(true);
      else if (!isInteger(input))
      {
         System.err.println("Invalid input: it's not a number.");
         return chooseConfig(randomly);
      }

      final int conf = Integer.parseInt(input) - 1;

      if (conf >= nbConf)
      {
         System.err.println("Invalid input: max is " + nbConf + ".");
         return chooseConfig(randomly);
      }
      else if (conf < 0)
      {
         System.err.println("Invalid input: min is 1.");
         return chooseConfig(randomly);
      }
      else return conf; // Valid input.
   }

   /**
    * Play the level chosen by the player.
    * Aks the user the level he wants to play, then play it.
    * Save his score and ask if he wants to continue after.
    */
   private static void play ()
   {
      // Ask user the level he wants to play.
      RushHour2.chooseLevel();

      // Play and save score.
      final int score = p.play(RushHour2.lvl, RushHour2.conf);
      RushHour2.p.setScore(RushHour2.lvl, RushHour2.conf, score);

      // Continue ?
      System.out.print("Continue [y or n]? ");
      if (!sc.hasNextLine() || "N".equals(sc.nextLine().toUpperCase()))
         RushHour2.exit(0); // End of game.

      System.out.println();
      System.out.println();
      Canvas.getCanvas().close();
   }

   /**
    * @return true if s is an Integer.
    */
   private static boolean isInteger (String s)
   {
      try {
         Integer.parseInt(s);
      } catch (NumberFormatException e) {
         return false;
      }
      return true;
   }
}
