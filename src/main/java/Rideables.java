import org.lwjgl.Sys;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Rideables extends Wrappable {
    Player player;
    protected boolean clippedPush = true;
    protected boolean pushPlayer = true;

    /**
     * The Rideables class defines the behaviour for any
     * subclasses to inherit the behaviour to push a player along.
     * @param img The Sprites image
     * @param x The x coordinate
     * @param y The y coordinate
     * @param travelSpeed The speed of the Rideable object
     * @param travelDir The direction of the object.
     */
    public Rideables(Image img,float x, float y, float travelSpeed,int travelDir) {
        super(img, x, y, travelSpeed, travelDir, true);
    }

    /**
     * The update of the ridables handles the pushing of an attached player.
     * @param input The input handling class.
     * @param delta The time since last update.
     */
    @Override
    public void update(Input input, int delta) {

        super.update(input, delta);

        // A player has been attached
        if(player != null) {
            // The player is on the rideable and is capable of being pushed
            if(player.intersects(this) && pushPlayer) {
                //Push the player in the rideables direction
                if(travelDirection == DIR_RIGHT) {
                    player.shiftPosition(this.speed*delta, 0, clippedPush);
                }else {
                    player.shiftPosition(-this.speed*delta, 0, clippedPush);
                }
            }else {
                //Detach the player
                player = null;
            }
        }

    }

    /**
     * Attach a player to a rideables class
     * @param player The player object
     */
    public void attachPlayer(Player player) {
        this.player = player;
    }
}