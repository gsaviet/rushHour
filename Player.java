import java.util.ArrayList;
public class Player {

   private String name;
   private ArrayList<int[]> scoreboard;
   
   /*public int play() {
      //Parking parking = new Parking();
      String command
      while (command != 'exit') {
      }
      if (command == 'exit') {
         score = - 1;
      }
      return score;
   }*/
   public void setScore(int level, int number, int score) {
      //foreach (int t[] : scoreboard) {
        // if ()
      int t [] = {level, number, score};
      this.scoreboard.add(t);
      save();
   }
   public void getScore() {}

   public void save() {
	LineFileWriter file = new LineFileWriter("scoreboard/" + this.name);
        file.open(false);
        for (int t[] : this.scoreboard) {
           for (int x : t) {
              file.print(Integer.toString(x));
           }
           file.print("\n");
        }
        file.close();
   }

   public void load() {}
}
