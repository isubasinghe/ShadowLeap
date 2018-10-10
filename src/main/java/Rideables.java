import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class Rideables extends Wrappable {
    Player player;
    public Rideables(Image img,float x, float y, float travelSpeed,int travelDir) {
        super(img, x, y, travelSpeed, travelDir, true);
    }

    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);
    }



    public void attachPlayer() {

    }
}
