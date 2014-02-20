import java.util.ArrayList;
public class Player {

   private String name;
   private ArrayList<int[]> scoreboard;
   

   public Player(String name) {
      this.name = name;
      this.scoreboard = new ArrayList<int[]>();
      this.load();
   }
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
      int t[] = {level, number, score};
      this.scoreboard.add(t);
      this.save();
   }
   public void getScore() {}

   public void save() {
	LineFileWriter file = new LineFileWriter("scoreboard/" + this.name);
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

   public void load() {
	LineFileReader file = new LineFileReader("scoreboard/" + this.name);
	file.open();
        String line = file.readLine();
        System.out.println(line);
        while (line != null) {
           String score[] = line.split(";");
           int t[] = new int[3];
           for (int i = 0 ; i < score.length ; i++) {
              t[i] = Integer.parseInt(score[i]);
           }
           this.scoreboard.add(t);
           line = file.readLine();
        }
        file.close();
   }
}
