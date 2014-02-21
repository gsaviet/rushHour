public class Position
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
