import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;

public class Log extends Rideables {
    public static final float LONG_LOG_SPEED = 0.07f;
    public static final float LOG_SPEED = 0.1f;

    /**
     * The log is a simple ridable class
     * @param name The image of the asset, to be loaded by the AssetManager
     * @param x The x coord
     * @param y The y coord
     * @param travelSpeed The travelSpeed of the Log
     * @param travelDir The travel direction of the Log
     * @throws SlickException
     */
    public Log(String name, float x, float y, float travelSpeed, int travelDir) throws SlickException {
        super(AssetManager.getImage(name), x, y, travelSpeed, travelDir);
    }



}
