import java.util.ArrayList;
import java.util.Collections;

/**
 * 路径
 */
public class Path {

    private Graph G;
    private int s;
    private int t;
    private boolean[] visited;

    private int[] pre; //  记录深度遍历时，每一个顶点的前一个顶点

    public Path(Graph G, int source, int target) {
        this.G = G;
        s = source;
        t = target;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for (int i = 0; i < pre.length; i ++) pre[i] = -1;

        dfs(s, s);
    }


    private boolean dfs(int v, int pre) {

        visited[v] = true;
        this.pre[v] = pre;

        if (v == t) return true;

        for (int w : this.G.adj(v))
            if (!visited[w])
                if (dfs(w, v)) return true;

        return false;
    }


    public boolean isConnected () {
        return visited[t];
    }



    public ArrayList<Integer> path() {

        ArrayList<Integer> res = new ArrayList<>();

        if (!isConnected()) return res;

        int cur = t;

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
        Path path = new Path(graph, 0, 3);
        System.out.println(path.path());
    }

}
