package Graph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdjMatrix {
    private int V, E;
    private int[][] adj;

    public AdjMatrix (String fileName) {
        File file = new File(fileName);

        try (Scanner scanner = new Scanner(file)) {

            V = scanner.nextInt();
            adj = new int[V][V];
            E = scanner.nextInt();

            for (int i = 0; i < E; i ++) {
                int a = scanner.nextInt(), b = scanner.nextInt();

                adj[a][b] = 1;
                adj[b][a] = 1;
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


    public boolean hasEdge (int a, int b) {
        return adj[a][b] == 1;
    }


    /**
     * 返回给定顶点的邻边
     * @param v     给定顶点
     * @return
     */
    public ArrayList<Integer> adj (int v) {
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < V; i ++)
            if (adj[v][i] == 1) res.add(i);

        return res;
    }


    /**
     * 返回给定顶点的度
     * @param v     给定顶点
     * @return
     */
    public int degree (int v) {
        return adj(v).size();
    }



    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(String.format("V = %d, E = %d\n", V, E));

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j ++)
                ret.append(String.format("%d ", adj[i][j]));
            ret.append('\n');
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("g.txt");
        System.out.println(adjMatrix);
    }
}
