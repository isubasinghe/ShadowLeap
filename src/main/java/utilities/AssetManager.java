package utilities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.HashMap;

public class AssetManager {
    private static HashMap<String, Image> assets;
    private static final String DEFAULT_ASSETS_FOLDER = "./assets/";
    public static Image addImage(String location, String name) throws SlickException {
        Image img = new Image(location);
        assets.putIfAbsent(name, img);
        return img;
    }

    public static Image getImage(String name) throws SlickException{
        Image img = assets.get(name);
        if(img==null) {
            return addImage(DEFAULT_ASSETS_FOLDER+name+".png", name);
        }
        return img;
    }
}
