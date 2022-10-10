package byog.Core;

import byog.TileEngine.TETile;

public class helper {
    public static boolean isOnedge(position des, TETile[][] world) {
        return des.xx == 0 || des.xx == world.length - 1 || des.yy == 0 || des.yy == world[0].length - 1;
    }

    public static position[] aroundPosition(position p) {
        position[] pArray = new position[4];
        pArray[0] = new position(p.xx - 1, p.yy);
        pArray[1] = new position(p.xx, p.yy - 1);
        pArray[2] = new position(p.xx + 1, p.yy);
        pArray[3] = new position(p.xx, p.yy + 1);
        return pArray;
    }

    public static position[] aroundPosition(position p, int distance) {
        position[] pArray = new position[4];
        pArray[0] = new position(p.xx - distance, p.yy);
        pArray[1] = new position(p.xx, p.yy - distance);
        pArray[2] = new position(p.xx + distance, p.yy);
        pArray[3] = new position(p.xx, p.yy + distance);
        return pArray;
    }

    public static boolean positionIsaround(position p1, position p2, int distance) {
        return Math.abs(p1.xx - p2.xx) > distance && Math.abs(p1.yy - p2.yy) > distance;
    }

}
