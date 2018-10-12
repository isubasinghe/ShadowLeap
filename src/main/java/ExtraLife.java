import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;
import utilities.BoundingBox;

import java.util.ArrayList;

public class ExtraLife extends Sprite {

    private static final int KILL_TIME_MILLI = 14*1000;
    private static final int MOVE_TIME_MILLI = 2*1000;
    private static final int DIR_LEFT = 0;
    private static final int DIR_RIGHT = 1;

    private int killCounter = 0;
    private int moveCounter = 0;
    private int moveStatus = DIR_RIGHT;
    private ArrayList<Sprite> sprites;
    private Log log;

    private float logRelativeX = 0;

    /**
     * ExtraLife is a collidable class that grants the player an extra life
     * @param sprites The sprite array which will contain itself after being initialised
     * @param log The log to spawn on
     * @throws SlickException
     */
    public ExtraLife(ArrayList<Sprite> sprites, Log log) throws SlickException {
        super(AssetManager.getImage("extralife"), log.getX(), log.getY(), true);
        this.sprites = sprites;
        this.log = log;
    }


    private void setLogRelativeX() {
        this.setPosition(log.getX() + logRelativeX, log.getY());
    }

    private void move() {
        // Create a bounding box to ensure that we are always in the log
        BoundingBox canMoveBox = new BoundingBox(this.getX(), this.getY(),
                this.getImage().getWidth(), this.getImage().getHeight());

        // Check if we can move to the right or left
        if(moveStatus == DIR_RIGHT) {
            canMoveBox.setX(this.getX() + App.TILE_SIZE);
            if(log.box.intersects(canMoveBox)) {
                // Set the relative position
                logRelativeX = logRelativeX + App.TILE_SIZE;
            }else {
                // Set the relative position and change the travel direction
                // since we have reached the edge
                logRelativeX = logRelativeX - App.TILE_SIZE;
                moveStatus = DIR_LEFT;
            }
        }else {
            canMoveBox.setX(this.getX() - App.TILE_SIZE);
            if(log.box.intersects(canMoveBox)) {
                // Set the relative position
                logRelativeX = logRelativeX - App.TILE_SIZE;
            }else {
                // Set the relative postion and change the travel direction
                // since we have reached the edge
                logRelativeX = logRelativeX + App.TILE_SIZE;
                moveStatus = DIR_RIGHT;
            }
        }

    }

    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);

        // update the ExtraLife position, in relation to the Log
        setLogRelativeX();

        // Ensure that we should still be alive
        if(killCounter >= KILL_TIME_MILLI) {
            sprites.remove(this);
        }
        killCounter += delta;

        // Move every MOVE_TIME_MILLI
        if(moveCounter >= MOVE_TIME_MILLI) {
            move();
            moveCounter = 0;
        }
        moveCounter += delta;

        if(box != null) {
            box.setX(this.getX());
            box.setY(this.getY());
        }

    }


    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    @Override
    public void contactSprite(Sprite other) {
        // On contact delete the ExtraLife sprite
        sprites.remove(this);

    }
}
