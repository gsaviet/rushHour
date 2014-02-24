/**
 * Class use to represent a movement in the game.
 * The user type a string and this class converts it to
 * a movement.
 */
public class Movement
{
   /** name of the vehicle that is moving. */
   public final String name;
   /** Sense of movement. U for up, D for Down, L for Left and R for Right. */
   public final String sense;
   /** Distance in number of square. */
   public final int dist;

   /**
    * Create a Movement from the string move typed by the user.
    */
   public Movement (String move)
   {
      this.name = move.substring(0, 1);
      this.sense = move.substring(1, 2);
      this.dist = Integer.parseInt(move.substring(2, 3));
   }

   /**
    * Convert the Movement to a String.
    */
   public String toString ()
   {
      return name + sense + dist;
   }
}
