import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;

public class Bike extends Wrappable{
    private static final float BIKE_SPEED = 0.2f;
    private static final int XLeft = 24;
    private static final int XRight = 1000;

    public Bike(float x, float y, int travelDir) throws SlickException {

        super(AssetManager.getImage("bike"), x, y, BIKE_SPEED, travelDir, true);
    }

    @Override
    public void update(Input input, int delta) {
        if(this.getX() <= 24) {
            this.setTravelDirection(DIR_RIGHT);
        }else if (this.getX() >= 1000) {
            this.setTravelDirection(DIR_LEFT);
        }
        super.update(input, delta);
        if(box != null) {
            box.setY(this.getY());
            box.setX(this.getX());
        }
    }
}
