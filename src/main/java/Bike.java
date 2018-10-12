import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;

public class Bike extends Wrappable{
    private static final float BIKE_SPEED = 0.2f;
    private static final int LEFT_X = 24;
    private static final int RIGHT_X = 1000;
    private static final String ASSET_NAME = "bike";
    /**
     * A Bike class flips after the XLeft and XRight coords have been reached
     * @param x The x position of the bik
     * @param y The y position of the bike
     * @param travelDir Wrappable.DIR_LEFT or Wrappable.DIR_RIGHT
     * @throws SlickException
     */
    public Bike(float x, float y, int travelDir) throws SlickException {

        super(AssetManager.getImage(ASSET_NAME), x, y, BIKE_SPEED, travelDir, true);
    }

    /**
     * The bike's update method flips direction after the coordinates
     * RIGHT_X or LEFT_X have been reached.
     * @param input The input handling class.
     * @param delta The time since last update.
     */
    @Override
    public void update(Input input, int delta) {

        if(this.getX() <= LEFT_X) {
            //We have reached the limit on the left hand side,
            //so flip direction.
            this.setTravelDirection(DIR_RIGHT);
        }else if (this.getX() >= RIGHT_X) {
            //We have reached the limit on the right hand side,
            // so flip direction
            this.setTravelDirection(DIR_LEFT);
        }
        super.update(input, delta);

    }
}
