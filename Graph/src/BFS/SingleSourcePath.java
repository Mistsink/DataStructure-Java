package BFS;

import Graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 无权图单源最短路径
 */
public class SingleSourcePath {

    private Graph G;
    private int s;
    private boolean[] visited;

    private int[] pre; //  记录深度遍历时，每一个顶点的前一个顶点
    private int[] dis;

    public SingleSourcePath(Graph G, int source) {
        this.G = G;
        s = source;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        dis = new int[G.V()];
        for (int i = 0; i < pre.length; i ++) dis[i] = pre[i] = -1;

        bfs(s, s);
    }


    private void bfs (int v, int pre) {
        visited[v] = true;
        this.pre[v] = pre;
        dis[v] = 0;

        Queue<Integer> queue = new LinkedList<>();

        queue.add(v);

        while (!queue.isEmpty()) {
            int cur = queue.remove();

            for (int w: G.adj(cur))
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    this.pre[w] = cur;
                    dis[w] = dis[cur] + 1;
                }
        }
    }


    public boolean isConnected (int w) {
        return visited[w];
    }


    public int dis (int v) {
        return dis[v];
    }



    public ArrayList<Integer> path(int v) {

        ArrayList<Integer> res = new ArrayList<>();

        if (!isConnected(v)) return res;

        int cur = v;

        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(cur);

        Collections.reverse(res);
        return res;
    }


    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        SingleSourcePath ssPath = new SingleSourcePath(graph, 0);
        System.out.println(ssPath.path(6));
        System.out.println(ssPath.dis(6));
    }

}
