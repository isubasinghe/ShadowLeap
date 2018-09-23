import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import utilities.BoundingBox;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Represents the games world.
 * @author Isitha Subasinghe
 * @author https://isubasinghe.me
 * @version 0.4
 */
public class World {

	// store all the sprites that we need to render
	private ArrayList<Sprite> sprites;


	/**
	 *  Create the world used in the game.
	 */
	public World() {
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

		for(int i=0; i < sprites.size(); i++) {
			Sprite s1 = sprites.get(i);

			for(int j=0; j < sprites.size(); j++) {
				// This will always trigger true, and just doesnt make
				// any sense to check for intersections, since they are
				// the same sprite
				if(j==i) {
					continue;
				}

				Sprite s2 = sprites.get(j);
				if(s1.isCollidable() && s2.isCollidable()) {
					if(s1.intersects(s2)) {
						s1.contactSprite(s2);
					}
				}
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

	}

}
