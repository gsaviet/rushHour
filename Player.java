import java.util.ArrayList;
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

   public Player(String name)
   {
      this.name = name;
      this.scoreboard = new ArrayList<int[]>();
      this.load();
   }

   /*public int play(int level, int number) {
      //Parking parking = new Parking();
      String command
      while (command != 'exit') {
      }
      if (command == 'exit') {
         score = - 1;
      }
      return score;
   }*/

   public void setScore(int level, int number, int score)
   {
      if (this.getScore(level, number) == -1) {
      	int t[] = {level, number, score};
      	this.scoreboard.add(t);
      } else {

      }
      	this.save();

   }

   /**
    * @param level 
    */
   public int getScore(int level, int number)
   {
	for (int t[] : this.scoreboard) {
           if (t[0] == level && t[1] == number) {
              return t[2];
           }
        }
        return -1;
   }

   /**
    * Save the score of the Player in a file.
    */
   public void save()
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
    * Load the score of the Player.
    */
   public void load() 
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
