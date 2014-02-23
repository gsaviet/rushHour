public class IllegalMovementException extends Exception
{
   public IllegalMovementException () { super(); }
   public IllegalMovementException (String message) { super(message); }
   public IllegalMovementException (String message, Throwable cause)
   {
      super(message, cause);
   }
   public IllegalMovementException (Throwable cause)
   {
      super(cause);
   }
}
