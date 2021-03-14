package Harmilton;

import Bridge.Graph;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Collections;

public class HamiltonLoop {

    private Graph G;
    private boolean[] visited;

    private int[] pre;
    private int end;

    public HamiltonLoop(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        dfs(0, 0);
    }


    private boolean dfs(int v, int parent){

        visited[v] = true;
        pre[v] = parent;
        for(int w: G.adj(v)) {
            if(!visited[w]) {
                if (dfs(w, v)) return true;
            } else if (w == 0 && allVisited()) {
                end = v;
                return true;
            }
        }


        visited[v] = false;     //  进行该节点的回溯
        return false;
    }

    private boolean allVisited() {
        for (boolean v : visited)
            if (!v) return false;

        return true;
    }


    public ArrayList<Integer> loopPath () {
        ArrayList<Integer> res = new ArrayList<>();
        if (end == -1) return res;

        int cur = end;
        while (cur != 0) {
            res.add(cur);
            cur = pre[cur];
        }

        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){

        Graph g = new Graph("g.txt");
        HamiltonLoop hamiltonLoop = new HamiltonLoop(g);
        System.out.println("hamiltonLoop loopPath : " + hamiltonLoop.loopPath());
    }
}

