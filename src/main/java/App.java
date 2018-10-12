/**
 * Sample Project for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 */


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame {

    /** a true here will draw the bounding boxes */
    public static final boolean DEBUG_MODE = false;

    /** screen width, in pixels */
    public static final int SCREEN_WIDTH = 1024;

    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 768;

    /** The pixel size of the tile*/
    public static final int TILE_SIZE = 48;

    /** Game FPS*/
    private static final int FPS = 60;

    private int SCALING_TIME = 1;
    private int counter = 0;

    private World world;

    public App() {
        super("Shadow Leap");
    }

    @Override
    public void init(GameContainer gc)
            throws SlickException {
        world = new World();

    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {

        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            System.out.println(input.getMouseX());
        }
        if(counter >= SCALING_TIME) {
            world.update(input, delta);
            counter = 0;
        }
        counter += delta;
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
            throws SlickException {
        world.render(g);
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
            throws Exception {
        AppGameContainer app = new AppGameContainer(new App());
        app.setTargetFrameRate(FPS);
        app.setShowFPS(false);
        app.setVSync(true);


        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);

        //LevelBuilder.buildWorldByCSV("./assets/levels/0.lvl");
        app.start();
    }

}