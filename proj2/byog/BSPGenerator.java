package byog;

import byog.TileEngine.TETile;

public class BSPGenerator implements WorldGenerator {
    @Override
    public void generate(TETile[][] world) {

    }

    private class BSPRect {
        public int xDR;
        public int yDR;
        public int width;
        public int height;

        public void BSPGenerator(int x, int y, int w, int h) {
            xDR = x;
            yDR = y;
            width = w;
            height = h;
        }
    }

    /*Task steps
    * 0.矩形的表示
    * 1.矩形切分
    * 2.矩形的表示
    * 3.矩形的二叉树存储
    * 4.矩形与父节点的联通
    * 5.设计管道
    * 6.管道生成
    * */

}
