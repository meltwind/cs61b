package byog.Core;

import byog.TileEngine.*;

import java.util.Random;

public class launch extends helper {
    public int WIDTH = 90;
    public int HEIGHT = 50;


    public void generateworld(TETile[][] T) {
        TERenderer ter = new TERenderer();
        ter.initialize(91, 51);
        ter.renderFrame(T);

    }

    public static void main(String[] args) {
        Random random = new Random(10086);
        TETile[][] world = new TETile[91][51];
        for (int x = 0; x < 91; x += 1) {
            for (int y = 0; y < 51; y += 1) {
                world[x][y] = Tileset.NOTHING;
                if (x % 2 == 0) {
                    world[x][y] = Tileset.WALL;
                }
                if (y % 2 == 0) {
                    world[x][y] = Tileset.WALL;
                }
            }
        }

        generateRoom ge = new generateRoom();
        ge.roomGenerate(random, world);
        hallwaygenerate ha = new hallwaygenerate();
        ha.generatemaze(world, random);
        launch la = new launch();
        la.generateworld(world);
    }


}

