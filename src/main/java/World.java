import org.lwjgl.Sys;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the games world.
 * @author Isitha Subasinghe
 * @author https://isubasinghe.me
 * @version 0.4
 */
public class World {
	private Player player;
	// store all the sprites that we need to render
	private ArrayList<Sprite> sprites;

	private ArrayList<Log> logs;

	private static final String LEVELS_FLDR = "./assets/levels";
	private static int level = 0;
	private static final int TIME_MIN_MILLI = 25*1000;
	private static  final int BOUND_ZEROED = (35*1000) - TIME_MIN_MILLI;

	private int currentTime = 0;
	private int randomTimer = 0;
	private Random rand;

	private void startTimer() {
		currentTime = 0;
		randomTimer = rand.nextInt(BOUND_ZEROED) + TIME_MIN_MILLI;
	}
	private void loadLevel() {
		try {
			String file = String.format("%s/%d.lvl", LEVELS_FLDR, level);
			sprites = LevelBuilder.buildWorldByCSV(file);
			level++;
		}catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(),
					"ShadowLeap", JOptionPane.ERROR_MESSAGE);

			System.exit(1);
		}
		getLogs();
	}

	// Store the logs in a separate array for quick access
	private void getLogs() {
		for(int i=0; i < sprites.size(); i++) {
			if(sprites.get(i) instanceof Log) {
				logs.add((Log)sprites.get(i));
			}
		}
	}

	/**
	 *  Create the world used in the game.
	 */
	public World() throws SlickException{
		player = new Player(AssetManager.getImage("frog"),
				LevelBuilder.PLAYER_X, LevelBuilder.PLAYER_Y, true);
		rand = new Random();
		logs = new ArrayList<>();
		loadLevel();
		startTimer();
	}

	/**
	 * Update the sprites states
	 * @param input Input handle
	 * @param delta The time passed in micro seconds.
	 */
	public void update(Input input, int delta) throws SlickException{

		// Iterate over the sprites and update them
		for(int i=0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			sprite.update(input, delta);
			player.setMoveValidity(sprite);
		}
		//player.update(input, delta);

		for(int i=sprites.size()-1; i >= 0; i--) {
			Sprite sprite = sprites.get(i);
			if(sprite.isCollidable() && player.intersects(sprite)) {
				System.out.println(sprite);
				player.contactSprite(sprite);
				sprite.contactSprite(player);
				break;
			}
		}
		player.update(input, delta);
		player.resetMoveValidity();

		checkWin();
		spawnExtraLife(delta);
	}


	/**
	 * Create a new ExtraLife at a random log
	 * @param delta time passed in milli seconds
	 * @throws SlickException
	 */
	private void spawnExtraLife(int delta) throws SlickException{
		// Ensure the time has past the random time
		if(currentTime >= randomTimer) {
			// restart the timer
			startTimer();
			//Get a random log a spawn it
			Log log = logs.get(rand.nextInt(logs.size()));
			ExtraLife extraLife = new ExtraLife(sprites, log);
			sprites.add(extraLife);
		}
		currentTime += delta;

	}

	/**
	 * Renderer
	 * @param g the graphics handle
	 */
	public void render(Graphics g) {

		// Iterate over the sprites and draw them to the
		// frame.
		for(int i=0; i < sprites.size(); i++) {
			sprites.get(i).render(g);
		}
		player.render(g);
	}

	private void checkWin() throws SlickException{
		// Check if the player is in a frog hole and get the index of the frog hole
		int frogSlotIndex = FrogSlots.checkInSlot(player);
		// A collision has happened, hence the player is in the frog hole
		if(frogSlotIndex >= 0) {
			// Get the coordinates
			float slotX = FrogSlots.getX(frogSlotIndex);
			float slotY = FrogSlots.getY(frogSlotIndex);
			//Spawn a new sprite at the coordinates
			Sprite playerInSlot = new Sprite(AssetManager.getImage("frog"), slotX, slotY, true);
			sprites.add(playerInSlot);
			//Move the player back
			player.resetPlayer();
		}
		// If the player has reached all the frog holes reset the count
		// and load the next level
		if(FrogSlots.allSlotsDone()) {
			FrogSlots.resetSlots();
			loadLevel();
		}
	}

}
