import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;


/**
 * A bus class that inherits from Wrappable,
 * this ensures that it wraps around the screen
 * @author Isitha Subasinghe
 * @author https://isubasinghe.me
 * @version 0.3
 */
public class Bus extends Wrappable{
    private static final String ASSET_NAME = "bus";
    // A global const for the speed of the bus, given in the spec sheet.
    protected static final float BUS_SPEED = 0.15f;


    /**
     * Create a new bus sprite with the given params
     * @param x The x coord
     * @param y The y coord
     * @param travelDir The travel direction
     * @throws SlickException
     */
    public Bus(float x, float y, int travelDir) throws SlickException {
        super(AssetManager.getImage(ASSET_NAME), x, y, BUS_SPEED, travelDir, true);
    }



    /**
     * Update the bus location and its physics objects
     * if it has any.
     * @param input The input handling class.
     * @param delta The time since last update, in micro seconds.
     */
    @Override
    public void update(Input input, int delta) {
        // The wrapping around logic, is implemented in the super class.
        super.update(input, delta);
        // Check if we have been given a bounding box
        if(box != null) {
            // Change the position of the bounding box.
            box.setX(this.getX());
            box.setY(this.getY());
        }
    }
}
