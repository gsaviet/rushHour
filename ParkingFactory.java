import java.util.Hashtable;

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

   private Hashtable<String, Vehicule> lsVehicule;

   private ParkingFactory ()
   {
   }
}
