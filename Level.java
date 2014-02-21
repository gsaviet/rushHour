import java.util.ArrayList;
import java.io.File;

public class Level
{
   private ArrayList<ArrayList<Position>> lsConfig;
   private String name;

   public Level (String pathLvlCfg)
   {
      this.name = new File(pathLvlCfg).getName();
      if (this.name.indexOf(".") > 0)
         this.name = this.name.substring(0, this.name.lastIndexOf("."));
      lsConfig = new ArrayList<ArrayList<Position>>();
      LineFileReader file = new LineFileReader(pathLvlCfg);
      file.open();
      String line = file.readLine();
      while (line != null)
      {
         lsConfig.add(createConfiguration(line));
         line = file.readLine();
      }
      file.close();
   }

   private ArrayList<Position> createConfiguration (String lineLvlCfg)
   {
      ArrayList<Position> config = new ArrayList<Position>();
      String[] posCfg = lineLvlCfg.split(" ");
      for (String s : posCfg)
         config.add(new Position(s));
      return config;
   }

   public String getName ()
   {
      return this.name;
   }
}

class Position
{
   public String name;
   public String direction;
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
