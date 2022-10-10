package byog.Core;

import byog.TileEngine.TETile;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class generateRoom {
    Deque<room> roomList = new LinkedList();

    public void roomGenerate(Random random, TETile[][] world) {
        int ranum = random.nextInt(1000);
        for (int i = 0; i < ranum; i += 1) {
            int raposx = random.nextInt(world.length);
            int raposy = random.nextInt(world[0].length);
            int w = random.nextInt(20) + 3;
            int h = random.nextInt(10) + 3;
            room ar = new room(new position(raposx, raposy), h, w);
            if (ar.isInworld(world) && !isOverload((LinkedList<room>) roomList, ar)) {
                ar.drawRoom(world);
                ar.digHole(world, random);
                roomList.add(ar);

            }

        }
    }

    private boolean isOverload(LinkedList<room> ls, room roo) {
        for (room it : ls) {
            if (roo.iscover(it) || it.iscover(roo)) {
                return true;
            }
        }
        return false;
    }

}
