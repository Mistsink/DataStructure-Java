package DFS;
import Graph.Graph;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 单源路径
 */
public class SingleSourcePath {

    private Graph G;
    private int s;
    private boolean[] visited;

    private int[] pre; //  记录深度遍历时，每一个顶点的前一个顶点

    public SingleSourcePath(Graph G, int source) {
        this.G = G;
        s = source;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for (int i = 0; i < pre.length; i ++) pre[i] = -1;

        dfs(s, s);
    }


    private void dfs(int v, int pre) {

        visited[v] = true;
        this.pre[v] = pre;

        for (int w : this.G.adj(v))
            if (!visited[w]) dfs(w, v);

    }


    public boolean isConnected (int w) {
        return visited[w];
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
        System.out.println(ssPath.path(3));
    }

}
