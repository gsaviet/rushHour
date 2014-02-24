import java.util.ArrayList;

/**
 * Class wich creates a Player, command vehicules and save his score in a file.
 *
 * @author BRESSAN Romain, DUCOURNEAU Jonathan, LEBLOND Xavier, YAHYAOUI Hichem.
 */
public class Player
{
   // This Player's name.
   private String name;

   // For keeping the score.
   private ArrayList<int[]> scoreboard;

   // Path to the directory which contains the score's files.
   private static final String SCORE_PATH = "scoreboard/"; 

   /**
    * Construct the Player. Only the player's name is needed.
    * The constructor load player's score.
    */
   public Player (String name)
   {
      this.name = name;
      this.scoreboard = new ArrayList<int[]>();
      this.load();
   }

   /**
    * Creates a Parking with the level and configuration given.
    * Allow user to move the vehicles.
    * @param idLvl index of the level of difficulty chosen by the player.
    * @param idConfig index of the configuration chosen by the player.
    * @return the score, ie the number of movement.
    *       -1 if the Player type "exit".
    */
   public int play (int idLvl, int idConfig)
   {
      int score = this.getScore(idLvl, idConfig);
      System.out.println();
      System.out.println("=====> Level " + (idLvl + 1)
            + "  Config " + (idConfig + 1) +" <=====");
      if (score != -1)
         System.out.println("Your best score for this level is " + score + ".");
      score = 0;
      System.out.println("Enter exit or quit to quit.");

      final Parking park = ParkingFactory.getParkingFactory().createParking(idLvl, idConfig);
      final java.util.Scanner sc = new java.util.Scanner(System.in);

      while (true)
      {
         // Ask user.
         System.out.print("movement? ");

         if (!sc.hasNextLine()) // quit when Ctrl+d is pressed.
         {
            System.out.println("exit");
            return -1;
         }

         final String movement = sc.nextLine().toUpperCase();
         if ("EXIT".equals(movement) || "QUIT".equals(movement)
               || "Q".equals(movement)) return -1; // quit

         try {
            park.move(new Movement(movement));
            ++score;
         } catch (IllegalMovementException e) {
            System.out.println(e + " is not a valid movement.");
         } catch (VictoryException e) {
            System.out.println("Victory");
            System.out.println("Your score is: " + ++score);
            return score;
         } catch (Exception e) {
            System.out.println("Syntax error: a movement has the following format XU1.");
            System.out.println("Where X is the vehicle's name. U the sense.");
            System.out.println("U for Up, D for Down, L for Left and R for Right.");
            System.out.println("And where 1 is the distance in number of square.");
         }
      }
   }

   /**
    * Add the score to the scoreboard and call save() to save
    * it in a file. Only the best score is saved.
    * The best score is the lowest number of movement the player has done.
    * @param idLvl index of the level of difficulty.
    * @param idConfig index of the configuration's level.
    * @param score of the player.
    */
   public void setScore (int idLvl, int idConfig, int score)
   {
      if (score == -1) // Don't save score equals to -1
         return;
      else if (this.getScore(idLvl, idConfig) == -1)
         this.scoreboard.add(new int[]{idLvl, idConfig, score});
      else
         for (int[] t : this.scoreboard)
            if (t[0] == idLvl && t[1] == idConfig && t[2] > score)
               this.scoreboard.set(scoreboard.indexOf(t),
                     new int[]{idLvl, idConfig, score});
      this.save();
   }

   /**
    * @param idLvl index of the level of difficulty.
    * @param idConfig index of the configuration.
    * @return the score of the Player for the idLvl and idConfig given. -1 if the
    * Player has never this idConfig.
    */
   public int getScore (int idLvl, int idConfig)
   {
      for (int[] t : this.scoreboard)
         if (t[0] == idLvl && t[1] == idConfig)
            return t[2];
      return -1;
   }

   /**
    * Save the score of the Player in a file.
    */
   public void save ()
   {
      // check if the score directory exists, else create it.
      final java.io.File f = new java.io.File(SCORE_PATH);
      if ((f.exists() && f.isDirectory()) == false)
         f.mkdir();

      final LineFileWriter file = new LineFileWriter(SCORE_PATH + this.name);
      file.open(false);
      String buf = ""; // use a buffer to remove the last ";"
      for (int[] t : this.scoreboard)
      {
         for (int x : t)
            buf += Integer.toString(x) + ";";
         file.println(buf.substring(0, buf.length() - 1));
         buf = "";
      }
      file.close();
   }

   /**
    * Load this Player's best score from a file.
    */
   public void load () 
   {
      final LineFileReader file = new LineFileReader(SCORE_PATH + this.name);
      if (file.open())
      {
         String line = file.readLine();
         while (line != null)
         {
            final String[] score = line.split(";");
            int[] t = new int[3];
            for (int i = 0 ; i < score.length ; i++)
               t[i] = Integer.parseInt(score[i]);
            this.scoreboard.add(t);
            line = file.readLine();
         }
      }
      file.close();
   }

   /**
    * @return the hardest level's index that the player had finished.
    */
   public int getHardestLvlDone ()
   {
      int hardestLvl = 0;
      for (int[] t : this.scoreboard)
         if (hardestLvl < t[0] && t[2] != -1)
            hardestLvl = t[0];

      return hardestLvl;
   }
}
