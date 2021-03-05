package BFS;

import Graph.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {

    private Graph G;
    private boolean[] visited;

    private ArrayList<Integer> order = new ArrayList<>();

    public GraphBFS(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];

        for (int v = 0; v < G.V(); v ++)
            if (!visited[v])
                bfs(v);
    }


    private void bfs (int v) {
        visited[v] = true;

        Queue<Integer> queue = new LinkedList<>();

        queue.add(v);

        while (!queue.isEmpty()) {
            int cur = queue.remove();
            order.add(cur);

            for (int w: G.adj(cur))
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
        }
    }


    public Iterable<Integer> order () {
        return order;
    }


    public static void main(String[] args) {
            Graph graph = new Graph("g.txt");
            GraphBFS graphBFS = new GraphBFS(graph);
            System.out.println(graphBFS.order());
    }

}
