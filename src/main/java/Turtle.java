import org.lwjgl.Sys;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;

public class Turtle extends Rideables{
    public static final float TURTLE_SPEED = 0.085f;

    private static int DIVING_TIME_MICRO = 7000;
    private static int REAPPEAR_TIME_MICRO = 2000 + DIVING_TIME_MICRO;

    public static final int STATUS_DIVE = 0;
    public static final int STATUS_SURFACE = 1;
    private int condition = STATUS_SURFACE;

    private int elapsedTime = 0;
    public Turtle(float x, float y, int travelDir) throws SlickException {
        super(AssetManager.getImage("turtles"), x, y, TURTLE_SPEED, travelDir);
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

        if(elapsedTime >= DIVING_TIME_MICRO && elapsedTime <= REAPPEAR_TIME_MICRO) {
            elapsedTime += delta;
            //System.out.println("Time to dive");
            condition = STATUS_DIVE;
            pushPlayer = false;
        }else if(elapsedTime >= REAPPEAR_TIME_MICRO) {
            elapsedTime = 0;
            // Time to resurface
            //System.out.println("Time to reappear");
            condition = STATUS_SURFACE;
            pushPlayer = true;
        }else {
            elapsedTime += delta;
        }
    }
}
