import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;

import java.util.ArrayList;
import java.util.Random;

public class ExtraLife extends Sprite {
    private static final int KILL_TIME_MILLI = 14*100;

    private int currentTime = 0;
    private ArrayList<Sprite> sprites;


    public ExtraLife(ArrayList<Sprite> sprites) throws SlickException {
        super(AssetManager.getImage("extralife"), 512-48, 720, true);
        this.sprites = sprites;
    }

    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);
        if(currentTime >= KILL_TIME_MILLI) {
            sprites.remove(this);
        }
        currentTime += delta;

    }


    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    @Override
    public void contactSprite(Sprite other) {
        sprites.remove(this);

    }
}
