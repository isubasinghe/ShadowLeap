import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;

public class Log extends Rideables {
    public static final float LONG_LOG_SPEED = 0.07f;
    public static final float LOG_SPEED = 0.1f;
    public Log(String type, float x, float y, float travelSpeed, int travelDir) throws SlickException {
        super(AssetManager.getImage(type), x, y, travelSpeed, travelDir);
    }



}
