import org.newdawn.slick.*;
import utilities.BoundingBox;

/**
 * Represents a sprite in the game
 * @author Isitha Subasinghe
 * @author https://isubasinghe.me
 * @version 0.3
 */
public class Sprite {
	//Store the coordinates
	private float x, y;

	protected boolean solid = false;
	// Store the image
	private Image image;
	protected BoundingBox box;
	private boolean collidable = false;

	/**
	 * Create a new sprite at the given coordinates
	 * @param imageSrc The location of the image file
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @throws SlickException A exception given by the slick library.
	 * @deprecated  This constructor does not deal with bounding boxes, please
	 * use the other constructor.
	 */
	@Deprecated
	public Sprite(String imageSrc, float x, float y) throws SlickException {
		this.image = new Image(imageSrc);
		this.x = x;
		this.y = y;

	}


	/**
	 * Create a new sprite with a bounding box
	 * @param image The image to use for the sprite
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param addBox Should we add a bounding box to the sprite
	 */
	public Sprite(Image image, float x, float y, boolean addBox) {
		this.image = image;
		this.x = x;
		this.y = y;
		if(addBox) {
			this.box = new BoundingBox(image, x, y);
			this.collidable = true;
		}else {
			this.collidable = false;
		}
	}

	/**
	 *  Shift the sprite by the given deltas
	 * @param deltaX The change to apply for the x value
	 * @param deltaY The change to apply for the y value
	 * @param clip Do we stop the sprite from going past the screen?
	 */
	public void shiftPosition(float deltaX, float deltaY, boolean clip) {
		if(clip) {
			/*
			 * If we are stopping the sprite from moving offscreen
			 * we need to set the changes to 0 in the respective coordinate system
			 */
			if(this.x + deltaX  + image.getWidth()/2 > App.SCREEN_WIDTH) {
				deltaX = 0;
			}
			if(this.x + deltaX - image.getWidth()/2< 0) {
				deltaX = 0;
			}
			if(this.y + deltaY > App.SCREEN_HEIGHT) {
				deltaY = 0;
			}
			if(this.y + deltaY < 0) {
				deltaY = 0;
			}
		}

		//Update the new position
		this.x += deltaX;
		this.y += deltaY;

		if(collidable) {
			this.box.setX(this.x);
			this.box.setY(this.y);
		}
	}

	/**
	 * Update the sprites position
	 * @param x The x coordinate
	 * @param y The y coordinate
	 */
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		if(collidable) {
			this.box.setX(this.x);
			this.box.setY(this.y);
		}
	}

	/**
	 *
	 * @return The x position
	 */
	public float getX() {
		return x;
	}

	/**
	 *
	 * @return The y position
	 */
	public float getY() {
		return  y;
	}

	public void update(Input input, int delta) {

	}
	/*
	public void setImage(Image image) {
		this.image = image;
	}
	*/

	/**
	 *
	 * @return The image that this sprite uses
	 */
	public Image getImage() {
		return image;
	}

	/**
	 *
	 * @param g The graphics handle
	 */
	public void render(Graphics g) {
		image.drawCentered(x, y);
	}


	/**
	 * Handle collisions
	 * @param other the other sprite that collided
	 */
	public void contactSprite(Sprite other) {
		// Should be called when one sprite makes contact with another.
	}

	/**
	 * Indicates if we can move up to this sprite or not
	 * @return True if the sprite is solid, else false
	 */
	public boolean isSolid() {
		return solid;
	}

	/**
	 * Sets the solidity of the sprite
	 * @param solid Is the sprite solid or not
	 */
	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	private BoundingBox getBox() {
		return this.box;
	}

	/**
	 * Checks if a sprite, intersects with another
	 * @param other The other sprite
	 * @return A truth value of intersection between the two sprites
	 */
	public boolean intersects(Sprite other) {
		return this.box.intersects(other.getBox());
	}

	/**
	 *
	 * @return is the sprite attached to a bounding box
	 */
	public boolean isCollidable () {
		return this.collidable;
	}
}
