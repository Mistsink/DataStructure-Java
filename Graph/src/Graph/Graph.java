package Graph;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class Graph {
    private int V, E;
    private TreeSet<Integer>[] adj;

    public Graph (String fileName) {
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {

            V = scanner.nextInt();
            adj = new TreeSet[V];
            for (int i = 0; i < V; i++) adj[i] = new TreeSet<>();
            E = scanner.nextInt();

            for (int i = 0; i < E; i ++) {
                int v = scanner.nextInt(), w = scanner.nextInt();

                adj[v].add(w);
                adj[w].add(v);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public int V () {
        return V;
    }


    public int E () {
        return E;
    }


    public boolean hasEdge (int v, int w) {
        return adj[v].contains(w);
    }


    /**
     * 返回给定顶点的邻边
     * @param v     给定顶点
     * @return
     */
    public Iterable<Integer> adj (int v) {
        return adj[v];
    }


    /**
     * 返回给定顶点的度
     * @param v     给定顶点
     * @return
     */
    public int degree (int v) {
        return adj[v].size();
    }



    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(String.format("V = %d, E = %d\n", V, E));

        for (int v = 0; v < V; v++) {
            ret.append(String.format("%d: ", v));
            for (int w : adj[v])
                ret.append(String.format("%d ", w));
            ret.append('\n');
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        System.out.println(graph);
    }
}
