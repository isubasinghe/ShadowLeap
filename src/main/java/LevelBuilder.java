import org.lwjgl.Sys;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import tile.TileImageBuilder;
import utilities.AssetManager;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * LevelBuilder is a class to simplify and abstract
 * the loading of sprites and collide-able objects.
 */
public class LevelBuilder {
    public static final int PLAYER_X = 512;
    public static final int PLAYER_Y = 720;
    public static ArrayList<Sprite> buildWorldByCSV(String path) throws Exception{
        ArrayList<Sprite> sprites = new ArrayList<>();
        FileReader fr = new FileReader(path);
        Scanner sc = new Scanner(fr);
        sc.useDelimiter(",");
        while (sc.hasNext()) {
            String info = sc.nextLine();
            String[] data = info.split(",");
            String assetName = data[0];
            int assetX = Integer.parseInt(data[1]);
            int assetY = Integer.parseInt(data[2]);

            System.out.print("|" + assetName + "|" + assetX + "|" + assetY + "|");
            if(data.length == 4) {
                boolean assetDir = Boolean.parseBoolean(data[3]);
                String dir = "LEFT";
                if(assetDir) {
                    dir = "RIGHT";
                }
                int travelDir = Wrappable.DIR_LEFT;
                if(assetDir) {
                    travelDir = Wrappable.DIR_RIGHT;
                }
                System.out.print(dir + "|");

                switch(assetName) {
                    case "log":
                        Log log = new Log("log", assetX, assetY, Log.LOG_SPEED, travelDir);
                        sprites.add(log);
                        break;
                    case "longLog":
                        Log longLog = new Log("longlog", assetX, assetY, Log.LONG_LOG_SPEED, travelDir);
                        sprites.add(longLog);
                        break;
                    case "bus":
                        Bus bus = new Bus(AssetManager.getImage("bus"), assetX, assetY, Bus.BUS_SPEED, travelDir, true);
                        sprites.add(bus);
                        break;
                    case "bulldozer":
                        Bulldozer bulldozer = new Bulldozer(assetX, assetY, travelDir);
                        sprites.add(bulldozer);
                        break;
                    case "bike":
                        Bike bike = new Bike(assetX, assetY, travelDir);
                        sprites.add(bike);
                        break;
                    case "racecar":
                        Racecar racecar = new Racecar(assetX, assetY, travelDir);
                        sprites.add(racecar);
                        break;
                    case "turtle":
                        Turtle turtle = new Turtle(assetX, assetY, travelDir);
                        sprites.add(turtle);
                        break;
                    default:
                        System.out.println("Unable to load this asset");
                }
            }else {
                switch(assetName) {
                    case "water":
                        Sprite water = new Sprite(AssetManager.getImage("water"), assetX, assetY, true);
                        sprites.add(water);
                        break;
                    case "grass":
                        Sprite grass = new Sprite(AssetManager.getImage("grass"), assetX, assetY, false);
                        sprites.add(grass);
                        break;
                    case "tree":
                        Sprite tree = new Sprite(AssetManager.getImage("tree"), assetX, assetY, true);
                        tree.setSolid(true);
                        sprites.add(tree);
                        break;
                    default:
                        System.out.println("Unable to load this asset");
                }
            }

            System.out.println();
        }
        sc.close();
        fr.close();
        return sprites;
    }



}
