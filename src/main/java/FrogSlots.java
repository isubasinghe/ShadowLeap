import utilities.BoundingBox;

public class FrogSlots {
    private static final int NUMBER_SLOTS = 5;
    private static BoundingBox[] boxes = createBoxes();

    private static final int HOLE_WIDTH = App.TILE_SIZE*3;
    private static final int HOLE_HEIGHT = App.TILE_SIZE;
    private static final int HOLE_Y = App.TILE_SIZE;
    private static int[] HOLES_X;

    private static int slotsVisited = 0;


    private static BoundingBox[] createBoxes() {
        if(HOLES_X == null) {
             HOLES_X = new int[]{120,312,504,696,888};
        }
        BoundingBox[] boxes = new BoundingBox[NUMBER_SLOTS];
        for(int i=0; i < NUMBER_SLOTS; i++) {
            BoundingBox box = new BoundingBox(HOLES_X[i], HOLE_Y, HOLE_WIDTH, HOLE_HEIGHT);
            boxes[i] = box;
        }
        return boxes;
    }

    public static int checkInSlot(Sprite sprite) {
        for(int i=0; i < NUMBER_SLOTS; i++) {
            if(boxes[i].intersects(sprite.box)) {
                System.out.println("Collision");
                slotsVisited++;
                return i;
            }
        }
        return -1;
    }

    public static void resetSlots() {
        slotsVisited = 0;
    }

    public static boolean allSlotsDone(Sprite sprite) {
        if(slotsVisited == NUMBER_SLOTS) {
            return true;
        }
        return false;
    }

    public static int getX(int index) {
        return HOLES_X[index];
    }

    public static int getY(int index) {
        return HOLE_Y;
    }

}
