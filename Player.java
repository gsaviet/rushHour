import java.util.ArrayList;

/**
 * Class wich creates a Player, command vehicules and save his score in a file.
 *
 * @author BRESSAN Romain, DUCOURNEAU Jonathan, LEBLOND Xavier, YAHYAOUI Hichem.
 */
public class Player
{
   private String name;
   private ArrayList<int[]> scoreboard;
   private static final String SCORE_PATH = "scoreboard/"; 

   /**
    * Construct the Player. Only the name of the Player is needed. The constructer
    * load the score of the player.
    */
   public Player (String name)
   {
      this.name = name;
      this.scoreboard = new ArrayList<int[]>();
      this.load();
   }

   /**
    * Creates a Parking with the level and configuration given. Allow user to move
    * the vehicules.
    * @param idLvl index of the level of difficulty chosen by the player.
    * @param idConfig index of the configuration chosen by the player.
    * @return the number of shots. -1 if the Player type "exit".
    */
   public int play (int idLvl, int idConfig)
   {
      System.out.println();
      System.out.println("Level " + (idLvl + 1) + " Config " + (idConfig + 1));
      final Parking park = ParkingFactory.getParkingFactory().createParking(idLvl, idConfig);
      final java.util.Scanner sc = new java.util.Scanner(System.in);
      int score = 0;
      while (true)
      {
         System.out.print("Enter a movement (exit to quit) : ");

         if (!sc.hasNextLine()) // quit when Ctrl+d is pressed.
         {
            System.out.println();
            return -1;
         }

         final String movement = sc.nextLine().toUpperCase();
         if ("EXIT".equals(movement)) return -1; // quit
         try {
            park.move(movement);
            ++score;
         } catch (IllegalMovementException e) {
            System.out.println(e + " is not a valid movement.");
         } catch (VictoryException e) {
            System.out.println(e);
            System.out.println("Your score is :" + ++score);
            return score;
         } catch (Exception e) { System.out.println("CACA");}
      }
   }

   /**
    * Add the score to the scoreboard and call save() to save 
    * it in a file. Only the best score is saved. The best score is the lower 
    * number of shots the player do.
    * @param idLvl index of the level of difficulty.
    * @param idConfig index of the configuation's level.
    * @param score of the player.
    */
   public void setScore (int idLvl, int idConfig, int score)
   {
      if (this.getScore(idLvl, idConfig) == -1)
         this.scoreboard.add(new int[]{idLvl, idConfig, score});
      else
         for (int t[] : this.scoreboard)
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
      for (int t[] : this.scoreboard)
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
      for (int t[] : this.scoreboard) {
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
            final String score[] = line.split(";");
            int t[] = new int[3];
            for (int i = 0 ; i < score.length ; i++)
               t[i] = Integer.parseInt(score[i]);
            this.scoreboard.add(t);
            line = file.readLine();
         }
      }
      file.close();
   }
}
