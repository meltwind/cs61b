package lab11.graphs;

import edu.princeton.cs.algs4.In;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;


    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        marked[v] = true;
        announce();
        Queue<Integer> fringe = new ArrayDeque<>();
        fringe.add(v);
        while (!fringe.isEmpty()){
            int source = fringe.poll();
            for(int w: maze.adj(source)){
                if(!marked[w]){
                    distTo[w] = distTo[source] + 1;
                    edgeTo[w] = source;
                    marked[w] = true;
                    fringe.add(w);
                    announce();
                    if (w == t) {
                        targetFound = true;
                    }

                    if (targetFound) {
                        return;
                    }
                }
            }
        }
    }


    @Override
    public void solve() {
         bfs(s);
    }
}

