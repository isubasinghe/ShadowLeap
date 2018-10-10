import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;

public class Bulldozer extends Rideables{
    private static final float BULLDOZER_SPEED = 0.05f;
    public Bulldozer(float x, float y, int travelDir) throws SlickException {
        super(AssetManager.getImage("bulldozer"), x, y, BULLDOZER_SPEED, travelDir);
        clippedPush = false;
    }

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
