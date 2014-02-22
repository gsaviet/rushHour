import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

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
   public int play (int idLvl, int idConfig) {
      Parking park = ParkingFactory.getParkingFactory().createParking(idLvl, idConfig);
      Scanner sc = new Scanner(System.in);
      String movement;
      int score = 0;
      do {
         System.out.println("Please, enter a movement to do (exit to quit) : ");
         movement = sc.nextLine();
         if ("exit".equals(movement)) {
           score = -1;
           break;
         }
         try {
            park.move(movement.toUpperCase());
            score += 1;
         } catch (Exception e) {
            System.out.println("Sorry, but this movement is not valid.");
         }
         //TODO Add a condition when red car is out parking.
      } while (true);
      return score;
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
      if (this.getScore(idLvl, idConfig) == -1) {
         int t[] = {idLvl, idConfig, score};
         this.scoreboard.add(t);
      } else {
         for (int i = 0 ; i < this.scoreboard.size() ; i++) {
            int t[] = this.scoreboard.get(i);
            if (t[0] == idLvl && t[1] == idConfig && t[2] > score) {
               int newScoreArray[] = {idLvl, idConfig, score};
               this.scoreboard.set(i, newScoreArray);
            }
         }
      }
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
      for (int t[] : this.scoreboard) {
         if (t[0] == idLvl && t[1] == idConfig) {
            return t[2];
         }
      }
      return -1;
   }

   /**
    * Save the score of the Player in a file.
    */
   public void save ()
   {
      File f = new File(SCORE_PATH);
      if ((f.exists() && f.isDirectory()) == false) {
         new File(SCORE_PATH).mkdir();
      }
      LineFileWriter file = new LineFileWriter(SCORE_PATH + this.name);
      file.open(false);
      String buf = "";
      for (int t[] : this.scoreboard) {
         for (int x : t) {
            buf += Integer.toString(x) + ";";
         }
         file.print(buf.substring(0,buf.length()-1) + "\n");
         buf = "";
      }
      file.close();
   }

   /**
    * Load the score of the Player read in the file..
    */
   public void load () 
   {
      LineFileReader file = new LineFileReader(SCORE_PATH + this.name);
      Boolean isFileExist = file.open();
      if (isFileExist == true) {
         String line = file.readLine();
         while (line != null) {
            String score[] = line.split(";");
            int t[] = new int[3];
            for (int i = 0 ; i < score.length ; i++) {
               t[i] = Integer.parseInt(score[i]);
            }
            this.scoreboard.add(t);
            line = file.readLine();
         }
      }
      file.close();
   }
}
