import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;

public class Turtle extends Rideables{
    public Turtle(float x, float y, float travelSpeed, int travelDir) throws SlickException {
        super(AssetManager.getImage("turtle"), x, y, travelSpeed, travelDir);
    }

    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);
    }
}
