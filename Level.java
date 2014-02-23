import java.util.ArrayList;

/**
 * Class use for loading data from the level.cfg files.
 * A level is one file which is composed of several
 * configuration. A config is a list of starting position
 * for the vehicle.
 *
 * @author BRESSAN Romain, DUCOURNEAU Jonathan, LEBLOND Xavier, YAHYAOUI Hichem.
 */
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
      this.name = new java.io.File(pathLvlCfg).getName();
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

   /**
    * Create a configuration which is a list of Position.
    * @param lineLvlCfg a line of a level.cfg file that store data
    *    about a configuration. 4 letters words (such as XH02)
    *    separated by spaces.
    */
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

   /**
    * @param idConfig index of a config in the list of configuration.
    * @return the list of vehicles' position at the beginning
    *    of a level for the given configuration.
    */
   public ArrayList<Position> getConfig (int idConfig)
   {
      return lsConfig.get(idConfig);
   }
}
