package tile;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.HashMap;

/**
 * Some tiles are not collidable and
 * are recurring over a simple geometry such
 * as a rectangle. We can improve performance by
 * batch processing these tiles.
 * @author Isitha Subasinghe
 * @author https://isubasinghe.me
 * @version 0.3
 */
public class TileImageBuilder {
    // We increase speed by using a hashmap to ensure
    // we haven't loaded these assets before.
    private HashMap<String, Image> imageData;
    //The image to render to
    private Image renderImage;
    // The render images underlying graphics context
    private Graphics renderGLContext;

    private int width;
    private int height;

    /**
     * A class to batch process tile sets
     * @param width The width of the image
     * @param height The height of the image
     * @throws SlickException a exception is thrown if the image cant be read.
     */
    public TileImageBuilder(int width, int height) throws  SlickException{
        imageData = new HashMap<>();
        renderImage = new Image(width, height);
        renderGLContext = renderImage.getGraphics();
        this.width = width;
        this.height = height;
    }


    /**
     * Fill the entire image with one tile
     * @param imgSrc The Image location
     * @return A pointer to itself
     * @throws SlickException an exception is thrown if the image cant be read.
     */
    public TileImageBuilder fillFullRect(String imgSrc) throws SlickException {
        // Obtain the image
        Image img = imageData.get(imgSrc);
        // Check if the image exists in the hashmap
        if(img == null) {
            // Load the image
            img = new Image(imgSrc);
            //Insert to the hashmap
            imageData.put(imgSrc, img);
        }

        // Get the dimensions of the image
        int imageWidth = img.getWidth();
        int imageHeight = img.getHeight();

        //Iterate over the entire rectangle or square
        // and populate it with the texture of the given
        // image.
        for(int i=0; i < width; i+= imageWidth) {
            for(int j=0; j < height; j+= imageHeight) {
                renderGLContext.drawImage(img, i, j);

            }
        }
        // Once again, stacking instructions is really nice,
        // that is why we return a pointer to itself.
        return this;
    }

    /**
     * Flushes the changes and returns the
     * rendered image
     * @return The rendered image
     */
    public Image buildImage() {
        renderGLContext.flush();
        return renderImage;
    }
}
