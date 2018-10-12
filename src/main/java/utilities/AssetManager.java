package utilities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.HashMap;

/**
 * AssetManager is a static class to load images, the images are stored in a HashMap
 * and passed to Sprites. This ensures that memory consumption is low
 */
public class AssetManager {
    private static HashMap<String, Image> assets = new HashMap<>();
    private static final String DEFAULT_ASSETS_FOLDER = "./assets/";

    /**
     * Add a new image given its location and the name to the HashMap
     * @param location Location of the image file
     * @param name The Name used for loading the asset
     * @return Returns the Image loaded to the HashMap
     * @throws SlickException
     */
    public static Image addImage(String location, String name) throws SlickException {
        Image img = new Image(location);
        assets.putIfAbsent(name, img);
        return img;
    }

    /**
     * Retrieve an image given its name
     * @param name The name used for image loading
     * @return A reference to the Image in the HashMap with the given name
     * @throws SlickException
     */
    public static Image getImage(String name) throws SlickException{
        Image img = assets.get(name);
        if(img==null) {
            return addImage(DEFAULT_ASSETS_FOLDER+name+".png", name);
        }
        return img;
    }
}
