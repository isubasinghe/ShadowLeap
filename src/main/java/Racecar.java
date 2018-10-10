import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import utilities.AssetManager;

public class Racecar extends Wrappable{
    private static final float RACECAR_SPEED = 0.5f;
    public Racecar(float x, float y, int travelDir) throws SlickException {
        super(AssetManager.getImage("racecar"), x, y,RACECAR_SPEED, travelDir, true);
    }





}
