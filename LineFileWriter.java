import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LineFileWriter
{
   private String filename;
   private BufferedWriter writer;

   public LineFileWriter (String filename)
   {
      this.filename = filename;
   }

   public boolean open (boolean append)
   {
      try {
         writer = new BufferedWriter(new FileWriter(filename, append));
         return true;
      } catch (IOException e) {
         System.err.println("Error while opening file \"" + filename + "\"");
      }
      return false;
   }

   public boolean print (String line)
   {
      if (writer != null)
      {
         try {
            writer.write(line);
            return true;
         } catch (IOException e) {
            System.err.println("Error while writing to file \"" + filename + "\"");
         }
      }
      return false;
   }

   public boolean close ()
   {
      if (writer != null)
      {
         try {
            writer.close();
            return true;
         } catch (IOException e) {
            System.err.println("Error while closing file \"" + filename + "\"");
         }
      }
      return false;
   }
}
