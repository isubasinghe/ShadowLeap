import org.lwjgl.Sys;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Rideables extends Wrappable {
    Player player;
    protected boolean clippedPush = true;
    protected boolean pushPlayer = true;
    public Rideables(Image img,float x, float y, float travelSpeed,int travelDir) {
        super(img, x, y, travelSpeed, travelDir, true);
    }

    @Override
    public void update(Input input, int delta) {
        //System.out.println("Done");
        super.update(input, delta);

        if(player != null) {
            if(player.intersects(this) && pushPlayer) {
                if(travelDirection == DIR_RIGHT) {
                    player.shiftPosition(this.speed*delta, 0, clippedPush);
                }else {
                    player.shiftPosition(-this.speed*delta, 0, clippedPush);
                }
            }else {
                player = null;
            }
        }

    }

    public void attachPlayer(Player player) {
        this.player = player;
    }
}
