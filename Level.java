import java.util.ArrayList;
import java.io.File;

public class Level
{
   /**
    * List of configuration available for this level.
    * A configuration is a list of starting position for
    * the vehicles.
    */
   private ArrayList<ArrayList<Position>> lsConfig;

   /**
    * The level's name. Eg: 1-beginner.
    * Name of the file without extension.
    */
   private String name;

   /**
    * Construct a level from the data inside the file.
    * @param pathLvlCfg path to the file.
    */
   public Level (String pathLvlCfg)
   {
      // Get the name.
      this.name = new File(pathLvlCfg).getName();
      if (this.name.indexOf(".") > 0)
         this.name = this.name.substring(0, this.name.lastIndexOf("."));

      // Get list of config from the file.
      lsConfig = new ArrayList<ArrayList<Position>>();
      LineFileReader file = new LineFileReader(pathLvlCfg);
      file.open();

      String line = file.readLine();
      while (line != null)
      {
         lsConfig.add(createConfiguration(line));
         line = file.readLine();
      }

      file.close();
   }

   private ArrayList<Position> createConfiguration (String lineLvlCfg)
   {
      ArrayList<Position> config = new ArrayList<Position>();
      String[] posCfg = lineLvlCfg.split(" ");
      for (String s : posCfg)
         config.add(new Position(s));
      return config;
   }

   /**
    * @return the level's name. Used to get a list of
    * every level available.
    */
   public String getName ()
   {
      return this.name;
   }

   /**
    * @return the number of configurations available for this level.
    */
   public int getNumberOfConfiguration ()
   {
      return lsConfig.size();
   }
}

class Position
{
   /** Vehicle's name. */
   public String name;
   /** Vehicle's direction. Eg: "H" or "V" */
   public String direction;
   /** Vehicle's position. */
   public int x;
   public int y;

   public Position (String posCfg)
   {
      this.name = posCfg.substring(0, 1);
      this.direction = posCfg.substring(1, 2);
      this.x = Integer.parseInt(posCfg.substring(2, 3));
      this.y = Integer.parseInt(posCfg.substring(3));
   }
}
