import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import utilities.AssetManager;

public class Racecar extends Wrappable{
    private static final String ASSET_NAME = "racecar";
    private static final float RACECAR_SPEED = 0.5f;

    /**
     * Create a racecar object at the given params
     * @param x The x coord
     * @param y The y coord
     * @param travelDir The direction of travel
     * @throws SlickException
     */
    public Racecar(float x, float y, int travelDir) throws SlickException {
        super(AssetManager.getImage(ASSET_NAME), x, y,RACECAR_SPEED, travelDir, true);
    }





}
