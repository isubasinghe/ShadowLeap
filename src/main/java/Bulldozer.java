import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;

public class Bulldozer extends Rideables{
    public Bulldozer(float x, float y, float travelSpeed, int travelDir) throws SlickException {
        super(AssetManager.getImage("bulldozer"), x, y, travelSpeed, travelDir);
    }

    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);
    }
}
