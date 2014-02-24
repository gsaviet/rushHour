/**
 * Exception thrown when a player win.
 */
public class VictoryException extends Exception
{
   public VictoryException () { super(); }
   public VictoryException (String message) { super(message); }
   public VictoryException (String message, Throwable cause)
   {
      super(message, cause);
   }
   public VictoryException (Throwable cause)
   {
      super(cause);
   }
}
