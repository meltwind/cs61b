package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class hallwaygenerate extends helper {
    private Deque<position> positionslist = new LinkedList();

    private position randomposition(TETile[][] T, Random random) {
        return new position(1, 1);
      /*  while (true) {
            int x = Math.abs(random.nextInt() % T.length);
            int y = Math.abs(random.nextInt() % T[0].length);
            if (!T[x][y].equals(Tileset.WALL)) {
                return new position(x, y);
            }
        }*/
    }

    private List<position> checkpath(TETile[][] T, position curr) {
        List<position> ls = new LinkedList<>();
        position[] around = aroundPosition(curr);
        position[] connect = aroundPosition(curr, 2);
        for (int i = 0; i < 4; i++) {
            if (!isOnedge(around[i], T)
                    && T[connect[i].xx][connect[i].yy].equals(Tileset.NOTHING)
                    && !T[around[i].xx][around[i].yy].equals(Tileset.FLOOR)) {
                ls.add(connect[i]);

            }

        }
        return ls;


    }

    private void connectpath(TETile[][] T, List<position> ls, position curr, Random random) {
        int choice = random.nextInt(ls.size());
        position des = ls.get(choice);
        position middlepos = curr.middleposition(des);
        T[middlepos.xx][middlepos.yy] = Tileset.FLOOR;
        T[des.xx][des.yy] = Tileset.FLOOR;
        positionslist.addLast(des);
    }

    public TETile[][] generatemaze(TETile[][] T, Random random) {
        position start = randomposition(T, random);
        T[start.xx][start.yy] = Tileset.FLOOR;
        positionslist.addLast(start);
        while (!positionslist.isEmpty()) {
            position currentpos = positionslist.getLast();
            List<position> currentpath = checkpath(T, currentpos);
            if (!currentpath.isEmpty()) {
                connectpath(T, currentpath, currentpos, random);
            } else {
                positionslist.removeLast();
            }


        }
        return T;


    }
}
