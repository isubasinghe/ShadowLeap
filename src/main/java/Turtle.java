import org.lwjgl.Sys;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;

public class Turtle extends Rideables{
    public static final float TURTLE_SPEED = 0.085f;

    private static int DIVING_TIME_MILLI = 7000;
    private static int REAPPEAR_TIME_MILLI = 2000 + DIVING_TIME_MILLI;
    public static final int STATUS_DIVE = 0;
    public static final int STATUS_SURFACE = 1;

    private int condition = STATUS_SURFACE;
    private int elapsedTime = 0;

    /**
     * Create a rideable turtle class, the turtle has unique ability
     * to dive underwater
     * @param x The x coordinate
     * @param y The y coordinate
     * @param travelDir The direction of travel
     * @throws SlickException
     */
    public Turtle(float x, float y, int travelDir) throws SlickException {
        super(AssetManager.getImage("turtles"), x, y, TURTLE_SPEED, travelDir);
    }

    /**
     * The turtle should only draw if
     * it is on the surface
     * @param g The graphics handle
     */
    @Override
    public void render(Graphics g) {
        if(condition == STATUS_SURFACE) {
            super.render(g);
        }
    }

    /**
     *
     * @return Returns the condition of the turtle
     */
    public int getCondition() {
        return condition;
    }

    /**
     * The turtle divies every 7 seconds then surfaces
     * after 2 seconds
     * @param input The input handling class.
     * @param delta The time since last update.
     */
    @Override
    public void update(Input input, int delta) {

        super.update(input, delta);


        if(elapsedTime >= DIVING_TIME_MILLI && elapsedTime <= REAPPEAR_TIME_MILLI) {
            // We have reached the DIVING TIME limit but not the REAPPEAR TIME limit,
            // this means that we should be in a diving state.

            //Increment because we reappear at REAPPEAR_TIME_MICRO
            elapsedTime += delta;
            //System.out.println("Time to dive");
            condition = STATUS_DIVE;
            pushPlayer = false;
        }else if(elapsedTime >= REAPPEAR_TIME_MILLI) {
            // We have reached the reappear time,
            // this means we should surface
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
