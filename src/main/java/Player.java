import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
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

    private static float ANIMATION_TIME_MICRO = 1000;
    private float deathCounter = ANIMATION_TIME_MICRO;
    private static int START_PLAYER_LIVES = 3;
    private int lives = START_PLAYER_LIVES;
    private boolean died = false;

    /**
     * Create a new player class
     * @param img The Image to use
     * @param x The x position
     * @param y The y position
     */
    public Player(Image img, float x, float y, boolean addBox) {
        super(img, x, y, addBox);
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
            this.setPosition(LevelBuilder.L1_PLAYER_X, LevelBuilder.L1_PLAYER_Y);
        }
        if(lives < 1) {
            JOptionPane.showMessageDialog(new JFrame(), "You have died", "Shadow leap", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        // Move the player and clip the players movements,
        // restricting them to the game window.
        if(input.isKeyPressed(Input.KEY_UP)) {
            this.shiftPosition(0, -App.TILE_SIZE, true);
        }else if(input.isKeyPressed(Input.KEY_DOWN)) {
            this.shiftPosition(0, App.TILE_SIZE, true);
        }else if (input.isKeyPressed(Input.KEY_LEFT)) {
            this.shiftPosition(-App.TILE_SIZE, 0, true);
        } else if(input.isKeyPressed(Input.KEY_RIGHT)) {
            this.shiftPosition(App.TILE_SIZE, 0, true);
        }
    }


    @Override
    public void contactSprite(Sprite other) {
        died = true;
        lives--;
    }

}
