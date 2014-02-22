import java.util.Hashtable;
import java.util.ArrayList;

/**
 * Class which creates a Parking and fill it with data
 * read from the configuration files.
 * It can only be created once.
 *
 * @author BRESSAN Romain, DUCOURNEAU Jonathan, LEBLOND Xavier, YAHYAOUI Hichem.
 */
public class ParkingFactory
{

   /** Contains the ParkingFactory if it has already been created.
    * Ensure that there is only one ParkingFactory.
    */
   private static ParkingFactory pfSingleton;

   /**
    * @return the ParkingFactory. Create it if it doesn't exist.
    */
   public static ParkingFactory getParkingFactory ()
   {
      if (pfSingleton == null) pfSingleton = new ParkingFactory();
      return pfSingleton;
   }

   // Constantes
   /**
    * Path to vehicles.dat. This file contains information about
    * all the vehicles used in the game.
    */
   private static final String PATH_VEHIC_DAT = "conf/vehicles.dat";
   /**
    * Path to all level.cfg. Those files describe how vehicles are positioned
    * at the beginning of a level.
    */
   private static final String[] PATH_LVL =
   {
      "conf/1-beginner.cfg",
      "conf/2-intermediate.cfg",
      "conf/3-advanced.cfg",
      "conf/4-expert.cfg",
      "conf/5-grandmaster.cfg"
   };

   /**
    * List of all the vehicles used in the game.
    * They are stored inside an hashtable. The vehicles' name
    * are used as index.
    */
   private Hashtable<String, Vehicule> lsVehicles;
   /**
    * List of level available. Each level contains a list of configuration
    * which is a list of vehicles' position at the beginning of a new level.
    */
   private ArrayList<Level> lsLvl;

   /**
    * Construct the ParkingFactory. Only one can be instancied.
    * It reads the configuration files so it can create Parking after that.
    */
   private ParkingFactory ()
   {
      lsVehicles = new Hashtable<String, Vehicule>();
      readVehicDat();
      lsLvl = new ArrayList<Level>();
      readLvlCfg();
   }

   /**
    * Reads the file vehicles.dat.
    * It creates an hashtable which contains the list of vehicles.
    * Vehicles are indexed by their name.
    */
   private void readVehicDat ()
   {
      LineFileReader file = new LineFileReader(PATH_VEHIC_DAT);
      file.open();

      String line = file.readLine();
      while (line != null)
      {
         String name = line.substring(0, 1);
         int size = Integer.parseInt(line.substring(1, 2));
         String col = line.substring(2);
         lsVehicles.put(name, new Vehicule(name, col, size, 0, 0, "0"));
         line = file.readLine();
      }

      file.close();
   }

   /**
    * Reads level.cfg files inside conf.
    * Those files describe how vehicles are positioned at the beginning
    * of a level. Each one contains a few different configuration.
    */
   private void readLvlCfg ()
   {
      for (String pathLvlCfg : PATH_LVL)
         lsLvl.add(new Level(pathLvlCfg));
   }

   /**
    * @return an array of string used to display every level available.
    */
   private String[] getListLevels ()
   {
      String[] listLevels = new String[lsLvl.size()];
      int i = 0;

      for (Level lvl : lsLvl)
         listLevels[i++] = lvl.getName();

      return listLevels;
   }

   /**
    * @param idLvl index of a level in the list of level. The first level
    *    has an id equals to 0.
    * @return the number of configuration for the given level.
    *    Or -1 if the level isn't available.
    */
   private int getNumberOfConfig (int idLvl)
   {
      if (idLvl >= lsLvl.size()) return -1;
      return lsLvl.get(idLvl).getNumberOfConfiguration();
   }

   /**
    * Create and initialize a Parking with a level from one of
    * the configuration file.
    * @param idLvl index of the level of difficulty chosen by the player.
    *    The first level is 0.
    * @param idConfig index of the configuration chosen by the player.
    *    The first configuration is 0.
    * @return the parking ready to play.
    */
   public Parking createParking (int idLvl, int idConfig)
   {
      Canvas.getCanvas().clearCanvas(false);
      Parking park = new Parking();

      ArrayList<Position> lsPos = lsLvl.get(idLvl).getConfig(idConfig);
      for (Position p : lsPos)
      {
         Vehicule v = lsVehicles.get(p.name);
         park.addVehicule(p.name, v.getColor(), v.getLongueur(),
               p.x, p.y, p.direction);
      }
      return park;
   }
}
