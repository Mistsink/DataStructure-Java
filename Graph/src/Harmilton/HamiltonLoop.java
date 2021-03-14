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
        dfs(0, 0, G.V());
    }


    private boolean dfs(int v, int parent, int leftCount){

        visited[v] = true;
        pre[v] = parent;
        leftCount --;

        if (leftCount == 0 && G.hasEdge(v, 0)) {
            end = v;
            return true;
        }

        for(int w: G.adj(v))
            if(!visited[w]) {
                if (dfs(w, v, leftCount)) return true;
            }


        visited[v] = false;     //  进行该节点的回溯
        //  leftCount ++;    这里不需要将leftCount回溯状态
        //  因为函数参数的该leftCount是int型，为值传递而非引用传递
        return false;
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

