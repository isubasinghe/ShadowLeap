import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.AssetManager;
import utilities.ClusteredArray;

import javax.swing.*;
import java.util.ArrayList;

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

	private ClusteredArray<Sprite> spritesCluster;

	/**
	 *  Create the world used in the game.
	 */
	public World() throws SlickException{
		player = new Player(AssetManager.getImage("frog"), LevelBuilder.L1_PLAYER_X, LevelBuilder.L1_PLAYER_Y, true);
		//sprites = new PriorityQueue<Sprite>(int,Collections.reverseOrder());
		sprites = new ArrayList<Sprite>();

		try {
			// Obtain the sprites for level 1, the parameter
			sprites = LevelBuilder.buildLevelOne();

		}catch(SlickException e) {

			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(),
					"SlickException", JOptionPane.ERROR_MESSAGE);

			System.exit(1);
		}


	}

	/**
	 * Update the sprites states
	 * @param input Input handle
	 * @param delta The time passed in micro seconds.
	 */
	public void update(Input input, int delta) {

		// Iterate over the sprites and update them
		for(int i=0; i < sprites.size(); i++) {
			sprites.get(i).update(input, delta);
		}
		player.update(input, delta);

		for(int i=0; i < sprites.size(); i++) {
			Sprite sprite = sprites.get(i);
			if(sprite.isCollidable() && player.intersects(sprite)) {
				player.contactSprite(sprite);
				break;
			}
		}
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

}
