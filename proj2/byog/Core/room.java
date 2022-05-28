package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import com.sun.imageio.spi.RAFImageInputStreamSpi;

import java.util.Random;

public class room extends helper{
    public position roompos;
    public int height;
    public int width;

    room(position pos, int h, int w) {
        roompos = pos;
        height = h;
        width = w;
    }

    public void drawRoom(TETile[][] world) {
        for (int i = roompos.xx; i < width + roompos.xx; i++) {
            for (int j = roompos.yy; j < height + roompos.yy; j++) {
                world[i][j] = Tileset.FLOOR;
                if (i == roompos.xx || i == width + roompos.xx - 1) {
                    world[i][j] = Tileset.WALL;
                }
                if (j == roompos.yy || j == roompos.yy + height - 1) {
                    world[i][j] = Tileset.WALL;
                }
            }
        }


    }

    public void digHole(TETile[][] world, Random random){
        int ra = random.nextInt(height)+roompos.yy+1;
        int ra1 = random.nextInt(height)+roompos.yy+1;
        int ra2 = random.nextInt(width)+roompos.xx+1;
        world[roompos.xx][ra] = Tileset.FLOOR;
        world[roompos.xx+width-1][ra1] = Tileset.FLOOR;
        world[ra2][roompos.yy] = Tileset.FLOOR;
        if(!isOnedge(roompos,world)){
            world[roompos.xx-1][ra] = Tileset.FLOOR;
            world[roompos.xx+1][ra] = Tileset.FLOOR;
            world[ra2][roompos.yy-1] = Tileset.FLOOR;
            world[ra2][roompos.yy+1] = Tileset.FLOOR;

        }
        if(!isOnedge(new position(roompos.xx+width-1, roompos.yy),world)){
            world[roompos.xx+width][ra1] = Tileset.FLOOR;
            world[roompos.xx+width-2][ra1] = Tileset.FLOOR;
        }


    }
    private boolean isAround(position pos,int dis){
        position pos0 = new position(roompos.xx + width - 1, roompos.yy + height - 1);
        position pos1 = new position(roompos.xx + width - 1,roompos.yy);
        position pos2 = new position(roompos.xx,roompos.yy + height - 1 );
        if(helper.positionIsaround(pos0,pos,dis)&&helper.positionIsaround(pos1,pos,dis)&&
        helper.positionIsaround(pos2,pos,dis)&&helper.positionIsaround(roompos,pos,dis)){
            return true;
        }
        return false;
    }

   public boolean isInn(position pos) {
        return pos.xx >= roompos.xx && pos.yy >= roompos.yy && pos.xx <= roompos.xx + width - 1 && pos.yy <= roompos.yy + height - 1;
    }

    public boolean iscover(room r1) {
        position pos = new position(r1.roompos.xx + r1.width - 1, r1.roompos.yy + r1.height - 1);
        position pos1 = new position(r1.roompos.xx + r1.width - 1,r1.roompos.yy);
        position pos2 = new position(r1.roompos.xx,r1.roompos.yy + r1.height - 1 );
        if (this.isInn(pos) ||this.isInn(r1.roompos)||this.isInn(pos1)||this.isInn(pos2)) {
         return true;
        }
        return !isAround(r1.roompos,1);


    }
    public boolean isInworld(TETile[][] world){
        int w = world.length;
        int h = world[0].length;
        if(roompos.xx>=0&&roompos.yy>=0&&roompos.xx+width<=w&&roompos.yy+height<=h){
            return true;
        }
        return false;
    }
}
