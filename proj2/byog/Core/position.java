package byog.Core;

import byog.TileEngine.TETile;

public class position {
    public int xx;
    public int yy;
    position(int x,int y){
        xx = x;
        yy = y;
    }
    public position middleposition(position des){
        int middlex = des.xx - xx;
        int middley = des.yy - yy;
        return  new position((int)(xx+0.5*middlex),(int)(yy+0.5*middley));
    }


}
