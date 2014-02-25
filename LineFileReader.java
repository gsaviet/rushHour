import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Class use for reading files.
 * Config files and scores files.
 *
 * @author BRESSAN Romain, DUCOURNEAU Jonathan, LEBLOND Xavier, YAHYAOUI Hichem.
 */
public class LineFileReader
{
   private String filename;
   private BufferedReader reader;

   /**
    * Get LineFileReader ready to read a file.
    * @param filename the file we want to read.
    */
   public LineFileReader (String filename)
   {
      this.filename = filename;
   }

   /**
    * Open the file selected with the constructor LineFileReader.
    */
   public boolean open ()
   {
      try {
         reader = new BufferedReader(new java.io.InputStreamReader(
                  LineFileReader.class.getResourceAsStream(this.filename)));
         return true;
      } catch (NullPointerException e) {
         System.err.println("Error while opening file \"" + filename + "\"");
      }
      return false;
   }

   public String readLine ()
   {
      String line = null;
      if (reader != null)
      {
         try {
            line = reader.readLine();
         } catch (java.io.IOException e) {
            System.err.println("Error while reading file \"" + filename + "\"");
         }
      }
      return line;
   }

   public boolean close ()
   {
      if (reader != null)
      {
         try {
            reader.close();
            return true;
         } catch (java.io.IOException e) {
            System.err.println("Error while closing file \"" + filename + "\"");
         }
      }
      return false;
   }
}
