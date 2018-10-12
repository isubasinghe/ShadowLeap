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

    private static final float LIFE_X_START = 24;
    private static final float LIFE_Y_START = 744;
    private static final float LIFE_X_SKIP = 32;

    private int START_PLAYER_LIVES = 4;
    private Sprite lifeSprite;
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
        lifeSprite = new Sprite(AssetManager.getImage("lives"), LIFE_X_START, LIFE_Y_START, false);
        moveCheckBox = new BoundingBox(this.getImage(), 0, 0);
    }

    private void addLife() {
        lives++;
    }

    /**
     * setMoveValidity sets the players available move positions based
     * upon the input
     * @param sprite The sprite to check move position
     */
    public void setMoveValidity(Sprite sprite) {
        // We can move if the sprite isnt solid,
        // so dont change anything
        if(!sprite.isSolid()) {
            return;
        }
        // Set a checkBox to A tile
        // right, left, down and up of the player
        // to see if it collides with the current sprite.
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

    /**
     * Resets the player ability to move to true
     * in all directions.
     */
    public void resetMoveValidity() {
        canMoveDown = true;
        canMoveUp = true;
        canMoveRight = true;
        canMoveLeft = true;
    }

    /**
     * Resets the players Position to (PLAYER_X, PLAYER_Y)
     */
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

    /**
     * Render the player and also the lifes it has
     * @param g The graphics handle
     */
    @Override
    public void render(Graphics g) {
        super.render(g);
        for(int i=0; i < lives-1; i++) {
            lifeSprite.setPosition(LIFE_X_START + LIFE_X_SKIP*i, LIFE_Y_START);
            lifeSprite.render(g);
        }
    }

    /**
     * Kill the player once
     */
    public void triggerDeath() {
        died = true;
        lives--;
    }

    /**
     * Handle collisions with a sprite
     * @param other the other sprite that contacted with player
     */
    @Override
    public void contactSprite(Sprite other) {
        // Rideables don't trigger death
        if(other instanceof Rideables) {
            // Attach the player to the rideable
            ((Rideables) other).attachPlayer(this);
        }else if(other instanceof ExtraLife) {
            addLife();
        }else {
            triggerDeath();
        }
        // Turtle may not trigger death,
        // however it depends on the condition of the turtle
        if(other instanceof Turtle) {
            if( ((Turtle) other).getCondition() == Turtle.STATUS_DIVE ) {
                triggerDeath();
            }
        }
    }


}
