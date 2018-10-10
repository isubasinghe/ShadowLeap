import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;

public class Turtle extends Rideables{
    private static int DIVING_TIME_MICRO = 7000;
    private static int REAPPEAR_TIME_MICRO = 2000;

    public static final int STATUS_DIVE = 0;
    public static final int STATUS_SURFACE = 1;
    private int condition = STATUS_SURFACE;

    private int elapsedTime = 0;
    public Turtle(float x, float y, float travelSpeed, int travelDir) throws SlickException {
        super(AssetManager.getImage("turtle"), x, y, travelSpeed, travelDir);
    }

    @Override
    public void render(Graphics g) {
        if(condition == STATUS_SURFACE) {
            super.render(g);
        }
    }

    public int getCondition() {
        return condition;
    }

    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);
    }
}
