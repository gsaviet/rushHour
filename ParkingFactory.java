import java.util.Hashtable;
import java.util.ArrayList;

/**
 * Class which creates a Parking and fill it with data
 * read from the configuration files.
 *
 * @author BRESSAN Romain, DUCOURNEAU Jonathan, LEBLOND Xavier, YAHYAOUI Hichem.
 */
public class ParkingFactory
{

   private static ParkingFactory pfSingleton;

   public static ParkingFactory getParkingFactory ()
   {
      if (pfSingleton == null)
         pfSingleton = new ParkingFactory();
      return pfSingleton;
   }

   /* Constantes */
   private static final String PATH_VEHIC_DAT = "conf/vehicles.dat";
   private static final String[] PATH_LVL =
   {
      "conf/1-beginner.cfg",
      "conf/2-intermediate.cfg",
      "conf/3-advanced.cfg",
      "conf/4-expert.cfg",
      "conf/5-grandmaster.cfg"
   };

   private Hashtable<String, Vehicule> lsVehicles;
   private ArrayList<Level> lsLvl;

   private ParkingFactory ()
   {
      lsVehicles = new Hashtable<String, Vehicule>();
      readVehicDat();
      lsLvl = new ArrayList<Level>();
      readLvlCfg();
   }

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
    */
   private int getNumberOfConfig (int idLvl)
   {
      return lsLvl.get(idLvl).getNumberOfConfiguration();
   }
}
