package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class TerrainBrush {
    //width 95 and height 47 should be the max size for this screen
    private static final int DEMO_WIDTH = 95;
    private static final int DEMO_HEIGHT = 47;


    private static void addHexagonHelperUp(TETile[][] tiles, int hexSize, int x, int y, int rank) {
        if (rank == hexSize) {
            return;
        }
        if (y < 0 || y >= tiles[0].length) {
            addHexagonHelperUp(tiles, hexSize, x, y - 1, rank + 1);
        }
        int px = x;
        int dpx1 = hexSize - 1 - rank;
        int dpx2 = hexSize + 2 * rank;
        for (int i = 0; i < dpx2; i++) {
            int xCurr = px + dpx1 + i;
            if(xCurr < tiles.length && xCurr >= 0) {
                tiles[xCurr][y] = Tileset.GRASS;
            }
        }
        addHexagonHelperUp(tiles, hexSize, x, y - 1, rank + 1);
    }
    private static void addHexagonHelperDown(TETile[][] tiles, int hexSize, int x, int y, int rank) {
        if (rank == hexSize) {
            return;
        }
        if (y < 0 || y >= tiles[0].length) {
            addHexagonHelperDown(tiles, hexSize, x, y + 1, rank + 1);
        }

        int px = x;
        int dpx1 = hexSize -1 - rank;
        int dpx2 = hexSize + 2 * rank;
        for (int i = 0; i < dpx2; i++) {
            int xCurr = px + dpx1 + i;
            if(xCurr < tiles.length && xCurr >= 0) {
                tiles[xCurr][y] = Tileset.GRASS;
            }
        }
        addHexagonHelperDown(tiles, hexSize, x, y + 1, rank + 1);
    }
    /**
     * To spawn a hexagon at the given position.The hexagon spawned later should cover the former tile.
     * If the position is out of range, the spawn stops.
     * @param xDR the x position int the 2D-array at the down-right center where our spawn begin.
     * @param yDR the y position int the 2D-array at the down-right center where our spawn begin.
     * */
    public static void addHexagon(TETile[][] tiles, int hexSize, int xDR, int yDR){
        int rank = 0;
        addHexagonHelperUp(tiles, hexSize, xDR, yDR + 2 * hexSize - 1, rank);
        addHexagonHelperDown(tiles, hexSize, xDR, yDR, rank);
    }


    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(DEMO_WIDTH,DEMO_HEIGHT);


        TETile[][] tiles = new TETile[DEMO_WIDTH][DEMO_HEIGHT];
        for (int x = 0; x < DEMO_WIDTH; x += 1) {
            for (int y = 0; y < DEMO_HEIGHT; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
        tiles[0][0] = Tileset.DEBUG_BOUNDARY;
        tiles[0][DEMO_HEIGHT - 1] = Tileset.DEBUG_BOUNDARY;
        tiles[DEMO_WIDTH - 1][0] = Tileset.DEBUG_BOUNDARY;
        tiles[DEMO_WIDTH - 1][DEMO_HEIGHT - 1] = Tileset.DEBUG_BOUNDARY;



        ter.renderFrame(tiles);
    }
}
