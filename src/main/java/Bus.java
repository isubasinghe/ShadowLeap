import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import utilities.BoundingBox;

/**
 * A bus class that inherits from Wrappable,
 * this ensures that it wraps around the screen
 * @author Isitha Subasinghe
 * @author https://isubasinghe.me
 * @version 0.1
 */
public class Bus extends Wrappable{
    // A global const for the speed of the bus, given in the spec sheet.
    protected static final float BUS_SPEED = 0.15f;
    // The physics updating is done through the bounding box held here
    private BoundingBox box;

    /**
     * Create a new bus sprite with the given params
     * @param busImg Image of the bus
     * @param x The x coord of the bus
     * @param y The y coord of the bus
     * @param speed The speed of the bus
     * @param travelDirection The direction of travel for the bus
     */
    public Bus(Image busImg, float x, float y, float speed, int travelDirection, boolean addBox) {
        super(busImg, x, y, speed, travelDirection, addBox);

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
