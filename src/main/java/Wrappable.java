import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

/**
 * Represents a wrapping sprite,
 * Any class that inherits from this class
 * will be able to wrap around the screen frame.
 * This is necessary in project 2, where there
 * will be obstacles that wrap around as well.
 * @author  Isitha Subasinghe
 * @author https://isubasinghe.me
 * @version 0.2
 */
public class Wrappable extends Sprite {
    /** A constant representing the left direction*/
    protected static final int DIR_LEFT = 0;
    /** A constant representing the right direction*/
    protected static final int DIR_RIGHT = 1;

    /** Represents the speed at which the sprite should move at*/
    protected float speed;

    /**Represents the direction of travel*/
    protected int travelDirection;

    /**
     * A class that im
     * @param img The image to use when rendering
     * @param x The x position of the sprite
     * @param y The y position of the sprite
     * @param speed The speed of the moving sprite
     * @param travelDirection The direction of the sprite
     */
    public Wrappable(Image img, float x, float y, float speed, int travelDirection, boolean addBox) {
        super(img, x, y, addBox);
        this.speed = speed;
        this.travelDirection = travelDirection;
    }

    /**
     * Change the position of the sprite
     * and manage the wrapping around.
     * @param input The input handling class.
     * @param delta The time since last update.
     */
    @Override
    public void update(Input input, int delta) {
        // Check the direction of travel.
        switch(travelDirection) {

            case DIR_LEFT:
                // Check if we have gone too much to the left
                if(this.getX() < 0) {
                    // We move the sprite to the right hand side of the screen
                    this.shiftPosition(App.SCREEN_WIDTH  + this.getImage().getWidth()/2, 0, false);
                }else {
                    // We can move more to the left, so move more.
                    this.shiftPosition(-delta*speed, 0, false);
                }
            break;

            case DIR_RIGHT:
                // Check if we have gone too much to the right
                if(this.getX() > App.SCREEN_WIDTH + this.getImage().getWidth()/2) {
                    // Move the sprite to the left hand side of the screen
                    this.shiftPosition(-App.SCREEN_WIDTH-this.getImage().getWidth()/2, 0, false);
                }else {
                    // The sprite can move more to the right, so keep on moving.
                    this.shiftPosition(delta*speed, 0, false);
                }
            break;

            default: break;
        }
        if(box != null) {
            box.setX(this.getX());
            box.setY(this.getY());
        }
    }

    public void setTravelDirection(int DIR) {
        this.travelDirection = DIR;
    }
}
