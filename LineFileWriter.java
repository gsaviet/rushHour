import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Class use for writing files. Use by Player for saving scores.
 *
 * @author BRESSAN Romain, DUCOURNEAU Jonathan, LEBLOND Xavier, YAHYAOUI Hichem.
 */
public class LineFileWriter
{
   private String filename;
   private BufferedWriter writer;

   /**
    * Get LineFileWriter ready to write to a file.
    * @param filename the file where we want to write.
    */
   public LineFileWriter (String filename)
   {
      this.filename = filename;
      final java.io.File file = new java.io.File(filename);
      // Create file if it doesn't exist.
      try {
         if (!file.isFile() && !file.createNewFile())
            throw new java.io.IOException();
      } catch (java.io.IOException e) {
         System.err.println("Error creating new file: " + file.getAbsolutePath());
      }
   }

   /**
    * Open the file selected with the constructor LineFileWriter.
    * @param append If true do not overwrite the file, but instead
    *    append.
    */
   public boolean open (boolean append)
   {
      try {
         writer = new BufferedWriter(new FileWriter(filename, append));
         return true;
      } catch (java.io.IOException e) {
         System.err.println("Error while opening file \"" + filename + "\"");
      }
      return false;
   }

   /**
    * Print line inside the opened file.
    */
   public boolean print (String line)
   {
      if (writer != null)
      {
         try {
            writer.write(line);
            return true;
         } catch (java.io.IOException e) {
            System.err.println("Error while writing to file \"" + filename + "\"");
         }
      }
      return false;
   }

   /**
    * Same as print but add a newline after.
    */
   public boolean println (String line)
   {
      if (writer != null)
      {
         try {
            writer.write(line);
         writer.newLine();
            return true;
         } catch (java.io.IOException e) {
            System.err.println("Error while writing to file \"" + filename + "\"");
         }
      }
      return false;
   }

   /**
    * Close the opened file.
    */
   public boolean close ()
   {
      if (writer != null)
      {
         try {
            writer.close();
            return true;
         } catch (java.io.IOException e) {
            System.err.println("Error while closing file \"" + filename + "\"");
         }
      }
      return false;
   }
}
