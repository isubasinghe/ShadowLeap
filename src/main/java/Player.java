import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;
import utilities.BoundingBox;

import javax.swing.*;

/**
 * A controllable class, in this game,
 * this is the frog.
 * @author Isitha Subasinghe
 * @author https://isubasinghe.me
 * @version 0.2
 */
public class Player extends Sprite {
    Sprite lifeSprite;
    private static float lifeXStart = 24;
    private static float lifeYStart = 744;
    private static float lifeXSkip = 32;
    private static int START_PLAYER_LIVES = 4;

    private boolean canMoveRight, canMoveLeft, canMoveUp, canMoveDown = true;
    private int lives = START_PLAYER_LIVES;
    private boolean died = false;
    private BoundingBox moveCheckBox;

    /**
     * Create a new player class
     * @param img The Image to use
     * @param x The x position
     * @param y The y position
     */
    public Player(Image img, float x, float y, boolean addBox) throws SlickException {

        super(img, x, y, addBox);
        lifeSprite = new Sprite(AssetManager.getImage("lives"), lifeXStart, lifeYStart, false);
        moveCheckBox = new BoundingBox(this.getImage(), 0, 0);
    }

    public void setMoveValidity(Sprite sprite) {
        if(!sprite.isSolid()) {
            return;
        }
        moveCheckBox.setY(getY());
        moveCheckBox.setX(getX() + App.TILE_SIZE);
        if(sprite.box.intersects(moveCheckBox)) {
            canMoveRight = false;
        }
        moveCheckBox.setX(getX() - App.TILE_SIZE);
        if(sprite.box.intersects(moveCheckBox)) {
            canMoveLeft = false;
        }
        moveCheckBox.setX(getX());
        moveCheckBox.setY(getY() + App.TILE_SIZE);
        if(sprite.box.intersects(moveCheckBox)) {
            canMoveDown = false;
        }
        moveCheckBox.setY(getY() - App.TILE_SIZE);
        if(sprite.box.intersects(moveCheckBox)){
            canMoveUp = false;
        }


    }

    public void resetMoveValidity() {
        canMoveDown = true;
        canMoveUp = true;
        canMoveRight = true;
        canMoveLeft = true;
    }

    public void resetPlayer() {
        setPosition(LevelBuilder.PLAYER_X, LevelBuilder.PLAYER_Y);
    }

    /**
     * Update the sprites location, if a
     * bounding box is present update that as well
     * @param input The input handle
     * @param delta The time passed in micro seconds.
     */
    @Override
    public void update(Input input, int delta) {

        if(died) {
            died = false;
            resetPlayer();
        }
        if(lives < 1) {
            JOptionPane.showMessageDialog(new JFrame(), "You have died", "Shadow leap", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        // Move the player and clip the players movements,
        // restricting them to the game window.
        if(input.isKeyPressed(Input.KEY_UP) && canMoveUp) {
            this.shiftPosition(0, -App.TILE_SIZE, true);
        }else if(input.isKeyPressed(Input.KEY_DOWN) && canMoveDown) {
            this.shiftPosition(0, App.TILE_SIZE, true);
        }else if (input.isKeyPressed(Input.KEY_LEFT) && canMoveLeft) {
            this.shiftPosition(-App.TILE_SIZE, 0, true);
        } else if(input.isKeyPressed(Input.KEY_RIGHT) && canMoveRight) {
            this.shiftPosition(App.TILE_SIZE, 0, true);
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        for(int i=0; i < lives-1; i++) {
            lifeSprite.setPosition(lifeXStart + lifeXSkip*i, lifeYStart);
            lifeSprite.render(g);
        }
    }

    public void triggerDeath() {
        died = true;
        lives--;
    }

    @Override
    public void contactSprite(Sprite other) {
        if(other instanceof Rideables) {
            ((Rideables) other).attachPlayer(this);
        }else {
            triggerDeath();
        }
        if(other instanceof Turtle) {
            if( ((Turtle) other).getCondition() == Turtle.STATUS_DIVE ) {
                triggerDeath();
            }
        }
    }


}
