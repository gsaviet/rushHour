/**
 * Class used for storing the data reads inside the level.cfg files.
 * Each line is several Position. The syntax for a Position is NDxy :
 *    – N is a char for the vehicle's name.
 *    - D is a char for the vehicle's direction. (H or V)
 *    - x and y for the vehicle's position.
 *
 * @author BRESSAN Romain, DUCOURNEAU Jonathan, LEBLOND Xavier, YAHYAOUI Hichem.
 */
public class Position
{
   /** Vehicle's name. */
   public String name;
   /** Vehicle's direction. Eg: "H" or "V" */
   public String direction;
   /** Vehicle's position. */
   public int x;
   public int y;

   /**
    * Create a Position with the given string readed in a level.cfg file.
    * @param posCfg the string with information about a Vehicle's starting position.
    * (Eg: XH12).
    */
   public Position (String posCfg)
   {
      this.name = posCfg.substring(0, 1);
      this.direction = posCfg.substring(1, 2);
      this.x = Integer.parseInt(posCfg.substring(2, 3));
      this.y = Integer.parseInt(posCfg.substring(3));
   }
}
