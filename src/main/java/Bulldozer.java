import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;

public class Bulldozer extends Rideables{
    private static final float BULLDOZER_SPEED = 0.05f;

    /**
     * Create a bulldozer at the given coordinates,
     * bulldozers have the ability to squish a player when
     * the edge is reached
     * @param x The x coord
     * @param y The y coord
     * @param travelDir The travel direction
     * @throws SlickException
     */
    public Bulldozer(float x, float y, int travelDir) throws SlickException {
        super(AssetManager.getImage("bulldozer"), x, y, BULLDOZER_SPEED, travelDir);
        clippedPush = false;
        solid = true;
    }

    /**
     * The Bulldozer update triggers a player death
     * if the player is on the screen's edges.
     * @param input The input handling class.
     * @param delta The time since last update.
     */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);
        if(player != null) {
            if(player.getX() >= App.SCREEN_WIDTH) {
                player.triggerDeath();
            }else if(player.getX() <= 0) {
                player.triggerDeath();
            }
        }
    }

}
